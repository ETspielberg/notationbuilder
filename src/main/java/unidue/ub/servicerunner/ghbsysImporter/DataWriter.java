package unidue.ub.servicerunner.ghbsysImporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import unidue.ub.servicerunner.model.SubjectArea;
import unidue.ub.servicerunner.model.Systematic;
import unidue.ub.servicerunner.repository.SystematicRepository;

import java.util.List;

@Component
@StepScope
public class DataWriter implements ItemWriter<SubjectArea> {

    private Logger log = LoggerFactory.getLogger(DataWriter.class);

    @Autowired
    private SystematicRepository systematicRepository;

    @Value("#{jobParameters['systematic.type'] ?: 'ghbsys'}")
    public String systematicType;

    @Override
    public void write(List list) {
        Systematic systematic = new Systematic();
        systematic.setType(systematicType);
        systematic.setSubjectAreas(list);
        systematicRepository.save(systematic);
    }
}
