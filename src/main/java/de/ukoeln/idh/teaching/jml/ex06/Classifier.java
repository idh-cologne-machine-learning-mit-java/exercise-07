package de.ukoeln.idh.teaching.jml.ex06;

import weka.core.Instances;

import java.util.HashMap;
import java.util.ArrayList;

public class Classifier {

	public Tree train(Instances instances) {
		// TODO: implement
		return null;
	};

	public double entropy(Instances instances) {
		
		double entropy = 0.0;
		HashMap <Double, Integer> classMap = new HashMap <Double, Integer>();
		
		for(int i=0; i < instances.numInstances(); i++) {
				double key = instances.get(i).classValue();
				if(!classMap.containsKey(key)) {
					classMap.put(key, 1);
				}
				
				else {
					classMap.put(key, classMap.get(key) +1);
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
		ArrayList <Integer> attValues = new ArrayList <Integer>();
		int attIndex = attributeIndex -1;
		double attValue = 0;
		instances.sort(attIndex);
		
		for(int i=0; i < instances.numInstances(); i++) {
			if(instances.get(i).value(attIndex) != attValue || i==0) {
				attValues.add(i);
				attValue = instances.get(i).value(attIndex);
			}
		}
		
		attValues.add(instances.numInstances());
		
		double unweightedEntropy = this.entropy(instances);
		double weightedEntropy = 0;
		Instances[] splits = new Instances [instances.numDistinctValues(attIndex)];
		double[] splitsEntropy = new double [instances.numDistinctValues(attIndex)];
		
		for(int i=0; i < instances.numDistinctValues(attIndex); i++) {
			splits[i] = new Instances(instances, attValues.get(i), attValues.get(i+1) - attValues.get(i));
			double split = (double) splits[i].numInstances() / instances.numInstances();
			splitsEntropy[i] = entropy(splits[i]);
			weightedEntropy += split * splitsEntropy[i];
		}
		
		return unweightedEntropy + weightedEntropy;
	}
}
