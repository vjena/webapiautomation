import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.Arrays;
import java.util.List;

public class ResourceUtils {

    public static String getHeaderJava8Way(CloseableHttpResponse response,String headerName){
        List<Header> httpsHeaders = Arrays.asList(response.getAllHeaders());

        Header matchedHeader = httpsHeaders.stream()
                .filter(header->headerName.equalsIgnoreCase(header.getName()))
                .findFirst().orElseThrow(()-> new RuntimeException("didnt find the header"));

        return matchedHeader.getValue();
    }

    public static boolean headerIsPresent(CloseableHttpResponse response,String headerName){
        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
        return httpHeaders.stream()
                .anyMatch(header -> header.getName().equalsIgnoreCase(headerName));

    }

}
