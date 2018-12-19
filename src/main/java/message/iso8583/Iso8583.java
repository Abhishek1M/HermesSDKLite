package message.iso8583;

import utils.FormatData;
import utils.Translate;
import utils.Utility;

import java.util.BitSet;
import java.util.concurrent.ConcurrentHashMap;
import messagebus.Iso8583DataBus;

/**
 *
 * @author Abhy
 */
public class Iso8583 extends Iso8583DataBus
{

    ConcurrentHashMap<Integer, Field> fieldmap;

    /**
     *
     */
    public Iso8583()
    {
        fieldmap = new ConcurrentHashMap<>(128);

        fieldmap.put(0, new Field(new DataFormatter(DataFormatter.DataType._HEX,
                "", false, true, 4),
                new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                        LengthFormatter.LengthFormat._NONE, false,
                        4)));
        fieldmap.put(1, new Field(new DataFormatter(
                DataFormatter.DataType._BINARY, "", false, true,
                32), new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                        LengthFormatter.LengthFormat._NONE, false, 32)));
        fieldmap.put(Bit._002_PAN,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, false, 19),
                        new LengthFormatter(LengthFormatter.LengthType._LLVAR,
                                LengthFormatter.LengthFormat._HEX, false,
                                19)));
        fieldmap.put(Bit._003_PROCESSING_CODE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 6),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                6)));
        fieldmap.put(Bit._004_AMOUNT_TRANSACTION,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", false, true, 12),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                12)));
        fieldmap.put(Bit._005_AMOUNT_SETTLE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", false, true, 12),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                12)));
        fieldmap.put(Bit._007_TRANSMISSION_DATE_TIME,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 10),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                10)));
        fieldmap.put(Bit._009_CONV_RATE_SETTLE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX, "0", true, true, 8),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false, 8)));
        fieldmap.put(Bit._011_SYSTEMS_TRACE_AUDIT_NR,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 06),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                06)));
        fieldmap.put(Bit._012_TIME_LOCAL,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 6),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                6)));
        fieldmap.put(Bit._013_DATE_LOCAL,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 4),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                4)));
        fieldmap.put(Bit._014_DATE_EXPIRATION,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 4),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                4)));
        fieldmap.put(Bit._015_DATE_SETTLE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 4),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                4)));
        fieldmap.put(Bit._016_DATE_CONV,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 4),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                4)));
        fieldmap.put(Bit._018_MERCHANT_TYPE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 4),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                4)));
        fieldmap.put(Bit._022_POS_ENTRY_MODE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 3),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                3)));
        fieldmap.put(Bit._023_CARD_SEQ_NR,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 3),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                3)));
        fieldmap.put(Bit._025_POS_CONDITION_CODE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 2),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                2)));
        fieldmap.put(Bit._026_POS_PIN_CAPTURE_CODE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 2),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                2)));
        fieldmap.put(Bit._032_ACQUIRING_INST_ID_CODE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, false, 11),
                        new LengthFormatter(LengthFormatter.LengthType._LLVAR,
                                LengthFormatter.LengthFormat._HEX, false,
                                11)));
        fieldmap.put(Bit._033_FORWARDING_INST_ID_CODE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, false, 11),
                        new LengthFormatter(LengthFormatter.LengthType._LLVAR,
                                LengthFormatter.LengthFormat._HEX, false,
                                11)));
        fieldmap.put(Bit._035_TRACK_2_DATA,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, false, 37),
                        new LengthFormatter(LengthFormatter.LengthType._LLVAR,
                                LengthFormatter.LengthFormat._HEX, false,
                                37)));
        fieldmap.put(Bit._037_RETRIEVAL_REF_NR,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 12),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                12)));
        fieldmap.put(Bit._038_AUTH_ID_RSP,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                " ", false, true, 6),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                6)));
        fieldmap.put(Bit._039_RSP_CODE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "", false, true, 2),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                2)));
        fieldmap.put(Bit._040_SERVICE_RESTRICTION_CODE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "", false, true, 3),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                3)));
        fieldmap.put(Bit._041_CARD_ACCEPTOR_TERM_ID,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                " ", true, true, 8),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                8)));
        fieldmap.put(Bit._042_CARD_ACCEPTOR_ID_CODE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                " ", true, true, 15),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                15)));
        fieldmap.put(Bit._043_CARD_ACCEPTOR_NAME_LOC,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                " ", false, true, 40),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                40)));
        fieldmap.put(Bit._045_TRACK_1_DATA,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                " ", false, false, 76),
                        new LengthFormatter(LengthFormatter.LengthType._LLVAR,
                                LengthFormatter.LengthFormat._HEX, false, 76)));
        fieldmap.put(Bit._048_ADDITIONAL_DATA,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                " ", false, false, 999),
                        new LengthFormatter(LengthFormatter.LengthType._LLLVAR,
                                LengthFormatter.LengthFormat._HEX, false,
                                999)));
        fieldmap.put(Bit._049_CURRENCY_CODE_TRAN,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 3),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                3)));
        fieldmap.put(Bit._050_CURRENCY_CODE_SETTLE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 3),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                3)));
        fieldmap.put(Bit._052_PIN_DATA,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                " ", false, true, 16),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                16)));
        fieldmap.put(Bit._054_ADDITIONAL_AMOUNTS,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                " ", false, false, 120),
                        new LengthFormatter(LengthFormatter.LengthType._LLLVAR,
                                LengthFormatter.LengthFormat._HEX, false,
                                120)));
        fieldmap.put(Bit._055_EMV_DATA,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                " ", false, false, 999),
                        new LengthFormatter(LengthFormatter.LengthType._LLLVAR,
                                LengthFormatter.LengthFormat._BINARY, false,
                                999)));
        fieldmap.put(Bit._060_ADVICE_REASON_CODE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                " ", false, false, 999),
                        new LengthFormatter(LengthFormatter.LengthType._LLLVAR,
                                LengthFormatter.LengthFormat._HEX, false,
                                999)));
        fieldmap.put(Bit._061_POS_DATA,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                " ", false, false, 999),
                        new LengthFormatter(LengthFormatter.LengthType._LLLVAR,
                                LengthFormatter.LengthFormat._HEX, false,
                                999)));
        fieldmap.put(Bit._062_TRANS_ID,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                " ", false, false, 999),
                        new LengthFormatter(LengthFormatter.LengthType._LLLVAR,
                                LengthFormatter.LengthFormat._HEX, false,
                                999)));
        fieldmap.put(Bit._063_PRIVATE_USE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                " ", false, false, 999),
                        new LengthFormatter(LengthFormatter.LengthType._LLLVAR,
                                LengthFormatter.LengthFormat._HEX, false,
                                999)));
        fieldmap.put(Bit._070_NETWORK_MNG_INFO_CODE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 3),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                3)));
        fieldmap.put(Bit._074_NR_CR,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 10),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                10)));
        fieldmap.put(Bit._075_NR_CR_REV,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 10),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                10)));
        fieldmap.put(Bit._076_NR_DT,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 10),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                10)));
        fieldmap.put(Bit._081_NR_AUTH,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 10),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                10)));
        fieldmap.put(Bit._086_AMOUNT_CR,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 16),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                16)));
        fieldmap.put(Bit._087_AMOUNT_CR_REV,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 16),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                16)));
        fieldmap.put(Bit._088_AMOUNT_DT,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 16),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                16)));
        fieldmap.put(Bit._090_ORIGINAL_DATA_ELEMENTS,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                " ", true, true, 42),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                42)));
        fieldmap.put(Bit._095_REPLACEMENT_AMOUNTS,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                " ", false, true, 42),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                42)));
        fieldmap.put(Bit._097_AMOUNT_NET_SETTLE,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, true, 17),
                        new LengthFormatter(LengthFormatter.LengthType._FIXED_LEN,
                                LengthFormatter.LengthFormat._HEX, false,
                                17)));
        fieldmap.put(Iso8583DataBus.Bit._102_ACCOUNT_ID_1,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, false, 28),
                        new LengthFormatter(LengthFormatter.LengthType._LLVAR,
                                LengthFormatter.LengthFormat._HEX, false,
                                28)));
        fieldmap.put(Iso8583DataBus.Bit._103_ACCOUNT_ID_2,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                "0", true, false, 28),
                        new LengthFormatter(LengthFormatter.LengthType._LLVAR,
                                LengthFormatter.LengthFormat._HEX, false,
                                28)));
        fieldmap.put(Bit._120_TRAN_DATA_REQ,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                " ", false, false, 999),
                        new LengthFormatter(LengthFormatter.LengthType._LLLVAR,
                                LengthFormatter.LengthFormat._HEX, false,
                                999)));
        fieldmap.put(Bit._123_EXTENDED_FIELD,
                new Field(new DataFormatter(DataFormatter.DataType._HEX,
                                " ", false, false, 999),
                        new LengthFormatter(LengthFormatter.LengthType._LLLVAR,
                                LengthFormatter.LengthFormat._HEX, false,
                                999)));
    }

    /**
     *
     * @return The Hashmap containing the ISO fields
     */
    public ConcurrentHashMap<Integer, Field> getIsoFields()
    {
        return fieldmap;
    }

    /**
     *
     * @param fieldmap
     */
    public void setIsoFields(ConcurrentHashMap<Integer, Field> fieldmap)
    {
        this.fieldmap = fieldmap;
    }

    /**
     *
     * @return
     */
    public byte[] getMessage() throws XFieldError
    {
        Field f;
        String fieldvalue;
        byte[] msg;
        byte[] temp;
        int totallen = 0;
        int pos = 0;
        BitSet bitmap = new BitSet();

        for (int i = 0; i <= 128; i++)
        {
            if (i != 1)
            {
                if (i == 0)
                {
                    fieldvalue = getMsgType();
                } else
                {
                    fieldvalue = getField(i);
                }

                if (fieldvalue != null && fieldvalue.length() > 0)
                {
                    f = fieldmap.get(i);

                    f.setContent(fieldvalue);
                    temp = f.getFormattedContent();

                    totallen = totallen + temp.length;

                    bitmap.set(i);

                    if (i > 64)
                    {
                        bitmap.set(1);
                    }
                }
            }
        }

        byte[] byte_bitmap = FormatData.bitSet2byte(bitmap);
        totallen = totallen + byte_bitmap.length;

        msg = new byte[totallen];

        for (int i = 0; i <= 128; i++)
        {
            if (i != 1)
            {
                if (i == 0)
                {
                    fieldvalue = getMsgType();
                } else
                {
                    fieldvalue = getField(i);
                }

                if (fieldvalue != null && fieldvalue.length() > 0)
                {
                    f = fieldmap.get(i);

                    f.setContent(fieldvalue);
                    temp = f.getFormattedContent();
                    System.arraycopy(temp, 0, msg, pos, temp.length);
                    pos = pos + temp.length;
                }
            } else
            {
                System.arraycopy(byte_bitmap, 0, msg, pos, byte_bitmap.length);
                pos = pos + byte_bitmap.length;
            }
        }

        return msg;
    }

    /**
     *
     * @param data
     */
    public void parseIso8583Msg(byte[] data) throws Exception
    {
        Field f;
        int flen;
        byte[] temp = null;
        byte[] temp_len;
        int pos = 0;
        int len_of_len;

        // first we read the MTI
        f = fieldmap.get(0);
        if (f.getFieldLengthType() == LengthFormatter.LengthType._FIXED_LEN)
        {
            flen = f.getFieldDataParseMsgLen();

            temp = new byte[flen];

            if (f.getFieldDataType() == DataFormatter.DataType._BINARY)
            {
                temp = new byte[flen];
                System.arraycopy(data, pos, temp, 0, flen);
                setMsgType(Translate.fromBinToHex(Translate.getString(
                        temp)));
            } else if (f.getFieldDataType()
                    == DataFormatter.DataType._EBCDIC)
            {
                temp = new byte[flen];
                System.arraycopy(data, pos, temp, 0, flen);
                setMsgType(Translate.fromEbcdicToAscii(
                        Translate.getString(temp)));
            } else if (f.getFieldDataType()
                    == DataFormatter.DataType._HEX)
            {
                temp = new byte[flen];
                System.arraycopy(data, pos, temp, 0, flen);
                setMsgType(Translate.getString(temp));
            }

            pos = pos + flen;
        }

        // read the Bitmap
        f = fieldmap.get(1);
        BitSet bitmap = FormatData.byte2BitSet(data, pos, 128);
        if (bitmap.get(1))
        {
            flen = 32;
        } else
        {
            flen = 16;
        }

        if (f.getFieldDataType() == DataFormatter.DataType._BINARY)
        {
            flen = flen / 2;
        }

        pos = pos + flen;

        // process for the remaining fields
        for (int i = 2; i <= 128; i++)
        {
            if (bitmap.get(i))
            {
                f = fieldmap.get(i);
                if (f.getFieldLengthType()
                        == LengthFormatter.LengthType._FIXED_LEN)
                {
                    flen = f.getFieldDataParseMsgLen();

                    if (f.getFieldDataType() == DataFormatter.DataType._BINARY)
                    {
                        temp = new byte[flen];
                        System.arraycopy(data, pos, temp, 0, flen);
                        String temp_str = Translate.fromBinToHex(Translate.
                                getString(temp));
                        int act_len = f.getFieldDataLength();
                        temp_str = Utility.removePadChars(temp_str,
                                f.getDf().isPadRight(), act_len);
                        setField(i, temp_str);
                    } else if (f.getFieldDataType()
                            == DataFormatter.DataType._EBCDIC)
                    {
                        temp = new byte[flen];
                        System.arraycopy(data, pos, temp, 0, flen);
                        setField(i, Translate.fromEbcdicToAscii(Translate.
                                getString(temp)));
                    } else if (f.getFieldDataType()
                            == DataFormatter.DataType._HEX)
                    {
                        temp = new byte[flen];
                        System.arraycopy(data, pos, temp, 0, flen);
                        setField(i, Translate.getString(temp));
                    }
                    pos = pos + flen;
                } else
                {
                    flen = f.getFieldParseLength();
                    temp_len = new byte[flen];
                    System.arraycopy(data, pos, temp_len, 0, flen);

                    if (f.getFieldLengthFormat()
                            == LengthFormatter.LengthFormat._BINARY)
                    {
                        len_of_len = Integer.parseInt(Translate.fromBinToHex(Translate.
                                getString(temp_len)));
                    } else if (f.getFieldLengthFormat()
                            == LengthFormatter.LengthFormat._EBCDIC)
                    {
                        len_of_len = Integer.parseInt(Translate.
                                fromEbcdicToAscii(Translate.getString(temp_len)));
                    } else if (f.getFieldLengthFormat()
                            == LengthFormatter.LengthFormat._HEX)
                    {
                        len_of_len = Integer.parseInt(Translate.getString(
                                temp_len));
                    } else
                    {
                        len_of_len = Integer.parseInt(Translate.getString(
                                temp_len));
                    }

                    pos = pos + flen;

                    if (f.getFieldDataType() == DataFormatter.DataType._BINARY)
                    {

                        int act_len = len_of_len;

                        if (Utility.isOdd(len_of_len))
                        {
                            len_of_len
                                    = (len_of_len + 1) / 2;
                        } else
                        {
                            len_of_len
                                    = len_of_len / 2;
                        }

                        temp = new byte[len_of_len];
                        System.arraycopy(data, pos, temp, 0, len_of_len);
                        String temp_str = Translate.fromBinToHex(Translate.
                                getString(temp));
                        temp_str = Utility.removePadChars(temp_str, f.getDf().
                                isPadRight(), act_len);
                        setField(i, temp_str);
                    } else if (f.getFieldDataType()
                            == DataFormatter.DataType._EBCDIC)
                    {
                        temp = new byte[len_of_len];
                        System.arraycopy(data, pos, temp, 0, len_of_len);
                        setField(i, Translate.fromEbcdicToAscii(Translate.
                                getString(temp)));
                    } else if (f.getFieldDataType()
                            == DataFormatter.DataType._HEX)
                    {
                        temp = new byte[len_of_len];
                        System.arraycopy(data, pos, temp, 0, len_of_len);
                        setField(i, Translate.getString(temp));
                    }
                    pos = pos + len_of_len;
                }
            }
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String dumpMsg()
    {
        StringBuilder str = new StringBuilder("");
        String value;

        str.append("\n").append(getMsgType()).append(":\n");

        // print all the fields except Extended Field 123
        for (int i = 2; i < 129; i++)
        {
//            if (i != 123)
            {
                value = getField(i);

                switch (i)
                {
                    case Iso8583DataBus.Bit._002_PAN:
                    case Iso8583DataBus.Bit._035_TRACK_2_DATA:
                        if (value != null && value.trim().length() > 0)
                        {
                            str.append("\t[F").append(Utility.resize(Integer.
                                    toString(i), 3, "0",
                                    false)).append("] = [").append(FormatData.
                                            protect(getField(i))).append("]\n");
                        }   break;
                    case Iso8583DataBus.Bit._045_TRACK_1_DATA:
                        if (value != null && value.trim().length() > 0)
                        {
                            String temp = getField(i);
                            temp = Utility.resize("*", temp.length(), "*", true);
                            str.append("\t[F").append(Utility.resize(Integer.
                                    toString(i), 3, "0",
                                    false)).append("] = [").append(temp).append(
                                            "]\n");
                        }   break;
                    case Iso8583DataBus.Bit._052_PIN_DATA:
                        if (value != null && value.trim().length() > 0)
                        {
                            str.append("\t[F").append(Utility.resize(Integer.
                                    toString(i), 3, "0",
                                    false)).append("] = [****************]\n");
                        }   break;
                    case Iso8583DataBus.Bit._055_EMV_DATA:
                        if (value != null && value.trim().length() > 0)
                        {
                            str.append("\t[F").append(Utility.resize(Integer.
                                    toString(i), 3, "0",
                                    false)).append("] = [").append(Translate.fromBinToHex(getField(i))).append("]\n");
                        }   break;
                    case Iso8583DataBus.Bit._063_PRIVATE_USE:
                        if (value != null && value.trim().length() > 0)
                        {
                            str.append("\t[F").append(Utility.resize(Integer.
                                    toString(i), 3, "0",
                                    false)).append("] = [").append(FormatData.protectAll(getField(i))).append(
                                            "]\n");
                        }   break;
                    default:
                        if (value != null && value.trim().length() > 0)
                        {
                            str.append("\t[F").append(Utility.resize(Integer.
                                    toString(i), 3, "0",
                                    false)).append("] = [").append(getField(i)).
                                    append("]\n");
                        }   break;
                }
            }
        }

        return str.toString();
    }

}
