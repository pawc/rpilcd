package pl.pawc.rpilcd.raspberry;

import org.apache.log4j.Logger;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import pl.pawc.rpilcd.shared.Data;

public class Main{
    public static void main(String[] args){

		Logger logger = Logger.getLogger(Main.class.getName());
		Lcd lcd = null;
		Led led = null;		
		Circuit circuit = null;
		
		ServerSocket serverSocket = null;
		try{
			serverSocket = new ServerSocket(3000);
			logger.info("Server socket opened successfully");
			logger.info("Initializing GPIO Controllers...");
			GpioController gpio = GpioFactory.getInstance();
			logger.info("...initialized");
			led = new Led(gpio);
			circuit = new Circuit(gpio);
			lcd = new Lcd();
			logger.info("... initialized");
		}
		catch(IOException e){
			logger.error(e.toString());
			logger.fatal("Shutting down the app");
			System.exit(-1);
		}
		catch(Exception e){
			logger.error(e.toString());
			logger.fatal("Shutting down the app");
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
				Data data = (Data) ois.readObject();
				boolean state = circuit.checkState();
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(state);
				oos.flush();
				logger.info("Circuit state: "+circuit.checkState());
				logger.info("Led state: "+data.getIsLedOn());
				if(data.getIsLedOn()){
					led.on();
				}
				else{
					led.off();		
				}	
				
				logger.info("Message received:");
				logger.info(data.getMessage());
				logger.info("Displaying it on LCD...");
				lcd.print(data.getMessage());
				logger.info("Message displayed. Closing streams...");
				ois.close();
				oos.close();
				socket.close();
				logger.info("Streams closed");	
			}
			catch(IOException e){
				e.printStackTrace();
				logger.error(e.toString());
			}
			catch(ClassNotFoundException e){
				logger.error(e.toString());
			}
			catch(Exception e){
				logger.error(e.toString());
			}
			
			logger.info("Awaiting another connections...");
		}
	
    }
}
