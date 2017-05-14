package pl.pawc.rpilcd.shared;

import java.io.Serializable;

public class Data implements Serializable{
	
	private String message;
	private boolean output;	
	
	public Data(String message, Boolean inputHigh){
		this.message = message;
		this.output = inputHigh;
	}
	
	public String getMessage(){
		return message;
	}

	public boolean isOutput(){
		return output;
	} 
	
}