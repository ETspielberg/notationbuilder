package unidue.ub.servicerunner;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import unidue.ub.media.analysis.Journaldata;

import java.net.URISyntaxException;

public class PriceExtender {

    private int year;

    private String identifier;

    private String type;

    PriceExtender() {
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setYear(int year) {
        this.year = year;
    }

    void run() throws URISyntaxException {
        ResponseEntity<Journaldata> response = new RestTemplate().getForEntity(
                "/api/data/journaldata/" + identifier,
                Journaldata.class);
        Journaldata journaldata;
        if (response.getStatusCodeValue() == 200)
            journaldata = response.getBody();
        else
            journaldata = new Journaldata(identifier, year);
        switch (type) {
            case "journal": {
                ResponseEntity<Journaldata> getterResponse = new RestTemplate().getForEntity(
                        "/data/journaldata/" + identifier,
                        Journaldata.class);

            }
            case "journaltitle": {

            }
            case "journalcollection": {

            }
        }

    }
}
