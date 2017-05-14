package pl.pawc.rpilcd.raspberry;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.system.NetworkInfo;

public class Output{

	final GpioPinDigitalOutput output;

    public Output(GpioController gpio, Pin pin) throws Exception{
		output = gpio.provisionDigitalOutputPin(pin, "output", PinState.LOW);
    }

    public void on(){
		output.high();
    }

	public void off(){
		output.low();
	}
    
}
