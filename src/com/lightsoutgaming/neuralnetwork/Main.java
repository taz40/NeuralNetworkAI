package com.lightsoutgaming.neuralnetwork;

public class Main {
	
	public static void main(String[] args){
		new Main();
	}
	
	public Main(){
		Network net = new Network(2,5,3,1);
		for(int i = 0; i < 50; i++){
			float[] output = net.update(new float[]{8f, 4f});
			System.out.println(output[0]);
		}
		float[] output = net.update(new float[]{8f, 4f});
		System.out.println(output[0]);
	}

}
