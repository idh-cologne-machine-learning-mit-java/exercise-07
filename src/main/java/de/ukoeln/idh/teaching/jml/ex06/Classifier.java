package de.ukoeln.idh.teaching.jml.ex06;

import weka.core.Instances;
import java.util.HashMap;

public class Classifier {

	public Tree train(Instances instances) {
		// TODO: implement
		return null;
	};

	public double entropy(Instances instances) {
		double ent = 0.0;
		HashMap<Double, Integer> classCounts = new HashMap<Double, Integer>();
		for(int i = 0; i < instances.numInstances(); i++) {
			double cls = instances.get(i).classValue();
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
		// TODO: implement
		return 0.0;
	}
}
