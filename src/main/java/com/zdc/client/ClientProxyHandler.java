package com.zdc.client;

import com.zdc.api.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ClientProxyHandler implements InvocationHandler {

	private String host;

	private int port;

	public ClientProxyHandler(String host,int port){
		this.host = host;
		this.port = port;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//请求会进入到这里
		System.out.println("come in");
		//请求数据的包装
		RpcRequest rpcRequest=new RpcRequest();
		rpcRequest.setClassName(method.getDeclaringClass().getName());
		rpcRequest.setMethodName(method.getName());
		rpcRequest.setParams(args);
		//远程通信
		RpcNetTransport netTransport=new RpcNetTransport(host,port);
		Object result=netTransport.send(rpcRequest);

		return result;
	}
}
