package unidue.ub.servicerunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import unidue.ub.media.journals.Journal;
import unidue.ub.media.journals.JournalCollection;
import unidue.ub.media.journals.JournalTitle;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class EzbUpload {

    private String resourcesUrl;

    private String dataDir;

    private ObjectMapper mapper = new ObjectMapper();


    private static final Pattern yearPattern = Pattern.compile("((19|20)\\d\\d)");

    EzbUpload(String dataDir, String resourcesUrl) {
        this.dataDir = dataDir;
        this.resourcesUrl = resourcesUrl;
    }

    void run(String filename) throws IOException {
        int year = LocalDate.now().getYear();
        Matcher matcher = yearPattern.matcher(filename);
        if (matcher.find())
            year = Integer.parseInt(matcher.group());
        InputStream input = loadFile(filename);
        readCsv(input, year);
    }

    private synchronized InputStream loadFile(String filename) throws FileNotFoundException {
        File file = new File(dataDir, filename);
        return new FileInputStream(file);
    }

    private void readCsv(InputStream csvFile, int year) throws IOException {
        HashSet<String> issnsCollection = new HashSet<>();
        Scanner inputStream = new Scanner(new InputStreamReader(csvFile));
        Hashtable<String, JournalCollection> collections = new Hashtable<>();
        List<Journal> journals = new ArrayList<>();
        List<JournalTitle> journalTitles = new ArrayList<>();
        inputStream.nextLine();

        while (inputStream.hasNext()) {
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

            Journal journal = new Journal();
            journal.setZdbID(zdbID).setEzbID(parts[0]).setSubject(subject).setActualName(name).setLink(parts[12]);
            if (!eIssns.isEmpty())
                journal.addISSN(eIssns);
            if (!pIssns.isEmpty())
                journal.addISSN(pIssns);

            journals.add(journal);
            String anchor = "";
            try {
                anchor = parts[13];
            } catch (Exception e1) {
                anchor = name;

            }
            if (collections.containsKey(anchor)) {
                JournalCollection pack = collections.get(anchor);
                if (!eIssns.isEmpty())
                    pack.addISSN(eIssns);
                if (!pIssns.isEmpty())
                    pack.addISSN(pIssns);
                collections.replace(anchor, pack);
            } else {
                if (!pIssns.isEmpty() || !eIssns.isEmpty()) {
                    JournalCollection pack = new JournalCollection().setYear(year);
                    if (!eIssns.isEmpty())
                        pack.setIssns(eIssns);
                    if (!pIssns.isEmpty())
                        pack.addISSN(pIssns);
                    pack.setAnchor(anchor);
                    collections.put(anchor, pack);
                }
            }
            journalTitles.addAll(buildJournalTitleList(eIssns, name, subject, anchor, zdbID, "electronic", issnsCollection));
            journalTitles.addAll(buildJournalTitleList(pIssns, name, subject, anchor, zdbID, "print", issnsCollection));
        }
        inputStream.close();
        saveJournalTitles(journalTitles);
        saveJournals(journals);
        saveJournalCollections(collections);

    }

    private List<JournalTitle> buildJournalTitleList(String issns, String name, String subject, String anchor, String zdbID, String type, HashSet<String> issnsCollection) {
        List<String> issnList = new ArrayList<>();
        List<JournalTitle> journalTitles = new ArrayList<>();
        if (issns.contains(";"))
            issnList = Arrays.asList(issns.split(";"));
        else
            issnList.add(issns);
        for (String issn : issnList) {
            if (!issn.isEmpty() && !issnsCollection.contains(issn)) {
                JournalTitle journalTitle = new JournalTitle();
                journalTitle.setName(name).setIssn(issn).setSubject(subject).setType(type).setAnchor(anchor).setZDBID(zdbID);
                journalTitles.add(journalTitle);
                issnsCollection.add(issn);
            }
        }
        return journalTitles;
    }

    private void saveJournalTitles(List<JournalTitle> journalTitles) throws IOException {
        for (JournalTitle journalTitle : journalTitles) {
            String json = mapper.writeValueAsString(journalTitle);
            if (!json.isEmpty())
                Tools.saveObject(json,resourcesUrl + "/journaltitle");
        }

    }

    private void saveJournals(List<Journal> journals) throws IOException {
        for (Journal journal : journals) {
            String json = mapper.writeValueAsString(journal);
            if (!json.isEmpty())
                Tools.saveObject(json,resourcesUrl + "/journal");
        }

    }

    private void saveJournalCollections(Hashtable<String,JournalCollection> journalCollections) throws IOException {
        Enumeration<JournalCollection> enumeration = journalCollections.elements();
        while (enumeration.hasMoreElements()) {
            JournalCollection journalCollection = enumeration.nextElement();
            String json = mapper.writeValueAsString(journalCollection);
            if (!json.isEmpty())
                Tools.saveObject(json,resourcesUrl + "/journalcollection");
        }
    }
}
