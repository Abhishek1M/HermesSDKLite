package utils;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Abhy
 */
public class TimerEventHandler extends Thread
{

    AppQueue event_q;
    boolean flag;
    ConcurrentHashMap<Timer, Long> tmr_list;

    /**
     *
     * @param event_q
     */
    public TimerEventHandler(AppQueue event_q)
    {
        this.event_q = event_q;

        tmr_list = new ConcurrentHashMap<>();

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
                Utility.debugPrint("TimerEventHandler.run()", ex.getMessage());
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
            tmr_list.put(tmr, System.currentTimeMillis());
        }
    }

    /**
     *
     */
    public void removeTimer()
    {
    }

    /**
     *
     * @return
     */
    public int noOfTimers()
    {
        return tmr_list.size();
    }

    /**
     *
     */
    private void processTimerEvents()
    {
        try
        {
            if (tmr_list != null)
            {
                Enumeration<Timer> itr = tmr_list.keys();
                while (itr.hasMoreElements())
                {
                    Timer tmr = itr.nextElement();

                    if (tmr != null && tmr.hasExpired())
                    {
                        event_q.add(new TimerEvent(tmr));
                        tmr_list.remove(tmr);
                    }
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
