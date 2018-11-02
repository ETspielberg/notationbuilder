package unidue.ub.servicerunner.ezbAnalyzer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

@Component
@StepScope
public class JournalFileReader implements ItemReader<String> {


    private Scanner lines;

    private boolean isRead;

    @Value("${ub.statistics.data.dir}")
    private String dataDir;

    @Value("#{jobParameters['ezb.filename'] ?: 'EZBListe2017.txt'}")
    public String filename;


    @Override
    public String read() {
        if (!isRead) {
            lines = readFile();
        }
        if (lines != null) {
            if (lines.hasNext()) {
                return lines.nextLine();
            }
        }
        return null;
    }

    private Scanner readFile() {
        File file = new File(dataDir + "/ezbUpload", filename);
        try {
            InputStream input = new FileInputStream(file);
            Scanner scanner = new Scanner(new InputStreamReader(input));
            isRead = true;
            return scanner;
        } catch (Exception e) {
            return null;
        }
    }
}
