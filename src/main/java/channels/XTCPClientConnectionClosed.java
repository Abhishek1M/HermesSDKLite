package channels;

/**
 * This exception will be thrown when the TCP Client / Server detects that the
 * connection is closed and data cannot be transmitted. With this exception the
 * caller class can take necessary action.
 */
public class XTCPClientConnectionClosed extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2669950925720272932L;
	String message;

    /**
     * XTCPClientConnectionClosed constructor
     *
     * @param message The message which has to be thrown
     * @param cause The Throwable clause
     */
    public XTCPClientConnectionClosed(String message, Throwable cause)
    {
        super(message, cause);
    }
}
