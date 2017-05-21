package pl.pawc.rpilcd.raspberry.component;

import com.pi4j.component.lcd.LCDTextAlignment;
import com.pi4j.component.lcd.impl.GpioLcdDisplay;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.system.NetworkInfo;

public class Lcd{
    public final static int LCD_ROW_1 = 0;
    public final static int LCD_ROW_2 = 1;
    final LCDLib display;
    
    public Lcd() throws Exception{
        display = new LCDLib(2,    // number of row supported by LCD
                16,       // number of columns supported by LCD
                RaspiPin.GPIO_09,  // LCD RS pin
                RaspiPin.GPIO_08,  // LCD strobe pin
                RaspiPin.GPIO_07,  // LCD data bit D4
                RaspiPin.GPIO_15,  // LCD data bit D5
                RaspiPin.GPIO_16,  // LCD data bit D6
                RaspiPin.GPIO_01); // LCD data bit D7
    }

    public void print(String message) throws Exception{
        display.clear();
        display.writeln(LCD_ROW_1, message);
        //display.writeln(LCD_ROW_2, "");
    }
    
}