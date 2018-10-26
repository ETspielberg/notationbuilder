package unidue.ub.servicerunner.ghbsysImporter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import unidue.ub.servicerunner.model.SubjectArea;


@Configuration
@EnableBatchProcessing
public class GhbsysImporterConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    @StepScope
    public DeletionTasklet deletionTasklet() {return new DeletionTasklet(); }

    @Bean
    @StepScope
    public FileTransformer fileTransformer() {
        return new FileTransformer();
    }

    @Bean
    @StepScope
    public FileReader fileReader() {
        return new FileReader();
    }

    @Bean
    @StepScope
    public FileProcessor fileProcessor() {
        return new FileProcessor();
    }

    @Bean
    @StepScope
    public DataWriter writer() {
        return new DataWriter();
    }

    @Bean
    public Job ghbsysImporterJob(JobExecutionListener listener) {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("ghbsysImporterFlow");
        Flow flow = flowBuilder
                .start(deleteOldSystematic())
                .next(transformData())
                .next(step())
                .end();
        return jobBuilderFactory.get("ghbsysImporterJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(flow)
                .end()
                .build();
    }

    @Bean
    public Step deleteOldSystematic() {
        return stepBuilderFactory.get("deletion")
                .tasklet(deletionTasklet())
                .build();
    }

    @Bean
    public Step transformData() {
        return stepBuilderFactory.get("transform")
                .tasklet(fileTransformer())
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .<String, SubjectArea>chunk(100)
                .reader(fileReader())
                .processor(fileProcessor())
                .writer(writer())
                .build();
    }
}
