package com.xiaobaby.myproject.service.server;

import com.xiaobaby.myproject.service.server.impl.HelloWorldServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @author Lu Yufeng
 * @date 2018/9/29 下午5:48
 */
public class HelloWorldServiceServer {

    public static void main(String[] args) throws TTransportException {

        TProcessor tProcessor = new HelloWorld.Processor<HelloWorld.Iface>(new HelloWorldServiceImpl());


        TServerSocket serverSocket = new TServerSocket(8080);

        TServer.Args tArgs = new TServer.Args(serverSocket);

        tArgs.processor(tProcessor);

        tArgs.protocolFactory(new TBinaryProtocol.Factory());

        TServer server = new TSimpleServer(tArgs);

        server.serve();
    }
}
