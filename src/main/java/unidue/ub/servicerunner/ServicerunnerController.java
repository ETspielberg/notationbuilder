package unidue.ub.servicerunner;

import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/run")
public class ServicerunnerController {

    @Value("${ub.statistics.data.dir}")
    private String dataDir;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/notationbuilder")
    public void runNotationBuilder() {
        log.info("building notations");
        new Notationbuilder(dataDir + "/systematik").run();
    }

    @RequestMapping("/ezbAnalyzer")
    public void runEzbUpload(String filename) throws IOException {
        log.info("running ezb analyzer with filename " + filename);
        EzbAnalyzer ezbAnalyzer = new EzbAnalyzer(dataDir);
        ezbAnalyzer.run(filename);
    }

    @RequestMapping("/extendyears")
    public void extendYears() throws JDOMException, IOException, TransformerException, SAXException, URISyntaxException {
        new JOPYearExtender().run();
    }

    @RequestMapping("/extendprices")
    public void extendPrices() throws URISyntaxException {
        new PriceExtender().run();
    }
}
