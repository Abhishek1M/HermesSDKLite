package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 *
 * @author Abhy
 */
public class ELoggerFormatter extends Formatter
{

    @Override
    public String format(LogRecord rec)
    {
        StringBuilder buf = new StringBuilder();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss:SSS");
        df.setTimeZone(TimeZone.getDefault());
        
        buf.append("[");
        buf.append(Utility.resize(rec.getLevel().getName(), 10, " ", true));
        buf.append("] - [");
        buf.append(df.format(new Date(rec.getMillis())));
        buf.append("]\n");
        buf.append(formatMessage(rec));
        buf.append("\n");

        return buf.toString();
    }
}
