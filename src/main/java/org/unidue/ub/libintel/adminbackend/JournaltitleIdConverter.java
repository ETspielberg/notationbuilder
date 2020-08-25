package org.unidue.ub.libintel.adminbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;
import org.unidue.ub.libintel.adminbackend.model.journals.Journalcollection;
import org.unidue.ub.libintel.adminbackend.model.journals.Journaltitle;
import org.unidue.ub.libintel.adminbackend.repository.graph.JournaltitleRepository;

import java.io.Serializable;
import java.util.Optional;

@Component
public class JournaltitleIdConverter implements BackendIdConverter {

    final
    private JournaltitleRepository journaltitleRepository;

    @Autowired
    public JournaltitleIdConverter(JournaltitleRepository journaltitleRepository) {
        this.journaltitleRepository = journaltitleRepository;
    }

    @Override
    public Serializable fromRequestId(String name, Class<?> entityType) {
        if (journaltitleRepository.findByName(name).isPresent())
            return journaltitleRepository.findByName(name).get().getId();
        else
            return null;
    }

    @Override
    public String toRequestId(Serializable serializable, Class<?> aClass) {
        Long id = (Long) serializable;
        Optional<Journaltitle> journaltitle = journaltitleRepository.findById(id);
        if (journaltitle.isPresent()) return journaltitle.get().getName();
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Journalcollection.class.equals(aClass);
    }

}
