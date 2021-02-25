package com.zdc.client;

import com.zdc.api.IHelloService;
import com.zdc.api.User;

public class App {
  public static void main(String[] args) {

  	  RpcClientPrxoy rpcClientPrxoy = new RpcClientPrxoy();
	  IHelloService iHelloService = rpcClientPrxoy.clientPrxoy(IHelloService.class, "127.0.0.1", 8080);
	 // String result = iHelloService.sayHello("zdcaaaaa");
	  User user = new User();
	  user.setAge(11);
	  user.setName("zdc");
	  String s = iHelloService.saveUser(user);
	  System.out.println("########################:"+s);
	  //System.out.println(result);

  }
}
