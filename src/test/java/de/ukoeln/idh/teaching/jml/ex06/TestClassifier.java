package de.ukoeln.idh.teaching.jml.ex06;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class TestClassifier {

	Classifier classifier = new Classifier();
	Instances data;
	Tree results;


	@BeforeEach
	public void setup() throws Exception{
		DataSource source = new DataSource("src/resources/germancredit/train.arff");
		data = source.getDataSet();
		if(data.classIndex() == -1){
			data.setClassIndex(data.numAttributes() - 1);
		}
		System.out.println(data.numInstances());
	}

	@Test
	public void testTrain(){
		results = classifier.train(data);
		assertThrows(IllegalArgumentException.class,()->{classifier.train(null);});
	}

	@Test
	public void testEntropy() {
		//illegal input
		assertThrows(IllegalArgumentException.class, ()->{classifier.entropy(null);});
		
		double H = classifier.entropy(data);
		
		assertTrue(0.0 <= H && H <= 2.0);

	}

	@Test
	public void testInformationGain() {
		assertThrows(IllegalArgumentException.class, () -> {classifier.informationGain(null, 0);});
		assertThrows(IllegalArgumentException.class, () -> {classifier.informationGain(data, -1);});
		assertThrows(IllegalArgumentException.class, ()-> {classifier.informationGain(data, data.numAttributes()+1);});

		double ig = classifier.informationGain(data, 1);
		assertTrue(0.0 <= ig && ig <= 1.0);
	}
}
