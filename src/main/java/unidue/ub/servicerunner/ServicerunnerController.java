package unidue.ub.servicerunner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/run")
public class ServicerunnerController {

    @Value("${ub.statistics.settings.url}")
    String settingsUrl;

    @Value("${ub.statistics.resources.url}")
    String resourcesUrl;

    @Value("${ub.statistics.systematik.data.dir}")
    private String dataDir;

    @RequestMapping("/notationbuilder")
    public void runNotationBuilder() throws IOException {
        Notationbuilder notationbuilder = new Notationbuilder(dataDir,settingsUrl);
        notationbuilder.run();
    }

    @RequestMapping("/ezbUpload")
    public void runEzbUpload(String filename) throws IOException {
        EzbUpload ezbUpload = new EzbUpload(dataDir,resourcesUrl);
        ezbUpload.run(filename);
    }

}
