package fr.emac.gipsi.gsi;

import java.io.File;
import java.util.Iterator;

public class GestionFichier {

	public int calculNbFichier(String path){
		int res = 0;
		
		File file = new File(path);
		
		if (!file.isDirectory()){
			res = 1;
		}
		else {
			for (File elt : file.listFiles()) {
				if (elt.isDirectory())
					res = res + this.calculNbFichier(elt.getAbsolutePath());
				else
					res = res + 1;
			}
		}
		
		return res;
	}
}
