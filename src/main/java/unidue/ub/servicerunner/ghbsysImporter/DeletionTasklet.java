package unidue.ub.servicerunner.ghbsysImporter;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unidue.ub.servicerunner.repository.SystematicRepository;

@Component
@StepScope
public class DeletionTasklet implements Tasklet {

    @Autowired
    private SystematicRepository systematicRepository;

    public RepeatStatus execute(StepContribution contribution,
                                ChunkContext chunkContext) {
        systematicRepository.deleteAll();
        return RepeatStatus.FINISHED;
    }
}
