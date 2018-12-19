package channels;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xsocket.DataConverter;
import org.xsocket.MaxReadSizeExceededException;
import org.xsocket.connection.*;

import utils.AppQueue;
import utils.Translate;
import utils.Utility;

/**
 * TCPClient initiates the connection to the remote server using the TCP/IP
 * protocol.
 *
 * @author Abhishek M
 */
public class TCPClient
{

    AppQueue event_q;
    InetAddress ina;
    int port;
    int backlog;
    long idletimeout;
    int connectiontimeout;
    INonBlockingConnection inbcx;
    String name;

    /**
     * Start the TCPClient with an existing NonBlockingConnection
     *
     * @param inbcx NonBlockingConnection object
     */
    public TCPClient(INonBlockingConnection inbcx)
    {
        this.inbcx = inbcx;
    }

    /**
     * The constructor class which takes in the parameter to start and connect
     * to the remote server.
     *
     * @param name The identification name of the TCPClient connection.
     * @param event_q The event queue wherein all the events of the TCPClient
     * will be placed
     * @param remoteaddress The remote IP address of the server
     * @param port The remote port of the server
     * @param backlog Not used. Can be supplied as zero.
     * @param idletimeout The time after which the connection's state will be
     * marked as idle. Note this will not incur in a disconnect.
     * @param connectiontimeout The time required for making a connection with
     * the remote server.
     * @throws IOException IOException will be thrown in case of any error
     */
    public TCPClient(String name, AppQueue event_q, String remoteaddress, int port,
            int backlog, long idletimeout, int connectiontimeout) throws
            IOException
    {
        this.name = name;
        this.event_q = event_q;
        this.ina = InetAddress.getByName(remoteaddress);
        this.port = port;
        this.backlog = backlog;
        this.idletimeout = idletimeout;
        this.connectiontimeout = connectiontimeout;

        inbcx = new NonBlockingConnection(ina, port, new ClientMessageHandler());

        /*
         * if (idletimeout > 0) { inbcx.setIdleTimeoutMillis(idletimeout); }
         *
         */

        /*
         * else { inbcx.setIdleTimeoutMillis(30 * 60 * 1000); }
         *
         */

        /*
         * if (connectiontimeout > 0) {
         * inbcx.setConnectionTimeoutMillis(connectiontimeout); }
         *
         */
    }

    /**
     * Starts the connection.
     *
     * @throws IOException
     */
    public void startprocess() throws IOException
    {
    }

    /**
     * Sets the NonBlockingConnection
     *
     * @param new_inbc NonBlockingConnection object
     */
    public void setINBC(INonBlockingConnection new_inbc)
    {
        inbcx = new_inbc;
    }

    /**
     *
     * @return The name of the TCPClient
     */
    public String getName()
    {
        return name;
    }

    /**
     * Resets the IdleTime
     */
    public void resetIdleTime()
    {
        if (idletimeout > 0)
        {
            inbcx.setIdleTimeoutMillis(idletimeout);
        } else
        {
            inbcx.setIdleTimeoutMillis(30 * 60 * 1000);
        }
    }

    /**
     * Resets the Connection Timeout
     */
    public void resetConnectionTO()
    {
        inbcx.setConnectionTimeoutMillis(connectiontimeout);
    }
    
    /**
     * 
     * @throws IOException 
     */
    public void closeConnection() throws IOException
    {
        inbcx.close();
    }

    /**
     * This method will send the given data to the remote entity.
     *
     * @param data The data which is to be sent to the remote entity
     * @throws IOException IOException will be thrown in case of an error
     * @throws XTCPClientConnectionClosed This exception will be raised when the
     * channel is closed and data transmission is tried
     */
    public void senddata(byte[] data) throws IOException,
            XTCPClientConnectionClosed
    {
        if (inbcx == null)
        {
            throw new XTCPClientConnectionClosed(
                    "TCPClient Connection Closed. NPE",
                    new Throwable("NPE > Connection closed for : " + name));
        }

        if (inbcx.isOpen())
        {
            inbcx.write(data);
        } else
        {
            System.out.println("Connection in closed mode...");
            throw new XTCPClientConnectionClosed(
                    "TCPClient Connection Closed. Cannot send data.",
                    new Throwable("Connection closed for : " + name));
        }
    }

    /**
     * This method is used to reconnect to the remote entity after a
     * disconnection.
     *
     * @param timer
     * @return boolean
     */
    public boolean reconnect(long timer)
    {
        try
        {
            Thread.sleep(timer);
        } catch (InterruptedException ex)
        {
            Logger.getLogger(TCPClient.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        try
        {
            inbcx = new NonBlockingConnection(ina, port,
                    new ClientMessageHandler());

            return true;
        } catch (IOException ex)
        {
            Logger.getLogger(TCPClient.class.getName()).
                    log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * This method will send the given data to the remote entity, but will
     * prepend a 2 byte length header. The length will be exclusive of the
     * tcp/ip length header.
     *
     * @param data The data which is to be sent to the remote entity.
     * @throws IOException IOException will be thrown in case of an error
     * @throws XTCPClientConnectionClosed This exception will be raised when the
     * channel is closed and data transmission is tried
     */
    public void senddatawith2bytehdr(byte[] data) throws IOException,
            XTCPClientConnectionClosed
    {
        byte[] newdata = new byte[data.length + 2];
        byte[] len_byte;
        int data_len = data.length;

        len_byte = Translate.getData(Translate.fromHexToBin(Utility.resize(Integer.
                toHexString(data_len), 4, "0", false)));

        System.arraycopy(len_byte, 0, newdata, 0, len_byte.length);
        System.arraycopy(data, 0, newdata, 2, data.length);

        //System.out.println(FormatData.hexdump(newdata));
        senddata(newdata);
    }

    /**
     * Sends a 2 byte ("0000") to the remote entity for the TCP keep alive.
     *
     * @throws IOException IOException will be thrown in case of an error
     * @throws XTCPClientConnectionClosed This exception will be raised when the
     * channel is closed and data transmission is tried
     */
    public void sendKeepAliveNullByte() throws IOException,
            XTCPClientConnectionClosed
    {
        //byte[] data = Translate.getData(Translate.fromHexToBin("0000"));
        //senddata(data);
    }

    /**
     * This the handler class which will handle the different events raised by
     * the TCPClient.
     */
    public class ClientMessageHandler implements IConnectHandler,
            IDisconnectHandler, IDataHandler, IConnectionTimeoutHandler,
            IIdleTimeoutHandler, IConnectExceptionHandler
    {

        /**
         * This event will be triggered which a connect happens with the remote
         * entity.
         *
         * @param inbc NonBlocking Connection object
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
            event_q.add(new ConnectEvent(inbc, name));
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
            event_q.add(new DisconnectEvent(inbc, name));
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
        public boolean onData(INonBlockingConnection inbc) throws IOException
        {
            try
            {
                byte[] parseddata;

                int utflen = Utility.
                        validateSufficientDatasizeByShortLengthField(inbc);
                ByteBuffer[] bbuf = inbc.readByteBufferByLength(utflen);
                parseddata = DataConverter.toBytes(bbuf);

                event_q.add(new DataEvent(parseddata, inbc, name));
                inbcx = inbc;
            } catch (BufferUnderflowException bue)
            {
                Utility.debugPrint(
                        Utility.getFormattedDateTime() + " - TCPClient ... onData(...) BufferUnderflowException",
                        bue.getMessage());
                inbc.resetToReadMark();

                return true;
            }

            return true;
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
            event_q.add(new ConnectionTimeout(inbc, name));
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
            event_q.add(new IdleTimeout(inbc, name, true));
            inbcx = inbc;

            return true;
        }

        /**
         * This event is triggered when a connection exception occurs.
         *
         * @param inbc NonBlockingConnection object
         * @param ioe IOException object
         * @return Will return <code>true</code> to maintain the connection and
         * <code>false</code> will be returned to terminate the connection.
         * @throws IOException Will be thrown in case of an input / output error
         * condition
         */
        @Override
        public boolean onConnectException(INonBlockingConnection inbc,
                IOException ioe) throws IOException
        {
            event_q.add(new ConnectException(inbc, name));
            inbcx = inbc;

            return false;
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
        String name;

        /**
         * The ConnectEvent constructor.
         *
         * @param inbc NonBlockingConnection object
         * @param name Name of the TCPClient for which the connection event has
         * occurred.
         */
        public ConnectEvent(INonBlockingConnection inbc, String name)
        {
            this.inbc = inbc;
            this.name = name;
        }

        /**
         *
         * @return The name of the TCPClient
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
        String name;

        /**
         * The DisConnectEvent constructor.
         *
         * @param inbc NonBlockingConnection object
         * @param name Name of the TCPClient for which the connection event has
         * occurred.
         */
        public DisconnectEvent(INonBlockingConnection inbc, String name)
        {
            this.inbc = inbc;
            this.name = name;
        }

        /**
         *
         * @return The name of the TCPClient
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

        byte[] data;
        INonBlockingConnection inbc;
        String name;

        /**
         * The DataEvent constructor.
         *
         * @param data The data received from the remote entity
         * @param inbc NonBlockingConnection object
         * @param name The name of the TCPClient
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
        public byte[] getData()
        {
            return data;
        }

        /**
         *
         * @return The name of the TCPClient
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
         * @param name The name of the TCPClient
         */
        public ConnectionTimeout(INonBlockingConnection inbc, String name)
        {
            this.inbc = inbc;
            this.name = name;
        }

        /**
         *
         * @return The name of the TCPClient
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
    public class IdleTimeout
    {

        INonBlockingConnection inbc;
        String name;

        /**
         * The IdleTimeout Constructor.
         *
         * @param inbc NonBlockingConnection object
         * @param name The name of the TCPClient
         * @param performReset if the idletimeout parameter should be resetted
         */
        public IdleTimeout(INonBlockingConnection inbc, String name,
                boolean performReset)
        {
            this.inbc = inbc;
            this.name = name;

            if (performReset)
            {
                inbc.setIdleTimeoutMillis(idletimeout);
            }
        }

        /**
         *
         * @return The name of the TCPClient
         */
        public String getName()
        {
            return name;
        }
    }

    /**
     * ConnectException event will be placed in the event queue when there is a
     * connect exception (connection failed, timeout exception, ...) between the
     * client the remote entity. With this, the parent class can identify the
     * event type and the data objects associated with it.
     */
    public static class ConnectException
    {

        INonBlockingConnection inbc;
        String name;

        /**
         * The ConnectException constructor
         *
         * @param inbc NonBlockingConnection object
         * @param name The name of the TCPClient
         */
        public ConnectException(INonBlockingConnection inbc, String name)
        {
            this.inbc = inbc;
            this.name = name;
        }

        /**
         *
         * @return The name of the TCPClient
         */
        public String getName()
        {
            return name;
        }
    }
}