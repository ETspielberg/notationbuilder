package unidue.ub.servicerunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;
import unidue.ub.servicerunner.model.journals.Journal;
import unidue.ub.servicerunner.model.journals.Journalcollection;
import unidue.ub.servicerunner.repository.graph.JournalRepository;

import java.io.Serializable;
import java.util.Optional;

@Component
public class JournalIdConverter implements BackendIdConverter {

    final
    private JournalRepository journalRepository;

    @Autowired
    public JournalIdConverter(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    @Override
    public Serializable fromRequestId(String name, Class<?> entityType) {
        if (journalRepository.findByName(name).isPresent())
            return journalRepository.findByName(name).get().getId();
        else
            return null;
    }

    @Override
    public String toRequestId(Serializable serializable, Class<?> aClass) {
        Long id = (Long) serializable;
        Optional<Journal> journal = journalRepository.findById(id);
        if (journal.isPresent()) return journal.get().getName();
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Journalcollection.class.equals(aClass);
    }

}
