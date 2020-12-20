package de.ukoeln.idh.teaching.jml.ex06;

import weka.core.Instances;

import java.util.HashMap;

public class Classifier {

	public Tree train(Instances instances) {
		// TODO: implement
		return null;
	};

	public double entropy(Instances instances) {
		double entropy = 0.0;
		HashMap<Double, Integer> classMap = new HashMap<Double, Integer>();
		for(int i=0; i < instances.numInstances(); i++) {
			double key = instances.get(i).classValue();
			if(!classMap.containsKey(key)){
				classMap.put(key, 1);
			}	else {
				classMap.put(key, classMap.get(key) + 1);
			}
		}

		for(double value : classMap.values()) {
			double frequency = value / instances.numInstances();
			entropy += (frequency * Math.log(frequency));
			}

		return entropy * -1;
		
	}

	/**
	 * calculates information gain for a individual feature
	 */
	public double informationGain(Instances instances, int attributeIndex) {
		// TODO: implement
		return 0.0;
	}
}
