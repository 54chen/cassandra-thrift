package com.xen.thrift.cassandra;

import com.xen.Test;
import com.xen.thrift.TestServer;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.AbstractNonblockingServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.*;

import java.net.InetSocketAddress;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhen
 * Date: 13-12-25
 * Time: 下午3:01
 * To change this template use File | Settings | File Templates.
 */
public class CassandraTestServer {
    public static void main(String[] args) throws Exception
    {
        Test.Processor processor = new Test.Processor(new TestServer());


        TServer server =  new CustomTNonBlockingServer.Factory().buildTServer(null,new InetSocketAddress("localhost", TestServer.port),processor);

        System.out.println("test server is starting...");
        server.serve();
    }


}
