package utils;

import java.util.LinkedList;

/**
 *
 * @author Abhy
 */
public class AppQueue
{

    private LinkedList<Object> msgq;

    /**
     *
     */
    public AppQueue()
    {
        msgq = new LinkedList<Object>();
    }

    /**
     *
     * @param obj
     * @return
     */
    public synchronized boolean add(Object obj)
    {
        msgq.add(obj);

        notify();

        return true;
    }

    /**
     *
     * @return
     */
    public synchronized int size()
    {
        return msgq.size();
    }

    /**
     *
     * @return @throws InterruptedException
     */
    public synchronized Object poll() throws InterruptedException
    {
        if (msgq.size() == 0)
        {
            wait();
        }

        return msgq.poll();
    }
}
