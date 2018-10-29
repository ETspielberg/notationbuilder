package unidue.ub.servicerunner.ghbsysImporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import unidue.ub.servicerunner.model.systematic.SubjectArea;

import javax.xml.transform.stream.StreamSource;

@Component
@StepScope
public class FileProcessor implements ItemProcessor<String, SubjectArea> {

    private Logger log = LoggerFactory.getLogger(FileProcessor.class);

    @Value("${ub.statistics.data.dir}")
    private String dataDir;

    @Value("#{jobParameters['systematic.type'] ?: 'ghbsys'}")
    public String systematicType;

    @Autowired
    Jaxb2Marshaller jaxb2Marshaller;

    @Override
    public SubjectArea process(final String filename) {
        StreamSource input = new StreamSource(dataDir + "/systematik/" + filename);
        SubjectArea subjectArea = (SubjectArea) jaxb2Marshaller.unmarshal(input);
        log.info(String.valueOf(subjectArea.getSubCategorys().size()));
        return subjectArea;

    }
}
