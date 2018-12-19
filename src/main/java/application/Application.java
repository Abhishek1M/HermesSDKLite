package application;

import java.util.logging.Level;
import java.util.logging.Logger;

import utils.AppQueue;

/**
 * <code>Application</code> has to be extended by every module. This is the base
 * thread which runs infinitely until stopped. It will read the events placed in
 * <code>MQueue</code> and will call the method
 * <code>processEvent</code>.
 *
 * @author Abhishek M
 */
public abstract class Application extends Thread
{

    private AppQueue event_q;
    private boolean flag;
    private Object obj;

    /**
     * The
     * <code>MQueue</code> object has to be passed on to the constructor so that
     * the same can be initialized and monitored.
     *
     * @param event_q The MQueue object which is received during object creation
     */
    public Application(AppQueue event_q)
    {
        this.event_q = event_q;
        flag = true;
    }

    /**
     * Events can be put into the MQueue using this method.
     *
     * @param obj The event object which has to be put in the MQueue
     *
     * @return boolean Will return
     * <code>false</code> in case of an exception otherwise it will return
     * <code>true</code>
     */
    public boolean putQueue(Object obj)
    {
        boolean returnval = false;

        try
        {
            returnval = event_q.add(obj);
        } catch (Exception ex)
        {
            return returnval;
        }

        return returnval;
    }

    /**
     * Fetch the MQueue object
     *
     * @return MQueue Fetch the MQueue object
     */
    public AppQueue getQueue()
    {
        return event_q;
    }

    /**
     * This is the abstract class which is called if an event is found in the
     * MQueue and the same event is passed as an object to this method.
     *
     * @param event Event for which the action has to be taken
     */
    public abstract void processEvent(Object event);

    /**
     * The thread's
     * <code>run</code> method. It sleeps for 150 milli-seconds and then reads
     * the MQueue
     */
    @Override
    public void run()
    {
        while (flag)
        {
            try
            {
                obj = event_q.poll();
                if (obj != null)
                {
                    processEvent(obj);
                }
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Application.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }
}
