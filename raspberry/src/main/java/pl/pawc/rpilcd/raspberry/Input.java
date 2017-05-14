package pl.pawc.rpilcd.raspberry;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;

public class Input {

	private GpioPinDigitalInput input;
	
	public Input(GpioController gpio, Pin pin) throws Exception{
		input = gpio.provisionDigitalInputPin(pin, "input", PinPullResistance.PULL_DOWN);
	}
	
	public boolean isHigh(){
		return input.isHigh();
	}
	
}