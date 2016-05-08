package com.lightsoutgaming.neuralnetwork;

public class Connection {
	
	float in;
	float out;
	float weight = 10;
	
	public void update(){
		out = in*weight;
	}
	
	public void setValue(float value){
		in = value;
	}
	
	public float getValue(){
		return out;
	}

}
