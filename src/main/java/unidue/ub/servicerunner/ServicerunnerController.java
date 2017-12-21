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

    @Value("${ub.statistics.settings.url}")
    private String settingsUrl;

    @Value("${ub.statistics.resources.url}")
    private String resourcesUrl;

    @Value("${ub.statistics.data.url}")
    private String dataUrl;

    @Value("${ub.statistics.data.dir}")
    private String dataDir;

    @Value("${ub.statistics.getter.url}")
    private String getterUrl;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/notationbuilder")
    public void runNotationBuilder() throws IOException {
        log.info("building notations");
        Notationbuilder notationbuilder = new Notationbuilder(dataDir + "/systematik",settingsUrl);
        notationbuilder.run();
    }

    @RequestMapping("/ezbAnalyzer")
    public void runEzbUpload(String filename) throws IOException {
        log.info("running ezb analyzer with filename " + filename);
        EzbAnalyzer ezbAnalyzer = new EzbAnalyzer(dataDir,resourcesUrl);
        ezbAnalyzer.run(filename);
    }

    @RequestMapping("/extendyears")
    public void extendYears() throws JDOMException, IOException, TransformerException, SAXException, URISyntaxException {
        JOPYearExtender JOPYearExtender = new JOPYearExtender(dataUrl,resourcesUrl);
        JOPYearExtender.run();
    }

    @RequestMapping("/extendprices")
    public void extendPrices() throws URISyntaxException {
        PriceExtender priceExtender = new PriceExtender(dataUrl,getterUrl);
        priceExtender.run();
    }
}
