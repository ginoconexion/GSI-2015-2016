package fr.emac.gipsi.gsi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class testCalcul {

	private Calcul calcul;
	@Before
	public void setUp() throws Exception {
		this.calcul = new Calcul();
	}

	@Test
	public void test() {
		assertEquals(1, calcul.calculFactorielle(1));
		
	}
	@Test
	public void testFactorielle0(){
		assertEquals(1, calcul.calculFactorielle(0));
	}
	@Test
	public void testFactorielle3(){
		assertEquals(6, calcul.calculFactorielle(3));
	}
	@Test
	public void testFactorielle(){
		assertEquals(0, calcul.calculFactorielle(-1));
	}
	
	public void testCoeffBinomial(){
		assertEquals(10, calcul.calculerCoefficientBinomial(5, 3));
	}

}
