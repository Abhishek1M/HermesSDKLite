package messagebus;

import java.util.HashMap;
import java.util.Iterator;

import utils.Utility;

/**
 *
 * @author Abhy
 */
public class KeyValuePair
{

    HashMap<String, String> input;

    /**
     *
     */
    public KeyValuePair()
    {
        input = new HashMap<String, String>();
    }

    /**
     *
     * @param key
     * @param value
     */
    public void put(String key, String value)
    {
        input.put(key, value);
    }

    /**
     *
     * @param input
     * @return
     */
    public String formMsg()
    {
        Iterator<String> itr = input.keySet().iterator();
        String key;
        String value;
        StringBuilder sb = new StringBuilder("");

        while (itr.hasNext())
        {
            key = itr.next();
            value = input.get(key);

            sb.append(Utility.resize(Integer.toString(key.length()), 3, "0", false));
            sb.append(key);
            sb.append(Utility.resize(Integer.toString(value.length()), 5, "0", false));
            sb.append(value);
        }

        return sb.toString();
    }

    /**
     *
     * @param data
     * @return
     */
    public HashMap<String, String> formHM(String data)
    {
        HashMap<String, String> hm_output = new HashMap<String, String>();

        int key_len;
        int value_len;
        String key;
        String value;

        int startpos = 0;
        int len;

        try
        {
            while (startpos < data.length())
            {
                len = 3;

                key_len = Integer.parseInt(data.substring(startpos, startpos + len));
                startpos = startpos + len;
                len = key_len;
                key = data.substring(startpos, startpos + len);
                startpos = startpos + len;
                len = 5;
                value_len = Integer.parseInt(
                        data.substring(startpos, startpos + len));
                startpos = startpos + len;
                len = value_len;
                value = data.substring(startpos, startpos + len);
                startpos = startpos + len;

                hm_output.put(key, value);
                //input.put(key, value);
            }
        } catch (Exception ex)
        {
            Utility.debugPrint(Utility.getFormattedDateTime(), "Error Parsing Data : " + 
                    data + "\n" + ex.getMessage());
            
            return new HashMap<>();
        }

        return hm_output;
    }

    /**
     *
     * @return
     */
    public HashMap<String, String> getKVPHM()
    {
        return input;
    }

    /**
     *
     * @param args
     */
//    public static void main(String[] args)
//    {
//        KeyValuePair td = new KeyValuePair();
//        
//        td.formHM("004CVV200000013_DI_DEVICE_ID000101234567890004NAME00018SOME VALUE / VALUE");
//        
//        System.out.println(td.formMsg());
//
////        td.put("CVV2", "");
////        td.put("_DI_DEVICE_ID", "1234567890");
////        td.put("NAME", "SOME VALUE / VALUE");
////
////        System.out.println("Output : " + td.formMsg());
////        td.formHM(td.formMsg());
////
////        System.out.println(td.getKVPHM().toString());
//    }
}
