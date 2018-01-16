package unidue.ub.servicerunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import unidue.ub.media.journals.Journal;
import unidue.ub.media.journals.Journalcollection;
import unidue.ub.media.journals.Journaltitle;

import java.io.*;
import java.net.URI;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class EzbAnalyzer {

    private static final Pattern yearPattern = Pattern.compile("((19|20)\\d\\d)");
    private String dataDir;
    private Hashtable<String, Journalcollection> collections;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    EzbAnalyzer(String dataDir) {
        this.dataDir = dataDir;
    }

    void run(String filename) throws IOException {
        int year = LocalDate.now().getYear();
        Matcher matcher = yearPattern.matcher(filename);
        if (matcher.find())
            year = Integer.parseInt(matcher.group());
        InputStream input = loadFile(filename);
        readCsv(input, year);
        saveJournalCollections(collections);
    }

    private synchronized InputStream loadFile(String filename) throws FileNotFoundException {
        File file = new File(dataDir + "/ezbUpload", filename);
        return new FileInputStream(file);
    }

    private void readCsv(InputStream csvFile, int year) {
        HashSet<String> issnsCollection = new HashSet<>();
        Scanner inputStream = new Scanner(new InputStreamReader(csvFile));
        collections = new Hashtable<>();
        inputStream.nextLine();

        while (inputStream.hasNext()) {
            List<Journaltitle> journalTitles = new ArrayList<>();
            // get the individual parts of the string
            String line = inputStream.nextLine();
            String[] parts = line.split("\t");

            // if there are multiple values, delete the "" enclosing the list of values for the electronic and print issns
            String eIssns = parts[5];
            if (eIssns.contains("\""))
                eIssns = eIssns.replace("\"", "");

            String pIssns = parts[6];
            if (pIssns.contains("\""))
                pIssns = pIssns.replace("\"", "");

            String subject = parts[4];
            if (subject.contains("\""))
                subject = subject.replace("\"", "");

            String zdbID = parts[7];
            String name = parts[1];
            if (name.contains("="))
                name = name.substring(0, name.indexOf("="));
            String type = "single";
            String anchor;
            try {
                anchor = parts[13];
                type = "collection";
            } catch (Exception e1) {
                anchor = name;
            }
            Journalcollection journalCollection;
            if (collections.containsKey(anchor))
                journalCollection = collections.get(anchor);
            else {
                journalCollection = new Journalcollection(anchor, year, type);
                journalCollection.setType("ezb");
                collections.put(anchor, journalCollection);
            }
            Journal journal = new Journal(zdbID, journalCollection);
            journal.setZdbid(zdbID).setEzbID(parts[0]).setSubject(subject).setActualName(name).setLink(parts[12]);
            journalCollection.addJournal(journal);
            journalTitles.addAll(buildJournalTitleList(journal, journalCollection, eIssns, name, "electronic", issnsCollection));
            journalTitles.addAll(buildJournalTitleList(journal, journalCollection, pIssns, name, "print", issnsCollection));
            journal.setJournaltitles(journalTitles);
        }
        inputStream.close();
    }

    private List<Journaltitle> buildJournalTitleList(Journal journal, Journalcollection journalcollection, String issns, String name, String type, HashSet<String> issnsCollection) {
        List<String> issnList = new ArrayList<>();
        List<Journaltitle> journalTitles = new ArrayList<>();
        if (issns.contains(";"))
            issnList = Arrays.asList(issns.split(";"));
        else
            issnList.add(issns);
        for (String issn : issnList) {
            if (!issn.isEmpty() && !issnsCollection.contains(issn)) {
                Journaltitle journalTitle = new Journaltitle(issn, journal, type, name);
                journalTitle.addJournalCollection(journalcollection);
                journalTitles.add(journalTitle);
                issnsCollection.add(issn);
            }
        }
        return journalTitles;
    }

    private void saveJournalCollections(Hashtable<String, Journalcollection> journalCollections) throws IOException {
        Enumeration<Journalcollection> enumeration = journalCollections.elements();
        while (enumeration.hasMoreElements()) {
            Journalcollection journalCollection = enumeration.nextElement();
            log.info("saving collection " + journalCollection.getAnchor());
            URI journalCollectionURI = Tools.saveObject(journalCollection.clone().setJournals(null), "/api/resources/journalcollection");
            for (Journal journal : journalCollection.getJournals()) {
                log.info("saving journal " + journal.getEzbid());
                URI journalURI = Tools.saveObject(journal.clone().setJournaltitles(null), "/api/resources/journal");
                log.info("connecting journal " + journalURI.toString() + " to collection " + journalCollectionURI.toString());
                Tools.connect(journalURI, journalCollectionURI, "journalcollection");
                for (Journaltitle journaltitle : journal.getJournaltitles()) {
                    log.info("saving journal title " + journaltitle.getIssn());
                    URI journaltitleURI = Tools.saveObject(journaltitle, "/api/resources/journaltitle");
                    log.info("connecting journal title " + journaltitleURI.toString() + " to jorunal " + journalURI.toString());
                    Tools.connect(journaltitleURI, journalURI, "journal");
                }
            }
        }
    }
}
