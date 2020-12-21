package de.ukoeln.idh.teaching.jml.ex06;

import java.util.HashMap;

import weka.core.Attribute;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.RemoveWithValues;

public class Classifier {

	public Tree train(Instances instances) {
		// TODO: implement
		return null;
	};

	public double entropy(Instances instances) {
		
		HashMap<Double, Integer> classCounter = new HashMap<Double, Integer>();
		Attribute classAttribute; 
		double ent = 0.0;
		
		try {
			classAttribute = instances.classAttribute();
		} catch (Exception e) {
			//take last attribute as class attribute
			classAttribute = instances.attribute(instances.numAttributes() - 1);
		}
		
		//count instances per class
		for(int i = 0; i < instances.numInstances(); i++) {
			
			double classOfInstance = instances.get(i).value(classAttribute);
			//if no entry, set to 1
			if(classCounter.get(classOfInstance) == null) {
				classCounter.put(classOfInstance, 1);
			}else{
				classCounter.put(classOfInstance, classCounter.get(classOfInstance) + 1);
			}
			
		}
		
		//calculate entropy
		int numInstances = instances.numInstances();
		for(int count : classCounter.values()) {
				double relFreq = count / (double)numInstances;
				ent += relFreq * Math.log(relFreq);
		}
		return ent * -1;
	}
		

	/**
	 * calculates information gain for a individual feature
	 */
	public double informationGain(Instances instances, int attributeIndex) {
		double origEntropy = entropy(instances);
		double currentEntropy;
		double weightedSum = 0d;
		Instances split;
		
		Attribute attrSplitOn = instances.attribute(attributeIndex);
		
		RemoveWithValues filter = new RemoveWithValues();
		//Sets index of the attribute used
		filter.setAttributeIndex(Integer.toString(attributeIndex));
		
		for(int i = 0; i < attrSplitOn.numValues(); i++) {
			//Set which nominal labels are to be included in the selection
			filter.setNominalIndices(Integer.toString(i +1));
			try {
				filter.setInputFormat(instances);
				split = Filter.useFilter(instances, filter);
				currentEntropy = entropy(split);
				weightedSum += split.size() * currentEntropy;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return origEntropy - weightedSum / instances.size();
	}

	
}
