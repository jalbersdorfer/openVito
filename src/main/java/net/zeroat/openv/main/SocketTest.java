package net.zeroat.openv.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.junit.Test;

public class SocketTest {
	@Test
	public void testSocketReadWrite()
	{
		try {
			Socket socket = new Socket("raspberrypi", 3003);
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			while (reader.ready())
			{
				char[] buf = new char[256];
				reader.read(buf);
				//System.out.print(buf);
			}
			
			writer.println("unit off");
			writer.println("getTempSTS");
			
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
				char[] buf = new char[256];
				int buflen = reader.read(buf);
				String s = new String(buf).replaceAll("[^0-9A-F]", "");
				Long l;
				try {
					if (!s.isEmpty()){
						l = Long.parseLong(s, 16);
						Float f = ByteBuffer.wrap(s.getBytes()).order(ByteOrder.LITTLE_ENDIAN).getFloat();
					 	System.out.println(f);
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
		}
	}
}
