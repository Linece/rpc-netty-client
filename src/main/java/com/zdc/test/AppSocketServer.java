package com.zdc.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class AppSocketServer {
  public static void main(String[] args) {

	  try {
		  ServerSocket server = new ServerSocket(8080);
		  Socket socket = server.accept();//阻塞
		  ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		  try {
			  String str = (String) in.readObject();
			  System.out.println(str);
			  OutputStream out = socket.getOutputStream();
			  out.write("sss".getBytes());
			  out.close();
		  } catch (ClassNotFoundException e) {
			  e.printStackTrace();
		  }
		  server.close();
		  socket.close();
		  in.close();
	  } catch (IOException e) {
		  e.printStackTrace();
	  }

  }
}
