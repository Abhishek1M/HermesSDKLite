package messagebus;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import utils.Translate;
import utils.Utility;
import utils.XHSMFieldParseError;

/**
 *
 * @author Abhy
 */
public class ThalesHSM {

	JSONObject hsmmsg;
	JSONParser hsmmsgparser;

	/**
	 *
	 */
	public ThalesHSM() {
		hsmmsg = new JSONObject();
		hsmmsgparser = new JSONParser();
	}

	/**
	 *
	 */
	public class Fields {

		public static final String _A2_PRINT_FIELD = "_A2_PRINT_FIELD";
		public static final String _PA_FORMATTING_DATA = "_PA_FORMATTING_DATA";
		public static final String _A4_N_KEY_COMPONENTS = "_A4_N_KEY_COMPONENTS";
		public static final String _ACCOUNT_NR = "_ACCOUNT_NR";
		public static final String _BDK = "_BDK";
		public static final String _CMD = "CMD";
		public static final String _CURR_TMK_TPK_PVK = "_CURR_TMK_TPK_PVK";
		public static final String _DEST_PIN_BLK = "_DEST_PIN_BLK";
		public static final String _DEST_PIN_BLK_FORMAT = "_DEST_PIN_BLK_FORMAT";
		public static final String _ZMK = "_DEST_ZMK";
		public static final String _DATA = "_DATA";
		public static final String _DEST_ZPK = "_DEST_ZPK";
		public static final String _DSP_FIRMWARE_NR = "_DSP_FIRMWARE_NR";
		public static final String _DSP_FITTED = "_DSP_FITTED";
		public static final String _ERROR_CODE = "_ERROR_CODE";
		public static final String _ETHERNET_TYPE = "_ETHERNET_TYPE";
		public static final String _FIRMWARE_NR = "_FIRMWARE_NR";
		public static final String _HEADER = "HEADER";
		public static final String _IO_BUFFER_SIZE = "_IO_BUFFER_SIZE";
		public static final String _KEY = "_KEY";
		public static final String _KEY_CHECK_VALUE = "_KEY_CHECK_VALUE";
		public static final String _KEY_SCHEME = "_KEY_SCHEME";
		public static final String _KEY_TYPE = "_KEY_TYPE";
		public static final String _KEY_UNDER_ZMK = "_ZMK";
		public static final String _KEY_LENGTH = "_KEY_LENGTH";
		public static final String _KSN = "_KSN";
		public static final String _KSN_DESCRIPTOR = "_KSN_DESCRIPTOR";
		public static final String _MAX_PIN_LEN = "_MAX_PIN_LEN";
		public static final String _MODE = "_MODE";
		public static final String _NEW_KEY_UNDER_CURR_KEY = "_NEW_KEY_UNDER_CURR_KEY";
		public static final String _NEW_KEY_UNDER_LMK = "_NEW_KEY_UNDER_LMK";
		public static final String _NE_PRINT_FIELD = "_NE_PRINT_FIELD";
		public static final String _NR_KEY_COMPONENTS = "_NR_KEY_COMPONENTS";
		public static final String _NR_TCP_SOCKETS = "_NR_TCP_SOCKETS";
		public static final String _PIN_LEN = "_PIN_LEN";
		public static final String _SOURCE_PIN_BLK = "_SOURCE_PIN_BLK";
		public static final String _SOURCE_PIN_BLK_FORMAT = "_SOURCE_PIN_BLK_FORMAT";
		public static final String _SOURCE_TPK = "_SOURCE_TPK";
		public static final String _SOURCE_ZPK = "_SOURCE_ZPK";

		protected Fields() {
		}
	}

	/**
	 *
	 */
	public class HSMCommands {

		public static final String _A0_GEN_KEY = "A0";
		public static final String _A1_GEN_KEY = "A1";
		public static final String _A2_GEN_PRINT_COMPONENT = "A2";
		public static final String _A3_GEN_PRINT_COMPONENT = "A3";
		public static final String _A4_FORM_KEY_ENC_COMPONENTS = "A4";
		public static final String _A5_FORM_KEY_ENC_COMPONENTS = "A5";
		public static final String _A6_IMPORT_KEY = "A6";
		public static final String _A7_IMPORT_KEY = "A7";
		public static final String _A8_EXPORT_KEY = "A8";
		public static final String _A9_EXPORT_KEY = "A9";
		public static final String _CA_XLATE_TPK_ZPK = "CA";
		public static final String _CB_XLATE_TPK_ZPK = "CB";
		public static final String _CC_XLATE_ZPK_ZPK = "CC";
		public static final String _CD_XLATE_ZPK_ZPK = "CD";
		public static final String _CI_XLATE_BDK_ZPK = "CI";
		public static final String _CJ_XLATE_BDK_ZPK = "CJ";
		public static final String _CW_GEN_VISA_CVV = "CW";
		public static final String _CX_GEN_VISA_CVV = "CX";
		public static final String _CY_VERIFY_VISA_CVV = "CY";
		public static final String _CZ_VERIFY_VISA_CVV = "CZ";
		public static final String _DE_GEN_PIN_OFFSET = "DE";
		public static final String _DF_GEN_PIN_OFFSET = "DF";
		public static final String _EA_VERIFY_IBM_PIN = "EA";
		public static final String _EB_VERIFY_IBM_PIN = "EB";
		public static final String _EE_DERIVE_IBM_PIN = "EE";
		public static final String _EF_DERIVE_IBM_PIN = "EF";
		public static final String _FA_XLATE_ZPK_ZMK_ZMK = "FA";
		public static final String _FB_XLATE_ZPK_ZMK_ZMK = "FB";
		public static final String _FE_XLATE_TMK_TPK_PVK_ZMK = "FE";
		public static final String _FF_XLATE_TMK_TPK_PVK_ZMK = "FF";
		public static final String _G0_XLATE_BDK_ZPK_TDES = "G0";
		public static final String _G1_XLATE_BDK_ZPK_TDES = "G1";
		public static final String _HC_GEN_TMK_TPK_PVK = "HC";
		public static final String _HD_GEN_TMK_TPK_PVK = "HD";
		public static final String _JA_GEN_RANDOM_PIN = "JA";
		public static final String _JB_GEN_RANDOM_PIN = "JB";
		public static final String _M0_ENCRYPT_DATA = "M0";
		public static final String _M1_ENCRYPT_DATA = "M1";
		public static final String _M2_DECRYPT_DATA = "M2";
		public static final String _M3_DECRYPT_DATA = "M3";
		public static final String _NE_GENERATE_PRINT_KEY = "NE";
		public static final String _NF_GENERATE_PRINT_KEY = "NF";
		public static final String _NZ_GENERATE_PRINT_KEY = "NZ";
		public static final String _NO_HSM_STATUS = "NO";
		public static final String _NP_HSM_STATUS = "NP";
		public static final String _PA_LOAD_FORMATTING_DATA_HSM = "PA";
		public static final String _PB_LOAD_FORMATTING_DATA_HSM = "PB";
		public static final String _BU_GENERATE_CHECKSUM = "BU";
		public static final String _BV_GENERATE_CHECKSUM = "BV";

		protected HSMCommands() {
		}
	}

	/**
	 *
	 * @param data
	 * @return
	 */
	public boolean parseMsg(String data) {
		try {
			hsmmsg = (JSONObject) hsmmsgparser.parse(data);
		} catch (ParseException ex) {
			Logger.getLogger(ThalesHSM.class.getName()).log(Level.SEVERE, null, ex);

			return false;
		}

		return true;
	}

	/**
	 *
	 * @return
	 */
	public String getMsgToHSM(int msghdrlen) {
		String msgtohsm = null;

		String cmd = getCommand();

		if (cmd.trim().length() == 0)
			return "NOK";

		switch (cmd) {
		case HSMCommands._A0_GEN_KEY:
			msgtohsm = getA0Request(msghdrlen);
			break;
		case HSMCommands._A2_GEN_PRINT_COMPONENT:
			msgtohsm = getA2Request(msghdrlen);
			break;
		case HSMCommands._A4_FORM_KEY_ENC_COMPONENTS:
			msgtohsm = getA4Request(msghdrlen);
			break;
		case HSMCommands._A6_IMPORT_KEY:
			msgtohsm = getA6Request(msghdrlen);
			break;
		case HSMCommands._A8_EXPORT_KEY:
			msgtohsm = getA8Request(msghdrlen);
			break;
		case HSMCommands._CA_XLATE_TPK_ZPK:
			msgtohsm = getCARequest(msghdrlen);
			break;
		case HSMCommands._CC_XLATE_ZPK_ZPK:
			msgtohsm = getCCRequest(msghdrlen);
			break;
		case HSMCommands._CI_XLATE_BDK_ZPK:
			msgtohsm = getCIRequest(msghdrlen);
			break;
		case HSMCommands._CW_GEN_VISA_CVV:
			break;
		case HSMCommands._CY_VERIFY_VISA_CVV:
			break;
		case HSMCommands._DE_GEN_PIN_OFFSET:
			break;
		case HSMCommands._EA_VERIFY_IBM_PIN:
			break;
		case HSMCommands._EE_DERIVE_IBM_PIN:
			break;
		case HSMCommands._FA_XLATE_ZPK_ZMK_ZMK:
			msgtohsm = getFARequest(msghdrlen);
			break;
		case HSMCommands._FE_XLATE_TMK_TPK_PVK_ZMK:
			msgtohsm = getFERequest(msghdrlen);
			break;
		case HSMCommands._G0_XLATE_BDK_ZPK_TDES:
			msgtohsm = getG0Request(msghdrlen);
			break;
		case HSMCommands._HC_GEN_TMK_TPK_PVK:
			msgtohsm = getHCRequest(msghdrlen);
			break;
		case HSMCommands._JA_GEN_RANDOM_PIN:
			break;
		case HSMCommands._M0_ENCRYPT_DATA:
			msgtohsm = getM0Request(msghdrlen);
			break;
		case HSMCommands._M2_DECRYPT_DATA:
			msgtohsm = getM2Request(msghdrlen);
			break;
		case HSMCommands._NE_GENERATE_PRINT_KEY:
			msgtohsm = getNERequest(msghdrlen);
			break;
		case HSMCommands._NO_HSM_STATUS:
			msgtohsm = getNORequest(msghdrlen);
			break;
		case HSMCommands._PA_LOAD_FORMATTING_DATA_HSM:
			msgtohsm = getPARequest(msghdrlen);
			break;
		case HSMCommands._BU_GENERATE_CHECKSUM:
			msgtohsm = getBURequest(msghdrlen);
			break;
		}

		return msgtohsm;
	}

	/**
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getHCRequest(int msghdrlen) {
		String hc_request = Utility.resize("0", msghdrlen, "0", false);

		hc_request = hc_request + HSMCommands._HC_GEN_TMK_TPK_PVK + getField(Fields._CURR_TMK_TPK_PVK) + ";XU1";

		return hc_request;
	}

	/**
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getCARequest(int msghdrlen) {
		String ca_request = Utility.resize("0", msghdrlen, "0", false);

		ca_request = ca_request + HSMCommands._CA_XLATE_TPK_ZPK + getField(Fields._SOURCE_TPK)
				+ getField(Fields._DEST_ZPK) + getField(Fields._MAX_PIN_LEN) + getField(Fields._SOURCE_PIN_BLK)
				+ getField(Fields._SOURCE_PIN_BLK_FORMAT) + getField(Fields._DEST_PIN_BLK_FORMAT)
				+ getField(Fields._ACCOUNT_NR);

		return ca_request;
	}

	/**
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getCCRequest(int msghdrlen) {
		String cc_request = Utility.resize("0", msghdrlen, "0", false);

		cc_request = cc_request + HSMCommands._CC_XLATE_ZPK_ZPK + getField(Fields._SOURCE_ZPK)
				+ getField(Fields._DEST_ZPK) + getField(Fields._MAX_PIN_LEN) + getField(Fields._SOURCE_PIN_BLK)
				+ getField(Fields._SOURCE_PIN_BLK_FORMAT) + getField(Fields._DEST_PIN_BLK_FORMAT)
				+ getField(Fields._ACCOUNT_NR);

		return cc_request;
	}

	/**
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getCIRequest(int msghdrlen) {
		String ci_request = Utility.resize("0", msghdrlen, "0", false);

		ci_request = ci_request + HSMCommands._CI_XLATE_BDK_ZPK + getField(Fields._BDK) + getField(Fields._DEST_ZPK)
				+ getField(Fields._KSN_DESCRIPTOR) + getField(Fields._KSN) + getField(Fields._SOURCE_PIN_BLK)
				+ getField(Fields._DEST_PIN_BLK_FORMAT) + getField(Fields._ACCOUNT_NR);

		return ci_request;
	}

	/**
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getG0Request(int msghdrlen) {
		String g0_request = Utility.resize("0", msghdrlen, "0", false);

		g0_request = g0_request + HSMCommands._G0_XLATE_BDK_ZPK_TDES + getField(Fields._BDK)
				+ getField(Fields._DEST_ZPK) + getField(Fields._KSN_DESCRIPTOR) + getField(Fields._KSN)
				+ getField(Fields._SOURCE_PIN_BLK) + getField(Fields._SOURCE_PIN_BLK_FORMAT)
				+ getField(Fields._DEST_PIN_BLK_FORMAT) + getField(Fields._ACCOUNT_NR);

		return g0_request;
	}

	/**
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getM0Request(int msghdrlen) {
		String m2_request = Utility.resize("0", msghdrlen, "0", false);
		String data = getField(Fields._DATA);
		int len = data.trim().length();
		data = new StringBuilder().append(Translate.resize(Integer.toHexString(len), 4, '0', false)).append(data)
				.toString();

		m2_request = new StringBuilder().append(m2_request).append(HSMCommands._M0_ENCRYPT_DATA).append("012100A")
				.append(getField(Fields._KEY)).append("0000000000000000").append(data).toString();

		return m2_request;
	}

	/**
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getM2Request(int msghdrlen) {
		String m2_request = Utility.resize("0", msghdrlen, "0", false);
		String data = getField(Fields._DATA);
		int len = data.trim().length();
		data = new StringBuilder().append(Translate.resize(Integer.toHexString(len), 4, '0', false)).append(data)
				.toString();

		if (getField(Fields._KSN).trim().length() > 0) {
			m2_request = new StringBuilder().append(m2_request).append(HSMCommands._M2_DECRYPT_DATA).append("0111")
					.append(getField(Fields._KEY_TYPE)).append(getField(Fields._KEY))
					.append(getField(Fields._KSN_DESCRIPTOR)).append(getField(Fields._KSN)).append("0000000000000000")
					.append(data).toString();
		} else {
			m2_request = new StringBuilder().append(m2_request).append(HSMCommands._M2_DECRYPT_DATA).append("0121")
					.append(getField(Fields._KEY_TYPE)).append(getField(Fields._KEY)).append("0000000000000000")
					.append(data).toString();
		}

		return m2_request;
	}

	/**
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getNERequest(int msghdrlen) {
		String ne_request = Utility.resize("0", msghdrlen, "0", false);

		ne_request = ne_request + HSMCommands._NE_GENERATE_PRINT_KEY + getField(Fields._NE_PRINT_FIELD);

		return ne_request;
	}

	/**
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getA0Request(int msghdrlen) {
		String ne_request = Utility.resize("0", msghdrlen, "0", false);

		ne_request = ne_request + HSMCommands._A0_GEN_KEY + getField(Fields._MODE) + getField(Fields._KEY_TYPE)
				+ getField(Fields._KEY_SCHEME) + getField(Fields._ZMK);
		if (getField(Fields._MODE).equals("1")) {
			ne_request = ne_request + "X";
		}

		return ne_request;
	}

	/**
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getA2Request(int msghdrlen) {
		String ne_request = Utility.resize("0", msghdrlen, "0", false);

		ne_request = ne_request + HSMCommands._A2_GEN_PRINT_COMPONENT + getField(Fields._KEY_TYPE)
				+ getField(Fields._KEY_SCHEME) + getField(Fields._A2_PRINT_FIELD);

		return ne_request;
	}

	/**
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getA4Request(int msghdrlen) {
		String ne_request = Utility.resize("0", msghdrlen, "0", false);

		ne_request = ne_request + HSMCommands._A4_FORM_KEY_ENC_COMPONENTS + getField(Fields._NR_KEY_COMPONENTS)
				+ getField(Fields._KEY_TYPE) + getField(Fields._KEY_SCHEME) + getField(Fields._A4_N_KEY_COMPONENTS);

		return ne_request;
	}

	/**
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getA6Request(int msghdrlen) {
		String ne_request = Utility.resize("0", msghdrlen, "0", false);

		ne_request = ne_request + HSMCommands._A6_IMPORT_KEY + getField(Fields._KEY_TYPE) + getField(Fields._ZMK)
				+ getField(Fields._KEY_UNDER_ZMK) + getField(Fields._KEY_SCHEME);

		return ne_request;
	}

	/**
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getA8Request(int msghdrlen) {
		String ne_request = Utility.resize("0", msghdrlen, "0", false);

		ne_request = ne_request + HSMCommands._A8_EXPORT_KEY + getField(Fields._KEY_TYPE) + getField(Fields._ZMK)
				+ getField(Fields._NEW_KEY_UNDER_LMK) + getField(Fields._KEY_SCHEME);

		return ne_request;
	}

	/**
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getNORequest(int msghdrlen) {
		String ne_request = Utility.resize("0", msghdrlen, "0", false);

		ne_request = ne_request + HSMCommands._NO_HSM_STATUS + "00";

		return ne_request;
	}

	/**
	 * Fields Used: - HSMCommands._PA_LOAD_FORMATTING_DATA_HSM -
	 * HSMJSON.Fields._PA_FORMATTING_DATA
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getPARequest(int msghdrlen) {
		String pa_request = Utility.resize("0", msghdrlen, "0", false);

		pa_request = pa_request + HSMCommands._PA_LOAD_FORMATTING_DATA_HSM
				+ getField(ThalesHSM.Fields._PA_FORMATTING_DATA);

		return pa_request;
	}

	/**
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getFARequest(int msghdrlen) {
		String fe_request = Utility.resize("0", msghdrlen, "0", false);

		fe_request = fe_request + HSMCommands._FA_XLATE_ZPK_ZMK_ZMK + getField(Fields._ZMK)
				+ getField(Fields._SOURCE_ZPK) + ";0U1";

		return fe_request;
	}

	/**
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getFERequest(int msghdrlen) {
		String fe_request = Utility.resize("0", msghdrlen, "0", false);

		fe_request = fe_request + HSMCommands._FE_XLATE_TMK_TPK_PVK_ZMK + getField(Fields._ZMK)
				+ getField(Fields._CURR_TMK_TPK_PVK) + ";U01";

		return fe_request;
	}

	/**
	 *
	 * @param msghdrlen
	 * @return
	 */
	private String getBURequest(int msghdrlen) {
		String ne_request = Utility.resize("0", msghdrlen, "0", false);

		String keytype = getKeyIdentifier(getField(Fields._KEY_TYPE));
		String keyscheme;

		switch (getField(Fields._KEY).length()) {
		case 33:
			keyscheme = "X";
			break;
		case 48:
			keyscheme = "Y";
			break;
		default:
			keyscheme = "Z";
			break;
		}

		ne_request = ne_request + HSMCommands._BU_GENERATE_CHECKSUM + keytype + getField(Fields._KEY_LENGTH)
				+ getField(Fields._KEY) + ";" + getField(Fields._KEY_TYPE) + ";" + keyscheme + keyscheme + "1";

		return ne_request;
	}

	/**
	 *
	 * @param data
	 * @param msghdrlen
	 * @throws XHSMFieldParseError
	 */
	public void parseRspIntoFields(String data, int msghdrlen) throws XHSMFieldParseError {
		String header;
		String command;
		int beginIndex = 0;
		int endIndex;

		if (data == null) {
			throw new XHSMFieldParseError(data, "DATA", "Null data received");
		}

		endIndex = beginIndex + msghdrlen;
		if (data.length() > endIndex) {
			header = data.substring(beginIndex, endIndex);
		} else {
			throw new XHSMFieldParseError(data, "Header", "Not enough data");
		}

		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() > endIndex) {
			command = data.substring(beginIndex, endIndex);
		} else {
			throw new XHSMFieldParseError(data, "Command", "Not enough data");
		}

		setField(Fields._CMD, command);
		switch (command) {
		case HSMCommands._A1_GEN_KEY:
			parseA0A1Rsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._A3_GEN_PRINT_COMPONENT:
			parseA2A3Rsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._A5_FORM_KEY_ENC_COMPONENTS:
			parseA4A5Rsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._A7_IMPORT_KEY:
			parseA6A7Rsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._A9_EXPORT_KEY:
			parseA8A9Rsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._CB_XLATE_TPK_ZPK:
			parseCACBRsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._CD_XLATE_ZPK_ZPK:
			parseCCCDRsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._CJ_XLATE_BDK_ZPK:
			parseCICJRsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._CX_GEN_VISA_CVV:
			break;
		case HSMCommands._FB_XLATE_ZPK_ZMK_ZMK:
			parseFAFBRsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._FF_XLATE_TMK_TPK_PVK_ZMK:
			parseFEFFRsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._G1_XLATE_BDK_ZPK_TDES:
			parseG0G1Rsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._HD_GEN_TMK_TPK_PVK:
			parseHCHDRsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._M1_ENCRYPT_DATA:
			parseM0M1Rsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._M3_DECRYPT_DATA:
			parseM2M3Rsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._NP_HSM_STATUS:
			parseNONPRsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._NF_GENERATE_PRINT_KEY:
			parseNENFRsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._NZ_GENERATE_PRINT_KEY:
			parseNENZRsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._PB_LOAD_FORMATTING_DATA_HSM:
			parsePAPBRsp(data, beginIndex, endIndex);
			break;
		case HSMCommands._BV_GENERATE_CHECKSUM:
			parseBUBVRsp(data, msghdrlen, msghdrlen);
			break;
		}
	}

	/**
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	private void parseA0A1Rsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}

		if (temp.equals("00")) {
			// Get New Key under LMK
			// check if single length OR double length (U) OR triple length (X), without
			// variant
			beginIndex = endIndex;
			endIndex = endIndex + 1;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
			switch (temp) {
			case "U":
				endIndex = endIndex + 32;
				break;
			case "X":
				endIndex = endIndex + 32;
				break;
			}

			// get new key under LMK
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._NEW_KEY_UNDER_LMK, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Key: ZMK
			beginIndex = endIndex;
			endIndex = endIndex + 1;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
			switch (temp) {
			case "U":
				endIndex = endIndex + 32;
				break;
			case "X":
				endIndex = endIndex + 32;
				break;
			}

			// get new key under LMK
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._NEW_KEY_UNDER_CURR_KEY, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get the Check Value
			beginIndex = endIndex;
			endIndex = endIndex + 6;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._KEY_CHECK_VALUE, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

		}
	}

	/**
	 * Fields._NEW_KEY_UNDER_LMK
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	private void parseA2A3Rsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}

		if (temp.equals("00")) {
			// Get New Key under LMK
			// check if single length OR double length (U) OR triple length (X), without
			// variant
			beginIndex = endIndex;
			endIndex = endIndex + 1;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
			switch (temp) {
			case "U":
				endIndex = endIndex + 32;
				break;
			case "X":
				endIndex = endIndex + 32;
				break;
			}

			// get new key under LMK
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._NEW_KEY_UNDER_LMK, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
		}
	}

	/**
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	private void parseA4A5Rsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}

		if (temp.equals("00")) {
			// Get New Key under LMK
			// check if single length OR double length (U) OR triple length (X), without
			// variant
			beginIndex = endIndex;
			endIndex = endIndex + 1;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
			switch (temp) {
			case "U":
				endIndex = endIndex + 32;
				break;
			case "X":
				endIndex = endIndex + 32;
				break;
			}

			// get new key under LMK
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._NEW_KEY_UNDER_LMK, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get the Check Value
			beginIndex = endIndex;
			endIndex = endIndex + 6;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._KEY_CHECK_VALUE, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
		}
	}

	/**
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	private void parseA6A7Rsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}

		if (temp.equals("00")) {
			// Get New Key under LMK
			// check if single length OR double length (U) OR triple length (X), without
			// variant
			beginIndex = endIndex;
			endIndex = endIndex + 1;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
			switch (temp) {
			case "U":
				endIndex = endIndex + 32;
				break;
			case "X":
				endIndex = endIndex + 32;
				break;
			}

			// get new key under LMK
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._NEW_KEY_UNDER_LMK, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get the Check Value
			beginIndex = endIndex;
			endIndex = endIndex + 6;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._KEY_CHECK_VALUE, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
		}
	}

	/**
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	private void parseA8A9Rsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}

		if (temp.equals("00")) {
			// Get New Key under LMK
			// check if single length OR double length (U) OR triple length (X), without
			// variant
			beginIndex = endIndex;
			endIndex = endIndex + 1;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
			} else {
				throw new XHSMFieldParseError(data, Fields._KEY_UNDER_ZMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
			switch (temp) {
			case "U":
				endIndex = endIndex + 32;
				break;
			case "X":
				endIndex = endIndex + 32;
				break;
			}

			// get new key under LMK
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._KEY_UNDER_ZMK, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get the Check Value
			beginIndex = endIndex;
			endIndex = endIndex + 6;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._KEY_CHECK_VALUE, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
		}
	}

	/**
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 */
	private void parseHCHDRsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}

		if (temp.equals("00")) {
			// Get New Key under Current Key
			// check if single length OR double length (U) OR triple length (X), without
			// variant
			beginIndex = endIndex;
			endIndex = endIndex + 1;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_CURR_KEY,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
			switch (temp) {
			case "U":
				endIndex = endIndex + 32;
				break;
			case "X":
				endIndex = endIndex + 32;
				break;
			}

			// get new key under current key
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._NEW_KEY_UNDER_CURR_KEY, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_CURR_KEY,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get New Key under LMK
			// check if single length OR double length (U) OR triple length (X), without
			// variant
			beginIndex = endIndex;
			endIndex = endIndex + 1;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
			switch (temp) {
			case "U":
				endIndex = endIndex + 32;
				break;
			case "X":
				endIndex = endIndex + 32;
				break;
			}

			// get new key under LMK
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._NEW_KEY_UNDER_LMK, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
		}
	}

	/**
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	private void parseCACBRsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}

		if (temp.equals("00")) {
			// Get PIN Length
			beginIndex = endIndex;
			endIndex = endIndex + 2;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._PIN_LEN, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._PIN_LEN,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get Destination PIN block
			beginIndex = endIndex;
			endIndex = endIndex + 16;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._DEST_PIN_BLK, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._DEST_PIN_BLK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get Destination PIN block format
			beginIndex = endIndex;
			endIndex = endIndex + 2;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._DEST_PIN_BLK_FORMAT, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._DEST_PIN_BLK_FORMAT,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
		}
	}

	/**
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	private void parseCCCDRsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}

		if (temp.equals("00")) {
			// Get PIN Length
			beginIndex = endIndex;
			endIndex = endIndex + 2;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._PIN_LEN, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._PIN_LEN,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get Destination PIN block
			beginIndex = endIndex;
			endIndex = endIndex + 16;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._DEST_PIN_BLK, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._DEST_PIN_BLK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get Destination PIN block format
			beginIndex = endIndex;
			endIndex = endIndex + 2;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._DEST_PIN_BLK_FORMAT, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._DEST_PIN_BLK_FORMAT,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
		}
	}

	/**
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	private void parseCICJRsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}

		if (temp.equals("00")) {
			// Get PIN Length
			beginIndex = endIndex;
			endIndex = endIndex + 2;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._PIN_LEN, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._PIN_LEN,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get Destination PIN block
			beginIndex = endIndex;
			endIndex = endIndex + 16;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._DEST_PIN_BLK, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._DEST_PIN_BLK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get Destination PIN block format
			beginIndex = endIndex;
			endIndex = endIndex + 2;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._DEST_PIN_BLK_FORMAT, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._DEST_PIN_BLK_FORMAT,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
		}
	}

	/**
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	private void parseG0G1Rsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}

		if (temp.equals("00")) {
			// Get PIN Length
			beginIndex = endIndex;
			endIndex = endIndex + 2;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._PIN_LEN, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._PIN_LEN,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get Destination PIN block
			beginIndex = endIndex;
			endIndex = endIndex + 16;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._DEST_PIN_BLK, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._DEST_PIN_BLK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get Destination PIN block format
			beginIndex = endIndex;
			endIndex = endIndex + 2;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._DEST_PIN_BLK_FORMAT, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._DEST_PIN_BLK_FORMAT,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
		}
	}

	/**
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	private void parseNENFRsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}

		if (temp.equals("00")) {
			// Get New Key under LMK
			// check if single length OR double length (U) OR triple length (X), without
			// variant
			beginIndex = endIndex;
			endIndex = endIndex + 1;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
			switch (temp) {
			case "U":
				endIndex = endIndex + 32;
				break;
			case "X":
				endIndex = endIndex + 48;
				break;
			}

			// get new key under LMK
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._NEW_KEY_UNDER_LMK, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get the Check Value
			beginIndex = endIndex;
			endIndex = endIndex + 6;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._KEY_CHECK_VALUE, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
		}
	}

	/**
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	private void parseNENZRsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}
	}

	/**
	 * Fields Used: - Fields._ERROR_CODE - Fields._IO_BUFFER_SIZE -
	 * Fields._ETHERNET_TYPE - Fields._NR_TCP_SOCKETS - Fields._FIRMWARE_NR -
	 * Fields._DSP_FITTED - Fields._DSP_FIRMWARE_NR
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	protected void parseNONPRsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}

		if (temp.equals("00")) {
			// Get IO Buffer Size
			beginIndex = endIndex;
			endIndex = endIndex + 1;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._IO_BUFFER_SIZE, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._IO_BUFFER_SIZE,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get Ethernet Type
			beginIndex = endIndex;
			endIndex = endIndex + 1;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._ETHERNET_TYPE, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._ETHERNET_TYPE,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get Number of TCP Sockets
			beginIndex = endIndex;
			endIndex = endIndex + 2;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._NR_TCP_SOCKETS, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._NR_TCP_SOCKETS,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get Firmware Number
			beginIndex = endIndex;
			endIndex = endIndex + 9;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._FIRMWARE_NR, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._FIRMWARE_NR,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get DSP fitted
			beginIndex = endIndex;
			endIndex = endIndex + 1;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._DSP_FITTED, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._DSP_FITTED,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get DSP fitted
			beginIndex = endIndex;
			endIndex = endIndex + 4;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._DSP_FIRMWARE_NR, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._DSP_FIRMWARE_NR,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
		}
	}

	/**
	 * Fields Used: - Fields._ERROR_CODE
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	private void parsePAPBRsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}
	}

	/**
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	private void parseFAFBRsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}

		if (temp.equals("00")) {
			// Get New Key under ZMK
			// check if single length OR double length (U) OR triple length (X), without
			// variant
			beginIndex = endIndex;
			endIndex = endIndex + 1;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
			switch (temp) {
			case "U":
				endIndex = endIndex + 32;
				break;
			case "X":
				endIndex = endIndex + 48;
				break;
			}

			// get new key under ZMK
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._NEW_KEY_UNDER_CURR_KEY, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_CURR_KEY,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get the Check Value
			beginIndex = endIndex;
			endIndex = endIndex + 6;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._KEY_CHECK_VALUE, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
		}
	}

	/**
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	private void parseFEFFRsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}

		if (temp.equals("00")) {
			// Get New Key under ZMK
			// check if single length OR double length (U) OR triple length (X), without
			// variant
			beginIndex = endIndex;
			endIndex = endIndex + 1;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
			switch (temp) {
			case "U":
				endIndex = endIndex + 32;
				break;
			case "X":
				endIndex = endIndex + 48;
				break;
			}

			// get new key under ZMK
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._NEW_KEY_UNDER_CURR_KEY, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_CURR_KEY,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			// Get the Check Value
			beginIndex = endIndex;
			endIndex = endIndex + 6;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._KEY_CHECK_VALUE, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
		}
	}

	/**
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	private void parseM0M1Rsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;
		int data_len;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}

		if (temp.equals("00")) {
			beginIndex = endIndex + 16;
			endIndex = endIndex + 16 + 4;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			data_len = Integer.parseInt(temp, 16);
			beginIndex = endIndex;
			endIndex = endIndex + data_len;

			// Get Encrypted Data
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._DATA, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_CURR_KEY,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
		}
	}

	/**
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	private void parseM2M3Rsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;
		int data_len;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}

		if (temp.equals("00")) {
			// Get New Key under ZMK
			// check if single length OR double length (U) OR triple length (X), without
			// variant
			beginIndex = endIndex + 16;
			endIndex = endIndex + 16 + 4;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_LMK,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}

			data_len = Integer.parseInt(temp, 16);
			beginIndex = endIndex;
			endIndex = endIndex + data_len;

			// Get Decrypted Data
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._DATA, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._NEW_KEY_UNDER_CURR_KEY,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
		}
	}

	/**
	 *
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @throws XHSMFieldParseError
	 */
	private void parseBUBVRsp(String data, int beginIndex, int endIndex) throws XHSMFieldParseError {
		String temp;
		int data_len;

		// get Error Code
		beginIndex = endIndex;
		endIndex = endIndex + 2;
		if (data.length() >= endIndex) {
			temp = data.substring(beginIndex, endIndex);
			setField(Fields._ERROR_CODE, temp);
		} else {
			throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
					"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
		}

		if (temp.equals("00")) {
			// Get the Check Value
			beginIndex = endIndex + 6;
			endIndex = endIndex + 6 + 2;
			if (data.length() >= endIndex) {
				temp = data.substring(beginIndex, endIndex);
				setField(Fields._KEY_CHECK_VALUE, temp);
			} else {
				throw new XHSMFieldParseError(data, Fields._ERROR_CODE,
						"Not enough data. Data Length = " + data.length() + " End Index = " + endIndex);
			}
		}
	}

	/**
	 *
	 * @return
	 */
	public String getJSONMsg() {
		return hsmmsg.toJSONString();
	}

	/**
	 *
	 * @return
	 */
	public String dumpMsg() {
		// String msg = "";

		Set s = hsmmsg.keySet();
		Iterator i = s.iterator();
		String key;
		StringBuilder msg = new StringBuilder("\n");

		while (i.hasNext()) {
			key = (String) i.next();
			msg.append("[").append(Utility.resize(key, 25, " ", true)).append("] - [").append(getField(key))
					.append("]\n");
		}

		return msg.toString();
	}

	/**
	 *
	 * @param fieldname
	 * @param value
	 */
	public void setField(String fieldname, String value) {
		removeField(fieldname);

		hsmmsg.put(fieldname, value);
	}

	/**
	 *
	 * @param fieldname
	 * @return
	 */
	public String getField(String fieldname) {
		String value;
		value = (String) hsmmsg.get(fieldname);

		if (value == null) {
			value = " ";
		}

		return value;
	}

	/**
	 *
	 * @param fieldname
	 */
	public void removeField(String fieldname) {
		hsmmsg.remove(fieldname);
	}

	/**
	 *
	 * @return
	 */
	private String getCommand() {
		String cmd = getField(Fields._CMD);

		if (cmd == null) {
			return "";
		}

		return cmd;
	}

	/**
	 *
	 * @return
	 */
	private String getErrorCode() {
		String error_code = getField(Fields._ERROR_CODE);

		if (error_code == null) {
			return "";
		}

		return error_code;
	}

	/**
	 *
	 * @param keytype
	 * @return
	 */
	private String getKeyIdentifier(String keytype) {
		switch (keytype) {
		// BDK
		case "009":
			return "08";
		// KEK
		case "000":
			return "00";
		// PEK
		case "001":
			return "01";
		}

		return "00";
	}
}
