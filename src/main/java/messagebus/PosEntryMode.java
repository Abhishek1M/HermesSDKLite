package messagebus;

/**
 *
 * @author Abhy
 */
public class PosEntryMode
{
    public static class PinEntryCapability
    {

        public static final String _0_UNSPECIFIED = "0";
        public static final String _1_YES = "1";
        public static final String _2_NO = "2";

        PinEntryCapability()
        {
        }
    }

    public static class PanEntryMode
    {

        public static boolean isTrack2Unmodified(String pan_entry_mode)
        {
            return pan_entry_mode.equals("05") || pan_entry_mode.equals("07") || pan_entry_mode.equals("90") || pan_entry_mode.equals("91");
        }

        public static final String _00_UNSPECIFIED = "00";
        public static final String _01_MANUAL = "01";
        public static final String _02_MAGNETIC_STRIPE_CVV_UNRELIABLE = "02";
        public static final String _03_BAR_CODE = "03";
        public static final String _04_OCR = "04";
        public static final String _05_INTEGRATED_CIRCUIT_CARD = "05";
        public static final String _07_CONTACTLESS_INTEGRATED_CIRCUIT_CARD = "07";
        public static final String _80_FALLBACK = "80";
        public static final String _90_MAGNETIC_STRIPE = "90";
        public static final String _91_CONTACTLESS_MAGNETIC_STRIPE = "91";
        public static final String _95_INTEGRATED_CIRCUIT_CARD_CVV_UNRELIABLE = "95";
        public static final String _99_SAME_AS_ORIGINAL_TRANSACTION = "99";

        PanEntryMode()
        {
        }
    }


    private PosEntryMode()
    {
        data = null;
    }

    PosEntryMode(byte buf[])
        throws Exception
    {
        data = null;
        if(buf.length != 3)
        {
            throw new Exception("Invalid Length");
        } else
        {
            data = buf;
            return;
        }
    }

    public PosEntryMode(String str)
        throws Exception
    {
        data = null;
        data = new byte[3];
        int len = str.length();
        str.getBytes(0, len, data, 0);
        if(len != 3)
            throw new Exception("Invalid Length");
        else
            return;
    }

    public PosEntryMode(String pan_entry_mode, String pin_entry_capability)
    {
        data = null;
        data = new byte[3];
        pan_entry_mode.getBytes(0, 2, data, 0);
        pin_entry_capability.getBytes(0, 1, data, 2);
    }

    public final String toString()
    {
        return new String(data, 0, 0, 3);
    }

    public final String getPanEntryMode()
    {
        return new String(data, 0, 0, 2);
    }

    public final void putPanEntryMode(String value)
    {
        value.getBytes(0, 2, data, 0);
    }

    public final String getPinEntryCapability()
    {
        return new String(data, 0, 2, 1);
    }

    public final void putPinEntryCapability(String value)
    {
        value.getBytes(0, 1, data, 2);
    }

    final byte[] getData()
    {
        return data;
    }

//    public static void main(String args[])
//    {
//        try
//        {
//            System.out.println("--------- PosEntryMode ---------");
//            PosEntryMode temp = new PosEntryMode("123");
//            String test1 = temp.toString();
//            String test2 = (new PosEntryMode("12", "3")).toString();
//            String test3 = (new StringBuilder()).append(temp.getPanEntryMode()).append(temp.getPinEntryCapability()).toString();
//            temp.putPanEntryMode("01");
//            temp.putPinEntryCapability("2");
//            String test4 = temp.toString();
//            System.out.println(test1);
//            System.out.println(test2);
//            System.out.println(test3);
//            System.out.println(test4);
//            if(!test1.equals(test2) || !test1.equals(test3) || !test4.equals("012"))
//                throw new RuntimeException();
//            if(args != null)
//            {
//                try
//                {
//                    System.in.read();
//                }
//                catch(Throwable e)
//                {
//                    System.out.println(e.toString());
//                }
//                try
//                {
//                    System.in.read();
//                }
//                catch(Throwable e)
//                {
//                    System.out.println(e.toString());
//                }
//            }
//        }
//        catch(Exception e)
//        {
//            System.out.println(e.toString());
//            throw new RuntimeException();
//        }
//    }

    private byte data[];    
}
