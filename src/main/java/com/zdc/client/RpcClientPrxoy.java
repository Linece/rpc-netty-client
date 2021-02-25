package com.zdc.client;

import java.lang.reflect.Proxy;

public class RpcClientPrxoy {

	public <T> T clientPrxoy(final Class<T> interfaces,final String host,final int port){
		return (T) Proxy.newProxyInstance(interfaces.getClassLoader(), new Class<?>[]{interfaces}, new ClientProxyHandler(host,port));

	}
}
