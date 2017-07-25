/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class CheckThread extends Thread
{
    ArrayList <String> Input = null;
    
    public CheckThread() 
    {
        try
        {
            Thread.sleep(5000);
            Input=NodoPrincipal.getDirecciones();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void run ()
    {
        try {
            Clasificar();
        } catch (UnknownHostException ex) {
            Logger.getLogger(CheckThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Clasificar () throws UnknownHostException
    {
        for(int i=0;i<Input.size();i++)
        {
            String address = Input.get(i);
            StringTokenizer token = new StringTokenizer(address, "/");
            while(token.hasMoreTokens())
            {
                NodoPrincipal.setHostnames(token.nextToken());
                NodoPrincipal.setNodos(Integer.parseInt(token.nextToken()));
            }
        }
    }
}
