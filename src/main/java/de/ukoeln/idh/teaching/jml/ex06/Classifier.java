package de.ukoeln.idh.teaching.jml.ex06;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.UnassignedClassException;

public class Classifier {

	public Tree train(Instances instances) {
		// TODO: implement
		return null;
	};

	public double entropy(Instances instances) {
		try {
			Map<Double, Double> countMap = new HashMap<Double, Double>();
			Enumeration<Instance> e = instances.enumerateInstances();
			
			while(e.hasMoreElements()) {
				Instance instance = e.nextElement();
				double value = instance.classValue();

				if (!countMap.containsKey(value))
					countMap.put(value, 1d);
				else
					countMap.put(value, countMap.get(value)+1);
			}
			
			double entropy = 0.0;
			double numInstances = instances.numInstances();
			
			for (double value : countMap.values()) {
				double freq = value / numInstances;
				entropy += (freq * Math.log(freq));
			}
			
			entropy = -entropy;
			return entropy;
		} catch (UnassignedClassException e) {
			instances.setClassIndex(instances.numAttributes() - 1);
			return entropy(instances);
		}
	}

	/**
	 * calculates information gain for a individual feature
	 */
	public double informationGain(Instances instances, int attributeIndex) {
		double entropy = this.entropy(instances);

		int attIndex = attributeIndex - 1;
		int distValues = instances.numDistinctValues(attIndex);
		int numInstances = instances.numInstances();
		
		Instances[] splits = new Instances[distValues];
		
		for (int i = 0; i < distValues; i++) 
			splits[i] = new Instances(instances, numInstances);
		
		Enumeration<Instance> e = instances.enumerateInstances();
		
		while (e.hasMoreElements()) {
			Instance instance = e.nextElement();
			splits[(int) instance.value(instances.attribute(attIndex))].add(instance);
		}

		double ig = entropy;
		
		for (int i = 0; i < distValues; i++) {
			double div = (double) splits[i].numInstances() / (double) numInstances;
			ig -= div * this.entropy(splits[i]);
		}
		
		return ig;
	}
}
