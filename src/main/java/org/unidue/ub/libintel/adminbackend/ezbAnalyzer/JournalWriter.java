package org.unidue.ub.libintel.adminbackend.ezbAnalyzer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.unidue.ub.libintel.adminbackend.model.journals.Journal;
import org.unidue.ub.libintel.adminbackend.model.journals.Journalcollection;
import org.unidue.ub.libintel.adminbackend.repository.graph.JournalcollectionRepository;

import java.util.*;

@Component
@StepScope
public class JournalWriter implements ItemWriter<Journal> {

    @Autowired
    private JournalcollectionRepository journalcollectionRepository;

    @Override
    public void write(List list) {
        HashMap<String, Set<Journal>> journals = new HashMap<>();
        for (Object object : list) {
            Journal journal = (Journal) object;
            String key = journal.getAnchor();
            if (journals.containsKey(key)) {
                journals.get(key).add(journal);
            } else {
                Set<Journal> journalsInd = new HashSet<>();
                journalsInd.add(journal);
                journals.put(key, journalsInd);
            }
        }
        journals.forEach((String key, Set<Journal> journalList) -> {
            Optional<Journalcollection> journalcollectionOptional = journalcollectionRepository.findByIdentifier(key);
            Journalcollection journalcollection = journalcollectionOptional.orElse(new Journalcollection(key));
            journalcollection.addJournals(journalList);
            journalcollectionRepository.save(journalcollection);
        });
    }
}
