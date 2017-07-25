/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria;

import java.util.ArrayList;
import java.util.StringTokenizer;

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
            Thread.sleep(10000);
            Input=NodoPrincipal.getDirecciones();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void run ()
    {
        Clasificar();
    }
    
    public void Clasificar ()
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
