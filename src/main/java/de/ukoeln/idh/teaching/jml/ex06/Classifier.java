package de.ukoeln.idh.teaching.jml.ex06;

import java.util.HashMap;

import weka.core.Instances;

public class Classifier {

	public Tree train(Instances instances) {
		// TODO: implement
		return null;
	};

	public HashMap<Double, Integer> buildFrequencyTable(Instances instances) {
		
		HashMap<Double, Integer> frequencyTable = new HashMap<Double, Integer>();		
		
		for(int index = 0; index < instances.numInstances(); index++) {
			
			double classValue = instances.get(index).classValue();
			
			if(frequencyTable.get(classValue) == null) {
				
				frequencyTable.put(classValue, 1);
				
			}else {
				
				frequencyTable.put(classValue, frequencyTable.get(classValue) + 1);
				
			}
			
		}
		
		return frequencyTable;
		
	}

	public double entropy(Instances instances) {
		
			if (instances.classIndex() == -1)
				
				 instances.setClassIndex(instances.numAttributes() - 1);
			
			//build Frequency Table
			
			HashMap<Double, Integer> frequencyTable = buildFrequencyTable(instances);
			
			// Calculate Entropy
			
			double entropy = 0.0;
			
			for(int count : frequencyTable.values()) {
				
				entropy -= (((double) count / instances.numInstances()) * Math.log((double) count / instances.numInstances()));
			}
			
			System.out.println(entropy);
		
			return (double)Math.round(entropy * 1000d) / 1000d;
		
		}
	/**
	 * calculates information gain for a individual feature
	 */
	public double informationGain(Instances instances, int attributeIndex) {
		// TODO: implement
		return 0.0;
	}
}
