package pl.pawc.rpilcd.raspberry;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import pl.pawc.rpilcd.raspberry.component.Buzzer;
import pl.pawc.rpilcd.raspberry.component.Lcd;
import pl.pawc.rpilcd.raspberry.component.Led;
import pl.pawc.rpilcd.raspberry.component.Sensor;
import pl.pawc.rpilcd.shared.Data;

import com.raspoid.GPIOPin;
import com.raspoid.PWMPin;
import com.raspoid.additionalcomponents.LED;
import com.raspoid.additionalcomponents.PassiveBuzzer;
import com.raspoid.additionalcomponents.notes.BaseNote;
import com.raspoid.additionalcomponents.servomotor.ServoMotor;
import com.raspoid.additionalcomponents.servomotor.TowerProMG90S;

public class Main{
    public static void main(String[] args){

		Logger logger = Logger.getLogger(Main.class.getName());
		Lcd lcd = null;	
		LED[] outputs = new LED[8];
		//Sensor sensor = null;
		
		ServerSocket serverSocket = null;
		
		try{
			serverSocket = new ServerSocket(3000);
			logger.info("Server socket opened successfully");
		
			//sensor = new Sensor(gpio, GPIOPin.GPIO_00);
			//new Thread(sensor).start();
						
			lcd = new Lcd();
			
		}
		catch(Exception e){
			e.printStackTrace();
			logger.fatal("Shutting down the app");
			System.exit(-1);
		}
		
		while(true){
			Socket socket;
			BufferedReader bufferedReader;
			try {
				logger.info("Awaiting connections...");
				socket = serverSocket.accept();
				logger.info("new connection incoming from "+socket.getInetAddress());
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Data data = (Data) ois.readObject();
				
				outputs[0] = null; // (reserved for sensor)
				outputs[1] = null; // (reserved for LCD)
				outputs[2] = new LED(GPIOPin.GPIO_02); 
				outputs[3] = new LED(GPIOPin.GPIO_03); 
				outputs[4] = new LED(GPIOPin.GPIO_04);
				outputs[5] = new LED(GPIOPin.GPIO_05);
				outputs[6] = new LED(GPIOPin.GPIO_06);
				outputs[7] = null; // (reserved for LCD)
				       
				Led.handle(outputs, data.getOutputs());
				Led.clean(outputs);
				
				PassiveBuzzer buzzer = new PassiveBuzzer(PWMPin.PWM1);
				buzzer.playNote(BaseNote.DO_0, 3, 500);
		        buzzer.playNote(BaseNote.RE_0, 3, 500);
		        buzzer.playNote(BaseNote.LA_0, 3, 500);
		        buzzer = null;
		        
		        ServoMotor motor = new TowerProMG90S(PWMPin.PWM0);
		        if("0".equals(data.getMessage())) motor.setAngle(0);
		        if("45".equals(data.getMessage())) motor.setAngle(45);
		        if("90".equals(data.getMessage())) motor.setAngle(90);
		        motor = null;
		        		
				lcd.print(data.getMessage());
				
				System.out.println("ok");
				
				ois.close();
				socket.close();
			}
			catch(Exception e){
				logger.error(e.toString());
			}

		}
	
    }
}
