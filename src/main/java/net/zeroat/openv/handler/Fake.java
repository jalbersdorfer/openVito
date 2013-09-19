package net.zeroat.openv.handler;

import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public final class Fake
{
	private static VitoClient client = ConcreteVitoClient.getInstance();
    private static String temp = "ERR";
    private static Calendar readNext = Calendar.getInstance();
    private static Calendar nextRequest = Calendar.getInstance();
    public static synchronized String getTemp()
    {
    	while (Calendar.getInstance().before(nextRequest))
    	{
    		try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
			}
    	}
    	
    	if (Calendar.getInstance().after(readNext))
    	{
    		String s = client.getKesseltemperatur();
    		if (!s.equals("ERR"))
    		{
    			temp = s;
    			readNext = Calendar.getInstance();
    			readNext.add(Calendar.SECOND, 10);
    		}
    	}
    	
    	nextRequest = Calendar.getInstance();
    	nextRequest.add(Calendar.SECOND, 3);
        return String.valueOf(temp);
    }

    public static void setTemp(final String value)
    {
        temp = value;
    }

    public static String inc()
    {
        return temp = String.valueOf(Integer.valueOf(temp) + 1);
    }
}
