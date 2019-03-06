package com.tomiyo.rpc.test;

import com.tomiyo.rpc.client.RPCClient;
import com.tomiyo.rpc.server.HelloService;
import com.tomiyo.rpc.server.HelloServiceImpl;
import com.tomiyo.rpc.servicecenter.Server;
import com.tomiyo.rpc.servicecenter.ServiceCenter;

import javax.security.sasl.SaslServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCTest {
  public  static void main(String[] args) throws IOException{
    new Thread(new Runnable() {
      public void run() {
        try{
          Server serviceServer = new ServiceCenter(8001);
          serviceServer.register(HelloService.class, HelloServiceImpl.class);
          serviceServer.start();
        }catch (Exception e){
          e.printStackTrace();
        }
      }
    }).start();

    HelloService service = RPCClient.getRemoteProxyObj(HelloService.class,new InetSocketAddress("localhost",8001));
    //service= new HelloServiceImpl();
    String hi = service.sayHello("kun kun kum");
    System.out.println(hi);
  }
}
