package messagebus;

/**
 *
 * @author Abhy
 */
public class PosGeographicData
{

    private PosGeographicData()
    {
        data = null;
    }

    PosGeographicData(byte buf[])
    {
        data = null;
        data = buf;
    }

    public PosGeographicData(String str)
    {
        data = null;
        data = new byte[17];
        int len = str.length();
        str.getBytes(0, len, data, 0);
    }

    public PosGeographicData(String state_code, String county_code,
            String postal_service_code, String country_code)
    {
        data = null;
        data = new byte[17];
        putStateCode(state_code);
        putCountyCode(county_code);
        putPostalServiceCode(postal_service_code);
        putCountryCode(country_code);
    }

    public final String toString()
    {
        return new String(data, 0, 0, 17);
    }

    public final String getCountryCode()
    {
        if (data[14] == 32)
        {
            return null;
        } else
        {
            return new String(data, 0, 14, 3);
        }
    }

    public final void putCountryCode(String value)
    {
        if (value == null)
        {
            clearCountryCode();
        } else
        {
            value.getBytes(0, 3, data, 14);
        }
    }

    public final void clearCountryCode()
    {
        for (int i = 14; i < 17; i++)
        {
            data[i] = 32;
        }

    }

    public final String getCountyCode()
    {
        if (data[2] == 32)
        {
            return null;
        } else
        {
            return new String(data, 0, 2, 3);
        }
    }

    public final void putCountyCode(String value)
    {
        if (value == null)
        {
            clearCountyCode();
        } else
        {
            value.getBytes(0, 3, data, 2);
        }
    }

    public final void clearCountyCode()
    {
        for (int i = 2; i < 5; i++)
        {
            data[i] = 32;
        }

    }

    public final String getPostalServiceCode()
    {
        if (data[5] == 32)
        {
            return null;
        } else
        {
            return new String(data, 0, 5, 9);
        }
    }

    public final void putPostalServiceCode(String value)
    {
        if (value == null)
        {
            clearPostalServiceCode();
        } else
        {
            value.getBytes(0, 9, data, 5);
        }
    }

    public final void clearPostalServiceCode()
    {
        for (int i = 5; i < 14; i++)
        {
            data[i] = 32;
        }

    }

    public final String getStateCode()
    {
        if (data[0] == 32)
        {
            return null;
        } else
        {
            return new String(data, 0, 0, 2);
        }
    }

    public final void putStateCode(String value)
    {
        if (value == null)
        {
            clearStateCode();
        } else
        {
            value.getBytes(0, 2, data, 0);
        }
    }

    public final void clearStateCode()
    {
        for (int i = 0; i < 2; i++)
        {
            data[i] = 32;
        }

    }

    final byte[] getData()
    {
        return data;
    }

//    public static void main(String args[])
//    {
//        System.out.println("--------- PosGeographicData ---------");
//        PosGeographicData temp = new PosGeographicData("12910123456789840");
//        String test1 = temp.toString();
//        String test2 = (new PosGeographicData("12", "910", "123456789", "840")).
//                toString();
//        String test3 = (new StringBuilder()).append(temp.getStateCode()).append(temp.
//                getCountyCode()).append(temp.getPostalServiceCode()).append(temp.
//                getCountryCode()).toString();
//        temp.putStateCode("34");
//        temp.putCountyCode("840");
//        temp.putPostalServiceCode("abcdefghi");
//        temp.putCountryCode("910");
//        String test4 = temp.toString();
//        String test5 = (new PosGeographicData(null, null, null, null)).toString();
//        temp.clearStateCode();
//        temp.clearCountyCode();
//        temp.clearPostalServiceCode();
//        temp.clearCountryCode();
//        String test6 = temp.toString();
//        System.out.println(test1);
//        System.out.println(test2);
//        System.out.println(test3);
//        System.out.println(test4);
//        if (!test1.equals(test2) || !test1.equals(test3) || !test4.equals(
//                "34840abcdefghi910") || !test5.equals("                 ")
//                || !test6.equals("                 "))
//        {
//            throw new RuntimeException();
//        }
//        if (args != null)
//        {
//            try
//            {
//                System.in.read();
//            } catch (Throwable e)
//            {
//                System.out.println(e.toString());
//            }
//            try
//            {
//                System.in.read();
//            } catch (Throwable e)
//            {
//                System.out.println(e.toString());
//            }
//        }
//    }
    private byte data[];
}
