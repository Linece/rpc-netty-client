package com.zdc.netty;

import com.zdc.api.RpcRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class RpcNetTransport extends SimpleChannelInboundHandler<Object> {

	private String host;

	private int port;

	private Object object;

	public RpcNetTransport(String host,int port){
		this.host = host;
		this.port = port;

	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

		//读取返回数据
			this.object = msg;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("异常：");
		ctx.close();
	}

	public Object send(RpcRequest request){

		NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE,ClassResolvers.cacheDisabled(null)))
						.addLast(new ObjectEncoder()).addLast(RpcNetTransport.this);
			}
		}).option(ChannelOption.TCP_NODELAY,true );
		try{
			ChannelFuture future = bootstrap.connect(host,port ).sync();
			future.channel().writeAndFlush(request).sync();
			if(request != null){
				future.channel().closeFuture().sync();
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			eventLoopGroup.shutdownGracefully();
		}
		return object;
	}
}
