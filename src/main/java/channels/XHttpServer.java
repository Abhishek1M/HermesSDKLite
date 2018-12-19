package channels;

import utils.AppQueue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xlightweb.*;
import org.xlightweb.server.HttpServer;
import org.xsocket.connection.ConnectionUtils;
import org.xsocket.connection.IServer;

/**
 * XHttpServer starts the port and accepts the incoming requests from the remote
 * entity using the HTTP protocol.
 *
 * @author Abhishek M
 */
public class XHttpServer
{

    String name;
    AppQueue event_q;
    InetAddress ina;
    int port;
    int backlog;
    long idletimeout;
    IServer xhttpserver;

    /**
     * The constructor class which takes in the parameter to start the requested
     * port and accept connections from the remote entity.
     *
     * @param name The name of the HTTP server
     * @param localip The IP on which the TCPServer should listen to.
     * @param port The port on which the TCPServer should listen to.
     * @param event_q The event queue wherein all the events of the TCPServer
     * will be placed
     * @param backlog The maximum number of connections which can be accepted on
     * the configured IP / Port.
     * @param idletimeout The time after which the connection's state will be
     * marked as idle. Note this will not incur in a disconnect.
     * @throws IOException
     */
    public XHttpServer(String name, String localip, int port, AppQueue event_q,
            int backlog, long idletimeout) throws IOException
    {
        this.name = name;
        this.ina = InetAddress.getByName(localip);
        this.port = port;
        this.event_q = event_q;
        this.backlog = backlog;
        this.idletimeout = idletimeout;

        xhttpserver = new HttpServer(ina, port, new HttpMsgHandler());
        
        if (idletimeout > 0)
        {
            xhttpserver.setIdleTimeoutMillis(idletimeout);
        }

        try
        {
            ConnectionUtils.start(xhttpserver);
        } catch (SocketTimeoutException ex)
        {
            Logger.getLogger(XHttpServer.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 
     * @param name
     * @param localip
     * @param port
     * @param event_q
     * @param backlog
     * @param idletimeout
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
    public XHttpServer(String name, String localip, int port, AppQueue event_q,
            int backlog, long idletimeout, boolean useSSL,
            String certificatepath) throws IOException, KeyStoreException,
            NoSuchAlgorithmException, CertificateException,
            UnrecoverableKeyException, KeyManagementException,
            FileNotFoundException, SQLException
    {
        this.name = name;
        this.ina = InetAddress.getByName(localip);
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
//            xhttpserver = new HttpServer(ina, port, new HttpMsgHandler(),
//                    sslcntxt, useSSL);
        } else
        {
            xhttpserver = new HttpServer(ina, port, new HttpMsgHandler());
        }

        if (idletimeout > 0)
        {
            xhttpserver.setIdleTimeoutMillis(idletimeout);
        }

        try
        {
            ConnectionUtils.start(xhttpserver);
        } catch (SocketTimeoutException ex)
        {
            Logger.getLogger(XHttpServer.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Start the XHttpServer instance
     */
    public void startprocess()
    {
    }

    /**
     * This the handler class which will handle the different events raised by
     * the XHttpServer.
     */
    public class HttpMsgHandler implements IHttpRequestHandler,
            IHttpConnectionHandler, IHttpDisconnectHandler
    {

        /**
         * This event will be triggered which a data is received from the remote
         * entity.
         *
         * @param ihe HttpExchange object
         * @throws IOException
         * @throws BadMessageException
         */
        @Override
        public void onRequest(IHttpExchange ihe) throws IOException,
                BadMessageException
        {
            IHttpRequest request = ihe.getRequest();
            int contentLen = request.getContentLength();

            if (contentLen > 0)
            {
                String data_str = new String(request.getBody().readBytesByLength(
                        contentLen));

                event_q.add(new DataEvent(data_str, ihe));
            } else
            {
                event_q.add(new DataEvent("", ihe));
            }
        }

        /**
         * This event will be triggered which a disconnect happens with the
         * remote entity.
         *
         * @param ihc HttpConnection object
         * @return Will return
         * <code>true</code> to maintain the connection and
         * <code>false</code> will be returned to terminate the connection.
         * @throws IOException
         */
        @Override
        public boolean onDisconnect(IHttpConnection ihc) throws IOException
        {
            event_q.add(new DisconnectEvent(ihc));
            return false;
        }
    }

    /**
     * DisConnectEvent will be placed in the event queue when a disconnection
     * event occurs. With this, the parent class can identify the event type and
     * the data objects associated with it.
     */
    public static class DisconnectEvent
    {

        IHttpConnection ihc;

        /**
         * The DisConnectEvent constructor
         *
         * @param ihc HttpConnection object
         */
        public DisconnectEvent(IHttpConnection ihc)
        {
            this.ihc = ihc;
        }
    }

    /**
     * DataEvent will be placed in the event queue when data is received on the
     * socket from the remote entity. With this, the parent class can identify
     * the event type and the data objects associated with it.
     */
    public static class DataEvent
    {

        public String data;
        public IHttpExchange ihe;

        /**
         * DataEvent constructor
         *
         * @param data The data received from the remote entity
         * @param ihe HttpExchange object
         */
        public DataEvent(String data, IHttpExchange ihe)
        {
            this.data = data;
            this.ihe = ihe;
        }
    }
}
