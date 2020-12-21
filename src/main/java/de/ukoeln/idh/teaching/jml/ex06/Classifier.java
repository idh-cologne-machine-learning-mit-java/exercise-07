package de.ukoeln.idh.teaching.jml.ex06;

import java.util.HashMap;

import weka.core.Instances;

public class Classifier {

	public Tree train(Instances instances) {
		// TODO: implement
		return null;
	};

	// calculate entropy with set class attribute
	public double entropy(Instances instances) {
		double entropy = 0.0;
		int countInstances = instances.numInstances();
		HashMap<Double, Integer> dtMap = new HashMap<Double, Integer>();

		for(int i=0; i < countInstances; i++) {
			double classAttribute = instances.get(i).classValue();
			dtMap.put(classAttribute, dtMap.get(classAttribute) + 1);
		}

		for(double value : dtMap.values()) {
			double freq = value / countInstances;
			entropy += (freq * Math.log(freq));
		}

		return entropy * -1;
	}

	/**
	 * calculates information gain for an individual feature
	 */
	public double informationGain(Instances instances, int attributeIndex) {
		// TODO: implement
		return 0.0;
	}
}
