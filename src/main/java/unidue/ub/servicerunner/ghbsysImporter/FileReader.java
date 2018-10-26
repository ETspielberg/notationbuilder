package unidue.ub.servicerunner.ghbsysImporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FileReader implements ItemReader<String> {

    private Logger log = LoggerFactory.getLogger(FileReader.class);

    private List<String> filenames;

    private boolean isRead;

    @Value("${ub.statistics.data.dir}")
    private String dataDir;

    @Override
    public String read() {
        if (!isRead) {
            filenames = listFiles();
            log.info(String.valueOf(filenames.size()));
        } if (!filenames.isEmpty()) {
            log.info(filenames.get(0));
            return filenames.remove(0);
        }
        return null;
    }

    private List<String> listFiles() {
        File xmlDirectory = new File(dataDir + "/systematik");
        FilenameFilter filter = (dir, name) -> {
            String lowercaseName = name.toLowerCase();
            return lowercaseName.startsWith("transformed_systematik-");
        };
        isRead = true;
        try {
            return new LinkedList<String>(Arrays.asList(xmlDirectory.list(filter)));
        } catch (Exception e) {
            return null;
        }
    }
}
