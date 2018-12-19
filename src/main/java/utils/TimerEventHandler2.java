package utils;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abhy
 */
public class TimerEventHandler2 extends Thread
{

    AppQueue event_q;
    boolean flag;
    LinkedList<Timer> tmr_linkedlist;

    /**
     *
     * @param event_q
     */
    public TimerEventHandler2(AppQueue event_q)
    {
        this.event_q = event_q;

        tmr_linkedlist = new LinkedList<>();

        flag = true;
    }

    /**
     *
     */
    @Override
    public void run()
    {
        while (flag)
        {
            try
            {
                sleep(150);
                processTimerEvents();
            } catch (InterruptedException ex)
            {
                Logger.getLogger(TimerEventHandler2.class.getName()).
                        log(Level.SEVERE, "TimerEventHandler.run()", ex);
            } finally
            {
                flag = true;
            }
        }
    }

    /**
     *
     * @param tmr
     */
    public void addTimer(Timer tmr)
    {
        if (tmr != null)
        {
            tmr_linkedlist.add(tmr);
        }
    }

    /**
     *
     */
    public void removeTimer()
    {
        tmr_linkedlist.remove();
    }

    /**
     * 
     * @return 
     */
    public int noOfTimers()
    {
        return tmr_linkedlist.size();
    }

    /**
     *
     */
    private void processTimerEvents()
    {
        int size = 0;
        int x;

        if (tmr_linkedlist != null)
        {
            size = tmr_linkedlist.size();
        }

        try
        {
            if (size > 0)
            {
                for (x = 0; x < size; x++)
                {
                    Timer tmr = tmr_linkedlist.get(x);

                    if (tmr != null && tmr.hasExpired())
                    {
                        event_q.add(new TimerEvent(tmr));
                        //tmr_linkedlist.remove(x);
                        tmr_linkedlist.remove(tmr);
                        // force break out
                        x = size + 1;
                    }
                    size = tmr_linkedlist.size();
                }
            }
        } catch (Exception ex)
        {
            Utility.debugPrint(Utility.getFormattedDateTime(),
                    "Exception# TimerEventHandler#processTimerEvents");
            Utility.debugPrint("Exception", ex.getMessage());
        }
    }

    /**
     * Timer Event class
     */
    public class TimerEvent
    {

        public Timer tmr;

        public TimerEvent(Timer tmr)
        {
            this.tmr = tmr;
        }
    }
}
