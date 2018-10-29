package unidue.ub.servicerunner.ghbsysImporter;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

@Component
@StepScope
public class FileTransformer implements Tasklet {

    @Value("${ub.statistics.data.dir}")
    private String dataDir;

    @Value("#{jobParameters['systematic.type'] ?: 'ghbsys'}")
    public String systematicType;


    public RepeatStatus execute(StepContribution contribution,
                                ChunkContext chunkContext) {
        List<String> filenames = listFilenames();
        for (String filename : filenames) {
            StreamSource input;
            switch (systematicType) {
                case "rvko":
                    cutFiles(filenames);
                default:
                    input = new StreamSource(dataDir + "/systematik/" + filename);
                    transformAndWrite(input, filename);
            }


        }
        return RepeatStatus.FINISHED;
    }

    private List<String> listFilenames() {
        File xmlDirectory = new File(dataDir + "/systematik");
        FilenameFilter filter = (dir, name) -> {
            String lowercaseName = name.toLowerCase();
            return lowercaseName.startsWith("systematik-");
        };
        return Arrays.asList(xmlDirectory.list(filter));
    }

    private void cutFiles(List<String> filenames) {
       for (String filename : filenames) {
           try {
               File file = new File(dataDir + "/systematik/" + filename);
               DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
               DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
               Document doc = dBuilder.parse(file);
               NodeList nodeList = doc.getChildNodes();
               for (int i = 0; i < nodeList.getLength(); ++i) {
                   Node node = nodeList.item(i).cloneNode(true);
                   String name = node.getAttributes().getNamedItem("notation").getNodeValue();
                   Document docXml = dBuilder.newDocument();
                   docXml.adoptNode(node);
                   DOMSource input = new DOMSource(docXml);
                   transformAndWrite(input, name);
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
    }

    private void transformAndWrite(Source input, String filename) {
        try {
            String pathToXSL = "xsl/" + systematicType + "2subjectArea.xsl";
            StreamSource stylesource = new StreamSource(getClass().getClassLoader().getResourceAsStream(pathToXSL));
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(stylesource);

            StreamResult result = new StreamResult(new File(dataDir + "/systematik/" + "transformed_" + filename));
            transformer.transform(input, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
