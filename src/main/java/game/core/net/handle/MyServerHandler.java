package game.core.net.handle;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import game.core.net.manager.ActionManager;
import game.core.statistics.StatisticsUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.netty.channel.ChannelHandler.Sharable;

/**
 * 
 * @author nullzZ
 *
 */

@Service
@Scope("prototype")
@Sharable // 注解@Sharable可以让它在channels间共享
public class MyServerHandler extends ChannelInboundHandlerAdapter {

	private static final Logger logger = Logger.getLogger(MyServerHandler.class);

	@Resource
	private ActionManager actionManager;

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		try {
			ByteBuf bb = (ByteBuf) msg;
			StatisticsUtil.getInstatnce().STReceiveSum(bb.capacity());

			int cmd = bb.readInt();
			byte[] mb = new byte[bb.capacity() - 4];
			bb.readBytes(mb);

			actionManager.handle(ctx.channel(), cmd, mb);
		} catch (Exception e) {
			logger.error("[channelRead][异常]", e);
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
