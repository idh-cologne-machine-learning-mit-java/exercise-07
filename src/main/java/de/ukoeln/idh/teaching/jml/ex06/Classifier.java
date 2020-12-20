package de.ukoeln.idh.teaching.jml.ex06;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import weka.core.Instance;
import weka.core.Instances;

public class Classifier {

	public Tree train(Instances instances) {
		// TODO: implement
		return null;
	};

	public double entropy(Instances instances) {
		// TODO: implement
		
		Map<Double, Double> entropies = new HashMap<Double, Double>();
		Enumeration<Instance> enumerations = instances.enumerateInstances();
		
		while(enumerations.hasMoreElements()) {
			Instance instance = enumerations.nextElement();
			double value = instance.classValue();

			if (!entropies.containsKey(value)) {
				entropies.put(value, 1d);
			} else {
				entropies.put(value, entropies.get(value)+1);
			}
				
		}
		
		double entropyValue = 0.0;
		double numInstances = instances.numInstances();
		
		for (double value : entropies.values()) {
			double freq = value / numInstances;
			entropyValue += (freq * Math.log(freq));
			System.out.println("Entropy value: " + entropyValue);
		}
		
		entropyValue = -entropyValue;
		return entropyValue;
	}

	/**
	 * calculates information gain for a individual feature
	 */
	public double informationGain(Instances instances, int attributeIndex) {
		// TODO: implement
		
		double entropy = this.entropy(instances);
		double weightedEntropy = 0.0;
		
		Random rnd = new Random();
		int numVals = rnd.nextInt(10);
		double weight = Math.random();
		double splitEntropy = 2.5;
		
		for(int i = 0; i < numVals; i++) {
			
			weightedEntropy += weight * splitEntropy;
		}
		
		return entropy - weightedEntropy;
	}
}
