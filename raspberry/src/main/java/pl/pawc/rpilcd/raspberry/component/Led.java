package pl.pawc.rpilcd.raspberry.component;

import com.raspoid.additionalcomponents.LED;

public class Led{
	
	public static void handle(LED[] outputs, boolean[] data){
		for(int i = 0; i < data.length ; i++){
			if(outputs[i] == null) continue;
			if(data[i]){
				outputs[i].on();
			}
			else{
				outputs[i].off();
			}
		}
	}
	
	public static void clean(LED[] outputs){
		for(int i = 0; i < outputs.length ; i++){
			outputs[i] = null;
		}
	}
    
}