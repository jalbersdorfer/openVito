package net.zeroat.openv.handler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedProperty;

@ManagedBean
@SessionScoped
public final class Fake
{
    private static int temp = 42;
    public static String getTemp()
    {
        return String.valueOf(temp);
    }

    public static void setTemp(final int value)
    {
        temp = value;
    }

    public static int inc()
    {
        return ++temp;
    }
}
