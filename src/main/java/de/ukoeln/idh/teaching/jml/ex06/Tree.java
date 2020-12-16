package de.ukoeln.idh.teaching.jml.ex06;

import weka.core.Instance;

public class Tree {
	Tree[] children = null;
	int attributeIndex = -1;
	double prediction = Double.NaN;

	public double predict(Instance instance) {
		if (this.isLeaf()) {
			return prediction;
		} else {
			double attributeValue = instance.value(attributeIndex);
			return this.children[(int) attributeValue].predict(instance);
		}
	}

	public boolean isLeaf() {
		if (this.children == null || this.children.length == 0)
			return true;
		return false;
	}
}
