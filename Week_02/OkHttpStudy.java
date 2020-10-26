import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * OkHttp 请求
 */
public class OkHttpStudy {

    private final static String WEB_URL = "http://localhost:8801";

    public static String getMethod(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response res = okHttpClient.newCall(request).execute();
        return res.body().string();
    }

    public static void main(String[] args) {
        try {
            System.out.println(getMethod(WEB_URL));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
