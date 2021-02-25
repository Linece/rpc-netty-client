package com.zdc.test;

import java.io.*;
import java.net.Socket;

public class AppSocketClient {

  public static void main(String[] args) {
	  try {
		  Socket socket = new Socket("127.0.0.1", 8080);
		  ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		  out.writeObject("stttt");

		  InputStream in = socket.getInputStream();
		  InputStreamReader isr = new InputStreamReader(in,"UTF-8");
		  BufferedReader br = new BufferedReader(isr);

		  String line = null;
		  while((line = br.readLine())!=null) {

		  }
		  in.close();
		  out.flush();
		  out.close();

	  } catch (IOException e) {
		  e.printStackTrace();
	  }
  }
}
