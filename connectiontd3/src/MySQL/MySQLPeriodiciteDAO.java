package MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.PeriodiciteDAO;
import metier.Periodicite;

public class MySQLPeriodiciteDAO implements PeriodiciteDAO{

	private static MySQLPeriodiciteDAO instance;
	
	private MySQLPeriodiciteDAO () {}
	
	
	public static MySQLPeriodiciteDAO getInstance() {
		if (instance==null) {
			instance = new MySQLPeriodiciteDAO();
		}
		return instance;
	}
	
	@Override
	public Periodicite getById(int id) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("Select * From Periodicite Where id_periodicite=?");
			requete.setInt(1,id);
			ResultSet res = requete.executeQuery();
			
			if (requete != null)requete.close();
			if (laConnexion != null)laConnexion.close();
			return new Periodicite(res.getInt("id_periodicite"),res.getString("libelle"));
			}
			catch (SQLException sqle) {
				throw (new IllegalArgumentException(sqle.getMessage()));

				}
	}

	@Override
	public boolean create(Periodicite periodicite) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("insert into Periodicite (libelle) values(?),Statement.RETURN_GENERATED_KEYS");
			requete.setString(1,periodicite.getNom());
			int res = requete.executeUpdate();
			
			if (res == 1) {
				ResultSet key = requete.getGeneratedKeys();
				
				if(key.next()) periodicite.setId(key.getInt(1));
			}
			if (requete != null)requete.close();
			
			if (laConnexion != null)laConnexion.close();
			return res==1;
			}
			catch (SQLException sqle) {
				System.out.println("Pb select" + sqle.getMessage());
				return false;
				}
		
	}

	@Override
	public boolean update(Periodicite periodicite) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("update Periodicite set libelle=? where id_periodicite=?");
			requete.setString(1, periodicite.getNom());
			requete.setInt(2, periodicite.getId());
			int res = requete.executeUpdate();
			if (requete != null)requete.close();
			
			if (laConnexion != null)laConnexion.close();
			return res==1;
			}
			catch (SQLException sqle) {
				throw (new IllegalArgumentException(sqle.getMessage()));

			}
			
	}

	@Override
	public boolean delete(Periodicite periodicite) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("delete from Periodicite where id_periodicite=?");
			requete.setInt(1, periodicite.getId());
			int res = requete.executeUpdate();
			
			if (requete != null)requete.close();
			
			if (laConnexion != null)laConnexion.close();
			
			return res==1;
			}
			catch (SQLException sqle) {
				throw (new IllegalArgumentException(sqle.getMessage()));

			}
	}

	@Override
	public Periodicite getByNom(Periodicite periodicite) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("Select * From Periodicite Where libelle=?");
			requete.setString(1,periodicite.getNom());
			ResultSet res = requete.executeQuery();
			
			if (requete != null)requete.close();
			if (laConnexion != null)laConnexion.close();
			return new Periodicite(res.getInt("id_periodicite"),res.getString("libelle"));
			}
			catch (SQLException sqle) {
				throw (new IllegalArgumentException(sqle.getMessage()));

				}
	}

}
