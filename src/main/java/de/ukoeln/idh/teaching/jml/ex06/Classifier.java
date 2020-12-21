package de.ukoeln.idh.teaching.jml.ex06;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.RemoveRange;
import weka.filters.unsupervised.instance.RemoveWithValues;

import java.util.HashMap;

public class Classifier {

	public Tree train(Instances instances) {
	

		return null;
	};

	public double entropy(Instances instances) {
		instances.setClassIndex(
				instances.classIndex() == -1 ? instances.numAttributes() -1 : instances.classIndex()
		);
	
		HashMap<Double, Integer> frequencies = new HashMap<Double, Integer>();
		for (Instance instance : instances) {
			double value = instance.classValue(); // getClass value of instance
			int occurrences = frequencies.containsKey(value) ? frequencies.get(value) + 1 : 1; // check if already occurred or initialize to 1
			frequencies.put(value, occurrences);
		}

	
		double entropy = 0;
		for (int freq : frequencies.values()) {
			double relFreq = (double) freq / instances.size(); // get rel freq
			entropy -= relFreq * Math.log(relFreq); // see formula
		}

		return entropy;
	}

	private double calculateInformationGain(Instances instances, int attributeIndex) throws Exception {
		instances.setClassIndex(
				instances.classIndex() == -1 ? instances.numAttributes() -1 : instances.classIndex()
		);
		Double entropy = entropy(instances);
		Attribute attr = instances.attribute(attributeIndex);


		RemoveWithValues filter = new RemoveWithValues();
		filter.setAttributeIndex(attributeIndex + "");

	
		Instances subInstances;
		Double subEntropy;
		double totalEntropy = 0;

		for (int i = 0; i < attr.numValues(); i++) {
		
			filter.setNominalIndices((i + 1) + "");
			filter.setInputFormat(instances);
			subInstances = Filter.useFilter(instances, filter);


			subEntropy = entropy(subInstances);
	
			totalEntropy += subEntropy * ((float)subInstances.size() / (float)instances.size()); 
		}

		return entropy - totalEntropy;
	}

	
	public double informationGain(Instances instances, int attributeIndex) {
		try {
			return calculateInformationGain(instances, attributeIndex);
		} catch (Exception e) {
			return -1d;
		}

	}
}