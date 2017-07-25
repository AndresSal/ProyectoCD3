/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;


/**
 *
 * @author andres
 */
public class AddressThread extends Thread
{
    public AddressThread()
    {
        try
        {
            Thread.sleep(10000);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void run ()
    {
        try
        {
            IPSearch("192.168.100");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void IPSearch(String subnet) throws UnknownHostException, IOException
    {
        int timeout = 100;
        String myIP = getIP();
        for (int i = 1; i < 255; i++)
        {
            String host = subnet + "." + i;
            if (InetAddress.getByName(host).isReachable(timeout) && !(host.equals(myIP)))
            //if (host.equals(myIP))
            {
                System.out.println(host + " is reachable");
                NodoPrincipal.setDirecciones(host+"/"+i);
            }
        }     
    }
    
    public static String getIP()
    {
        String ip = null;
        try 
        {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) 
            {
                NetworkInterface iface = interfaces.nextElement();
                if (iface.isLoopback() || !iface.isUp())
                continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) 
                {
                    InetAddress addr = addresses.nextElement();
                    if (addr instanceof Inet6Address) continue;

                    ip = addr.getHostAddress();
                }
            }
        } 
        catch (SocketException e) 
        {
            throw new RuntimeException(e);
        }
        return ip;
    }
}
