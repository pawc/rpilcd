package pl.pawc.rpilcd.raspberry;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.system.NetworkInfo;

public class Led{
    
	final GpioController gpio;
	final GpioPinDigitalOutput led;

    public Led() throws Exception{
		gpio = GpioFactory.getInstance();
		led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "led", PinState.LOW);
    }

    public void on(){
		led.high();
    }

	public void off(){
		led.low();
	}
    
}
