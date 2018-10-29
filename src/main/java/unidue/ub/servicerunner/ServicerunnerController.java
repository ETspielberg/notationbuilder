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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class ServicerunnerController {

    @Value("${ub.statistics.data.dir}")
    private String dataDir;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job ghbsysImporterJob;

    @Autowired
    Job journalJob;

    @RequestMapping("/systematicBuilder/{type}")
    public ResponseEntity<?> runNotationBuilder(@PathVariable String type) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("building notations");
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString("systematic.type", type).addLong("time",System.currentTimeMillis()).toJobParameters();
        JobParameters jobParameters = jobParametersBuilder.toJobParameters();
        jobLauncher.run(ghbsysImporterJob, jobParameters);
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }

    @RequestMapping("/ezbAnalyzer")
    public ResponseEntity<?> runEzbUpload(String filename, String year) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("running ezb analyzer with filename " + filename);
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString("ezb.filename", filename)
                .addString("ezb.year", year)
                .addLong("time",System.currentTimeMillis()).toJobParameters();
        JobParameters jobParameters = jobParametersBuilder.toJobParameters();
        jobLauncher.run(journalJob, jobParameters);
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }
}
