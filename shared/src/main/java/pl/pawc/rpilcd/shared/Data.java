package pl.pawc.rpilcd.shared;

import java.io.Serializable;

public class Data implements Serializable{
	
	private String message;
	
	public Data(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
}