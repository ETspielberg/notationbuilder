package unidue.ub.servicerunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

public class Tools {

    private static Logger log = LoggerFactory.getLogger(Tools.class);

    static URI saveObject(Object object, String url) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<>(object);
        URI location = restTemplate.postForLocation(url,request,Object.class);
        return location;
    }

    static void connect(URI child, URI parent, String type) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new org.springframework.http.MediaType("text","uri-list"));
        HttpEntity<String> request = new HttpEntity<>(parent.toString(),headers);
        String url = child.toString() + "/" + type;
        log.info("sending put request to " + url);
        restTemplate.put(url,request);
    }


}
