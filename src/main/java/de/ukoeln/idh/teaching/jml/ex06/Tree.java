package de.ukoeln.idh.teaching.jml.ex06;

import weka.core.Instance;

public class Tree {
	Tree[] children = null;
	int attributeIndex = -1;
	double prediction = Double.NaN;

	// nur von Kategorienfeats ausgegangen

	public double predict(Instance instance) throws IllegalArgumentException {
		if (instance == null) {
			throw new IllegalArgumentException("Please provide an instance");
		}
		double attributeValue = instance.value(attributeIndex);
		if (this.isLeaf()) {
			return prediction;
		} else {
			return this.children[(int) attributeValue].predict(instance);
		}
	}

	public boolean isLeaf() {
		return children == null || children.length == 0;
	}
}
