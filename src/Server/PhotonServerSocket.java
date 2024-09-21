/*
Name: Will Taylor
Class: CSCE 35103
Assignment: Sprint 2

Docu:
This file is the class for the server sockets of the photon laser game and should use udp socket 22

TODO:
CHANGE PORT SOCKET BACK TO 22 WHEN DONE TESTING
ADD A MORE ROBUST SYSTEM FOR CLIENT DISCONNECTION FROM SERVER TO FREE UP RESOURCES
THE ARRAY LIST STORING CODES MIGHT NEED TO STORE OBJECTS WHICH BETTER REPRESENT WHAT ITS COMMUNICATING WITH

*/

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class PhotonServerSocket{
    
//Testing with main
//--------------------------------------------------------------------------------------------------------------------------------
/*
    public static void main(String args[]){

        //System.out.println("In main");

        try{
            //System.out.println("In try block");
            PhotonServerSocket pss = new PhotonServerSocket();
            //System.out.println("Create photon server socket");
            pss.AddClient();
            //System.out.println("Added client method has been run"); //will indicate if the program is listening in a separate thread if this function doesn't run then program is hung on the accept blocking statement
        }catch(IOException e){
            e.printStackTrace();
        }
    
    }

*/
//--------------------------------------------------------------------------------------------------------------------------------

    static private ArrayList<String> ClientCodes;
    private ServerSocket ss;

    PhotonServerSocket() throws IOException{ //will return an exception if error instead of actual server socket class

        ClientCodes = new ArrayList<String>();

        //System.out.println("Inside photon server constructor");
        try{
            ss = new ServerSocket(65535);
        }catch(IOException e){
            System.out.println("There was an issue making the server socket");
            e.printStackTrace();
        }
        //System.out.println("Create new server socket");

    }

    public void AddClient(){ //should run constantly so it can check for new clients and add them to the server will also throw exception on error

        new Thread(() -> { //create new thread to handle accepting clients
            
            while(true){
                try{

                    Socket clientSocket = ss.accept(); //this is a blocking statement so it needs to be run in a thread to not halt execution of main thread
                    System.out.println("New client has connected");
    
                    ClientHandler ch = new ClientHandler(clientSocket); //make a new handler which will be used to listen for inputs
    
                    new Thread(ch).start(); //run this handler in a new thread so this can listen for other clients needing to connect

                }catch(IOException e){
                    System.err.println("Error accepting new client " + e.getMessage());
                }

            }

        }).start();

    }

    private static class ClientHandler extends Thread{ //inner class to help handle each client insider server

        private Socket cs;

        public ClientHandler(Socket s){ //instantiate with socket from accept in AddClient
            
            //System.out.println("In client handler constructor");
            this.cs = s;

        }

        public void run(){ //this is what runs when thread is executed and gets info from client as a thread separate from main so it can run concurrently waiting without blocking main thread

            //System.out.println("In client handler run");
            //reader should be what the client sends while writer is what the server sends
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(cs.getInputStream())); PrintWriter writer = new PrintWriter(cs.getOutputStream(), true)){

                String message;
                while((message = reader.readLine()) != null){
                    System.out.println("Client message: " + message);
                    writer.println("Server received: " + message);

                }

                ClientCodes.add(message); //keep codes as a string for now

            }catch(IOException e){

                System.err.println("Error with client " + e.getMessage());

            }

        }

    }

}
