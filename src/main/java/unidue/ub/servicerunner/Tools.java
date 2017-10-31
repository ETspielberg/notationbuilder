package unidue.ub.servicerunner;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.IOException;

public class Tools {

    static void saveObject(String json, String url) throws IOException {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        RequestEntity entity = new StringRequestEntity(json, "application/json", null);
        post.setRequestEntity(entity);
        client.executeMethod(post);
    }


}
