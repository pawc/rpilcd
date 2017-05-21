package pl.pawc.rpilcd.raspberry.component;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.raspoid.PWMComponent;
import com.raspoid.PWMPin;
import com.raspoid.additionalcomponents.PassiveBuzzer;
import com.raspoid.additionalcomponents.notes.BaseNote;

public class Buzzer {

	final PassiveBuzzer buzzer;
	
	public Buzzer(PWMPin pin){
		buzzer = new PassiveBuzzer(pin);
	}
	
	public void beep(){
		int timePerNote = 500;
		int i = 3;
        buzzer.playNote(BaseNote.DO_0, i, timePerNote);
        buzzer.playNote(BaseNote.RE_0, i, timePerNote);
        buzzer.playNote(BaseNote.MI_0, i, timePerNote);
        buzzer.playNote(BaseNote.FA_0, i, timePerNote);
        buzzer.playNote(BaseNote.SOL_0, i, timePerNote);
        buzzer.playNote(BaseNote.LA_0, i, timePerNote);
        buzzer.playNote(BaseNote.SI_0, i, timePerNote);
        System.out.println("Signal sent");
	}
	
}