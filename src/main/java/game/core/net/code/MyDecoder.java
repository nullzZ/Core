package game.core.net.code;

import java.util.List;

import game.core.net.handle.MsgModel;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;

/**
 * 
 * @author nullzZ
 *
 */
public class MyDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		try {
			ByteBuf bb = (ByteBuf) in;
			int cmd = bb.readInt();
			byte[] mb = new byte[bb.capacity() - 4];
			bb.readBytes(mb);
			out.add(new MsgModel(ctx.channel(), cmd, mb));
		} catch (Exception e) {
		} finally {
			ReferenceCountUtil.release(in);
		}
	}

}
