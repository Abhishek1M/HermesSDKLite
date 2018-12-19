package utils;

import java.util.ArrayList;

/**
 *
 * @author Abhy
 */
public class TMAction
{

    ArrayList<String> msg_to_acq_node;
    ArrayList<String> msg_to_iss_node;

    /**
     *
     */
    public TMAction()
    {
        msg_to_acq_node = new ArrayList<String>(2);
        msg_to_iss_node = new ArrayList<String>(2);
    }

    /**
     *
     * @param str_msg_to_acq_node
     * @param str_msg_to_iss_node
     */
    public TMAction(String str_msg_to_acq_node,
            String str_msg_to_iss_node)
    {
        msg_to_acq_node = new ArrayList<String>(2);
        msg_to_iss_node = new ArrayList<String>(2);

        if (str_msg_to_acq_node != null)
        {
            msg_to_acq_node.add(str_msg_to_acq_node);
        }

        if (str_msg_to_iss_node != null)
        {
            msg_to_iss_node.add(str_msg_to_iss_node);
        }
    }

    /**
     *
     * @param str_msg_to_acq_node
     */
    public void putDataToAcqNode(String str_msg_to_acq_node)
    {
        if (str_msg_to_acq_node != null)
        {
            msg_to_acq_node.add(str_msg_to_acq_node);
        }
    }

    /**
     *
     * @param str_msg_to_iss_node
     */
    public void putDataToIssNode(String str_msg_to_iss_node)
    {
        if (str_msg_to_iss_node != null)
        {
            msg_to_iss_node.add(str_msg_to_iss_node);
        }
    }

    /**
     * 
     * @return 
     */
    public String getDataToAcqNode()
    {
        if(msg_to_acq_node.size()>0)
        {
            return msg_to_acq_node.remove(0);
        }else
        {
            return null;
        }
    }

    /**
     * 
     * @return 
     */
    public String getDataToIssNode()
    {
        if(msg_to_iss_node.size()>0)
        {
            return msg_to_iss_node.remove(0);
        }else
        {
            return null;
        }
    }
}
