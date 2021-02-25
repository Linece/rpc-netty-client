package com.zdc.client;

import com.zdc.api.RpcRequest;

import java.io.*;
import java.net.Socket;

public class RpcNetTransport {

	private String host;

	private int port;

	public RpcNetTransport(String host,int port){
		this.host = host;
		this.port = port;
	}

	public Object send(RpcRequest rpcRequest){
		Socket socket = null;
		Object result = null;
		ObjectInputStream objectInputStream = null;
		ObjectOutputStream objectOutputStream = null;

		try {
			socket = new Socket(host, port);
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(rpcRequest);
			objectOutputStream.flush();

//			InputStream in = socket.getInputStream();
//
//			byte[] b = new byte[1024];
//
//			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            objectInputStream = new ObjectInputStream(socket.getInputStream());
			result = objectInputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
