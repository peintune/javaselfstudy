package com.tomiyo.rpc.server;

public class HelloServiceImpl implements HelloService {

  public String sayHello(String name) {
    return "Hi:"+name;
  }

}
