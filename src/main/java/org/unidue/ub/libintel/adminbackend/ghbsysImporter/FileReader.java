package org.unidue.ub.libintel.adminbackend.ghbsysImporter;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FileReader implements ItemReader<String> {

    private List<String> filenames;

    private boolean isRead;

    @Value("${ub.statistics.data.dir}")
    private String dataDir;

    @Override
    public String read() {
        if (!isRead)
            filenames = listFiles();

        if (filenames == null)
            return null;

        if (!filenames.isEmpty()) {
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
            return new LinkedList<>(Arrays.asList(xmlDirectory.list(filter)));
        } catch (Exception e) {
            return null;
        }
    }
}
