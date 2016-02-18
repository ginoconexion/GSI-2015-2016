package fr.emac.gipsi.gsi.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.emac.gipsi.gsi.Polynome2nd;
import fr.emac.gipsi.gsi.Racine;

public class TestPolynome2nd {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Polynome2nd p1 = new Polynome2nd(1, 1, -2);
		System.out.println(p1.resoudre());
		ArrayList<Racine> res = p1.resoudre();
		assertTrue(res.get(0).getPartieReelle()== 0.5 || res.get(0).getPartieReelle() == -2.5);
	}

}
