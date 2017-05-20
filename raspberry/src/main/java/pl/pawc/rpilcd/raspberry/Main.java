package pl.pawc.rpilcd.raspberry;

import org.apache.log4j.Logger;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;

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
		//Lcd lcd = null;	
		Output[] outputs = new Output[8];
		
		ServerSocket serverSocket = null;
		
		try{
			serverSocket = new ServerSocket(3000);
			logger.info("Server socket opened successfully");
			logger.info("Initializing GPIO Controller...");
			GpioController gpio = GpioFactory.getInstance();
			outputs[0] = new Output(gpio, RaspiPin.GPIO_00);
			outputs[1] = new Output(gpio, RaspiPin.GPIO_01);
			outputs[2] = new Output(gpio, RaspiPin.GPIO_02);
			outputs[3] = new Output(gpio, RaspiPin.GPIO_03);
			outputs[4] = new Output(gpio, RaspiPin.GPIO_04);
			outputs[5] = new Output(gpio, RaspiPin.GPIO_05);
			outputs[6] = new Output(gpio, RaspiPin.GPIO_06);
			outputs[7] = new Output(gpio, RaspiPin.GPIO_07);
			
			//lcd = new Lcd();
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
								
				Output.handle(outputs, data.getOutputs());
				
				//logger.info("Message received:");
				//logger.info(data.getMessage());
				//logger.info("Displaying it on LCD...");
				//lcd.print(data.getMessage());
				//logger.info("Message displayed. Closing streams...");
				
				ois.close();
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
