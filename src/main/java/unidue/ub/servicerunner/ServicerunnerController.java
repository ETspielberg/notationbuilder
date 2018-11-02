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

    private final JobLauncher jobLauncher;

    private final Job ghbsysImporterJob;

    private final Job journalJob;

    @Autowired
    public ServicerunnerController(JobLauncher jobLauncher, Job ghbsysImporterJob, Job journalJob) {
        this.jobLauncher = jobLauncher;
        this.ghbsysImporterJob = ghbsysImporterJob;
        this.journalJob = journalJob;
    }

    @RequestMapping("/systematicBuilder/{type}")
    public ResponseEntity<?> runNotationBuilder(@PathVariable String type) {
        log.info("building notations");
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString("systematic.type", type).addLong("time",System.currentTimeMillis()).toJobParameters();
        JobParameters jobParameters = jobParametersBuilder.toJobParameters();
        return runJobWithParameters(ghbsysImporterJob, jobParameters);
    }

    @RequestMapping("/ezbAnalyzer")
    public ResponseEntity<?> runEzbUpload(String filename, String year, String institution) {
        log.info("running ezb analyzer with filename " + filename);
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString("ezb.filename", filename)
                .addString("ezb.year", year)
                .addString("ezb.institution", institution)
                .addLong("time",System.currentTimeMillis()).toJobParameters();
        JobParameters jobParameters = jobParametersBuilder.toJobParameters();
        return runJobWithParameters(journalJob, jobParameters);
    }

    private ResponseEntity<?> runJobWithParameters(Job job, JobParameters jobParameters) {
        try {
            jobLauncher.run(job, jobParameters);
            return ResponseEntity.status(HttpStatus.FOUND).build();
        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException | JobParametersInvalidException | JobRestartException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
