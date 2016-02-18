package fr.emac.gipsi.gsi.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.emac.gipsi.gsi.element.Grille;

public class TestDemineur {
	
	private Grille grille;

	@Before
	public void setUp() throws Exception {
		this.grille = new Grille(10,10, 10);
	}

	@Test
	public void test() {
		Grille grille = new Grille(1, 1, 1);
	}
	
	@Test 
	public void creationGrille(){
		Grille grille = new Grille(10,10, 10);
	}
	
	@Test
	public void mettreVisible(){
		assertFalse(grille.getCase(0, 0).isVisible());
		grille.decouvrir1Case(0, 0);
		assertTrue(grille.getCase(0, 0).isVisible());
	}
	@Test
	public void calculNbBombes(){
		
	}
	
	

}
