package fr.emac.gipsi.gsi;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import java.io.File;

public class testNbFichier {
	
	private GestionFichier gestion;
	private String current;
	

	@Before
	public void setUp() throws Exception {
		this.gestion = new GestionFichier();
		this.current = new File( "." ).getCanonicalPath();
	}

	@Test
	public void test() {
		
		String path = System.getProperty("user.dir");
		assertEquals(2, gestion.calculNbFichier(path + "/test"));
	}

}
