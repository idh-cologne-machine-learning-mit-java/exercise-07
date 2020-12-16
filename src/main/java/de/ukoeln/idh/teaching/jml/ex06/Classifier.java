package de.ukoeln.idh.teaching.jml.ex06;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import weka.core.Instance;
import weka.core.Instances;

public class Classifier {

	public Tree train(Instances instances) {
		// TODO: implement
		return null;
	};

	public double entropy(Instances instances) {
		// TODO: implement
		
		Enumeration<Instance> instanceEnumeration = instances.enumerateInstances();
		Map<Double, Integer> classCounter = new HashMap<>();
		// there must be a weka method that does this...
		while (instanceEnumeration.hasMoreElements()) {
			double value = instanceEnumeration.nextElement().classValue();
			int count = 0;
			if (classCounter.containsKey(value))
				count = classCounter.get(value);
			classCounter.put(value, count+ 1);
		}
		
		int instancesSize = instances.size();	
		double entropy = 0.0;
		double relFreq;
		for(Map.Entry<Double, Integer> e : classCounter.entrySet()) {
			relFreq = e.getValue() / (double) instancesSize;
			double current =  relFreq * Math.log(relFreq);
			entropy += current;
			
		}
		entropy = - entropy;
		return entropy;
	}

	/**
	 * calculates information gain for a individual feature
	 */
	public double informationGain(Instances instances, int attributeIndex) {
		// TODO: implement
		double entropyBefore = entropy(instances);
		// TODO split instances according to attributeValue
		
		
		
		
		// TODO compute entropy for each part
		return 0.0;
	}
}
