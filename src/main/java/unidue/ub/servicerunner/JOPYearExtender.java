package unidue.ub.servicerunner;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.mycore.common.content.MCRContent;
import org.mycore.common.content.MCRSourceContent;
import org.mycore.common.content.transformer.MCRXSL2XMLTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Traverson;
import org.xml.sax.SAXException;
import unidue.ub.media.analysis.Journaldata;
import unidue.ub.media.journals.Journal;
import unidue.ub.media.journals.Journaltitle;
import unidue.ub.media.tools.JournalTools;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class JOPYearExtender {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private List<Journaldata> journalsdata;

    JOPYearExtender() {
    }

    void run() throws URISyntaxException, TransformerException, IOException, JDOMException, SAXException {
        Traverson traverson = new Traverson(new URI("/api/resources/journal"), MediaTypes.HAL_JSON);
        Traverson.TraversalBuilder tb = traverson.follow("$._links.self.href");
        ParameterizedTypeReference<Resources<Journal>> typeRefDevices = new ParameterizedTypeReference<Resources<Journal>>() {
        };
        Resources<Journal> journalsResources = tb.toObject(typeRefDevices);
        List<Journal> journals = new ArrayList(journalsResources.getContent());
        journalsdata = new ArrayList<>();
        for (Journal journal : journals) {
            String queryIssn = journal.getJournaltitles().get(0).getIssn();
            String uri = "jop:genre=journal&sid=bib:ughe&pid=bibid%3DUGHE&issn=" + queryIssn;
            MCRXSL2XMLTransformer transformer = new MCRXSL2XMLTransformer("xsl/ezb2periods.xsl");
            MCRContent jopResponse = MCRSourceContent.getInstance(uri);
            Element data = transformer.transform(jopResponse).asXML().detachRootElement().clone();
            List<Element> sourcesElectronic = new ArrayList<>();
            List<Element> sourcesPrint = new ArrayList<>();
            boolean electronic = true;
            try {
                sourcesElectronic = data.getChild("electronic").getChild("sources").getChildren("source");
            } catch (Exception e1) {
                electronic = false;
            }
            boolean print = true;
            try {
                sourcesPrint = data.getChild("print").getChild("sources").getChildren("source");
            } catch (Exception e2) {
                print = false;
            }
            if (electronic)
                addJournalData(sourcesElectronic, journal);
            if (print)
                addJournalData(sourcesPrint, journal);
        }
        saveJournaldata();
    }

    private void saveJournaldata() throws IOException {
        for (Journaldata journaldata : journalsdata) {
            URI journalDataURI = Tools.saveObject(journaldata, "/api/data/journaldata");
        }
    }

    private void addJournalData(List<Element> sources, Journal journal) {
        for (Element source : sources) {
            try {
                Set<Integer> years = JournalTools.getAvailableYears(source.getChild("period").getText());
                Iterator<Integer> iterator = years.iterator();
                while (iterator.hasNext()) {
                    int year = iterator.next();
                    for (Journaltitle journaltitle : journal.getJournaltitles()) {
                        Journaldata journaldata = new Journaldata(journaltitle.getIssn(), year).setType(journaltitle.getType());
                        journalsdata.add(journaldata);
                    }
                }
            } catch (Exception e) {
                log.info("could not read electronic range");
            }
        }
    }
}
