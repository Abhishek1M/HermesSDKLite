package message.iso8583;

import utils.Utility;

/**
 *
 * @author Abhy
 */
public class Field
{

    private DataFormatter df;
    private LengthFormatter lf;
    private String content;

    /**
     *
     * @param df
     * @param lf
     */
    public Field(DataFormatter df, LengthFormatter lf)
    {
        this.df = df;
        this.lf = lf;
    }

    /**
     *
     * @return
     */
    public String getContent()
    {
        return content;
    }

    /**
     *
     * @param content
     * @throws XFieldError
     */
    public void setContent(String content) throws XFieldError
    {
        if (content.length() > lf.getMaxlen())
        {
            throw new XFieldError("setContent", "content", content,
                    "Set Field Length greater than max length. Received Field Length : "
                    + content.length() + " ... Max Field Length : " + lf.
                    getMaxlen());
        } else
        {
            this.content = content;
        }
    }

    /**
     *
     * @return
     */
    public byte[] getFormattedContent()
    {
        if (getContent() == null)
        {
            return null;
        }

        byte[] byte_content;
        byte[] byte_lencontent;
        byte[] data;

        String data_content = getContent();

        byte_content = df.getFormattedContent(data_content);

        if (byte_content == null)
        {
            return null;
        }

        if (lf.getLenType() == LengthFormatter.LengthType._FIXED_LEN)
        {
            data = new byte[byte_content.length];
            System.arraycopy(byte_content, 0, data, 0, byte_content.length);
        } else
        {
            if (df.getDataType() == DataFormatter.DataType._BINARY)
            {
                byte_lencontent = lf.getFormattedLen(data_content.length());
            } else
            {
                byte_lencontent = lf.getFormattedLen(byte_content.length);
            }

            if (byte_lencontent == null)
            {
                return null;
            }

            data = new byte[byte_content.length + byte_lencontent.length];
            System.arraycopy(byte_lencontent, 0, data, 0, byte_lencontent.length);
            System.arraycopy(byte_content, 0, data, byte_lencontent.length,
                    byte_content.length);
        }

        return data;
    }

    /**
     *
     * @return
     */
    public int getFieldLength()
    {
        int l = Integer.toString(lf.getMaxlen()).length();

        return l;
    }

    /**
     *
     * @return
     */
    public int getFieldParseLength()
    {
        int l;

        if (lf.getLenType() == LengthFormatter.LengthType._FIXED_LEN)
        {
            l = Integer.toString(lf.getMaxlen()).length();
        } else
        {
            l = lf.getLenType();
        }

        if ((lf.getLenFormat() == LengthFormatter.LengthFormat._BINARY)
                || (lf.getLenFormat() == LengthFormatter.LengthFormat._BCD))
        {
            if ((l % 2) == 0)
            {
                return (l / 2);
            } else
            {
                return ((l + 1) / 2);
            }
        } else
        {
            return l;
        }
    }

    /**
     *
     * @return
     */
    public int getFieldDataLength()
    {
        return lf.getMaxlen();
    }

    /**
     *
     * @return
     */
    public int getFieldDataParseMsgLen()
    {
        if (df.getDataType() == DataFormatter.DataType._BINARY)
        {
            if (Utility.isOdd(lf.getMaxlen()))
            {
                return ((lf.getMaxlen() + 1) / 2);
            } else
            {
                return (lf.getMaxlen() / 2);
            }
        } else
        {
            return lf.getMaxlen();
        }
    }

    /**
     *
     * @return
     */
    public int getFieldLengthType()
    {
        return lf.getLenType();
    }

    /**
     *
     * @return
     */
    public int getFieldLengthFormat()
    {
        return lf.getLenFormat();
    }

    /**
     *
     * @return
     */
    public int getFieldDataType()
    {
        return df.getDataType();
    }

    /**
     *
     * @return
     */
    public DataFormatter getDf()
    {
        return df;
    }

    /**
     *
     * @return
     */
    public LengthFormatter getLf()
    {
        return lf;
    }

    /**
     *
     * @param args
     */
//    public static void main(String[] args)
//    {
//        byte[] data = Translate.getData(Translate.fromHexToBin("0135"));
//        Utility.printByteArray("Message", data, data.length);
//
//        /**
//         * Variable Field Definition
//         */
//        DataFormatter df2 = new DataFormatter(DataFormatter.DataType._BINARY,
//                "F",
//                true, true, 19);
//        LengthFormatter lf2 = new LengthFormatter(
//                LengthFormatter.LengthType._LLVAR,
//                LengthFormatter.LengthFormat._BINARY, false,
//                19);
//        Field f2 = new Field(df2, lf2);
//        try
//        {
//            f2.setContent("42162100000000271");
//            byte[] f2_data = f2.getFormattedContent();
//            Utility.printByteArray("Field 002", f2_data, f2_data.length);
//        } catch (XFieldError ex)
//        {
//            Logger.getLogger(Field.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        /**
//         * Fixed Length Definition
//         */
//        DataFormatter df3 = new DataFormatter(DataFormatter.DataType._HEX, "",
//                false, true, 6);
//        LengthFormatter lf3 = new LengthFormatter(
//                LengthFormatter.LengthType._FIXED_LEN,
//                LengthFormatter.LengthFormat._HEX, false,
//                6);
//
//        Field f3 = new Field(df3, lf3);
//        try
//        {
//            f3.setContent("000000");
//        } catch (XFieldError ex)
//        {
//            Logger.getLogger(Field.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println(ex.getErrorMsg());
//        }
//    }
}
