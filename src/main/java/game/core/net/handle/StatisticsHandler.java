package game.core.net.handle;

import game.core.statistics.StatisticsUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * 统计
 * 
 * @author nullzZ
 *
 */
public class StatisticsHandler extends ChannelDuplexHandler {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf bb = (ByteBuf) msg;
		StatisticsUtil.getInstatnce().STReceiveSum(bb.readableBytes());
		ctx.fireChannelRead(msg);
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		ByteBuf bb = (ByteBuf) msg;
		StatisticsUtil.getInstatnce().STSendSum(bb.readableBytes());
		ctx.write(msg, promise);
	}
}
