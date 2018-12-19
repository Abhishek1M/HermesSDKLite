package utils;

/**
 *
 * @author Abhy
 */
class HexNibbleConverter implements NibbleToCharConverter, CharToNibbleConverter
{
        HexNibbleConverter()
    {
    }

    public int convertNibbleToChar(int nibble)
    {
        if(nibble < 10)
            return nibble + 48;
        else
            return nibble + 55;
    }

    public byte convertCharToNibble(byte data)
    {
        if(data >= 48 && data <= 57)
            return (byte)(data & 0xf);
        if(data >= 65 && data <= 70)
            return (byte)(data - 55);
        if(data >= 97 && data <= 102)
            return (byte)(data - 87);
        else
            return 0;
    }

    public char convertNibbleToChar(byte nibble)
    {
        switch(nibble)
        {
        case 0: // '\0'
            return '0';

        case 1: // '\001'
            return '1';

        case 2: // '\002'
            return '2';

        case 3: // '\003'
            return '3';

        case 4: // '\004'
            return '4';

        case 5: // '\005'
            return '5';

        case 6: // '\006'
            return '6';

        case 7: // '\007'
            return '7';

        case 8: // '\b'
            return '8';

        case 9: // '\t'
            return '9';

        case 10: // '\n'
            return 'A';

        case 11: // '\013'
            return 'B';

        case 12: // '\f'
            return 'C';

        case 13: // '\r'
            return 'D';

        case 14: // '\016'
            return 'E';

        case 15: // '\017'
            return 'F';
        }
        return '\0';
    }

    public byte convertCharToNibble(char data)
    {
        switch(Character.toLowerCase(data))
        {
        case 48: // '0'
            return 0;

        case 49: // '1'
            return 1;

        case 50: // '2'
            return 2;

        case 51: // '3'
            return 3;

        case 52: // '4'
            return 4;

        case 53: // '5'
            return 5;

        case 54: // '6'
            return 6;

        case 55: // '7'
            return 7;

        case 56: // '8'
            return 8;

        case 57: // '9'
            return 9;

        case 97: // 'a'
            return 10;

        case 98: // 'b'
            return 11;

        case 99: // 'c'
            return 12;

        case 100: // 'd'
            return 13;

        case 101: // 'e'
            return 14;

        case 102: // 'f'
            return 15;

        case 58: // ':'
        case 59: // ';'
        case 60: // '<'
        case 61: // '='
        case 62: // '>'
        case 63: // '?'
        case 64: // '@'
        case 65: // 'A'
        case 66: // 'B'
        case 67: // 'C'
        case 68: // 'D'
        case 69: // 'E'
        case 70: // 'F'
        case 71: // 'G'
        case 72: // 'H'
        case 73: // 'I'
        case 74: // 'J'
        case 75: // 'K'
        case 76: // 'L'
        case 77: // 'M'
        case 78: // 'N'
        case 79: // 'O'
        case 80: // 'P'
        case 81: // 'Q'
        case 82: // 'R'
        case 83: // 'S'
        case 84: // 'T'
        case 85: // 'U'
        case 86: // 'V'
        case 87: // 'W'
        case 88: // 'X'
        case 89: // 'Y'
        case 90: // 'Z'
        case 91: // '['
        case 92: // '\\'
        case 93: // ']'
        case 94: // '^'
        case 95: // '_'
        case 96: // '`'
        default:
            return 0;
        }
    }
}
