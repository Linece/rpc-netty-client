package com.zdc.netty;

import com.zdc.api.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteClientHandler implements InvocationHandler {

	private String version;

	private int port;

	private String host;

	public RemoteClientHandler(String host,int port,String version){
		this.host = host;
		this.port = port;
		this.version = version;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		RpcRequest request = new RpcRequest();
		request.setParams(args);
		request.setMethodName(method.getName());
		request.setClassName(method.getDeclaringClass().getName());
		request.setParamTypes(method.getParameterTypes());
		request.setVersion(version);
		RpcNetTransport transport = new RpcNetTransport(host, port);

		Object result = transport.send(request);
		return result;
	}
}
