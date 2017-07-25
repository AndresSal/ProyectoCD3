/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class ThreadC extends Thread
{
    DatagramSocket socket = null;
    InetAddress address = null; 
    byte buf[];
    
    public ThreadC(String host) throws SocketException, UnknownHostException
    {
     socket = new DatagramSocket();
     buf = new byte [256];
     address = InetAddress.getByName(host);
    }
    
    
    public void run()
    {
        try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)))
        {
            
            String input =stdIn.readLine();
            buf = input.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 5000);
            socket.send(packet);
            
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String recibe = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Mensaje: "+recibe);
            
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
