package game.core.net;

import java.util.concurrent.ThreadFactory;

import org.apache.log4j.Logger;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 
 * @author nullzZ
 *
 */
public class Server implements IServer {

	private static final Logger logger = Logger.getLogger(Server.class);
	private final int bossThreadSize = 1;
	private final int workerThreadSize = Runtime.getRuntime().availableProcessors() * 2;
	private final int sendBufferSize = 512 * 1024;
	private final int receiveBufferSize = 8 * 1024;

	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	private int port;
	// private String host;
	private ChannelFuture f;
	private ChannelInboundHandler handler;

	/**
	 * 
	 * @param host
	 * @param port
	 * @param handler
	 *            (spring管理的bean)
	 */
	public Server(int port, ChannelInboundHandler handler) {
		this.port = port;
		this.handler = handler;
	}

	public void start() {
		try {
			ServerBootstrap b = new ServerBootstrap();
			bossGroup = new NioEventLoopGroup(bossThreadSize, new ThreadFactory() {

				public Thread newThread(Runnable r) {
					return new Thread(r, "netty_boss");
				}
			});
			workerGroup = new NioEventLoopGroup(workerThreadSize, new ThreadFactory() {

				public Thread newThread(Runnable r) {
					return new Thread(r, "netty_worker");
				}
			});
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.config().setReceiveBufferSize(receiveBufferSize);
							ch.config().setSendBufferSize(sendBufferSize);

							ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
							ch.pipeline().addLast("decoder", new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2));
							ch.pipeline().addLast("encoder", new LengthFieldPrepender(2));
							ch.pipeline().addLast("handler", handler);
						}
					}).option(ChannelOption.SO_BACKLOG, 128).option(ChannelOption.TCP_NODELAY, true)
					.option(ChannelOption.SO_KEEPALIVE, true).option(ChannelOption.SO_REUSEADDR, true);
			f = b.bind(this.port).sync();
			logger.info("start server---");
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
			logger.info("stop server");
		}
	}

	public void stop() {
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
	}

}
