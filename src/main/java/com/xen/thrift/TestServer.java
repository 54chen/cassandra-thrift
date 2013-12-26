package com.xen.thrift;

import com.xen.Test;
import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhen
 * Date: 13-12-25
 * Time: 下午4:45
 * To change this template use File | Settings | File Templates.
 */
public class TestServer implements Test.Iface{
    public static final int port = 1234;
    public static void main(String[] args) throws Exception
    {
        Test.Processor processor = new Test.Processor(new TestServer());
        TServerTransport serverTransport = new TServerSocket(TestServer.port);
        TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
        System.out.println("test server is starting...");
        server.serve();
    }

    @Override
    public void sayHello(String name) throws TException {
        System.out.println("Hello " + name);
    }
}
