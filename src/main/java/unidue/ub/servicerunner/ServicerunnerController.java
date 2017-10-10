package unidue.ub.servicerunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import unidue.ub.settings.fachref.Notation;
import unidue.ub.settings.fachref.Notationgroup;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/run")
public class ServicerunnerController {

    private ObjectMapper mapper = new ObjectMapper();

    @Value("${notationsDir}")
    private String dataDir;

    @RequestMapping("/notationbuilder")
    public void runNotationBuilder() throws IOException {
        List<String> filenames = listFiles();
        List<Notation> notations = new ArrayList<>();
        List<Notationgroup> notationgroups = new ArrayList<>();

        for (String filename : filenames) {
            try {
                Element current = loadFile(filename);
                Notationgroup notationsPerSubject = new Notationgroup();
                notationsPerSubject.setNotationgroupName(current.getAttributeValue("zahl"));
                notationsPerSubject.setType("main");
                setValuesForNotationgroup(current,notationsPerSubject);
                notationgroups.add(notationsPerSubject);
                Iterator<Element> descendants = current.getDescendants(new ElementFilter());
                while (descendants.hasNext()) {
                    Element descendant = descendants.next();
                    if (descendant.getName().equals("stelle")) {
                        notations.add(new Notation().setNotation(descendant.getAttributeValue("code")).setDescription(descendant.getChildText("bez")));
                    } else if (descendant.getName().equals("gruppe")) {
                        Notationgroup notationsPerGroup = new Notationgroup();
                        notationsPerGroup.setType("group");
                        notationsPerGroup.setNotationgroupName(descendant.getAttributeValue("von") + " - " + descendant.getAttributeValue("bis"));
                        setValuesForNotationgroup(descendant,notationsPerGroup);
                        notationgroups.add(notationsPerGroup);
                    }
                }
            } catch (IOException | JDOMException e) {
                e.printStackTrace();
            }
        }
        try {
            saveNotations(notations);
            saveNotationgroups(notationgroups);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setValuesForNotationgroup(Element node, Notationgroup notationgroup) {
        notationgroup.setDescription(node.getChildText("bez"));
        notationgroup.setNotationsStart(node.getAttributeValue("von"));
        notationgroup.setNotationsEnd(node.getAttributeValue("bis"));
    }

    private synchronized Element loadFile(String filename) throws IOException, JDOMException {
        File file = new File(dataDir, filename);
        Document doc = new SAXBuilder().build(file);
        return doc.detachRootElement().clone();
    }

    private List<String> listFiles() {
        File xmlDirectory = new File(dataDir);
        FilenameFilter filter = (dir, name) -> {
            String lowercaseName = name.toLowerCase();
            return lowercaseName.startsWith("systematik-");
        };
        try {
            return Arrays.asList(xmlDirectory.list(filter));
        } catch (Exception e) {
            return null;
        }
    }

    private void saveNotations(List<Notation> notations) throws IOException {
        for (Notation notation : notations) {
            String json = mapper.writeValueAsString(notation);
            if (!json.isEmpty())
            saveObject(json,"http://localhost:11300/notation");
        }

    }

    private void saveNotationgroups(List<Notationgroup> notationgroups) throws IOException {
        for (Notationgroup notationgroup : notationgroups) {
            String json = mapper.writeValueAsString(notationgroup);
            if (!json.isEmpty())
            saveObject(json,"http://localhost:11300/notationgroup");
        }
    }

    private void saveObject(String json, String url) throws IOException {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        RequestEntity entity = new StringRequestEntity(json, "application/json", null);
        post.setRequestEntity(entity);
        client.executeMethod(post);
    }
}
