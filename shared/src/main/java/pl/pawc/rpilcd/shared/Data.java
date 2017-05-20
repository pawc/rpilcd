package pl.pawc.rpilcd.shared;

import java.io.Serializable;

public class Data implements Serializable{
	
	private String message;
	private boolean[] outputs;
	
	public Data(String message, boolean[] outputs){
		this.message = message;
		this.outputs = outputs;
	}
	
	public String getMessage(){
		return message;
	}

	public boolean[] getOutputs(){
		return outputs;
	}
	
}