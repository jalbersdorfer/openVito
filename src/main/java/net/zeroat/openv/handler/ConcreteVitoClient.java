package net.zeroat.openv.handler;

/**
 * <b>Title:</b> VitoClient <br>
 * <b>Description:</b> This Class provides an Object Oriented Access to the vcontrol Deamon<br>
 * <b>Copyright:</b> Copyright (c) 2013 <br>
 * <b>Company:</b> Speed4Trade GmbH <br>
 *
 * @author ja
 * @version 1.0.0
 */
public final class ConcreteVitoClient implements VitoClient
{
    private static final VitoClient INSTANCE = new ConcreteVitoClient();

    private ConcreteVitoClient()
    {
        super();
    }

    public static VitoClient getInstance()
    {
        return INSTANCE;
    }

    public Integer getTemperatureM1()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public void setTemperatureM1(Integer value)
    {
        // TODO Auto-generated method stub
    }

    private void sendRequest(final String request)
    {

    }
}
