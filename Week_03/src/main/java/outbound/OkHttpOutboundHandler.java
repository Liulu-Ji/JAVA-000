package outbound;

import inbound.HttpInboundHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import javafx.util.Builder;
import kotlin.Pair;
import okhttp3.*;
import okhttp3.internal.http2.Header;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class OkHttpOutboundHandler {
    private static Logger logger = LoggerFactory.getLogger(OkHttpOutboundHandler.class);
    private OkHttpClient okHttpClient;
    private String backendUrl;

    private final static String BAIDU ="http://www.baidu.com";


    public OkHttpOutboundHandler(String url){
        okHttpClient = new OkHttpClient();
        backendUrl = url;
    }


    public void handle(FullHttpRequest fullRequest, ChannelHandlerContext ctx ) throws IOException {
        System.out.println("backendUrl is "+this.backendUrl);
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder = requestBuilder.url(this.backendUrl);

        for(Map.Entry<String,String> entry:fullRequest.headers().entries()){
            String key = entry.getKey();
            String value = entry.getValue();
            requestBuilder = requestBuilder.addHeader(key,value);
        }

        Request request = requestBuilder.build();
        for(String s:request.headers().names()) {
            System.out.println(s);
        }

        //Request request = new Request.Builder().url(BAIDU).build();
        this.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                logger.info("okhttp failed",e);
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                handleResponse(fullRequest,ctx,response);
            }
        });
        //return res.body().string();
    }

    private void handleResponse(FullHttpRequest fullRequest, ChannelHandlerContext ctx,Response response) {
        FullHttpResponse resultResponse = null;
        try {
            byte[] body = response.body().bytes();
            resultResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            resultResponse.headers().set("Content-Type", "application/json");
            resultResponse.headers().setInt("Content-Length", Integer.parseInt(response.header("Content-Length","0")));
        }catch (Exception e){
            exceptionCaught(ctx,e);
            logger.info("okhttp handleRes failed",e);
        }finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(resultResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(resultResponse);
                }
            }
            ctx.flush();
        }


    }
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


}
