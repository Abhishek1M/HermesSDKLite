package message.iso8583;

/**
 *
 * @author Abhy
 */
public class XFieldError extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String methodname;
    private String parameter;
    private String value;

    /**
     *
     * @param methodname
     * @param parameter
     * @param value
     * @param message
     */
    public XFieldError(String methodname, String parameter, String value,
            String message)
    {
        super(message);
        this.methodname = methodname;
        this.parameter = parameter;
        this.value = value;
    }

    /**
     *
     * @return
     */
    public String getErrorMsg()
    {
        String errormsg = "Error in Method [" + methodname + "] / Parameter ["
                + parameter + "] / Value [" + value + "]";
        
        return errormsg;
    }
}
