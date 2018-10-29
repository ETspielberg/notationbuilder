package unidue.ub.servicerunner.ghbsysImporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unidue.ub.servicerunner.repository.systematic.SystematicRepository;

@Component
@StepScope
public class DeletionTasklet implements Tasklet {

    private Logger log = LoggerFactory.getLogger(DeletionTasklet.class);

    @Autowired
    private SystematicRepository systematicRepository;

    public RepeatStatus execute(StepContribution contribution,
                                ChunkContext chunkContext) {
        systematicRepository.deleteAll();
        log.info("deleted old entries");
        return RepeatStatus.FINISHED;
    }
}
