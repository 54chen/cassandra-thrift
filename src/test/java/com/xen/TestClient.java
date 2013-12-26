package com.xen;

import com.xen.thrift.TestServer;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhen
 * Date: 13-12-25
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */

public class TestClient {

    @org.junit.Test
    public void testCassandraTestServer() throws Exception
    {
        TTransport transport = new TFramedTransport(new TSocket("localhost", TestServer.port));
        TProtocol protocol = new TBinaryProtocol(transport);
        Test.Client client = new Test.Client(protocol);
        transport.open();
        System.out.println("Client is calling...");
        client.sayHello("54chen");
        transport.close();
    }

    @org.junit.Test
    public void testDefaultTestServer() throws Exception
    {
        TTransport transport = new TSocket("localhost", TestServer.port);
        TProtocol protocol = new TBinaryProtocol(transport);
        Test.Client client = new Test.Client(protocol);
        transport.open();
        System.out.println("Client is calling...");
        client.sayHello("54chen");
        transport.close();
    }
}
