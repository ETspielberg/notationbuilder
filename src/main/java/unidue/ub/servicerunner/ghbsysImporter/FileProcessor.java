package unidue.ub.servicerunner.ghbsysImporter;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import unidue.ub.servicerunner.model.SubjectArea;

import javax.xml.transform.stream.StreamSource;

@Component
@StepScope
public class FileProcessor implements ItemProcessor<String, SubjectArea> {

    @Value("${ub.statistics.data.dir}")
    private String dataDir;

    @Value("#{jobParameters['systematic.type'] ?: 'ghbsys'}")
    public String systematicType;

    @Autowired
    Jaxb2Marshaller jaxb2Marshaller;

    @Override
    public SubjectArea process(final String filename) {
        StreamSource input = new StreamSource(dataDir + "/systematik/" + filename);
        return (SubjectArea) jaxb2Marshaller.unmarshal(input);

    }
}
