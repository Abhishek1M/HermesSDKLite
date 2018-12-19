package messagebus;

/**
 *
 * @author Abhy
 */
public class PosDataCode
{

    private byte data[];

    /**
     *
     */
    public static class TerminalType
    {

        public static final String _00_ADMIN = "00";
        public static final String _01_POS = "01";
        public static final String _02_ATM = "02";
        public static final String _03_HOME = "03";
        public static final String _04_ECR = "04";
        public static final String _05_DIAL = "05";
        public static final String _06_TRAVELLERS_CHECK = "06";
        public static final String _07_FUEL = "07";
        public static final String _08_SCRIP = "08";
        public static final String _09_COUPON = "09";
        public static final String _10_TICKET = "10";
        public static final String _11_POINT_OF_BANKING = "11";
        public static final String _12_TELLER = "12";
        public static final String _13_FRANCHISE_TELLER = "13";
        public static final String _14_PERSONAL_BANKING = "14";
        public static final String _15_PUBLIC_UTILITY = "15";
        public static final String _16_VENDING = "16";
        public static final String _17_SELF_SERVICE = "17";
        public static final String _18_AUTHORIZATION = "18";
        public static final String _19_PAYMENT = "19";
        public static final String _20_VRU = "20";
        public static final String _21_SMART_PHONE = "21";
        public static final String _22_INTERACTIVE_TELEVISION = "22";
        public static final String _23_PERSONAL_DIGITAL_ASSISTANT = "23";
        public static final String _24_SCREEN_PHONE = "24";
        public static final String _25_BUSINESS_BANKING = "25";
        public static final String _90_E_COMMERCE = "90";
        public static final String _91_E_COMMERCE_SET_NO_CH_CERT = "91";
        public static final String _92_E_COMMERCE_SET_CH_CERT = "92";
        public static final String _93_E_COMMERCE_SET_CHIP_NO_CH_CERT = "93";
        public static final String _94_E_COMMERCE_SET_CHIP_CH_CERT = "94";
        public static final String _95_E_COMMERCE_SSL_NO_CH_CERT = "95";
        public static final String _96_E_COMMERCE_SSL_CHIP_NO_CH_CERT = "96";

        public TerminalType()
        {
        }
    }

    /**
     *
     */
    public static class TerminalOutputCapability
    {

        public static final String _0_UNKNOWN = "0";
        public static final String _1_NONE = "1";
        public static final String _2_PRINT = "2";
        public static final String _3_DISPLAY = "3";
        public static final String _4_PRINT_DISPLAY = "4";

        public TerminalOutputCapability()
        {
        }
    }

    /**
     *
     */
    public static class TerminalOperator
    {

        public static final String _0_CUSTOMER = "0";
        public static final String _1_CARD_ACCEPTOR = "1";
        public static final String _2_ADMINISTRATIVE = "2";

        public TerminalOperator()
        {
        }
    }

    public static class PinCaptureCapability
    {

        public static final String _0_NONE = "0";
        public static final String _1_UNKNOWN = "1";
        public static final String _4_FOUR = "4";
        public static final String _5_FIVE = "5";
        public static final String _6_SIX = "6";
        public static final String _7_SEVEN = "7";
        public static final String _8_EIGHT = "8";
        public static final String _9_NINE = "9";
        public static final String _A_TEN = "A";
        public static final String _B_ELEVEN = "B";
        public static final String _C_TWELVE = "C";

        public PinCaptureCapability()
        {
        }
    }

    public static class OperatingEnvironment
    {

        public static final String NO_TERMINAL = "0";
        public static final String _0_NO_TERMINAL = "0";
        public static final String ATTENDED_ON_ACCEPTOR_PREMISES = "1";
        public static final String _1_ATTENDED_ON_ACCEPTOR_PREMISES = "1";
        public static final String UNATTENDED_ON_ACCEPTOR_PREMISES = "2";
        public static final String _2_UNATTENDED_ON_ACCEPTOR_PREMISES = "2";
        public static final String ATTENDED_OFF_ACCEPTOR_PREMISES = "3";
        public static final String _3_ATTENDED_OFF_ACCEPTOR_PREMISES = "3";
        public static final String UNATTENDED_OFF_ACCEPTOR_PREMISES = "4";
        public static final String _4_UNATTENDED_OFF_ACCEPTOR_PREMISES = "4";
        public static final String UNATTENDED_ON_HOLDER_PREMISES = "5";
        public static final String _5_UNATTENDED_ON_HOLDER_PREMISES = "5";

        public OperatingEnvironment()
        {
        }
    }

    public static class CardPresent
    {

        public static final String NOT_PRESENT = "0";
        public static final String _0_NOT_PRESENT = "0";
        public static final String PRESENT = "1";
        public static final String _1_PRESENT = "1";

        public CardPresent()
        {
        }
    }

    public static class CardholderPresent
    {

        public static final String PRESENT = "0";
        public static final String _0_PRESENT = "0";
        public static final String NOT_PRESENT = "1";
        public static final String _1_NOT_PRESENT = "1";
        public static final String MAIL_ORDER = "2";
        public static final String _2_MAIL_ORDER = "2";
        public static final String TELEPHONE = "3";
        public static final String _3_TELEPHONE = "3";
        public static final String STANDING_AUTH = "4";
        public static final String _4_STANDING_AUTH = "4";
        public static final String ELECTRONIC_ORDER = "5";
        public static final String _5_ELECTRONIC_ORDER = "5";

        public CardholderPresent()
        {
        }
    }

    public static class CardholderAuthMethod
    {

        public static final String NONE = "0";
        public static final String _0_NONE = "0";
        public static final String PIN = "1";
        public static final String _1_PIN = "1";
        public static final String SIGNATURE = "2";
        public static final String _2_SIGNATURE = "2";
        public static final String BIOMETRICS = "3";
        public static final String _3_BIOMETRICS = "3";
        public static final String BIOGRAPHIC = "4";
        public static final String _4_BIOGRAPHIC = "4";
        public static final String MANUAL = "5";
        public static final String _5_MANUAL = "5";
        public static final String OTHER = "6";
        public static final String _6_OTHER = "6";

        public CardholderAuthMethod()
        {
        }
    }

    public static class CardholderAuthEntity
    {

        public static final String NOT_AUTHENTICATED = "0";
        public static final String _0_NOT_AUTHENTICATED = "0";
        public static final String ICC = "1";
        public static final String _1_ICC = "1";
        public static final String CAD = "2";
        public static final String _2_CAD = "2";
        public static final String TERMINAL = "2";
        public static final String _2_TERMINAL = "2";
        public static final String AUTHORISER = "3";
        public static final String _3_AUTHORISER = "3";
        public static final String MERCHANT = "4";
        public static final String _4_MERCHANT = "4";
        public static final String OTHER = "5";
        public static final String _5_OTHER = "5";

        public CardholderAuthEntity()
        {
        }
    }

    public static class CardholderAuthCapability
    {

        public static final String _0_NONE = "0";
        public static final String _1_PIN = "1";
        public static final String _2_SIGNATURE = "2";
        public static final String _3_BIOMETRICS = "3";
        public static final String _4_BIOGRAPHIC = "4";
        public static final String _5_INOPERATIVE = "5";
        public static final String _6_OTHER = "6";

        public CardholderAuthCapability()
        {
        }
    }

    public static class CardDataInputMode
    {

        public static final String _0_UNKNOWN = "0";
        public static final String _1_MANUAL = "1";
        public static final String _2_MAGSTRIPE = "2";
        public static final String _3_BAR_CODE = "3";
        public static final String _4_OCR = "4";
        public static final String _5_ICC = "5";
        public static final String _6_KEY_ENTRY = "6";
        public static final String _7_CONTACTLESS_ICC = "7";
        public static final String _8_CONTACTLESS_MAGSTRIPE = "8";

        public CardDataInputMode()
        {
        }
    }

    public static class CardDataOutputCapability
    {

        public static final String _0_UNKNOWN = "0";
        public static final String _1_NONE = "1";
        public static final String _2_MAGSTRIPE = "2";
        public static final String _3_ICC = "3";

        public CardDataOutputCapability()
        {
        }
    }

    public static class CardDataInputCapability
    {

        public static final String _0_UNKNOWN = "0";
        public static final String _1_MANUAL = "1";
        public static final String _2_MAGSTRIPE = "2";
        public static final String _3_BAR_CODE = "3";
        public static final String _4_OCR = "4";
        public static final String _5_MAGSTRIPE_KEY_ENTRY_ICC = "5";
        public static final String _6_KEY_ENTRY = "6";
        public static final String _7_MAGSTRIPE_KEY_ENTRY = "7";
        public static final String _8_MAGSTRIPE_ICC = "8";
        public static final String _9_ICC = "9";
        public static final String _A_CONTACTLESS_ICC = "A";
        public static final String _B_CONTACTLESS_MAGSTRIPE = "B";

        public CardDataInputCapability()
        {
        }
    }

    /**
     * 
     */
    public static class CardCaptureCapability
    {

        public static final String _0_NONE = "0";
        public static final String _1_CAPTURE = "1";

        public CardCaptureCapability()
        {
        }
    }

    /**
     * 
     */
    private PosDataCode()
    {
        data = null;
    }

    /**
     * 
     * @param buf 
     */
    PosDataCode(byte buf[])
    {
        data = buf;
    }

    /**
     * 
     * @param str 
     */
    public PosDataCode(String str)
    {
        data = null;
        data = new byte[25];
        int len = str.length();
        str.getBytes(0, len, data, 0);
    }

    /**
     * 
     * @param card_data_input_capability
     * @param cardholder_auth_capability
     * @param card_capture_capability
     * @param operating_environment
     * @param cardholder_present
     * @param card_present
     * @param card_data_input_mode
     * @param cardholder_auth_method
     * @param cardholder_auth_entity
     * @param card_data_output_capability
     * @param terminal_output_capability
     * @param pin_capture_capability
     * @param terminal_operator
     * @param terminal_type 
     */
    public PosDataCode(String card_data_input_capability,
            String cardholder_auth_capability, String card_capture_capability,
            String operating_environment, String cardholder_present,
            String card_present, String card_data_input_mode,
            String cardholder_auth_method, String cardholder_auth_entity,
            String card_data_output_capability,
            String terminal_output_capability, String pin_capture_capability,
            String terminal_operator, String terminal_type)
    {
        data = null;
        data = new byte[25];
        card_data_input_capability.getBytes(0, 1, data, 0);
        cardholder_auth_capability.getBytes(0, 1, data, 1);
        card_capture_capability.getBytes(0, 1, data, 2);
        operating_environment.getBytes(0, 1, data, 3);
        cardholder_present.getBytes(0, 1, data, 4);
        card_present.getBytes(0, 1, data, 5);
        card_data_input_mode.getBytes(0, 1, data, 6);
        cardholder_auth_method.getBytes(0, 1, data, 7);
        cardholder_auth_entity.getBytes(0, 1, data, 8);
        card_data_output_capability.getBytes(0, 1, data, 9);
        terminal_output_capability.getBytes(0, 1, data, 10);
        pin_capture_capability.getBytes(0, 1, data, 11);
        terminal_operator.getBytes(0, 1, data, 12);
        terminal_type.getBytes(0, 2, data, 13);
    }

    /**
     * 
     * @return 
     */
    @Override
    public final String toString()
    {
        return new String(data, 0, 0, 25);
    }

    public final String getCardCaptureCapability()
    {
        return new String(data, 0, 2, 1);
    }

    public final void putCardCaptureCapability(String value)
    {
        value.getBytes(0, 1, data, 2);
    }

    public final String getCardDataInputCapability()
    {
        return new String(data, 0, 0, 1);
    }

    public final void putCardDataInputCapability(String value)
    {
        value.getBytes(0, 1, data, 0);
    }

    public final String getCardDataInputMode()
    {
        return new String(data, 0, 6, 1);
    }

    public final void putCardDataInputMode(String value)
    {
        value.getBytes(0, 1, data, 6);
    }

    public final String getCardDataOutputCapability()
    {
        return new String(data, 0, 9, 1);
    }

    public final void putCardDataOutputCapability(String value)
    {
        value.getBytes(0, 1, data, 9);
    }

    public final String getCardholderAuthCapability()
    {
        return new String(data, 0, 1, 1);
    }

    public final void putCardholderAuthCapability(String value)
    {
        value.getBytes(0, 1, data, 1);
    }

    public final String getCardholderAuthEntity()
    {
        return new String(data, 0, 8, 1);
    }

    public final void putCardholderAuthEntity(String value)
    {
        value.getBytes(0, 1, data, 8);
    }

    public final String getCardholderAuthMethod()
    {
        return new String(data, 0, 7, 1);
    }

    public final void putCardholderAuthMethod(String value)
    {
        value.getBytes(0, 1, data, 7);
    }

    public final String getCardholderPresent()
    {
        return new String(data, 0, 4, 1);
    }

    public final void putCardholderPresent(String value)
    {
        value.getBytes(0, 1, data, 4);
    }

    public final String getCardPresent()
    {
        return new String(data, 0, 5, 1);
    }

    public final void putCardPresent(String value)
    {
        value.getBytes(0, 1, data, 5);
    }

    public final String getOperatingEnvironment()
    {
        return new String(data, 0, 3, 1);
    }

    public final void putOperatingEnvironment(String value)
    {
        value.getBytes(0, 1, data, 3);
    }

    public final String getPinCaptureCapability()
    {
        return new String(data, 0, 11, 1);
    }

    public final void putPinCaptureCapability(String value)
    {
        value.getBytes(0, 1, data, 11);
    }

    public final String getTerminalOperator()
    {
        return new String(data, 0, 12, 1);
    }

    public final void putTerminalOperator(String value)
    {
        value.getBytes(0, 1, data, 12);
    }

    public final String getTerminalOutputCapability()
    {
        return new String(data, 0, 10, 1);
    }

    public final void putTerminalOutputCapability(String value)
    {
        value.getBytes(0, 1, data, 10);
    }

    public final String getTerminalType()
    {
        return new String(data, 0, 13, 2);
    }

    public final void putTerminalType(String value)
    {
        value.getBytes(0, 2, data, 13);
    }

    final byte[] getData()
    {
        return data;
    }
}
