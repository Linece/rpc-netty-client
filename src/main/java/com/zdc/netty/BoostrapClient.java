package com.zdc.netty;

import com.zdc.api.IHelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BoostrapClient {
  public static void main(String[] args) {

    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    RpcProxyClient rpcProxyClient = context.getBean(RpcProxyClient.class);
    IHelloService iHelloService = rpcProxyClient.clientPrxoy(IHelloService.class, "localhost", 8080, "V2.0");
    String sayHello = iHelloService.sayHello("heeeeeeeeeeeeeeeeeeee");
    System.out.println(sayHello);

  }
}
