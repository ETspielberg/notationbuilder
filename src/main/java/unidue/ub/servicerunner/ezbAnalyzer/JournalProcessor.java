package unidue.ub.servicerunner.ezbAnalyzer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import unidue.ub.servicerunner.model.journalHoldings.Journal;
import unidue.ub.servicerunner.model.journalHoldings.Journaltitle;

import java.util.*;

@Component
@StepScope
public class JournalProcessor implements ItemProcessor<String, Journal> {

    @Value("${ub.statistics.data.dir}")
    private String dataDir;

    @Value("#{jobParameters['systematic.type'] ?: 'ghbsys'}")
    public String systematicType;

    @Autowired
    Jaxb2Marshaller jaxb2Marshaller;

    @Override
    public Journal process(final String line) {
        HashSet<String> issnsCollection = new HashSet<>();
        Set<Journaltitle> journaltitles = new HashSet<>();
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
        String anchor;
        try {
            anchor = parts[13];
        } catch (Exception e1) {
            anchor = name;
        }
        Journal journal = new Journal(zdbID, anchor);
        journal.setZdbid(zdbID).setEzbID(parts[0]).setSubject(subject).setActualName(name).setLink(parts[12]).setActualName(anchor);
        journaltitles.addAll(buildJournalTitleList(journal, eIssns, name, "electronic", issnsCollection));
        journaltitles.addAll(buildJournalTitleList(journal, pIssns, name, "print", issnsCollection));
        journal.setJournaltitles(new HashSet<>(journaltitles));
        return journal;
    }

    private List<Journaltitle> buildJournalTitleList(Journal journal, String issns, String name, String type, HashSet<String> issnsCollection) {
        List<String> issnList = new ArrayList<>();
        List<Journaltitle> journalTitles = new ArrayList<>();
        if (issns.contains(";"))
            issnList = Arrays.asList(issns.split(";"));
        else
            issnList.add(issns);
        for (String issn : issnList) {
            if (!issn.isEmpty() && !issnsCollection.contains(issn)) {
                Journaltitle journalTitle = new Journaltitle(issn, journal, type, name);
                journalTitles.add(journalTitle);
                issnsCollection.add(issn);
            }
        }
        return journalTitles;
    }
}
