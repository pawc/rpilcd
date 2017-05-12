package pl.pawc.rpilcd.raspberry;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;

public class Circuit {

	private GpioPinDigitalInput pin;
	
	public Circuit(GpioController gpio) throws Exception{
		pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_06, "circuit", PinPullResistance.PULL_DOWN);
	}
	
	public boolean checkState(){
		return pin.isHigh();
	}
	
}