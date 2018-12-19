package message.iso8583;

import utils.Translate;
import utils.Utility;

/**
 *
 * @author Abhishek
 */
public class LengthFormatter
{

    private int lenType;
    private int lenFormat;
    private boolean padZeroLeft;
    private int maxlen;

    /**
     *
     * @param lenType
     * @param lenFormat
     * @param padCharLeft
     * @param maxlen
     */
    public LengthFormatter(int lenType, int lenFormat, boolean padZeroLeft,
            int maxlen)
    {
        this.lenType = lenType;
        this.lenFormat = lenFormat;
        this.padZeroLeft = padZeroLeft;
        this.maxlen = maxlen;
    }

    /**
     *
     */
    public class LengthType
    {

        public static final int _FIXED_LEN = 0;
        public static final int _LVAR = 1;
        public static final int _LLVAR = 2;
        public static final int _LLLVAR = 3;
        public static final int _LLLLVAR = 4;
        public static final int _LLLLLVAR = 5;
    }

    /**
     *
     */
    public class LengthFormat
    {

        public static final int _NONE = -1;
        public static final int _HEX = 0;
        public static final int _EBCDIC = 1;
        public static final int _BINARY = 2;
        public static final int _BCD = 3;
    }

    /**
     *
     * @return
     */
    public int getLenFormat()
    {
        return lenFormat;
    }

    /**
     *
     * @param lenFormat
     */
    public void setLenFormat(int lenFormat)
    {
        this.lenFormat = lenFormat;
    }

    /**
     *
     * @return
     */
    public boolean padZeroLeft()
    {
        return padZeroLeft;
    }

    /**
     *
     * @param padCharLeft
     */
    public void padZeroLeft(boolean padZeroLeft)
    {
        this.padZeroLeft = padZeroLeft;
    }

    /**
     *
     * @return
     */
    public int getLenType()
    {
        return lenType;
    }

    /**
     *
     * @param lenType
     */
    public void setLenType(int lenType)
    {
        this.lenType = lenType;
    }

    /**
     *
     * @return
     */
    public int getMaxlen()
    {
        return maxlen;
    }

    /**
     *
     * @param maxlen
     */
    public void setMaxlen(int maxlen)
    {
        this.maxlen = maxlen;
    }

    /**
     *
     * @return
     */
    public byte[] getFormattedLen(int len)
    {
        String str_datalen;
        byte[] byte_datalen;

        if (lenType == LengthType._FIXED_LEN)
        {
            str_datalen = Integer.toString(len);
        } else
        {
            str_datalen = Utility.resize(Integer.toString(len), lenType,
                    "0", false);
        }

        if (lenFormat == LengthFormat._HEX)
        {
            byte_datalen = Translate.getData(str_datalen);
        } else if (lenFormat == LengthFormat._BINARY)
        {
            if ((str_datalen.length() % 2) != 0)
            {
                str_datalen = "0" + str_datalen;
            }
            byte_datalen = Translate.getData(Translate.fromHexToBin(str_datalen));
        } else if (lenFormat == LengthFormat._EBCDIC)
        {
            byte_datalen = Translate.getData(Translate.fromAsciiToEbcdic(
                    str_datalen));
        } else if (lenFormat == LengthFormat._BCD)
        {
            str_datalen = Integer.toHexString(Integer.parseInt(str_datalen));
            if ((str_datalen.length() % 2) != 0)
            {
                str_datalen = "0" + str_datalen;
            }
            byte_datalen = Translate.getData(Translate.fromHexToBin(str_datalen));
        } else
        {
            byte_datalen = null;
        }

        return byte_datalen;
    }
}
