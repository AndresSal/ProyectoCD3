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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 *
 * @author andres
 */
public class NodoPrincipal 
{
    public static ArrayList<String> direcciones = new ArrayList<>();
    public static ArrayList <String> hostnames = new ArrayList<>();
    public static ArrayList <Integer> nodos = new ArrayList<>();

    public static void main (String args[]) throws IOException, InterruptedException
    {
        boolean check = true;
        new ThreadS().start();
        
        while(check)
        {            
            AddressThread ThA = new AddressThread();
            CheckThread ThC = new CheckThread();
            ThA.start();
            ThC.start();
            ThA.join();
            ThC.join();
            
            System.out.println("Lista de direcciones: ");
            for (int i=0;i<hostnames.size();i++)
            {
                System.out.println(hostnames.get(i));
            }
            
                for (int i=0;i<hostnames.size();i++)
                {
                    String direccion=hostnames.get(i);
                    try
                    {
                        new ThreadS().start();
                        new ThreadC(InetAddress.getByName(direccion)).start();
                    }
                    catch(Exception e)
                    {
                        System.out.println("No pudo conectarse a la dirección: "+direccion);
                    }
                }
            
        }
        
        
    }
    
    public static ArrayList<String> getDirecciones() {
        return direcciones;
    }

    public static void setDirecciones(String direccion) {
        if(!direcciones.contains(direccion))
        {
            direcciones.add(direccion);
        }
    }

    public static ArrayList<String> getHostnames() {
        return hostnames;
    }

    public static void setHostnames(String hostname) {
            
        if(!hostnames.contains(hostname))
        {
            hostnames.add(hostname);
        }
        
    }

    public static ArrayList<Integer> getNodos() {
        return nodos;
    }

    public static void setNodos(int nodo) {
        if(!nodos.contains(nodo))
        {
            nodos.add(nodo);
        }
    }
}
