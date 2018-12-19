package security;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

/**
 *
 * @author Abhy
 */
public class DUKPT
{

    public static final String DES_ENCRYPTION_SCHEME = "DES";
    public static final String DESEDE_ENCRYPTION_ALGORITHM = "DESede/ECB/NoPadding";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

    /**
     *
     * @param ksn
     * @param dk
     * @return
     * @throws Exception
     */
    public static byte[] getIPEK(byte ksn[], byte dk[])
            throws Exception
    {
        byte ksnTemp[];
        byte dkTemp1[];
        byte dkTemp2[];
        byte temp1[];
        byte temp2[];
        ksnTemp = Arrays.copyOf(ksn, 8);
        dkTemp1 = Arrays.copyOf(dk, 8);
        dkTemp2 = Arrays.copyOfRange(dk, 8, 16);
        ksnTemp[7] &= 0xe0;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        bout.write(dkTemp1);
        bout.write(dkTemp2);
        bout.write(dkTemp1);
        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        SecretKey key = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(bout.
                toByteArray()));
        cipher.init(1, key);
        temp1 = cipher.doFinal(ksnTemp);
        dkTemp1[0] ^= 0xc0;
        dkTemp1[1] ^= 0xc0;
        dkTemp1[2] ^= 0xc0;
        dkTemp1[3] ^= 0xc0;
        dkTemp2[0] ^= 0xc0;
        dkTemp2[1] ^= 0xc0;
        dkTemp2[2] ^= 0xc0;
        dkTemp2[3] ^= 0xc0;
        bout.reset();
        bout.write(dkTemp1);
        bout.write(dkTemp2);
        bout.write(dkTemp1);
        key = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(bout.
                toByteArray()));
        cipher.init(1, key);
        temp2 = cipher.doFinal(ksnTemp);
        bout.reset();
        bout.write(temp1, 0, 8);
        bout.write(temp2, 0, 8);
        return bout.toByteArray();
    }

    /**
     *
     * @param ksn
     * @param dk
     * @return
     * @throws Exception
     */
    public static byte[] getIPEKFormat1(byte ksn[], byte dk[])
            throws Exception
    {
        byte ksnTemp[];
        byte dkTemp1[];
        byte dkTemp2[];
        byte temp1[];
        byte temp2[];
        ksnTemp = Arrays.copyOf(ksn, 8);
        dkTemp1 = Arrays.copyOf(dk, 8);
        dkTemp2 = Arrays.copyOfRange(dk, 8, 16);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        bout.write(dkTemp1);
        bout.write(dkTemp2);
        bout.write(dkTemp1);
        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        SecretKey key = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(bout.
                toByteArray()));
        cipher.init(1, key);
        temp1 = cipher.doFinal(ksnTemp);
        dkTemp1[0] ^= 0xc0;
        dkTemp1[1] ^= 0xc0;
        dkTemp1[2] ^= 0xc0;
        dkTemp1[3] ^= 0xc0;
        dkTemp2[0] ^= 0xc0;
        dkTemp2[1] ^= 0xc0;
        dkTemp2[2] ^= 0xc0;
        dkTemp2[3] ^= 0xc0;
        bout.reset();
        bout.write(dkTemp1);
        bout.write(dkTemp2);
        bout.write(dkTemp1);
        key = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(bout.
                toByteArray()));
        cipher.init(1, key);
        temp2 = cipher.doFinal(ksnTemp);
        bout.reset();
        bout.write(temp1, 0, 8);
        bout.write(temp2, 0, 8);
        return bout.toByteArray();
    }

    /**
     *
     * @param ksn
     * @param dk
     * @return
     * @throws Exception
     */
    public static byte[] genTransactionKey(byte ksn[], byte dk[])
            throws Exception
    {
        byte ksnTemp[];
        byte counter[];
        byte key[];
        byte temp1[];
        byte temp2[] = new byte[8];
        byte ipek[] = getIPEK(ksn, dk);
        ksnTemp = Arrays.copyOfRange(ksn, 2, 10);
        ksnTemp[5] &= 0xe0;
        ksnTemp[6] = 0;
        ksnTemp[7] = 0;
        counter = Arrays.copyOfRange(ksn, 7, 10);
        counter[0] &= 0x1f;
        int num = countOne(counter[0]);
        num += countOne(counter[1]);
        num += countOne(counter[2]);
        byte counterTemp[] = searchOne(counter);
        procCounter(ksnTemp, counter, counterTemp);
        key = Arrays.copyOf(ipek, 16);
        temp1 = Arrays.copyOf(ksnTemp, 8);
        for (; num > 0; num--)
        {
            nonRevKeyGen(temp1, temp2, key);
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(Arrays.copyOf(temp1, 8));
            bout.write(Arrays.copyOf(temp2, 8));
            key = bout.toByteArray();
            counterTemp = searchOne(counter);
            procCounter(ksnTemp, counter, counterTemp);
            temp1 = Arrays.copyOf(ksnTemp, 8);
        }

        key[7] ^= 0xff;
        key[15] ^= 0xff;
        return key;
    }

    /**
     *
     * @param input
     * @return
     */
    private static int countOne(byte input)
    {
        int result = 0;
        if ((input & 0x80) == 128)
        {
            result++;
        }
        if ((input & 0x40) == 64)
        {
            result++;
        }
        if ((input & 0x20) == 32)
        {
            result++;
        }
        if ((input & 0x10) == 16)
        {
            result++;
        }
        if ((input & 8) == 8)
        {
            result++;
        }
        if ((input & 4) == 4)
        {
            result++;
        }
        if ((input & 2) == 2)
        {
            result++;
        }
        if ((input & 1) == 1)
        {
            result++;
        }
        return result;
    }

    /**
     *
     * @param counter
     * @return
     */
    private static byte[] searchOne(byte counter[])
    {
        byte temp[] = new byte[3];
        if (counter[0] == 0)
        {
            if (counter[1] == 0)
            {
                temp[2] = searchOneCore(counter[2]);
            } else
            {
                temp[1] = searchOneCore(counter[1]);
            }
        } else
        {
            temp[0] = searchOneCore(counter[0]);
        }
        return temp;
    }

    /**
     *
     * @param input
     * @return
     */
    private static byte searchOneCore(byte input)
    {
        if ((input & 0x80) == 128)
        {
            return -128;
        }
        if ((input & 0x40) == 64)
        {
            return 64;
        }
        if ((input & 0x20) == 32)
        {
            return 32;
        }
        if ((input & 0x10) == 16)
        {
            return 16;
        }
        if ((input & 8) == 8)
        {
            return 8;
        }
        if ((input & 4) == 4)
        {
            return 4;
        }
        if ((input & 2) == 2)
        {
            return 2;
        }
        return ((byte) ((input & 1) != 1 ? 0 : 1));
    }

    /**
     *
     * @param ksn
     * @param counter
     * @param counterTemp
     */
    private static void procCounter(byte ksn[], byte counter[],
            byte counterTemp[])
    {
        ksn[5] |= counterTemp[0];
        counter[0] ^= counterTemp[0];
        ksn[6] |= counterTemp[1];
        counter[1] ^= counterTemp[1];
        ksn[7] |= counterTemp[2];
        counter[2] ^= counterTemp[2];
    }

    /**
     *
     * @param cReg1
     * @param cReg2
     * @param key
     * @throws Exception
     */
    private static void nonRevKeyGen(byte cReg1[], byte cReg2[], byte key[])
            throws Exception
    {
        for (int i = 0; i < 8; i++)
        {
            cReg2[i] = (byte) (cReg1[i] ^ key[i + 8]);
        }

        byte keyTemp[] = Arrays.copyOf(key, 8);
        Cipher cipher = Cipher.getInstance("DES");
        SecretKey sKey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(
                keyTemp));
        cipher.init(1, sKey);
        byte cipherResult[] = cipher.doFinal(cReg2);
        for (int i = 0; i < 8; i++)
        {
            cReg2[i] = (byte) (cipherResult[i] ^ key[i + 8]);
        }

        key[0] ^= 0xc0;
        key[1] ^= 0xc0;
        key[2] ^= 0xc0;
        key[3] ^= 0xc0;
        key[8] ^= 0xc0;
        key[9] ^= 0xc0;
        key[10] ^= 0xc0;
        key[11] ^= 0xc0;
        for (int i = 0; i < 8; i++)
        {
            cReg1[i] ^= key[i + 8];
        }

        keyTemp = Arrays.copyOf(key, 8);
        sKey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(
                keyTemp));
        cipher.init(1, sKey);
        cipherResult = cipher.doFinal(cReg1);
        for (int i = 0; i < 8; i++)
        {
            cReg1[i] = (byte) (cipherResult[i] ^ key[i + 8]);
        }
    }

    /**
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte data[], byte key[])
            throws Exception
    {
        byte keyBuffer[] = new byte[24];
        if (key.length == 16)
        {
            System.arraycopy(key, 0, keyBuffer, 0, 16);
            System.arraycopy(key, 0, keyBuffer, 16, 8);
        } else
        {
            System.arraycopy(key, 0, keyBuffer, 0, 24);
        }
        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        javax.crypto.SecretKey sKey = SecretKeyFactory.getInstance("DESede").
                generateSecret(new DESedeKeySpec(keyBuffer));
        cipher.init(Cipher.ENCRYPT_MODE, sKey);
        return cipher.doFinal(data);
    }

    /**
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte data[], byte key[])
            throws Exception
    {
        byte keyBuffer[] = new byte[24];
        if (key.length == 16)
        {
            System.arraycopy(key, 0, keyBuffer, 0, 16);
            System.arraycopy(key, 0, keyBuffer, 16, 8);

        } else
        {
            System.arraycopy(key, 0, keyBuffer, 0, 24);

        }
        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        javax.crypto.SecretKey sKey = SecretKeyFactory.getInstance("DESede").
                generateSecret(new DESedeKeySpec(keyBuffer));
        cipher.init(Cipher.DECRYPT_MODE, sKey);
        return cipher.doFinal(data);
    }
}
