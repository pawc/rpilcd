package pl.pawc.rpilcd.shared;

import java.io.Serializable;

public class Data implements Serializable{
	
	private String message;
	private boolean isLedOn;	
	
	public Data(String message, Boolean isLedOn){
		this.message = message;
		this.isLedOn = isLedOn;
	}
	
	public String getMessage(){
		return message;
	}

	public boolean getIsLedOn(){
		return isLedOn;
	} 
	
}
