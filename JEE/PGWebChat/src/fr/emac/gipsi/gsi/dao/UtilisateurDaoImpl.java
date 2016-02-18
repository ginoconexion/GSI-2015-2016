package fr.emac.gipsi.gsi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import fr.emac.gipsi.gsi.model.Utilisateur;


public class UtilisateurDaoImpl implements UtilisateurDao {

	private DAOFactory daoFactory;
	private static final String SQL_SELECT_PAR_PSEUDO = "SELECT id, pseudo, mdp FROM Utilisateur WHERE pseudo = ?";
	private static final String SQL_INSERT = "INSERT INTO Utilisateur (pseudo, mdp) VALUES (?, ?)";

	public UtilisateurDaoImpl(DAOFactory daoFactory) {

		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(Utilisateur utilisateur) throws DAOException {
		
		Connection connexion = null;
		ResultSet valeursAutoGeneres = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, utilisateur.getLogin(), utilisateur.getPwd());
			int statut = preparedStatement.executeUpdate();
			
			if (statut == 0)
				throw new DAOException("Echec de la création de l'utilisateur");
			valeursAutoGeneres = preparedStatement.getGeneratedKeys();
			if (valeursAutoGeneres.next())
				utilisateur.setId(valeursAutoGeneres.getLong(1));
			else
				throw new DAOException("Echec de la création de l'utilisateur, aucun ID retourné");
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally {
			fermeturesSilencieuses(valeursAutoGeneres, preparedStatement, connexion);
		}
	}

	@Override
	public Utilisateur trouver(String pseudo) throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement= null;
		ResultSet resultSet = null;
		Utilisateur utilisateur = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_PSEUDO, false, pseudo );
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */

			if ( resultSet.next() ) {
				utilisateur = map( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
	
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}

		return utilisateur;

	}

	
	
	//* Fermeture silencieuse du resultset */
    public static void fermetureSilencieuse( ResultSet resultSet ) {
        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture du ResultSet : " + e.getMessage() );
            }
        }
    }

    /* Fermeture silencieuse du statement */
    public static void fermetureSilencieuse( Statement statement ) {
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture du Statement : " + e.getMessage() );
            }
        }
    }

    /* Fermeture silencieuse de la connexion */
    public static void fermetureSilencieuse( Connection connexion ) {
        if ( connexion != null ) {
            try {
                connexion.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture de la connexion : " + e.getMessage() );
            }
        }
    }

    /* Fermetures silencieuses du statement et de la connexion */
    public static void fermeturesSilencieuses( Statement statement, Connection connexion ) {
        fermetureSilencieuse( statement );
        fermetureSilencieuse( connexion );
    }

    /* Fermetures silencieuses du resultset, du statement et de la connexion */
    public static void fermeturesSilencieuses( ResultSet resultSet, Statement statement, Connection connexion ) {
        fermetureSilencieuse( resultSet );
        fermetureSilencieuse( statement );
        fermetureSilencieuse( connexion );
    }

    /*
     * Initialise la requête préparée basée sur la connexion passée en argument,
     * avec la requête SQL et les objets donnés.
     */
    public static PreparedStatement initialisationRequetePreparee( Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
        for ( int i = 0; i < objets.length; i++ ) {
            preparedStatement.setObject( i + 1, objets[i] );
        }
        return preparedStatement;
    }
    
    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des utilisateurs (un
     * ResultSet) et un bean Utilisateur.
     */
    private static Utilisateur map( ResultSet resultSet ) throws SQLException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId( resultSet.getLong( "id" ) );
        utilisateur.setLogin(resultSet.getString("pseudo"));
        utilisateur.setPwd(resultSet.getString("mdp"));
        return utilisateur;
    }

}
