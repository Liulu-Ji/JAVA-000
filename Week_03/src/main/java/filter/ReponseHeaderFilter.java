package filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class ReponseHeaderFilter {

    public static void addHeaderToReq(FullHttpRequest fullRequest, ChannelHandlerContext ctx){
        fullRequest.headers().set("nio", "liulu_grid");
    }

}
