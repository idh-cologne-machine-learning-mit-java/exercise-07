package de.ukoeln.idh.teaching.jml.ex06;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import weka.core.Instances;
import weka.core.Attribute;
import weka.core.Instance;

public class Classifier {

	public Tree train(Instances instances) {
		// TODO: implement
		return null;
	};
	
	public HashMap<Double, Integer> distribution(Instances instances) {
		
		HashMap<Double, Integer> distribution = new HashMap<Double, Integer>();		
		
		Attribute classAttribute; 
		try {
			classAttribute = instances.classAttribute();
		} catch (Exception e) {
			classAttribute = instances.attribute(instances.numAttributes() - 1);
		}
		
		for(int index = 0; index < instances.numInstances(); index++) {
			
			double classValue = instances.get(index).value(classAttribute);
			
			if(distribution.get(classValue) == null) {
				distribution.put(classValue, 1);
			}else{
				distribution.put(classValue, distribution.get(classValue) + 1);
			}
			
		}
		
		return distribution;
	}

	public double entropy(Instances instances) {
		double sum = 0;
		HashMap<Double, Integer> distribution = this.distribution(instances);
		
		int numInstances = instances.numInstances();
		for(int count : distribution.values()) {
				double relativeFrequency = count / (double)numInstances;
				sum += relativeFrequency * Math.log(relativeFrequency);
				System.out.println(sum);
		}
		return sum * -1;
	}

	
	private Instances[] instancesSubsets(Instances instances, int attributeIndex) {
		int numDistinctValues = instances.numDistinctValues(attributeIndex - 1);
		Instances[] instancesSubsets = new Instances[instances.numDistinctValues(attributeIndex - 1)];
		instances.sort(attributeIndex - 1);

		ArrayList<Integer> splitIndices = new ArrayList<Integer>();
		
		splitIndices.add(0);
		double attributeValue = instances.get(0).value(attributeIndex - 1);
		for(int i = 0; i < instances.numInstances(); i++) {
			if(instances.get(i).value(attributeIndex - 1) != attributeValue) {
				splitIndices.add(i);
				attributeValue = instances.get(i).value(attributeIndex - 1);
			}
		}
		splitIndices.add(instances.numInstances());
		
		for(int i = 0; i < numDistinctValues; i++) {
			instancesSubsets[i] =new Instances(instances,splitIndices.get(i), splitIndices.get(i+1) - splitIndices.get(i));
		}
		return instancesSubsets;
	}
		
	/**
	 * calculates information gain for a individual feature
	 */
	public double informationGain(Instances instances, int attributeIndex) {
		double entropy = this.entropy(instances);
		int numDistinctValues = instances.numDistinctValues(attributeIndex - 1);
		
		Instances[] instancesSubset = this.instancesSubsets(instances, attributeIndex);

		double weightedEntropy = 0;
		for(int i = 0; i < numDistinctValues; i++) {
			double weight = (double) instancesSubset[i].numInstances() / instances.numInstances();
			double splitEntropy = this.entropy(instancesSubset[i]);
			weightedEntropy += weight * splitEntropy;
		}
		return entropy - weightedEntropy;
	}
}
