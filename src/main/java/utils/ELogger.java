package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abhy
 */
public class ELogger
{

    //DBManager dbm;
    //CallableStatement cs;
    //Connection conn;
    FileWriter fstream;
    BufferedWriter out;
    String pattern;

    /**
     * 
     * @param pattern
     * @param limit
     * @param count 
     */
    public ELogger(String pattern, int limit, int count)
    {
        this.pattern = pattern;
        /*
         * try { dbm = new DBManager(); conn = dbm.getConnection(); } catch
         * (SQLException ex) {
         * Logger.getLogger(ELogger.class.getName()).log(Level.SEVERE, null,
         * ex); }
         *
         */
    }

    /**
     *
     * @param modulename Name of the module which is logging the event
     * @param methodName Name of the method which is logging the event
     * @param lvl Logging level (INFO/SEVERE/WARNING)
     * @param description Description of the logged event
     * @param data Event log
     */
    public void log(String modulename, String methodName, String lvl,
            String description, String data)
    {
        String output = "\n" + Utility.formatDate(Utility.getDate()) + " - "
                + Utility.formatTime(Utility.getTime()) + " - " + modulename
                + " - " + methodName + " - " + lvl + " - " + description
                + "\n" + data + "\n" + Utility.resize("-", 80, "-",
                        true);
        try
        {
            getFileName();
            out.write(output);
        } catch (IOException ex)
        {
            Logger.getLogger(ELogger.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally
        {
            try
            {
                out.close();
            } catch (IOException ex1)
            {
                Logger.getLogger(ELogger.class.getName()).log(Level.SEVERE, null,
                        ex1);
            }
        }

        /*
         * try { cs = conn.prepareCall("{call logtrans(?,?,?,?,?)}");
         * cs.setString(1, modulename); cs.setString(2, methodName);
         * cs.setString(3, lvl); cs.setString(4, description); cs.setString(5,
         * data);
         *
         * cs.execute();
         *
         * } catch (SQLException ex) {
         * Logger.getLogger(ELogger.class.getName()).log(Level.SEVERE, null,
         * ex); }
         *
         */
    }

    /**
     *
     * @param modulename
     * @param methodName
     * @param description
     * @param data
     */
    public void logSevere(String modulename, String methodName,
            String description, String data)
    {
        String lvl = "SEVERE";

        String output = "\n" + Utility.formatDate(Utility.getDate()) + " - "
                + Utility.formatTime(Utility.getTime()) + " - " + modulename
                + " - " + methodName + " - " + lvl + " - " + description
                + "\n" + data + "\n" + Utility.resize("-", 80, "-",
                        true);
        try
        {
            getFileName();
            out.write(output);
        } catch (IOException ex)
        {
            Logger.getLogger(ELogger.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally
        {
            try
            {
                out.close();
            } catch (IOException ex1)
            {
                Logger.getLogger(ELogger.class.getName()).log(Level.SEVERE, null,
                        ex1);
            }
        }
        /*
         * try { cs = conn.prepareCall("{call logtrans(?,?,?,?,?)}");
         * cs.setString(1, modulename); cs.setString(2, methodName);
         * cs.setString(3, lvl); cs.setString(4, description); cs.setString(5,
         * data);
         *
         * cs.execute();
         *
         * } catch (SQLException ex) {
         * Logger.getLogger(ELogger.class.getName()).log(Level.SEVERE, null,
         * ex); }
         *
         */
    }

    /**
     *
     * @param moduleName
     * @param methodName
     * @param description
     * @param data
     */
    public void logWarning(String modulename, String methodName,
            String description, String data)
    {
        String lvl = "WARNING";

        String output = "\n" + Utility.formatDate(Utility.getDate()) + " - "
                + Utility.formatTime(Utility.getTime()) + " - " + modulename
                + " - " + methodName + " - " + lvl + " - " + description
                + "\n" + data + "\n" + Utility.resize("-", 80, "-",
                        true);
        try
        {
            getFileName();
            out.write(output);
        } catch (IOException ex)
        {
            Logger.getLogger(ELogger.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally
        {
            try
            {
                out.close();
            } catch (IOException ex1)
            {
                Logger.getLogger(ELogger.class.getName()).log(Level.SEVERE, null,
                        ex1);
            }
        }

        /*
         * try { cs = conn.prepareCall("{call logtrans(?,?,?,?,?)}");
         * cs.setString(1, modulename); cs.setString(2, methodName);
         * cs.setString(3, lvl); cs.setString(4, description); cs.setString(5,
         * data);
         *
         * cs.execute();
         *
         * } catch (SQLException ex) {
         * Logger.getLogger(ELogger.class.getName()).log(Level.SEVERE, null,
         * ex); }
         *
         */
    }

    /**
     *
     * @param modulename
     * @param methodName
     * @param exc
     */
    public void logException(String modulename, String methodName,
            Exception exc)
    {
        String lvl = "SEVERE";
        String description = "EXCEPTION";
        String data = exc.getMessage();

        String output = "\n" + Utility.formatDate(Utility.getDate()) + " - "
                + Utility.formatTime(Utility.getTime()) + " - " + modulename
                + " - " + methodName + " - " + lvl + " - " + description
                + "\n" + data + "\n" + Utility.resize("-", 80, "-",
                        true);
        try
        {
            getFileName();
            out.write(output);
        } catch (IOException ex)
        {
            Logger.getLogger(ELogger.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally
        {
            try
            {
                out.close();
            } catch (IOException ex1)
            {
                Logger.getLogger(ELogger.class.getName()).log(Level.SEVERE, null,
                        ex1);
            }
        }

        /*
         * try { cs = conn.prepareCall("{call logtrans(?,?,?,?,?)}");
         * cs.setString(1, modulename); cs.setString(2, methodName);
         * cs.setString(3, lvl); cs.setString(4, description); cs.setString(5,
         * data);
         *
         * cs.execute();
         *
         * } catch (SQLException ex) {
         * Logger.getLogger(ELogger.class.getName()).log(Level.SEVERE, null,
         * ex); }
         *
         */
    }

    /**
     *
     */
    private void getFileName()
    {
        String fileName = pattern + "-" + Utility.getDate() + ".log";
        try
        {
            fstream = new FileWriter(fileName, true);
            out = new BufferedWriter(fstream);
        } catch (IOException e)
        {
            Logger.getLogger(ELogger.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}