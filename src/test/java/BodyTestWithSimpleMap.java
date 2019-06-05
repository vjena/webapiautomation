import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static entities.User.LOGIN;
import static  entities.User.ID;
import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class BodyTestWithSimpleMap extends BaseClass {

    CloseableHttpClient client;
    CloseableHttpResponse response;


    @BeforeMethod
    public void setup(){
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResources() throws IOException {
        client.close();
        // response.close();
    }
    @Test
    public void returnCorrectLogin() throws IOException{

        HttpGet get = new HttpGet(BASE_ENDPOINT +"/users/vijeta");
        response = client.execute(get);
        String jsonBody = EntityUtils.toString(response.getEntity());

        JSONObject jsonObject = new JSONObject(jsonBody);

        String loginValue = (String)getValueFor(jsonObject,LOGIN);
        assertEquals(loginValue,"vijeta");


    }

    private Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }

    @Test
    public void returnCorrectId() throws IOException{

        HttpGet get = new HttpGet(BASE_ENDPOINT +"/users/vijeta");
        response = client.execute(get);
        String jsonBody = EntityUtils.toString(response.getEntity());

        JSONObject jsonObject = new JSONObject(jsonBody);

        Integer id = (Integer)getValueFor(jsonObject,ID);
        assertEquals(id,Integer.valueOf(1567832));


    }

}
