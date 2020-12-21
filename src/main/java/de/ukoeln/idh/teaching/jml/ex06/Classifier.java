package de.ukoeln.idh.teaching.jml.ex06;

import java.util.HashMap;

import weka.core.Attribute;
import weka.core.Instances;

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
		// TODO: implement
		return 0.0;
	}
}
