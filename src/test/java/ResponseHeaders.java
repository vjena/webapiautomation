import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ResponseHeaders extends BaseClass {


    CloseableHttpClient client;
    CloseableHttpResponse response;


    @BeforeMethod
    public void setup(){
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResources() throws IOException {
        client.close();
        response.close();
    }

    @Test
    public void contentTypeIsJson() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT);

        response = client.execute(get);
        Header contentType=response.getEntity().getContentType();
        assertEquals(contentType.getValue(),"application/json; charset=utf-8");

        ContentType ct= ContentType.getOrDefault(response.getEntity());
        assertEquals(ct.getMimeType(),"application/json");

    }

    @Test
    public void serverIsGitHub()throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        String headerValue = ResourceUtils.getHeaderJava8Way(response,"Server");
        assertEquals(headerValue,"GitHub.com");

    }

    @Test
    public void xRateLimitIsSixty() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT);
         response = client.execute(get);
         String limitVal = ResourceUtils.getHeaderJava8Way(response,"X-RateLimit-Limit");
         assertEquals(limitVal,"60");
    }

    //method to verify if header is present,can be customised
    @Test
    public void eTagIsPresent() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);

        boolean tagIsPresent = ResourceUtils.headerIsPresent(response,"Etag");

        assertEquals(tagIsPresent,true);
    }









}
