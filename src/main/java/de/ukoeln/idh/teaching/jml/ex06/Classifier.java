package de.ukoeln.idh.teaching.jml.ex06;

import weka.core.Instances;
import weka.core.Instance;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Classifier {

	public Tree train(Instances instances) {
		// TODO: implement
		return null;
	};

	public double entropy(Instances instances) throws IllegalArgumentException {
		if (instances == null) {
			throw new IllegalArgumentException("Please provide instances!");
		}
		double H = 0.0;
		HashMap<Double, Integer> classFreq = new HashMap<Double, Integer>();
		double totalNumberInstances = instances.numInstances();

		for (Instance instance : instances) {
			double classValue = instance.classValue();
			if (!classFreq.containsKey(classValue)) {
				classFreq.put(classValue, 1);
			} else {
				classFreq.put(classValue, classFreq.get(classValue) + 1);
			}
		}

		for (int freq : classFreq.values()) {
			if (freq == 0) {
				continue;
			}
			double p = freq / totalNumberInstances;
			System.out.println(p);
			H += (p * Math.log(p));
			System.out.println(H);
		}
		System.out.println(H);
		return (-1) * H;
	}

	/**
	 * calculates information gain for an individual feature
	 */
	public double informationGain(Instances instances, int attributeIndex) throws IllegalArgumentException {
		if (instances == null || attributeIndex == -1 || attributeIndex > instances.numAttributes()) {
			throw new IllegalArgumentException("Please verify that you have called with correct parameters");
		}
		
		//
		int distinct = instances.numDistinctValues(attributeIndex);
		
		int cls = instances.numClasses();
		HashMap<Double, Integer> splits = new HashMap<Double, Integer>();
		HashMap<Double, ArrayList<Instance>> subsets = new HashMap<Double, ArrayList<Instance>>();
		int size = 0 ;
		size=1;

		for (Instance instance : instances) {
			double key = instance.value(attributeIndex);
			if(splits.containsKey(key)){
				splits.put(key, splits.get(key)+1);
				ArrayList<Instance> set = subsets.get(key);
				set.add(instance);
				subsets.put(key, set);
			}else{
				splits.put(key, 1);
				ArrayList<Instance> set = new ArrayList<Instance>();
				set.add(instance);
				subsets.put(key, set);
			}
		}
		double dataSetEntropy = this.entropy(instances);
		double ig = 0.0;
		for(Map.Entry<Double, Integer> me:splits.entrySet()){
			int countSet =  me.getValue();
			int total = instances.numInstances();
			double distri = countSet/total;
			Instances subset = new Instances(instances);
			double k = me.getKey();
			ArrayList<Instance> bla = subsets.get(k);
			for(Instance instance : subset){
				if(!bla.contains(instance)){
					subset.remove(instance);
				}
			}
			double weiEntropy = this.entropy(subset);
			ig+= distri*weiEntropy;
		}

		return dataSetEntropy-ig;
	}
}
