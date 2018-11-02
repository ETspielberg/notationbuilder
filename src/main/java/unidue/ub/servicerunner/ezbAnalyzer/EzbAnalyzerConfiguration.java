package unidue.ub.servicerunner.ezbAnalyzer;

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
import unidue.ub.servicerunner.model.journals.Journal;

@Configuration
@EnableBatchProcessing
public class EzbAnalyzerConfiguration {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    public EzbAnalyzerConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    @StepScope
    public JournalFileReader journalFileReader() {
        return new JournalFileReader();
    }

    @Bean
    @StepScope
    public JournalProcessor journalFileProcessor() {
        return new JournalProcessor();
    }

    @Bean
    @StepScope
    public JournalWriter journalWriter() {
        return new JournalWriter();
    }

    @Bean
    public Step journalStep() {
        return stepBuilderFactory.get("journalStep")
                .<String, Journal>chunk(100)
                .reader(journalFileReader())
                .processor(journalFileProcessor())
                .writer(journalWriter())
                .build();
    }

    @Bean
    public Job journalJob(JobExecutionListener listener) {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("journalFlow");
        Flow flow = flowBuilder
                .start(journalStep())
                .end();
        return jobBuilderFactory.get("journalJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(flow)
                .end()
                .build();
    }
}
