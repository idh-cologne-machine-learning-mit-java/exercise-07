package de.ukoeln.idh.teaching.jml.ex06;

import java.util.ArrayList;
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
				
			}else{
				
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
	 * calculates information gain for a individual feature (Reduzierung der Entropie nach Spalten)
	 * 
	 */
	
	
	public ArrayList<Integer> getAttributes(Instances instances, int subAttributeIndex){
		
		
		ArrayList<Integer> attributeValues = new ArrayList<Integer>();
				
				double attributeValue = 0;
				instances.sort(subAttributeIndex);
				for(int i = 0; i<instances.numInstances(); i++) {
					 if(instances.get(i).value(subAttributeIndex) != attributeValue || i==0  ) {
						attributeValues.add(i);
						attributeValue = instances.get(i).value(subAttributeIndex);
					}
				}
		
		attributeValues.add(instances.numInstances());
		return attributeValues;
		
	}
	
	
	public double informationGain(Instances instances, int attributeIndex) {
		// TODO: implement
		
		if (instances.classIndex() == -1)
			
			 instances.setClassIndex(instances.numAttributes() - 1);
		
		
		double originalEntropy = entropy(instances);
		
		int subAttributeIndex = attributeIndex - 1; 
		
		int numberOfInstances = instances.numInstances();
		
		int numberOfUniqueValues = instances.numDistinctValues(attributeIndex);
		
		Instances[] splitInstances = new Instances[numberOfUniqueValues];
		
		double[] splitEntropies = new double[numberOfUniqueValues];
		
		double totalSplitEntropy = 0;
		
		ArrayList<Integer> attributeValues = getAttributes(instances, subAttributeIndex);
		
		
		
		for(int i = 0; i < numberOfUniqueValues; i++) {
			splitInstances[i] = new Instances(instances, attributeValues.get(i), attributeValues.get(i+1) - attributeValues.get(i));
			splitEntropies[i] = entropy(splitInstances[i]);
			double split = (double) splitInstances[i].numInstances() / numberOfInstances; 
			totalSplitEntropy += split * splitEntropies[i];
		}
		

		double result = originalEntropy - totalSplitEntropy;
		
		System.out.println(result);
		
		return result;
	}
}
