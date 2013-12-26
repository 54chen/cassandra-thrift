package com.xen.thrift.cassandra;

import com.xen.Test;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.*;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhen
 * Date: 13-12-25
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
public class CustomTNonBlockingServer  extends TNonblockingServer
{


    public CustomTNonBlockingServer(AbstractNonblockingServerArgs args) {
        super(args);
    }

    @Override
    protected boolean requestInvoke(FrameBuffer frameBuffer)
    {
        System.out.println("into requestInvoke");

//        TNonblockingSocket socket = (TNonblockingSocket) ((CustomFrameBuffer)frameBuffer).getTransport();
        // session start
//        System.out.println(socket.getSocketChannel().socket().getRemoteSocketAddress());
        frameBuffer.invoke();
        return true;
    }

    public static class Factory
    {
        public TServer buildTServer(TNonblockingServer.Args args, InetSocketAddress addr,TProcessor processor)
        {

            TNonblockingServerTransport serverTransport;
            try
            {
                serverTransport = new TCustomNonblockingServerSocket(addr, true, 20000, 20000);
            }
            catch (TTransportException e)
            {
                throw new RuntimeException(String.format("Unable to create thrift socket to %s:%s", addr.getAddress(), addr.getPort()), e);
            }

            // This is single threaded hence the invocation will be all
            // in one thread.
            TTransportFactory tProtocolFactory = new TFramedTransport.Factory(40000);
            TNonblockingServer.Args serverArgs = new TNonblockingServer.Args(serverTransport).inputTransportFactory(tProtocolFactory)
                    .outputTransportFactory(tProtocolFactory).processor(processor);
            return new CustomTNonBlockingServer(serverArgs);
        }

    }

    public class CustomFrameBuffer extends FrameBuffer
    {
        public CustomFrameBuffer(final TNonblockingTransport trans,
                                 final SelectionKey selectionKey,
                                 final AbstractSelectThread selectThread) {
            super(trans, selectionKey, selectThread);
        }

        public TNonblockingTransport getTransport() {
            return this.trans_;
        }
    }
}
