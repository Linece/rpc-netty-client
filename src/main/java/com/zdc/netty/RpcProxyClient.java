package com.zdc.netty;

import com.zdc.client.ClientProxyHandler;

import java.lang.reflect.Proxy;

public class RpcProxyClient {

	public <T> T clientPrxoy(final Class<T> interfaces,final String host,final int port,String version){
		return (T) Proxy.newProxyInstance(interfaces.getClassLoader(), new Class<?>[]{interfaces}, new RemoteClientHandler(host,port,version));

	}
}
