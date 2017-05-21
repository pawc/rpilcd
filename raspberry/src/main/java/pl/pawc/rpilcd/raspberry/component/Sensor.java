package pl.pawc.rpilcd.raspberry.component;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.raspoid.GPIOPin;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Sensor implements Runnable{

		final GpioPinDigitalInput input;
		
		public Sensor(GpioController gpio, GPIOPin pin) throws Exception{
			input = gpio.provisionDigitalInputPin(pin.getWiringPiPin());
			System.out.println("sensor initialized");
			
			/*input.addListener(new GpioPinListenerDigital() {
				
				public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
					System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
				}
			});*/
		}
		
		public void run(){
			
			while(true){
				try{
					Thread.sleep(1000);
					System.out.println("Sensor is low: "+input.isLow()+" is high: "+input.isHigh());
				} 
				catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			
		}
		
}