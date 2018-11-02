package unidue.ub.servicerunner.ezbAnalyzer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import unidue.ub.servicerunner.model.journals.Journal;
import unidue.ub.servicerunner.model.journals.Journalcollection;
import unidue.ub.servicerunner.model.journals.Journalholding;
import unidue.ub.servicerunner.repository.graph.JournalholdingRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
@StepScope
public class JournalWriter implements ItemWriter<Journal> {

    @Autowired
    private JournalholdingRepository journalholdingRepository;

    @Value("#{jobParameters['ezb.filename'] ?: 'EZBListe2017.txt'}")
    public String filename;

    @Value("#{jobParameters['ezb.year'] ?: '2017'}")
    public String year;

    @Value("#{jobParameters['ezb.institution'] ?: ''}")
    public String institution;

    private HashMap<String, Journalcollection> journalcollections = new HashMap<>();

    @Override
    public void write(List list) {
        Optional<Journalholding> journalholdingOptional = journalholdingRepository.findByName(institution);
        Journalholding journalholding = journalholdingOptional.orElse(new Journalholding());
        for (Object object : list) {
            Journal journal = (Journal) object;
            String key = journal.getAnchor();
            if (journalcollections.containsKey(key)) {
                Journalcollection journalcollection = journalcollections.get(key);
                journalcollection.addJournal(journal);
                journalcollections.put(key, journalcollection);
            } else {
                Journalcollection journalcollection = new Journalcollection();
                journalcollection.setYear(Integer.parseInt(year));
                journalcollection.setName(key);
                journalcollection.addJournal(journal);
                journalcollections.put(key, journalcollection);
            }
        }

        journalholding.setName(institution);
        journalcollections.forEach((String key, Journalcollection entry) -> journalholding.addJournalcollection(entry));
        journalholdingRepository.save(journalholding);
    }
}
