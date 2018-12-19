/**
 * This is the communications package which includes TCPClient, TCPServer and
 * XHttpServer classes.
 */
package channels;

import utils.AppQueue;
import utils.Translate;
import utils.Utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.BufferUnderflowException;
import java.nio.channels.ClosedChannelException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.sql.SQLException;
import javax.management.JMException;
import org.xsocket.MaxReadSizeExceededException;
import org.xsocket.connection.*;

/**
 * TCPServer starts the port and accepts the incoming requests from the remote
 * entity using the TCP/IP protocol.
 *
 * @author Abhishek M
 */
public class TCPServerDL
{

    String name;
    AppQueue event_q;
    InetAddress ina;
    int port;
    int backlog;
    long idletimeout;
    Server tcpserver;
    INonBlockingConnection inbcx;

    /**
     * The constructor class which takes in the parameter to start the requested
     * port and accept connections from the remote entity.
     *
     * @param name The name of the TCPServer.
     * @param ipaddress The IP on which the TCPServer should listen to.
     * @param port The port on which the TCPServer should listen to.
     * @param event_q The event queue wherein all the events of the TCPServer
     * will be placed
     * @param backlog The maximum number of connections which can be accepted on
     * the configured IP / Port.
     * @param idletimeout The time after which the connection's state will be
     * marked as idle. Note this will not incur in a disconnect.
     * @throws UnknownHostException
     */
    public TCPServerDL(String name, String ipaddress, int port, AppQueue event_q,
            int backlog, long idletimeout) throws IOException
    {
        this.name = name;
        this.ina = InetAddress.getByName(ipaddress);
        this.port = port;
        this.event_q = event_q;
        this.backlog = backlog;
        this.idletimeout = idletimeout;

        tcpserver = new Server(ina, port, new MsgHandler());
        tcpserver.setMaxConcurrentConnections(backlog);

        if (idletimeout > 0)
        {
            tcpserver.setIdleTimeoutMillis(idletimeout);
        }
        
        tcpserver.setFlushmode(IConnection.FlushMode.ASYNC);
        
        ConnectionUtils.start(tcpserver);
        try
        {
            ConnectionUtils.registerMBean(tcpserver);
        } catch (JMException ex)
        {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }

    /**
     * The constructor class which takes in the parameter to start the requested
     * port and accept connections from the remote entity.
     *
     * @param name The name of the TCPServer.
     * @param ipaddress The IP on which the TCPServer should listen to.
     * @param port The port on which the TCPServer should listen to.
     * @param event_q The event queue wherein all the events of the TCPServer
     * will be placed
     * @param backlog The maximum number of connections which can be accepted on
     * the configured IP / Port.
     * @param idletimeout The time after which the connection's state will be
     * marked as idle. Note this will not incur in a disconnect.
     * @param useSSL
     * @param certificatepath
     * @throws IOException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws UnrecoverableKeyException
     * @throws KeyManagementException
     * @throws FileNotFoundException
     * @throws SQLException
     */
    public TCPServerDL(String name, String ipaddress, int port, AppQueue event_q,
            int backlog, long idletimeout, boolean useSSL,
            String certificatepath) throws IOException, KeyStoreException,
            NoSuchAlgorithmException, CertificateException,
            UnrecoverableKeyException, KeyManagementException,
            FileNotFoundException, SQLException
    {
        this.name = name;
        this.ina = InetAddress.getByName(ipaddress);
        this.port = port;
        this.event_q = event_q;
        this.backlog = backlog;
        this.idletimeout = idletimeout;

        if (useSSL)
        {
//            SSLContext sslcntxt = MPSSLContext.getETPSSSLContext(
//                    certificatepath, "JKS", "SSLv3");
//            System.out.println(sslcntxt.getProtocol() + " / " + sslcntxt.
//                    getProvider().getInfo() + " / ");
//            tcpserver = new Server(port, new MsgHandler(), sslcntxt, useSSL, 0,
//                    backlog);
        } else
        {
            tcpserver = new Server(port, new MsgHandler(), backlog);
        }

        if (idletimeout > 0)
        {
            tcpserver.setIdleTimeoutMillis(idletimeout);
        }

        tcpserver.setFlushmode(IConnection.FlushMode.ASYNC);
        
        ConnectionUtils.start(tcpserver);
        try
        {
            ConnectionUtils.registerMBean(tcpserver);
        } catch (JMException ex)
        {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }

    /**
     * Start the TCPServer instance
     *
     * @throws UnknownHostException
     * @throws IOException
     */
    public void startprocess() throws UnknownHostException, IOException
    {
    }

    /**
     * Get the name of TCPServer which was set during the initialization.
     *
     * @return The name of the TCPServer.
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     */
    public void close()
    {
        tcpserver.close();
    }

    /**
     * This method will send the given data to the remote entity.
     *
     * @param data The data which is to be sent to the remote entity.
     * @throws IOException
     */
    public void senddata(byte[] data) throws IOException
    {
        inbcx.write(data);
    }

    /**
     * This method will send the given data to the remote entity, but will
     * prepend a 2 byte length header. The length will be exclusive of the
     * tcp/ip length header.
     *
     * @param data The data which is to be sent to the remote entity.
     * @throws IOException
     */
    public void senddatawith2bytehdr(byte[] data) throws IOException
    {
        byte[] newdata = new byte[data.length + 2];
        byte[] len_byte;
        int data_len = data.length;

        len_byte = Translate.getData(Translate.fromHexToBin(Utility.resize(Integer.
                toHexString(data_len), 4, "0", false)));

        System.arraycopy(len_byte, 0, newdata, 0, len_byte.length);
        System.arraycopy(data, 0, newdata, 2, data.length);
        
        senddatawithdl(Translate.getString(newdata));
    }

    /**
     * This method will send the given data to the remote entity, but will
     * prepend a 2 byte length header. The length will be exclusive of the
     * tcp/ip length header.
     *
     * @param data The data which is to be sent to the remote entity.
     * @throws IOException
     */
    public void senddatawithdl(String data) throws IOException
    {
        data = data + "\r\n";

        senddata(Translate.getData(data));
    }

    /**
     * This the handler class which will handle the different events raised by
     * the TCPServer.
     */
    private class MsgHandler implements IConnectHandler, IDataHandler,
            IDisconnectHandler, IConnectionTimeoutHandler, IIdleTimeoutHandler
    {

        /**
         * This event will be triggered which a connect happens with the remote
         * entity.
         *
         * @param inbc NonBlockingConnection object
         * @return Will return <code>true</code> to maintain the connection and
         * <code>false</code> will be returned to terminate the connection.
         * @throws IOException Will be thrown in case of an input / output error
         * condition
         * @throws BufferUnderflowException Will be thrown in case of a Buffer
         * under flow occurs
         * @throws MaxReadSizeExceededException Will be thrown in case data
         * exceeds the maximum size set
         */
        @Override
        public boolean onConnect(INonBlockingConnection inbc) throws IOException,
                BufferUnderflowException, MaxReadSizeExceededException
        {
            event_q.add(new ConnectEvent(inbc, getName()));
            inbcx = inbc;

            return true;
        }

        /**
         * This event will be triggered which a data is received from the remote
         * entity.
         *
         * @param inbc NonBlocking Connection object
         * @return Will return <code>true</code> to maintain the connection and
         * <code>false</code> will be returned to terminate the connection.
         * @throws IOException Will be thrown in case of an input / output error
         * condition
         * @throws BufferUnderflowException Will be thrown in case of a Buffer
         * under flow occurs
         * @throws ClosedChannelException Will be thrown when data sending is
         * tried and the socket / channel is closed
         * @throws MaxReadSizeExceededException Will be thrown in case data
         * exceeds the maximum size set
         */
        @Override
        public boolean onData(INonBlockingConnection inbc) throws IOException,
                BufferUnderflowException, ClosedChannelException,
                MaxReadSizeExceededException
        {
            byte[] parseddata;

            parseddata = inbc.readBytesByDelimiter("\r\n");

            event_q.add(new DataEvent(parseddata, inbc, getName()));
            inbcx = inbc;

            return true;
        }

        /**
         * This event will be triggered which a disconnect happens with the
         * remote entity.
         *
         * @param inbc NonBlocking Connection object
         * @return Will return <code>true</code> to maintain the connection and
         * <code>false</code> will be returned to terminate the connection.
         * @throws IOException Will be thrown in case of an input / output error
         * condition
         */
        @Override
        public boolean onDisconnect(INonBlockingConnection inbc) throws
                IOException
        {
            event_q.add(new DisconnectEvent(inbc, getName()));
            inbcx = inbc;

            return false;
        }

        /**
         * This event will be triggered which a connection timeout is occurred.
         *
         * @param inbc NonBlocking Connection object
         * @return Will return <code>true</code> to maintain the connection and
         * <code>false</code> will be returned to terminate the connection.
         * @throws IOException Will be thrown in case of an input / output error
         * condition
         */
        @Override
        public boolean onConnectionTimeout(INonBlockingConnection inbc) throws
                IOException
        {
            event_q.add(new ConnectionTimeout(inbc, getName()));
            inbcx = inbc;

            return true;
        }

        /**
         * This event will be triggered which a idle timeout is occurred.
         *
         * @param inbc NonBlocking Connection object
         * @return Will return <code>true</code> to maintain the connection and
         * <code>false</code> will be returned to terminate the connection.
         * @throws IOException Will be thrown in case of an input / output error
         * condition
         */
        @Override
        public boolean onIdleTimeout(INonBlockingConnection inbc) throws
                IOException
        {
            event_q.add(new IdleTimeout(inbc, getName()));
            inbcx = inbc;

            return true;
        }
    }

    /**
     * ConnectEvent will be placed in the event queue when a connection event
     * occurs. With this, the parent class can identify the event type and the
     * data objects associated with it.
     */
    public static class ConnectEvent
    {

        INonBlockingConnection inbc;
        private String name;

        /**
         * The ConnectEvent constructor.
         *
         * @param inbc NonBlockingConnection object
         * @param name Name of the TCPServer for which the connection event has
         * occurred.
         */
        public ConnectEvent(INonBlockingConnection inbc, String name)
        {
            this.inbc = inbc;
            this.name = name;
        }

        /**
         *
         * @return The name of the TCPServer
         */
        public String getName()
        {
            return name;
        }
    }

    /**
     * DisConnectEvent will be placed in the event queue when a disconnection
     * event occurs. With this, the parent class can identify the event type and
     * the data objects associated with it.
     */
    public static class DisconnectEvent
    {

        INonBlockingConnection inbc;
        private String name;

        /**
         * The DisConnectEvent constructor.
         *
         * @param inbc NonBlockingConnection object
         * @param name Name of the TCPServer for which the connection event has
         * occurred.
         */
        public DisconnectEvent(INonBlockingConnection inbc, String name)
        {
            this.inbc = inbc;
            this.name = name;
        }

        /**
         *
         * @return The name of the TCPServer
         */
        public String getName()
        {
            return name;
        }
    }

    /**
     * DataEvent will be placed in the event queue when data is received on the
     * socket from the remote entity. With this, the parent class can identify
     * the event type and the data objects associated with it.
     */
    public static class DataEvent
    {

        public byte[] data;
        public INonBlockingConnection inbc;
        private String name;

        /**
         * The DataEvent constructor.
         *
         * @param data The data received from the remote entity
         * @param inbc NonBlockingConnection object
         * @param name The name of the TCPServer
         */
        public DataEvent(byte[] data, INonBlockingConnection inbc, String name)
        {
            this.data = data;
            this.inbc = inbc;
            this.name = name;
        }

        /**
         *
         * @return the data received from the remote in byte array.
         */
        public String getName()
        {
            return name;
        }
    }

    /**
     * ConnectionTimeout event will be placed in the event queue when the client
     * tries to connect to the remote entity and is not able to connect with the
     * specified time. With this, the parent class can identify the event type
     * and the data objects associated with it.
     */
    public static class ConnectionTimeout
    {

        INonBlockingConnection inbc;
        String name;

        /**
         * The ConnectionTimeout constructor.
         *
         * @param inbc NonBlockingConnection object
         * @param name The name of the TCPServer
         */
        public ConnectionTimeout(INonBlockingConnection inbc, String name)
        {
            this.inbc = inbc;
            this.name = name;
        }

        /**
         *
         * @return The name of the TCPServer
         */
        public String getName()
        {
            return name;
        }
    }

    /**
     * IdleTimeout event will be placed in the event queue when there is no data
     * sent between the client the remote entity. With this, the parent class
     * can identify the event type and the data objects associated with it.
     *
     */
    public static class IdleTimeout
    {

        INonBlockingConnection inbc;
        String name;

        /**
         * The IdleTimeout Constructor.
         *
         * @param inbc NonBlockingConnection object
         * @param name The name of the TCPServer
         */
        public IdleTimeout(INonBlockingConnection inbc, String name)
        {
            this.inbc = inbc;
            this.name = name;
        }

        /**
         *
         * @return The name of the TCPServer
         */
        public String getName()
        {
            return name;
        }
    }
}
