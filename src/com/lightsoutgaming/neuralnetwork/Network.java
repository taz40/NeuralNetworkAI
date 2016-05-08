package com.lightsoutgaming.neuralnetwork;

import java.util.ArrayList;

public class Network {
	Neuron[][] neurons;
	ArrayList<Connection> connections = new ArrayList<Connection>();
	ArrayList<ConnectionInput> inputs = new ArrayList<ConnectionInput>();
	ArrayList<ConnectionOutput> outputs = new ArrayList<ConnectionOutput>();
	
	public Network(int inputs, int hiddenLayerSize, int hiddenLayerCount, int outputs){
		//setup neurons, no connections yet.
		neurons = new Neuron[2+hiddenLayerCount][];
		int neuronCount = inputs+outputs+(hiddenLayerCount*hiddenLayerSize);
		//setup input layer
		neurons[0] = new Neuron[inputs];
		//hidden layers
		for(int i = 1; i <= hiddenLayerCount; i++){
			neurons[i] = new Neuron[hiddenLayerSize];
		}
		//hidden layers
		neurons[1+hiddenLayerCount] = new Neuron[outputs];
		//instantiate neurons
		for(int layerI = 0; layerI < neurons.length; layerI++){
			Neuron[] layer = neurons[layerI];
			for(int neuronI = 0; neuronI < layer.length; neuronI++){
				Neuron n = new Neuron();
				n.inputs = new ArrayList<Connection>();
				n.outputs = new ArrayList<Connection>();
				neurons[layerI][neuronI] = n;
			}
		}
		
		//now all neurons exist, lets setup the connections
		for(int layerI = 0; layerI < neurons.length; layerI++){
			Neuron[] layer = neurons[layerI];
			for(int neuronI = 0; neuronI < layer.length; neuronI++){
				Neuron n = neurons[layerI][neuronI];
				if(layerI == 0){ // this is the input layer
					ConnectionInput ic = new ConnectionInput();
					this.inputs.add(ic);
					n.inputs.add(ic);
					for(int layerI1 = 0; layerI1 < neurons.length; layerI1++){
						Neuron[] layer1 = neurons[layerI1];
						for(int neuronI1 = 0; neuronI1 < layer1.length; neuronI1++){
							Neuron n1 = neurons[layerI][neuronI];
							if(n1 != n){
								Connection c = new Connection();
								n.outputs.add(c);
								n1.inputs.add(c);
							}
						}
					}
				}else if(layerI == 1+hiddenLayerCount){// this is the output layer
					ConnectionOutput co = new ConnectionOutput();
					n.outputs.add(co);
					this.outputs.add(co);
				}else{ // this is a hidden layer
					for(int layerI1 = 0; layerI1 < neurons.length; layerI1++){
						Neuron[] layer1 = neurons[layerI1];
						for(int neuronI1 = 0; neuronI1 < layer1.length; neuronI1++){
							Neuron n1 = neurons[layerI][neuronI];
							if(n1 != n){
								Connection c = new Connection();
								n.outputs.add(c);
								n1.inputs.add(c);
							}
						}
					}
				}
			}
		}
		
	}
	
	public float[] update(float[] in){
		if(in.length != inputs.size()){
			return null;
		}
		for(int i = 0; i < inputs.size(); i++){
			inputs.get(i).setValue(in[i]);
		}
		for(int layerI = 0; layerI < neurons.length; layerI++){
			Neuron[] layer = neurons[layerI];
			for(int neuronI = 0; neuronI < layer.length; neuronI++){
				Neuron n = neurons[layerI][neuronI];
				n.update();
			}
		}
		for(Connection c : connections){
			c.update();
		}
		float[] outputs = new float[this.outputs.size()];
		for(int i = 0; i < outputs.length; i++){
			outputs[i] = this.outputs.get(i).getValue();
		}
		return outputs;
	}
	
}
