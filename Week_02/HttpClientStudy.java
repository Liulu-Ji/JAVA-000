import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * HttpClient 请求
 */
public class HttpClientStudy {
    private final static String WEB_URL = "http://localhost:8801";
    private final static String BAIDU_WEB_URL = "http://www.baidu.com";

    public static String getMethod(String url) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse res = httpClient.execute(httpGet);
        return EntityUtils.toString(res.getEntity(),"UTF-8");
    }

    public static void main(String[] args) {
        try {
            System.out.println(getMethod(BAIDU_WEB_URL));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
