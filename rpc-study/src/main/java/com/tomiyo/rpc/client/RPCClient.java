package com.tomiyo.rpc.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RPCClient<T> {

  public static <T> T getRemoteProxyObj(final Class<?> serviceInterface, final InetSocketAddress addr) {

    T t= (T)Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface},
        new InvocationHandler() {
          public Object invoke(Object o, Method method, Object[] args) throws Throwable {
            Socket socket = null;
            ObjectOutputStream output = null;
            ObjectInputStream input = null;

            try {
              socket = new Socket();
              socket.connect(addr);

              output = new ObjectOutputStream(socket.getOutputStream());

              output.writeUTF(serviceInterface.getName());
              output.writeUTF(method.getName());
              output.writeObject(method.getParameterTypes());
              output.writeObject(args);

              input = new ObjectInputStream(socket.getInputStream());
              return input.readObject();
            } finally {
              if (socket != null) {
                socket.close();
              }
              ;
              if (output != null) {
                output.close();
              }
              if (input != null) {
                input.close();
              }
            }

          }
        });

      return  t;
  }

}
