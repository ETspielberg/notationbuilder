package unidue.ub.servicerunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class DataWriter {

    public DataWriter() {}

    private static final Logger log = LoggerFactory.getLogger(DataWriter.class);
    private ObjectMapper mapper = new ObjectMapper();
    private static final HttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();

    public void write(List list) throws IOException {
        int succesfullPosts = 0;
        for (Object object : list) {
            String json = mapper.writeValueAsString(object);
            HttpClient client = new HttpClient(httpConnectionManager);
            String objectType = object.getClass().getSimpleName();
            PostMethod post = new PostMethod("http://localhost:8082/api/settings/" + objectType.toLowerCase());
            RequestEntity entity = new StringRequestEntity(json, "application/json", null);
            post.setRequestEntity(entity);
            int status = client.executeMethod(post);
            if (status == 201)
                succesfullPosts++;
            else log.info("posted " + objectType + " to api/data/" + objectType.toLowerCase() + " with return status " + status);
            post.releaseConnection();
        }
        log.info("successfully posted " + succesfullPosts + " of " + list.size() + " counter data.");
    }

}
