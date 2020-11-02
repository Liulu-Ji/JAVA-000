package filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

public class ReponseHeaderFilter extends ChannelInboundHandlerAdapter {

    public static void addHeaderToReq(FullHttpRequest fullRequest, ChannelHandlerContext ctx){
        fullRequest.headers().set("nio", "liulu_grid");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            ReponseHeaderFilter.addHeaderToReq(fullRequest,ctx);
            ReferenceCountUtil.release(msg);
            ctx.fireChannelRead(fullRequest);

    }

}
