package net.zeroat.openv.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;

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
    	String s = queryRequest("getTempKTS");
        return (null != s) ? Integer.parseInt(s, 16) : -1;
    }
    
    public String getKesseltemperatur()
    {
    	String s = queryRequest("getTempKTS");
    	Long l = Long.parseLong(s, 16);
    	return s.equals("ERR") ? s : String.format("%.1f", l / 1000.0f);
    }

    public void setTemperatureM1(Integer value)
    {
        // TODO Auto-generated method stub
    }

    private String queryRequest(final String request)
    {
    	Calendar timeout = Calendar.getInstance();
    	timeout.add(Calendar.SECOND, 5);
    	char[] buf = new char[256];
    	Socket socket = null;
		try {
			socket = new Socket("raspberrypi", 3003);
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			while (reader.ready())
			{
				reader.read(buf);
				System.out.println(buf);
			}
			
			writer.println("unit off");
//			writer.println("getTempSTS");
			writer.println(request);
			
			System.out.println(String.format("Reading for thread %s", Thread.currentThread().getName()));
			
			while (!reader.ready())
			{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			while(reader.ready())
			{
				int buflen = reader.read(buf);
				System.out.println(buf);
				String s = new String(buf).replaceAll("[^0-9A-F]", "");
				try {
					if (!s.isEmpty()){
						return s;
					} else {
						while(!reader.ready() && Calendar.getInstance().before(timeout))
						{
							Thread.sleep(250);
						}
					}
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
			
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally
		{
			if (null != socket)
				try {
					socket.close();
				} catch (IOException e) {
				}
		}
		
		return "ERR";
	}
}
