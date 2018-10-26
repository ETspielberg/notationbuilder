package unidue.ub.servicerunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/run")
public class ServicerunnerController {

    @Value("${ub.statistics.data.dir}")
    private String dataDir;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job systemaicBuilder;

    @RequestMapping("/systematicBuilder/{type}")
    public ResponseEntity<Object> runNotationBuilder(String type) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("building notations");
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString("systematic.type", type).addLong("time",System.currentTimeMillis()).toJobParameters();
        JobParameters jobParameters = jobParametersBuilder.toJobParameters();
        jobLauncher.run(systemaicBuilder, jobParameters);
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }

    @RequestMapping("/ezbAnalyzer")
    public void runEzbUpload(String filename) {
        log.info("running ezb analyzer with filename " + filename);
        EzbAnalyzer ezbAnalyzer = new EzbAnalyzer(dataDir);
        ezbAnalyzer.run(filename);
    }
}
