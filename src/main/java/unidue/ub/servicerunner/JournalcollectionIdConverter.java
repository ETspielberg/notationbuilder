package unidue.ub.servicerunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;
import unidue.ub.servicerunner.model.journals.Journalcollection;
import unidue.ub.servicerunner.repository.graph.JournalcollectionRepository;

import java.io.Serializable;
import java.util.Optional;

@Component
public class JournalcollectionIdConverter implements BackendIdConverter {

    final
    private JournalcollectionRepository journalcollectionRepository;

    @Autowired
    public JournalcollectionIdConverter(JournalcollectionRepository journalcollectionRepository) {
        this.journalcollectionRepository = journalcollectionRepository;
    }

    @Override
    public Serializable fromRequestId(String id, Class<?> entityType) {
        if (journalcollectionRepository.findByIdentifier(id).isPresent())
            return journalcollectionRepository.findByIdentifier(id).get().getId();
        else
            return null;
    }

    @Override
    public String toRequestId(Serializable serializable, Class<?> aClass) {
        Long id = (Long) serializable;
        Optional<Journalcollection> journalcollection = journalcollectionRepository.findById(id);
        if (journalcollection.isPresent()) return journalcollection.get().getIdentifier();
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Journalcollection.class.equals(aClass);
    }

}
