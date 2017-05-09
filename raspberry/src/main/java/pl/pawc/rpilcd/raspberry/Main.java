package pl.pawc.rpilcd.raspberry;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main{
    public static void main(String[] args){

		Logger logger = Logger.getLogger(Main.class.getName());

		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(3000);
			logger.info("Server socket opened successfully");
		} catch (IOException e) {
			logger.error(e.toString());
			logger.fatal("Shutting down the app");
			System.exit(-1);
		}
		
		while(true){
			Socket socket;
			BufferedReader bufferedReader;
			try {
				logger.info("Awaiting connections...");
				socket = serverSocket.accept();
				logger.info("new connection incoming from "+socket.getInetAddress()+". Creating streams...");
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				logger.info("Streams created. Awaiting the message...");
				String message = (String) ois.readObject();
				logger.info("Message received:");
				logger.info(message);
				logger.info("Displaying it on LCD...");
				//print on LCD
				logger.info("Message displayed. Closing streams...");
				ois.close();
				socket.close();
				logger.info("Streams closed");	
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.toString());
			} catch (ClassNotFoundException e) {
				logger.error(e.toString());
			}
			logger.info("Awaiting another connections...");
		}
	
    }
}
