package utils;

/**
 *
 * @author Abhy
 */
public class Timer
{

    String name;
    long timer;
    Object user_obj;
    long expiry_time;

    /**
     *
     * @param name This is the name of the Timer
     * @param timer This is the time in milli-seconds after which the timeout
     * should happen
     * @param user_obj This is the user object which has to be timed out
     */
    public Timer(String name, long timer, Object user_obj)
    {
        this.name = name;
        this.timer = timer;
        this.user_obj = user_obj;

        setExpiryTime();
    }

    /**
     *
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     */
    private void setExpiryTime()
    {
        expiry_time = System.currentTimeMillis() + timer;
    }

    /**
     *
     * @return
     */
    public long getExpiry_time()
    {
        return expiry_time;
    }

    /**
     *
     * @return
     */
    public Object getUser_obj()
    {
        return user_obj;
    }

    /**
     *
     * @return
     */
    public boolean hasExpired()
    {
        return System.currentTimeMillis() >= expiry_time;
    }
}
