package MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.RevueDAO;
import metier.Revue;

public class MySQLRevueDAO implements RevueDAO{
	
	private static MySQLRevueDAO instance;
	
	private MySQLRevueDAO() {}

	public static MySQLRevueDAO getInstance() {
		if (instance==null) {
			instance = new MySQLRevueDAO();
		}
		return instance;
	}



	@Override
	public boolean create(Revue revue) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("insert into Revue (titre, description, tarif_numero, visuel, id_periodicite) values(?,?,?,?,?)");
			requete.setString(1, revue.getTitre());
			requete.setString(2, revue.getDescription());
			requete.setDouble(3, revue.getTarif_numero());
			requete.setString(4, revue.getVisuel());
			requete.setInt(5, revue.getId_perio());
			int res = requete.executeUpdate();
			
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
	public boolean update(Revue revue) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("update Revue set titre=?, description=?,tarif_numero=?,visuel=?,id_periodicite=?  where id_revue=?");
			requete.setString(1, revue.getTitre());
			requete.setString(2, revue.getDescription());
			requete.setDouble(3, revue.getTarif_numero());
			requete.setString(4, revue.getVisuel());
			requete.setInt(5, revue.getId_perio());
			requete.setInt(6, revue.getId());
			
			int res = requete.executeUpdate();
			
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
	public boolean delete(Revue revue) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("delete from Revue where id_revue=?");
			requete.setInt(1, revue.getId());
			int res = requete.executeUpdate();
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
	public Revue getById(int id) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("Select * From Revue Where id_revue=?");
			requete.setInt(1,id);
			ResultSet res = requete.executeQuery();
			
			if (requete != null)requete.close();
			if (laConnexion != null)laConnexion.close();
			return new Revue(res.getInt("id_revue"),res.getString("titre"),res.getString("description"),res.getDouble("tarif_numero"),res.getString("visuel"),res.getInt("id_periodicite"));
			}
			catch (SQLException sqle) {
				System.out.println("Pb select" + sqle.getMessage());
				return null;
				}
	}
	
	@Override
	public Revue getByTitre(Revue revue) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("Select * From Revue Where titre=?");
			requete.setString(1,revue.getTitre());
			ResultSet res = requete.executeQuery();
			
			if (requete != null)requete.close();
			if (laConnexion != null)laConnexion.close();
			return new Revue(res.getInt("id_revue"),res.getString("titre"),res.getString("description"),res.getDouble("tarif_numero"),res.getString("visuel"),res.getInt("id_periodicite"));
			}
			catch (SQLException sqle) {
				System.out.println("Pb select" + sqle.getMessage());
				return null;
				}
	}



	@Override
	public ArrayList<Revue> GetByPerio(Revue revue) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("Select * From Revue Where id_periodicite=?");
			requete.setInt(1,revue.getId_perio());
			ResultSet res = requete.executeQuery();
			
			if (requete != null)requete.close();
			if (laConnexion != null)laConnexion.close();
			ArrayList<Revue> Liste = new ArrayList<Revue>();
			
			while (res.next()) {
				Liste.add(new Revue(res.getInt("id_revue"),res.getString("titre"),res.getString("description"),res.getDouble("tarif_numero"),res.getString("visuel"),res.getInt("id_periodicite")));
				}
			return Liste;
			
			}
			catch (SQLException sqle) {
				System.out.println("Pb select" + sqle.getMessage());
				return null;
				}
	}

	
	
}
