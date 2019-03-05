package com.tomiyo.rpc.test;

import com.tomiyo.rpc.server.HelloService;
import com.tomiyo.rpc.server.HelloServiceImpl;
import com.tomiyo.rpc.servicecenter.Server;
import com.tomiyo.rpc.servicecenter.ServiceCenter;
import java.io.IOException;

public class RPCTest {
  public  static void main(String[] args) throws IOException{
    new Thread(new Runnable() {
      public void run() {
        try{
          Server serviceServer = new ServiceCenter(8088);
          serviceServer.register(HelloService.class, HelloServiceImpl.class);
          serviceServer.start();
        }catch (Exception e){
          e.printStackTrace();
        }
      }
    }).start();
  }
}
