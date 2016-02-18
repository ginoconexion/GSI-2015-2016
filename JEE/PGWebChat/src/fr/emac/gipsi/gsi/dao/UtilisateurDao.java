/**
 * 
 */
package fr.emac.gipsi.gsi.dao;

import fr.emac.gipsi.gsi.model.Utilisateur;

/**
 * @author Paul
 *
 */
public interface UtilisateurDao {
	
	void creer(Utilisateur utilisateur) throws DAOException;
	Utilisateur trouver(String pseudo) throws DAOException;

}
