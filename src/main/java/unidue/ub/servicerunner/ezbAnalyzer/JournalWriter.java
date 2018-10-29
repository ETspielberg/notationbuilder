package unidue.ub.servicerunner.ezbAnalyzer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import unidue.ub.servicerunner.model.journalHoldings.Journal;
import unidue.ub.servicerunner.model.journalHoldings.Journalcollection;
import unidue.ub.servicerunner.model.journalHoldings.Journalholding;
import unidue.ub.servicerunner.repository.journalHoldings.JournalholdingRepository;

import java.util.HashMap;
import java.util.List;

@Component
@StepScope
public class JournalWriter implements ItemWriter<Journal> {

    @Autowired
    private JournalholdingRepository journalholdingRepository;

    @Value("#{jobParameters['ezb.filename'] ?: 'EZBListe2017.txt'}")
    public String filename;

    @Value("#{jobParameters['ezb.year'] ?: '2017'}")
    public String year;

    @Override
    public void write(List list) {
        HashMap<String, Journalcollection> journalcollections = new HashMap<>();
        for (Object object : list) {
            Journal journal = (Journal) object;
            String key = journal.getAnchor();
            if (journalcollections.containsKey(key)) {
                Journalcollection journalcollection = journalcollections.get(key);
                journalcollection.addJournal(journal);
                journalcollections.put(key, journalcollection);
            } else {
                Journalcollection journalcollection = new Journalcollection();
                journalcollection.setAnchor(key);
                journalcollection.addJournal(journal);
                journalcollections.put(key, journalcollection);
            }
        }
        Journalholding journalholding = new Journalholding();
        journalcollections.forEach((String key, Journalcollection entry) -> journalholding.addJournalcollection(entry));
        journalholdingRepository.save(journalholding);
    }
}
