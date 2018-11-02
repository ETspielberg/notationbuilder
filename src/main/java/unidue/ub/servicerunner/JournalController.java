package unidue.ub.servicerunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import unidue.ub.servicerunner.repository.graph.JournalRepository;
import unidue.ub.servicerunner.repository.graph.JournalcollectionRepository;
import unidue.ub.servicerunner.repository.graph.JournaltitleRepository;

@Controller
public class JournalController {

    private final JournaltitleRepository journaltitleRepository;

    private final JournalcollectionRepository journalcollectionRepository;

    private final JournalRepository journalRepository;

    @Autowired
    JournalController(JournalRepository journalRepository,
                      JournalcollectionRepository journalcollectionRepository,
                      JournaltitleRepository journaltitleRepository) {
        this.journalcollectionRepository = journalcollectionRepository;
        this.journalRepository = journalRepository;
        this.journaltitleRepository = journaltitleRepository;
    }


    @RequestMapping("/journaltitle/getAllRelatedIssns/{issn}")
    public ResponseEntity<?> getAllRelatedIssns(@PathVariable String issn) {
        return ResponseEntity.ok(journaltitleRepository.getRelatedIssnsFor(issn));
    }
}
