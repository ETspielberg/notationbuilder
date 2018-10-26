package unidue.ub.servicerunner.ghbsysImporter;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

@Component
@StepScope
public class FileTransformer implements Tasklet {

    @Value("${ub.statistics.data.dir}")
    private String dataDir;

    @Value("#{jobParameters['systematic.type'] ?: 'ghbsys'}")
    public String systematicType;


    public RepeatStatus execute(StepContribution contribution,
                                ChunkContext chunkContext) {
        File xmlDirectory = new File(dataDir + "/systematik");
        FilenameFilter filter = (dir, name) -> {
            String lowercaseName = name.toLowerCase();
            return lowercaseName.startsWith("systematik-");
        };
        for (String filename : Arrays.asList(xmlDirectory.list(filter))) {
            try {
                String pathToXSL = "xsl/" + systematicType + "2subjectArea.xsl";
                StreamSource stylesource = new StreamSource(getClass().getClassLoader().getResourceAsStream(pathToXSL));
                TransformerFactory factory = TransformerFactory.newInstance();
                Transformer transformer = factory.newTransformer(stylesource);
                StreamSource input = new StreamSource(dataDir + "/systematik/" + filename);
                StreamResult result = new StreamResult(new File(dataDir + "/systematik/" + "transformed_" + filename));
                transformer.transform(input, result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return RepeatStatus.FINISHED;
    }
}
