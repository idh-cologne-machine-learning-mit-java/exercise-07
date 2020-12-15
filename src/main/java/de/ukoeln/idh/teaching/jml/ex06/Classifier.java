package de.ukoeln.idh.teaching.jml.ex06;

import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.HashMap;

public class Classifier {

	public Tree train(Instances instances) {
		// TODO: implement
		return null;
	};

	public double entropy(Instances instances) {
		double ent = 0.0;
		
		//This should be in it's own method and should be tested. Sorry
		HashMap<Double, Integer> classCounts = new HashMap<Double, Integer>();
		for(int i = 0; i < instances.numInstances(); i++) {
			double cls;
			try {
				cls = instances.get(i).classValue();
			} catch (Exception e) {
				cls = instances.get(i).value(instances.numAttributes() - 1);
			}
		//
			Integer count = classCounts.get(cls);
			
			if(count == null) {
				classCounts.put(cls, 1);
			}
			else classCounts.put(cls, count + 1);
		}
		
		for(int count : classCounts.values()) {
			double div = (double) count / instances.numInstances();
			ent += (div * Math.log(div));
		}
		if(ent == 0.0) return ent;
		return - ent;
	}

	/**
	 * calculates information gain for a individual feature
	 */
	public double informationGain(Instances instances, int attributeIndex) {
		//This method is really, really ugly and should be refactored into smaller methods.
		//Submethods should also be tested individually.
		
		double baseEntropy = this.entropy(instances);
		int difValues = instances.numDistinctValues(attributeIndex - 1);
		
		Instances[] subInstances = new Instances[difValues];
		instances.sort(attributeIndex - 1);

		ArrayList<Integer> splits = new ArrayList<Integer>();
		
		splits.add(0);
		double attributeValue = instances.get(0).value(attributeIndex - 1);
		for(int i = 0; i < instances.numInstances(); i++) {
			if(instances.get(i).value(attributeIndex - 1) != attributeValue) {
				splits.add(i);
				attributeValue = instances.get(i).value(attributeIndex - 1);
			}
		}
		splits.add(instances.numInstances());
		
		for(int i = 0; i < difValues; i++) {
			subInstances[i] = new Instances(instances, splits.get(i), splits.get(i+1) - splits.get(i));
		}

		double[] entropyList = new double[difValues];	
		for(int i = 0; i < difValues; i++) {
			entropyList[i] = this.entropy(subInstances[i]);
		}
		
		double weightedEntropy = 0;
		for(int i = 0; i < difValues; i++) {
			double weight = (double) subInstances[i].numInstances() / instances.numInstances();
			weightedEntropy += weight * entropyList[i];
		}
		return baseEntropy - weightedEntropy;
	}
}
