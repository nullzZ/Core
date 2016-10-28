package game.core.net.my;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.protobuf.GeneratedMessage;

import game.core.net.my.manager.ActionManager;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 
 * @author nullzZ
 *
 */
@Service
@Scope("prototype")
@Sharable // 注解@Sharable可以让它在channels间共享
public class MyEncoder extends MessageToByteEncoder<GeneratedMessage> {

	private static final Logger logger = Logger.getLogger(MyEncoder.class);
	@Resource
	private ActionManager actionManager;

	@Override
	protected void encode(ChannelHandlerContext ctx, GeneratedMessage msg, ByteBuf out) throws Exception {
		Integer cmd = actionManager.getResponseCmd(msg);
		if (cmd == null) {
			logger.error("[编码][不存在]" + msg.getClass().getCanonicalName());
			return;
		}
		out.writeInt(cmd);
		out.writeBytes(msg.toByteArray());
	}

}
