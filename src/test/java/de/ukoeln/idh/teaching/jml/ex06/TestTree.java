package de.ukoeln.idh.teaching.jml.ex06;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTree {
	Tree tree = new Tree();

	@BeforeEach
	public void setup() {

	}

	@Test
	public void testPredict() {
		assertThrows(IllegalArgumentException.class, () -> {
			tree.predict(null);
		});
	}

	@Test
	public void testIsLeaf() {

		assertTrue(tree.isLeaf());

		tree.children = null;
		assertTrue(tree.isLeaf());

		tree.children = new Tree[] { new Tree(), new Tree() };
		assertFalse(tree.isLeaf());
	}

}
