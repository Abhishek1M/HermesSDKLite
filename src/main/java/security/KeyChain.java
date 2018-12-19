package security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import utils.Translate;

/**
 * This class is responsible for performing the encryption and decryption of
 * sensitive data (card number). This class will access the keystore and will
 * load the keys during the initialization.
 *
 * @author Abhishek M
 * @version 1.0
 */
public class KeyChain
{

    String dataencrypt_key;
    String version;
    static final String _ENC_ALGO = "AES/CBC/PKCS5Padding";
    static final String _STORE_TYPE = "JCEKS";

    /**
     * The Constructor.
     *
     * @param path
     * @throws FileNotFoundException
     * @throws IOException
     */
    public KeyChain(String path) throws FileNotFoundException, IOException
    {
        Properties keyChainFile = new Properties();

        keyChainFile.load(new FileInputStream(new File(path)));

        dataencrypt_key = keyChainFile.getProperty("DEK");
        version = keyChainFile.getProperty("Ver");
    }

    /**
     *
     * @param plainText
     * @return
     * @throws Exception
     */
    public String encryptCardData(String plainText) throws Exception
    {
        byte[] clean = plainText.getBytes();

        // Generating IV.
        int ivSize = 16;
        byte[] iv = new byte[ivSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Hashing key.
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(dataencrypt_key.getBytes("UTF-8"));
        byte[] keyBytes = new byte[16];
        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // Encrypt.
        Cipher cipher = Cipher.getInstance(_ENC_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(clean);

        // Combine IV and encrypted part.
        byte[] encryptedIVAndText = new byte[ivSize + encrypted.length];
        System.arraycopy(iv, 0, encryptedIVAndText, 0, ivSize);
        System.arraycopy(encrypted, 0, encryptedIVAndText, ivSize, encrypted.length);

        String encryptedData = "{" + version + "}" + Translate.fromBinToHex(Translate.getString(encryptedIVAndText));

        return encryptedData;
    }

    /**
     *
     * @param encryptedIvText
     * @return
     * @throws Exception
     */
    public String decryptCardData(String encryptedIvText) throws Exception
    {
        int ivSize = 16;
        int keySize = 16;

        int pos = encryptedIvText.indexOf("}");

        if (pos == -1)
        {
            return null;
        }

        pos++;
        encryptedIvText = encryptedIvText.substring(pos);

        byte[] encryptedIvTextBytes = Translate.getData(Translate.fromHexToBin(encryptedIvText));
        // Extract IV.
        byte[] iv = new byte[ivSize];
        System.arraycopy(encryptedIvTextBytes, 0, iv, 0, iv.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Extract encrypted part.
        int encryptedSize = encryptedIvTextBytes.length - ivSize;
        byte[] encryptedBytes = new byte[encryptedSize];
        System.arraycopy(encryptedIvTextBytes, ivSize, encryptedBytes, 0, encryptedSize);

        // Hash key.
        byte[] keyBytes = new byte[keySize];
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(dataencrypt_key.getBytes());
        System.arraycopy(md.digest(), 0, keyBytes, 0, keyBytes.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // Decrypt.
        Cipher cipherDecrypt = Cipher.getInstance(_ENC_ALGO);
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decrypted = cipherDecrypt.doFinal(encryptedBytes);

        return new String(decrypted);
    }
}
