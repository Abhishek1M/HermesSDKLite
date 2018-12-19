package messagebus;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import utils.FormatData;
import utils.Utility;

/**
 *
 * @author Abhy
 */
public class Iso8583DataBus
{

    /**
     *
     */
    public static class TranType
    {

        public static boolean isCredit(String tran_type)
        {
            int val = Integer.parseInt(tran_type);
            return val >= 20 && val <= 29;
        }

        public static boolean isDebit(String tran_type)
        {
            int val = Integer.parseInt(tran_type);
            return val >= 0 && val <= 19;
        }

        public static boolean isInquiry(String tran_type)
        {
            int val = Integer.parseInt(tran_type);
            return val >= 30 && val <= 39;
        }

        public static boolean isPayment(String tran_type)
        {
            int val = Integer.parseInt(tran_type);
            return val >= 50 && val <= 59;
        }

        public static boolean isTransfer(String tran_type)
        {
            int val = Integer.parseInt(tran_type);
            return val >= 40 && val <= 49;
        }
        public static final String _00_GOODS_SERVICES = "00";
        public static final String _01_CASH = "01";
        public static final String _02_ADJUSTMENTS = "02";
        public static final String _03_CHEQUE_GUARANTEE = "03";
        public static final String _04_CHEQUE_VERIFICATION = "04";
        public static final String _05_EUROCHEQUE = "05";
        public static final String _06_TRAVELLER_CHEQUE = "06";
        public static final String _07_LETTER_OF_CREDIT = "07";
        public static final String _08_GIRO = "08";
        public static final String _09_GOODS_SERVICES_CASH_BACK = "09";
        public static final String _10_NON_CASH_FIN_INSTRUMENT = "10";
        public static final String _11_QUASI_CASH_AND_SCRIP = "11";
        public static final String _20_RETURNS = "20";
        public static final String _21_DEPOSITS = "21";
        public static final String _22_CREDIT_ADJUSTMENTS = "22";
        public static final String _23_CHEQUE_DEPOSIT_GUARANTEE = "23";
        public static final String _24_CHEQUE_DEPOSIT = "24";
        public static final String _28_PAYMENT = "28";
        public static final String _30_AVAILABLE_FUNDS_INQUIRY = "30";
        public static final String _31_BALANCE_INQUIRY = "31";
        public static final String _32_GENERAL_INQUIRY = "32";
        public static final String _33_GET_OTP = "33";
        public static final String _35_FULL_STATEMENT_INQUIRY = "35";
        public static final String _40_CARDHOLDER_ACCOUNTS_TRANSFER = "40";
        public static final String _61_CARD_ACTIVATE = "61";
        public static final String _62_CARD_LOAD = "62";
        public static final String _63_CARD_DEACTIVATE = "63";

        protected TranType()
        {
        }
    }

    /**
     *
     */
    public static class Sign
    {

        public static String get(double amount)
        {
            if (amount >= 0.0D)
            {
                return "C";
            } else
            {
                return "D";
            }
        }

        public static int get(String sign)
        {
            return !sign.toUpperCase().equals("D") ? 1 : -1;
        }
        public static final String _D_DEBIT = "D";
        public static final String _C_CREDIT = "C";

        protected Sign()
        {
        }
    }

    /**
     *
     */
    public static class SettlementCode
    {

        public static final String _0_UNKNOWN = "0";
        public static final String _1_IN_BALANCE = "1";
        public static final String _2_OUT_OF_BALANCE = "2";
        public static final String _3_ERROR = "3";

        protected SettlementCode()
        {
        }
    }

    /**
     *
     */
    public static class AdviceReasonCode
    {

        public static final String _910_0011_TM_TO = "9100011";
        public static final String _910_0012_RM_TO = "9100012";

        protected AdviceReasonCode()
        {
        }
    }

    /**
     *
     */
    public static class RspCode
    {

        public static boolean isApprove(String rsp_code)
        {
            return rsp_code.equals("00") || rsp_code.equals("08")
                    || rsp_code.equals("10") || rsp_code.equals("11")
                    || rsp_code.equals("16") || rsp_code.equals("32");
        }

        public static boolean isCapture(String rsp_code)
        {
            return rsp_code.equals("04") || rsp_code.equals("07")
                    || rsp_code.equals("33") || rsp_code.equals("34")
                    || rsp_code.equals("35") || rsp_code.equals("36")
                    || rsp_code.equals("37") || rsp_code.equals("38")
                    || rsp_code.equals("41") || rsp_code.equals("43")
                    || rsp_code.equals("67");
        }

        public static boolean isDecline(String rsp_code)
        {
            return !isApprove(rsp_code) && !isCapture(rsp_code);
        }

        public static boolean isInterveneRspCode(String rsp_code)
        {
            return rsp_code.equals("01") || rsp_code.equals("02")
                    || rsp_code.equals("60") || rsp_code.equals("77")
                    || rsp_code.equals("78");
        }

        /**
         *
         * @param rsp_code
         * @return
         */
        public static boolean isSwitchInoperative(String rsp_code)
        {
            return rsp_code.equals("96") || rsp_code.equals("91");
        }
        public static final String _00_SUCCESSFUL = "00";
        public static final String _01_REFER_TO_CI = "01";
        public static final String _02_REFER_TO_CI_SPECIAL = "02";
        public static final String _03_INVALID_MERCHANT = "03";
        public static final String _04_PICK_UP = "04";
        public static final String _05_DO_NOT_HONOUR = "05";
        public static final String _06_ERROR = "06";
        public static final String _07_PICK_UP_SPECIAL = "07";
        public static final String _08_HONOUR_WITH_ID = "08";
        public static final String _09_REQUEST_IN_PROGRESS = "09";
        public static final String _10_APPROVED_PARTIAL = "10";
        public static final String _11_APPROVED_VIP = "11";
        public static final String _12_INVALID_TRAN = "12";
        public static final String _13_INVALID_AMOUNT = "13";
        public static final String _14_INVALID_CARD_NUMBER = "14";
        public static final String _15_NO_SUCH_ISSUER = "15";
        public static final String _16_APPROVED_UPDATE_TRACK_3 = "16";
        public static final String _17_CUSTOMER_CANCELLATION = "17";
        public static final String _18_CUSTOMER_DISPUTE = "18";
        public static final String _19_REENTER_TRAN = "19";
        public static final String _20_INVALID_RESPONSE = "20";
        public static final String _21_NO_ACTION_TAKEN = "21";
        public static final String _22_SUSPECTED_MALFUNCTION = "22";
        public static final String _23_UNACCEPTABLE_TRAN_FEE = "23";
        public static final String _24_FILE_UPDATE_NOT_SUPPORTED = "24";
        public static final String _25_UNABLE_TO_LOCATE_RECORD = "25";
        public static final String _26_DUPLICATE_RECORD = "26";
        public static final String _27_FILE_UPDATE_EDIT_ERROR = "27";
        public static final String _28_FILE_UPDATE_FILE_LOCKED = "28";
        public static final String _29_FILE_UPDATE_FAILED = "29";
        public static final String _30_FORMAT_ERROR = "30";
        public static final String _31_BANK_NOT_SUPPORTED = "31";
        public static final String _32_COMPLETED_PARTIALLY = "32";
        public static final String _33_EXPIRED_CARD_PICK_UP = "33";
        public static final String _34_SUSPECTED_FRAUD_PICK_UP = "34";
        public static final String _35_CONTACT_ACQ_PICK_UP = "35";
        public static final String _36_RESTRICTED_CARD_PICK_UP = "36";
        public static final String _37_CALL_ACQ_SECURITY_PICK_UP = "37";
        public static final String _38_PIN_TRIES_EXCEEDED_PICK_UP = "38";
        public static final String _39_NO_CREDIT_ACCOUNT = "39";
        public static final String _40_FUNCTION_NOT_SUPPORTED = "40";
        public static final String _41_LOST_CARD = "41";
        public static final String _42_NO_UNIVERSAL_ACCOUNT = "42";
        public static final String _43_STOLEN_CARD = "43";
        public static final String _44_NO_INVESTMENT_ACCOUNT = "44";
        public static final String _48_NO_CUSTOMER_RECORD = "48";
        public static final String _51_NOT_SUFFICIENT_FUNDS = "51";
        public static final String _52_NO_CHEQUING_ACCOUNT = "52";
        public static final String _53_NO_SAVINGS_ACCOUNT = "53";
        public static final String _54_EXPIRED_CARD = "54";
        public static final String _55_INCORRECT_PIN = "55";
        public static final String _56_NO_CARD_RECORD = "56";
        public static final String _57_TRAN_NOT_PERMITTED_CARDHOLDER = "57";
        public static final String _58_TRAN_NOT_PERMITTED_TERMINAL = "58";
        public static final String _59_SUSPECTED_FRAUD_DECLINED = "59";
        public static final String _60_CONTACT_ACQUIRER = "60";
        public static final String _61_EXCEEDS_WITHDRAWAL_LIMIT = "61";
        public static final String _62_RESTRICTED_CARD = "62";
        public static final String _63_SECURITY_VIOLATION = "63";
        public static final String _64_ORIGINAL_AMOUNT_INCORRECT = "64";
        public static final String _65_EXCEEDS_WITHDRAWAL_FREQUENCY = "65";
        public static final String _66_CALL_ACQ_SECURITY = "66";
        public static final String _67_HARD_CAPTURE = "67";
        public static final String _68_RESPONSE_RECEIVED_TOO_LATE = "68";
        public static final String _75_PIN_TRIES_EXCEEDED = "75";
        public static final String _77_INTERVENE_BANK_APPROVAL_REQD = "77";
        public static final String _78_INTERVENE_PARTIAL_AMOUNT = "78";
        public static final String _81_CRYPTOGRAPHIC_ERROR = "81";
        public static final String _89_INVALID_TERMINAL = "89";
        public static final String _90_CUTOFF_IN_PROGRESS = "90";
        public static final String _91_ISSUER_OR_SWITCH_INOPERATIVE = "91";
        public static final String _92_ROUTING_ERROR = "92";
        public static final String _93_VIOLATION_OF_LAW = "93";
        public static final String _94_DUPLICATE_TRANSMISSION = "94";
        public static final String _95_RECONCILE_ERROR = "95";
        public static final String _96_SYSTEM_MALFUNCTION = "96";
        public static final String _98_EXCEEDS_CASHLIMIT = "98";
        /**/
        public static final String _L1_AMOUNT_EXCEEDED = "L1";
        public static final String _L2_CARD_USE_EXCEEDED = "L2";
        public static final String _L3_ICA_DISABLED = "L3";

        public static final String _X1_INVALID_TRACK = "X1";

        protected RspCode()
        {
        }
    }

    /**
     *
     */
    public static class PosCondCode
    {

        public static final String _00_NORMAL_PRESENTMENT = "00";
        public static final String _01_CUSTOMER_NOT_PRESENT = "01";
        public static final String _02_UNATTENDED_RETAIN_TERMINAL = "02";
        public static final String _03_MERCHANT_SUSPICIOUS = "03";
        public static final String _04_ECR_INTERFACE = "04";
        public static final String _05_CUSTOMER_PRESENT_CARD_NOT = "05";
        public static final String _06_PRE_AUTHORISED_REQ = "06";
        public static final String _07_TELEPHONE_DEVICE_REQ = "07";
        public static final String _08_MAIL_TELEPHONE_ORDER = "08";
        public static final String _09_POS_SECURITY_ALERT = "09";
        public static final String _10_CUSTOMER_ID_VERIFIED = "10";
        public static final String _11_SUSPECTED_FRAUD = "11";
        public static final String _12_SECURITY_REASONS = "12";
        public static final String _13_REPRESENTATION_OF_ITEM = "13";
        public static final String _14_PUBLIC_UTILITY_TERMINAL = "14";
        public static final String _15_CUSTOMER_TERMINAL = "15";
        public static final String _16_ADMIN_TERMINAL = "16";
        public static final String _17_RETURNED_ITEM = "17";
        public static final String _18_NO_CHEQUE_IN_ENVELOPE_RETURN = "18";
        public static final String _19_DEPOSIT_OUT_OF_BALANCE_RETURN = "19";
        public static final String _20_PAYMENT_OUT_OF_BALANCE_RETURN = "20";
        public static final String _21_MANUAL_REVERSAL = "21";
        public static final String _22_TERMINAL_ERROR_COUNTED = "22";
        public static final String _23_TERMINAL_ERROR_NOT_COUNTED = "23";
        public static final String _24_DEPOSIT_OUT_OF_BALANCE_APPLY = "24";
        public static final String _25_PAYMENT_OUT_OF_BALANCE_APPLY = "25";
        public static final String _26_WITHDRAWAL_ERROR_REVERSED = "26";
        public static final String _27_UNATTENDED_NO_RETAIN_TERMINAL = "27";
        public static final String _41_PARTIAL_APPROVAL_ALLOWED = "41";

        protected PosCondCode()
        {
        }
    }

    /**
     *
     */
    public static class NwrkMngInfoCode
    {

        public static final String _001_SIGN_ON = "001";
        public static final String _002_SIGN_OFF = "002";
        public static final String _003_TARGET_SYSTEM_UNAVAILABLE = "003";
        public static final String _004_BACK_UP_MODE = "004";
        public static final String _005_SPECIAL_INSTRUCTION = "005";
        public static final String _006_INITIATE_ALT_ROUTING = "006";
        public static final String _101_KEY_CHANGE = "101";
        public static final String _102_SECURITY_ALERT = "102";
        public static final String _103_PASSWORD_CHANGE = "103";
        public static final String _104_DEVICE_AUTHENTICATION = "104";
        public static final String _201_INITIATE_CUTOFF = "201";
        public static final String _202_CUTOFF_COMPLETE = "202";
        public static final String _301_ECHO_TEST = "301";

        protected NwrkMngInfoCode()
        {
        }
    }

    /**
     *
     */
    public static class MsgTypeStr
    {

        public static final String UNKNOWN = "0000";
        public static final String _0100_AUTH_REQ = "0100";
        public static final String _0101_AUTH_REQ_REP = "0101";
        public static final String _0110_AUTH_REQ_RSP = "0110";
        public static final String _0120_AUTH_ADV = "0120";
        public static final String _0121_AUTH_ADV_REP = "0121";
        public static final String _0130_AUTH_ADV_RSP = "0130";
        public static final String _0200_TRAN_REQ = "0200";
        public static final String _0201_TRAN_REQ_REP = "0201";
        public static final String _0210_TRAN_REQ_RSP = "0210";
        public static final String _0220_TRAN_ADV = "0220";
        public static final String _0221_TRAN_ADV_REP = "0221";
        public static final String _0230_TRAN_ADV_RSP = "0230";
        public static final String _0300_ACQUIRER_FILE_UPDATE_REQ = "0300";
        public static final String _0301_ACQUIRER_FILE_UPDATE_REQ_REP = "0301";
        public static final String _0310_ACQUIRER_FILE_UPDATE_REQ_RSP = "0310";
        public static final String _0320_ACQUIRER_FILE_UPDATE_ADV = "0320";
        public static final String _0321_ACQUIRER_FILE_UPDATE_ADV_REP = "0321";
        public static final String _0330_ACQUIRER_FILE_UPDATE_ADV_RSP = "0330";
        public static final String _0400_ACQUIRER_REV_REQ = "0400";
        public static final String _0401_ACQUIRER_REV_REQ_REP = "0401";
        public static final String _0410_ACQUIRER_REV_REQ_RSP = "0410";
        public static final String _0420_ACQUIRER_REV_ADV = "0420";
        public static final String _0421_ACQUIRER_REV_ADV_REP = "0421";
        public static final String _0430_ACQUIRER_REV_ADV_RSP = "0430";
        public static final String _0500_ACQUIRER_RECONCILE_REQ = "0500";
        public static final String _0501_ACQUIRER_RECONCILE_REQ_REP = "0501";
        public static final String _0510_ACQUIRER_RECONCILE_REQ_RSP = "0510";
        public static final String _0520_ACQUIRER_RECONCILE_ADV = "0520";
        public static final String _0521_ACQUIRER_RECONCILE_ADV_REP = "0521";
        public static final String _0530_ACQUIRER_RECONCILE_ADV_RSP = "0530";
        public static final String _0600_ADMIN_REQ = "0600";
        public static final String _0601_ADMIN_REQ_REP = "0601";
        public static final String _0610_ADMIN_REQ_RSP = "0610";
        public static final String _0620_ADMIN_ADV = "0620";
        public static final String _0621_ADMIN_ADV_REP = "0621";
        public static final String _0630_ADMIN_ADV_RSP = "0630";
        public static final String _0800_NWRK_MNG_REQ = "0800";
        public static final String _0801_NWRK_MNG_REQ_REP = "0801";
        public static final String _0810_NWRK_MNG_REQ_RSP = "0810";
        public static final String _0820_NWRK_MNG_ADV = "0820";
        public static final String _0830_NWRK_MNG_ADV_RSP = "0830";

        protected MsgTypeStr()
        {
        }
    }

    /**
     *
     */
    public static class Bit
    {

        public static final int FIRST = 1;
        public static final int MAP_EXTENDED = 1;
        public static final int FIRST_FIELD = 2;
        public static final int _002_PAN = 2;
        public static final int _003_PROCESSING_CODE = 3;
        public static final int _004_AMOUNT_TRANSACTION = 4;
        public static final int _005_AMOUNT_SETTLE = 5;
        public static final int _006_AMOUNT_CARDHOLDER_BILL = 6;
        public static final int _007_TRANSMISSION_DATE_TIME = 7;
        public static final int _008_AMOUNT_CARDHOLDER_BILL_FEE = 8;
        public static final int _009_CONV_RATE_SETTLE = 9;
        public static final int _010_CONV_RATE_CARDHOLDER_BILL = 10;
        public static final int _011_SYSTEMS_TRACE_AUDIT_NR = 11;
        public static final int _012_TIME_LOCAL = 12;
        public static final int _013_DATE_LOCAL = 13;
        public static final int _014_DATE_EXPIRATION = 14;
        public static final int _015_DATE_SETTLE = 15;
        public static final int _016_DATE_CONV = 16;
        public static final int _017_DATE_CAPTURE = 17;
        public static final int _018_MERCHANT_TYPE = 18;
        public static final int _019_ACQUIRING_INST_COUNTRY_CODE = 19;
        public static final int _020_PAN_EXTENDED_COUNTRY_CODE = 20;
        public static final int _021_FORWARDING_INST_COUNTRY_CODE = 21;
        public static final int _022_POS_ENTRY_MODE = 22;
        public static final int _023_CARD_SEQ_NR = 23;
        public static final int _024_NETWORK_INTL_ID = 24;
        public static final int _025_POS_CONDITION_CODE = 25;
        public static final int _026_POS_PIN_CAPTURE_CODE = 26;
        public static final int _027_AUTH_ID_RSP_LEN = 27;
        public static final int _028_AMOUNT_TRAN_FEE = 28;
        public static final int _029_AMOUNT_SETTLE_FEE = 29;
        public static final int _030_AMOUNT_TRAN_PROC_FEE = 30;
        public static final int _031_AMOUNT_SETTLE_PROC_FEE = 31;
        public static final int _032_ACQUIRING_INST_ID_CODE = 32;
        public static final int _033_FORWARDING_INST_ID_CODE = 33;
        public static final int _034_PAN_EXTENDED = 34;
        public static final int _035_TRACK_2_DATA = 35;
        public static final int _036_TRACK_3_DATA = 36;
        public static final int _037_RETRIEVAL_REF_NR = 37;
        public static final int _038_AUTH_ID_RSP = 38;
        public static final int _039_RSP_CODE = 39;
        public static final int _040_SERVICE_RESTRICTION_CODE = 40;
        public static final int _041_CARD_ACCEPTOR_TERM_ID = 41;
        public static final int _042_CARD_ACCEPTOR_ID_CODE = 42;
        public static final int _043_CARD_ACCEPTOR_NAME_LOC = 43;
        public static final int _044_ADDITIONAL_RSP_DATA = 44;
        public static final int _045_TRACK_1_DATA = 45;
        public static final int _046_ADDITIONAL_DATA_ISO = 46;
        public static final int _047_ADDITIONAL_DATA_NATIONAL = 47;
        public static final int _048_ADDITIONAL_DATA = 48;
        public static final int _049_CURRENCY_CODE_TRAN = 49;
        public static final int _050_CURRENCY_CODE_SETTLE = 50;
        public static final int _051_CURRENCY_CODE_BILL = 51;
        public static final int _052_PIN_DATA = 52;
        public static final int _053_SECURITY_INFO = 53;
        public static final int _054_ADDITIONAL_AMOUNTS = 54;
        public static final int _055_EMV_DATA = 55;
        public static final int _060_ADVICE_REASON_CODE = 60;
        public static final int _061_POS_DATA = 61;
        public static final int _062_TRANS_ID = 62;
        public static final int _063_PRIVATE_USE = 63;
        public static final int _064_MAC_NORMAL = 64;
        public static final int _066_SETTLEMENT_CODE = 66;
        public static final int _067_EXTENDED_PAYMENT_CODE = 67;
        public static final int _068_RECEIVING_INST_COUNTRY_CODE = 68;
        public static final int _069_SETTLE_INST_COUNTRY_CODE = 69;
        public static final int _070_NETWORK_MNG_INFO_CODE = 70;
        public static final int _071_MSG_NR = 71;
        public static final int _072_MSG_NR_LAST = 72;
        public static final int _073_DATE_ACTION = 73;
        public static final int _074_NR_CR = 74;
        public static final int _075_NR_CR_REV = 75;
        public static final int _076_NR_DT = 76;
        public static final int _077_NR_DT_REV = 77;
        public static final int _078_NR_TRANSFER = 78;
        public static final int _079_NR_TRANSFER_REV = 79;
        public static final int _080_NR_INQUIRIES = 80;
        public static final int _081_NR_AUTH = 81;
        public static final int _082_AMOUNT_CR_PROC_FEE = 82;
        public static final int _083_AMOUNT_CR_TRAN_FEE = 83;
        public static final int _084_AMOUNT_DT_PROC_FEE = 84;
        public static final int _085_AMOUNT_DT_TRAN_FEE = 85;
        public static final int _086_AMOUNT_CR = 86;
        public static final int _087_AMOUNT_CR_REV = 87;
        public static final int _088_AMOUNT_DT = 88;
        public static final int _089_AMOUNT_DT_REV = 89;
        public static final int _090_ORIGINAL_DATA_ELEMENTS = 90;
        public static final int _091_FILE_UPDATE_CODE = 91;
        public static final int _092_FILE_SECURITY_CODE = 92;
        public static final int _093_RSP_IND = 93;
        public static final int _094_SERVICE_IND = 94;
        public static final int _095_REPLACEMENT_AMOUNTS = 95;
        public static final int _096_MSG_SECURITY_CODE = 96;
        public static final int _097_AMOUNT_NET_SETTLE = 97;
        public static final int _098_PAYEE = 98;
        public static final int _099_SETTLE_INST_ID_CODE = 99;
        public static final int _100_RECEIVING_INST_ID_CODE = 100;
        public static final int _101_FILE_NAME = 101;
        public static final int _102_ACCOUNT_ID_1 = 102;
        public static final int _103_ACCOUNT_ID_2 = 103;
        public static final int _104_TRAN_DESCRIPTION = 104;
        public static final int _120_TRAN_DATA_REQ = 120;
        public static final int _121_TRAN_DATA_RSP = 121;
        public static final int _122_NODE_INFO = 122;
        public static final int _123_EXTENDED_FIELD = 123;
        public static final int _124_PREV_ACQ_NODE_KEY = 124;
        public static final int _128_MAC_EXTENDED = 128;

        protected Bit()
        {
        }
    }

    /*
     *
     */
    public static class ExtendedBit
    {

        public static final int _001_ACQ_NODE_KEY = 1;
        public static final int _002_ORG_ACQ_NODE_KEY = 2;
        public static final int _003_STORE_ID = 3;
        public static final int _004_DEVICE_ID = 4;
        public static final int _005_ACQ_PART_NAME = 5;
        public static final int _006_TERM_BATCH_NR = 6;
        public static final int _007_TRAN_NR = 7;
        public static final int _008_RETAILER_ID = 8;
        public static final int _009_PREV_TRAN_NR = 9;
        public static final int _010_ACQ_NODE_KEY_NAME = 10;
        public static final int _011_ORIGINATOR_TYPE = 11;
        public static final int _012_MOBILE_NR = 12;
        public static final int _013_IMEI = 13;
        public static final int _014_USER_NAME = 14;
        public static final int _015_PASSWORD = 15;
        public static final int _016_PIN_KEY = 16;
        public static final int _017_DATA_KEY = 17;
        public static final int _018_CVV2 = 18;
        public static final int _020_UCAF_DATA = 20;
        public static final int _021_XID = 21;
        public static final int _022_CAVV = 22;
        public static final int _023_ECI = 23;
        public static final int _024_ALGO = 24;
        public static final int _025_3DS_ENROLLMENT = 25;
        public static final int _026_3DS_AUTHENTICATION_RETURN_CODE = 26;

        protected ExtendedBit()
        {
        }
    }

    /**
     *
     */
    public static class OriginatorType
    {

        public static final String _001_ATM = "001";
        public static final String _002_IPOS = "002";
        public static final String _003_POS = "003";
        public static final String _004_MPOS = "004";
        public static final String _005_PG = "005";
        public static final String _006_SI = "006";
        public static final String _101_HOST = "101";
    }

    /**
     *
     * @author Abhy
     *
     */
    public static class TranState
    {

        public static final int _000_TRAN_INPROGRESS = 0;
        public static final int _001_TRAN_COMPLETE = 1;
        public static final int _002_TRAN_ABORTED = 2;

        protected TranState()
        {
        }
    }

    /**
     *
     */
    public static class AmountType
    {

        public static final String _01_LEDGER_BALANCE = "01";
        public static final String _02_AVAILABLE_BALANCE = "02";
        public static final String _03_AMOUNT_OWING = "03";
        public static final String _04_AMOUNT_DUE = "04";
        public static final String _40_CASH = "40";
        public static final String _41_GOODS_SERVICES = "41";
        public static final String _53_APPROVED = "53";
        public static final String _56_AMOUNT_TIP = "56";
        public static final String _90_AVAILABLE_CREDIT = "90";
        public static final String _91_CREDIT_LIMIT = "91";

        protected AmountType()
        {
        }
    }

    /**
     *
     */
    public static class AccountType
    {

        public static final String _00_DEFAULT = "00";
        public static final String _10_SAVINGS = "10";
        public static final String _20_CHECK = "20";
        public static final String _30_CREDIT = "30";
        public static final String _40_UNIVERSAL = "40";
        public static final String _50_INVESTMENT = "50";
        public static final String _60_ELECTRONIC_PURSE_DEFAULT = "60";

        protected AccountType()
        {
        }
    }
    /**
     *
     */
    JSONObject isoobj;
    JSONParser isoparser;

    public Iso8583DataBus()
    {
        isoobj = new JSONObject();
    }

    /**
     *
     * @param fieldnum
     * @param value
     * @return
     */
    public boolean setMsgType(String value)
    {
        String fieldName = "MsgType";

        isoobj.remove(fieldName);

        isoobj.put(fieldName, value);

        return true;
    }

    /**
     *
     * @param fieldnum
     * @return
     */
    public String getMsgType()
    {
        String fieldName = "MsgType";
        return (String) isoobj.get(fieldName);
    }

    /**
     *
     * @param fieldnum
     * @param value
     * @return
     */
    public boolean setField(int fieldnum, String value)
    {
        if (fieldnum < 1 || fieldnum > 128)
        {
            return false;
        }

        if (value != null && value.length() > 0)
        {
            removeField(fieldnum);

            String fieldName = "F"
                    + FormatData.padleft(Integer.toString(fieldnum), 3, '0');
            isoobj.put(fieldName, value);
        }

        return true;
    }

    /**
     *
     * @param fieldnum
     * @return
     */
    public String getField(int fieldnum)
    {
        if (fieldnum < 1 || fieldnum > 128)
        {
            return null;
        }

        String fieldName = "F"
                + FormatData.padleft(Integer.toString(fieldnum), 3, '0');
        String value = (String) isoobj.get(fieldName);

        if (value == null)
        {
            return "";
        }

        return value;
    }

    /**
     *
     * @param fieldnum
     */
    public void removeField(int fieldnum)
    {
        if (fieldnum < 1 || fieldnum > 128)
        {
            return;
        }

        String fieldName = "F"
                + FormatData.padleft(Integer.toString(fieldnum), 3, '0');
        isoobj.remove(fieldName);
    }

    /**
     *
     * @param trannr
     */
    public void setTranNr(long trannr)
    {
        isoobj.put("TranNr", trannr);
    }

    /**
     *
     * @return
     */
    public long getTranNr()
    {
        return Long.parseLong(getExtendedField(ExtendedBit._007_TRAN_NR));
    }

    /**
     *
     * @return
     */
    public String toMsg()
    {
        return isoobj.toJSONString();
    }

    /**
     *
     * @return
     */
    public String dumpMsg()
    {
        StringBuilder str = new StringBuilder("");
        String value;

        str.append("\n").append(getMsgType()).append(":\n");

        // print all the fields except Extended Field 123
        for (int i = 2; i < 129; i++)
        {
            if (i != 123)
            {
                value = getField(i);

                if (i == Iso8583DataBus.Bit._002_PAN || i
                        == Iso8583DataBus.Bit._035_TRACK_2_DATA)
                {
                    if (value != null && value.trim().length() > 0)
                    {
                        str.append("\t[F").append(Utility.resize(Integer.
                                toString(i), 3, "0",
                                false)).append("] = [").append(FormatData.
                                protect(getField(i))).append("]\n");
                    }
                } else if (i == Iso8583DataBus.Bit._045_TRACK_1_DATA)
                {
                    if (value != null && value.trim().length() > 0)
                    {
                        String temp = getField(i);
                        temp = Utility.resize("*", temp.length(), "*", true);
                        str.append("\t[F").append(Utility.resize(Integer.
                                toString(i), 3, "0",
                                false)).append("] = [").append(temp).append(
                                "]\n");
                    }
                } else if (i == Iso8583DataBus.Bit._052_PIN_DATA)
                {
                    if (value != null && value.trim().length() > 0)
                    {
                        str.append("\t[F").append(Utility.resize(Integer.
                                toString(i), 3, "0",
                                false)).append("] = [****************]\n");
                    }
                } else if (value != null && value.trim().length() > 0)
                {
                    str.append("\t[F").append(Utility.resize(Integer.
                            toString(i), 3, "0",
                            false)).append("] = [").append(getField(i)).
                            append("]\n");
                }
            }
        }

        // print 123 in the end
        str.append("\t[F123 Data ...]\n");
        for (int i = 1; i < 129; i++)
        {
            value = getExtendedField(i);

            if (i == Iso8583DataBus.ExtendedBit._018_CVV2)
            {
                if (value != null && value.trim().length() > 0)
                {
                    str.append("\t\t[F123_").append(Utility.resize(Integer.
                            toString(i), 3, "0", false)).append("] = [").append(
                            FormatData.protect(getExtendedField(i))).append(
                            "]\n");
                }
            } else if (value != null && value.trim().length() > 0)
            {
                str.append("\t\t[F123_").append(Utility.resize(Integer.
                        toString(i), 3, "0", false)).append("] = [").append(
                        getExtendedField(i)).append("]\n");
            }
        }

        return str.toString();
    }

    /**
     *
     * @param msg
     * @return 
     */
    public boolean parseMsg(String msg)
    {
        isoparser = new JSONParser();
        try
        {
            isoobj = (JSONObject) isoparser.parse(msg);
        } catch (ParseException ex)
        {
            Logger.getLogger(Iso8583DataBus.class.getName()).log(Level.SEVERE,
                    null, ex);
            System.out.printf("[%s]-[JSON Parsing Error]-[%s]", Utility.
                    getFormattedDateTime(), msg);
            
            return false;
        }
        
        return true;
    }

    /**
     *
     * @param fieldnum
     * @return
     */
    public boolean isFieldSet(int fieldnum)
    {
        String fieldValue = getField(fieldnum);

        if (fieldValue != null && fieldValue.trim().length() > 0)
        {
            return true;
        }

        return false;
    }

    /**
     *
     */
    public boolean isExtendedFieldSet(int fieldnum)
    {
        String fieldValue = getExtendedField(fieldnum);

        if (fieldValue != null && fieldValue.trim().length() > 0)
        {
            return true;
        }

        return false;
    }

    /**
     *
     * @return
     */
    public void setRspMsgType()
    {
        if (getMsgType() != null)
        {
            setMsgType("0"
                    + Integer.toString(Integer.parseInt(getMsgType()) + 10));
        } else
        {
            setMsgType("0010");
        }
    }

    /**
     *
     * @param fieldnum
     * @param value
     * @return
     */
    public boolean setExtendedField(int fieldnum, String value)
    {
        if (fieldnum < 1 || fieldnum > 128)
        {
            return false;
        }

        if (value != null)
        {
            removeExtendedField(fieldnum);

            String fieldName = "F123_"
                    + FormatData.padleft(Integer.toString(fieldnum), 3, '0');
            isoobj.put(fieldName, value);
        }

        return true;
    }

    /**
     *
     * @param fieldnum
     * @return
     */
    public String getExtendedField(int fieldnum)
    {
        if (fieldnum < 1 || fieldnum > 128)
        {
            return null;
        }

        String fieldName = "F123_"
                + FormatData.padleft(Integer.toString(fieldnum), 3, '0');
        String value = (String) isoobj.get(fieldName);

        if (value == null)
        {
            return " ";
        }

        return value;
    }

    /**
     *
     * @param fieldnum
     */
    public void removeExtendedField(int fieldnum)
    {
        if (fieldnum < 1 || fieldnum > 128)
        {
            return;
        }

        String fieldName = "F123_"
                + FormatData.padleft(Integer.toString(fieldnum), 3, '0');
        isoobj.remove(fieldName);
    }

    /**
     *
     * @return
     */
    public NodeInfo getNodeInfo()
    {
        NodeInfo ni = new NodeInfo(getField(Bit._122_NODE_INFO));

        return ni;
    }

    /**
     *
     * @return
     */
    public ProcessingCode getProcessingCode()
    {
        ProcessingCode pcode = new ProcessingCode(getField(
                Bit._003_PROCESSING_CODE));

        return pcode;
    }

    /**
     *
     * @return
     */
    public boolean isApprovedResponse()
    {
        boolean is_ar = false;
        String rsp_code = getField(Bit._039_RSP_CODE);
        if (rsp_code != null && RspCode.isApprove(rsp_code))
        {
            is_ar = true;
        }
        return is_ar;
    }

    /**
     *
     * @param mti
     * @param stan
     * @param transdatetime
     * @param acqinsttid
     * @param fwdinsttid
     */
    public void setField090(String mti, String stan, String transdatetime,
            String acqinsttid, String fwdinsttid)
    {
        mti = Utility.resize(mti, 4, "0", false);
        stan = Utility.resize(stan, 6, "0", false);
        transdatetime = Utility.resize(transdatetime, 10, "0", false);
        acqinsttid = Utility.resize(acqinsttid, 11, "0", false);
        fwdinsttid = Utility.resize(fwdinsttid, 11, "0", false);

        String f090 = mti + stan + transdatetime + acqinsttid + fwdinsttid;

        setField(Iso8583DataBus.Bit._090_ORIGINAL_DATA_ELEMENTS, f090);
    }

    /**
     *
     * @return
     */
    public PosDataCode getPosDataCode()
    {
        String pdc = getField(Bit._061_POS_DATA);
        PosDataCode posdatacode = new PosDataCode(pdc);

        return posdatacode;
    }

    /**
     *
     * @return @throws Exception
     */
    public PosEntryMode getPosEntryMode() throws Exception
    {
        return new PosEntryMode(getField(Iso8583DataBus.Bit._022_POS_ENTRY_MODE));
    }

    public String getServiceRestrictionCode()
    {
        return getField(Iso8583DataBus.Bit._040_SERVICE_RESTRICTION_CODE);
    }

    public PosGeographicData getPosGeographicData()
    {
        return new PosGeographicData("");
    }
}
