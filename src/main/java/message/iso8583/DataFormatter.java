package message.iso8583;

import utils.Translate;
import utils.Utility;

/**
 *
 * @author Abhy
 */
public class DataFormatter
{

    private int dataType;
    private String padText;
    private boolean isFixedLen;
    private boolean padRight;
    private LengthFormatter lf;
    private int maxlen;

    /**
     *
     * @param dataType
     * @param padText
     * @param padLeft
     * @param isFixedLen
     * @param maxlen
     *
     */
    public DataFormatter(int dataType, String padText, boolean padRight,
            boolean isFixedLen, int maxlen)
    {
        this.dataType = dataType;
        this.padText = padText;
        this.padRight = padRight;
        this.isFixedLen = isFixedLen;
        this.maxlen = maxlen;
    }

    /**
     *
     */
    public class DataType
    {

        public static final int _HEX = 0;
        public static final int _EBCDIC = 1;
        public static final int _BINARY = 2;
    }

    /**
     *
     * @return
     */
    public int getDataType()
    {
        return dataType;
    }

    /**
     *
     * @param dataType
     */
    public void setDataType(int dataType)
    {
        this.dataType = dataType;
    }

    /**
     *
     * @return
     */
    public byte[] getFormattedContent(String data)
    {
        byte[] byte_content = null;

        if (isFixedLen)
        {
            if (data.length() < maxlen)
            {
                data = Utility.resize(data, maxlen, padText, padRight);
            }
        }

        if (dataType == DataType._HEX)
        {
            byte_content = Translate.getData(data);
        } else if (dataType == DataType._BINARY)
        {
            if ((data.length() % 2) != 0)
            {
                if (!padRight)
                {
                    data = padText + data;
                } else
                {
                    data = data + padText;
                }
            }

            byte_content = Translate.getData(Translate.fromHexToBin(data));
        } else if (dataType == DataType._EBCDIC)
        {
            byte_content = Translate.getData(Translate.fromAsciiToEbcdic(
                    data));
        }

        return byte_content;
    }

    /**
     * 
     * @return 
     */
    public boolean isIsFixedLen()
    {
        return isFixedLen;
    }

    /**
     * 
     * @param isFixedLen 
     */
    public void setIsFixedLen(boolean isFixedLen)
    {
        this.isFixedLen = isFixedLen;
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
     * @param lf 
     */
    public void setLf(LengthFormatter lf)
    {
        this.lf = lf;
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
    public boolean isPadRight()
    {
        return padRight;
    }

    /**
     * 
     * @param padRight 
     */
    public void setPadRight(boolean padRight)
    {
        this.padRight = padRight;
    }

    /**
     * 
     * @return 
     */
    public String getPadText()
    {
        return padText;
    }

    /**
     * 
     * @param padText 
     */
    public void setPadText(String padText)
    {
        this.padText = padText;
    }
}
