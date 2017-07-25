/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author andres
 */
public class ThreadS extends Thread{
    
    protected DatagramSocket socket = null;
    protected boolean listening = true;
    int port=5000;
    
    
    public ThreadS() throws SocketException 
    {
        socket = new DatagramSocket(port);
    }
    
    public void run()
    {
        while(listening)
        {
            try
            {
                byte [] buf = new byte [256];
                
                DatagramPacket packet = new DatagramPacket(buf,buf.length);
                socket.receive(packet);
                
                BufferedReader StdIn = new BufferedReader(new InputStreamReader(System.in));
                String input;
                input=StdIn.readLine();
                buf = input.getBytes();
                
                InetAddress address = packet.getAddress();
                int Clientport = packet.getPort();
                System.out.println("Direccion IP del cliente: "+address+"\n Puerto del cliente: "+Clientport);
                packet = new DatagramPacket(buf, buf.length, address, Clientport);
                socket.send(packet);
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        socket.close();
    }
}
