package com.lightsoutgaming.neuralnetwork;

import java.util.ArrayList;

public class Neuron {
	
	public ArrayList<Connection> inputs;
	public ArrayList<Connection> outputs;
	
	int threshhold = 1;
	
	public void update(){
		float value = 0;
		for(Connection in : inputs){
			value += in.getValue();
		}
		if(value >= threshhold){
			value = 1;
		}else{
			value = 0;
		}
		for(Connection out : outputs){
			out.setValue(value);
		}
	}
	

}
