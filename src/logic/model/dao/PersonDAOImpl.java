package logic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.*;

public class PersonDAOImpl implements PersonDAO {
	
	private static final String CREATE_PERSON_QUERY = "INSERT INTO person (Username, Study_Grade, School, Account, Host_Rating, Guest_Rating) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String DELETE_PERSON_QUERY = "DELETE FROM person where ID = ?";
	private static final String GET_PERSON_ID_QUERY = "SELECT ID FROM person WHERE Account = ?";
	private static final String GETALL_PERSONS_QUERY = "SELECT * FROM person";
	private static final String UPDATE_PERSON_RATINGS_QUERY = "UPDATE person SET Host_Rating = ?, Guest_Rating = ? WHERE ID = ?";
	private static final String GET_PERSON_QUERY = "SELECT * FROM person WHERE ID = ?";
	private static final String GET_PERSON_ACCOUNT_QUERY = "SELECT * FROM person WHERE Account = ?";
	private static final String GET_GROUP_PARTECIPANTS_QUERY = "SELECT Partecipant FROM group WHERE Name = ? AND Admin = ?";
	
	@Override
	public int createPerson(PersonBean personBean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(CREATE_PERSON_QUERY);
			stmt.setString(1, personBean.getUsername());
			stmt.setString(2, personBean.getStudyGrade());
			stmt.setString(3, personBean.getSchool());
			stmt.setString(4, personBean.getAccount());
			stmt.setFloat(5, personBean.getHostRating());
			stmt.setFloat(6, personBean.getGuestRating());
			
			stmt.executeUpdate();
			
			return getPersonId(personBean);
				
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	@Override
	public int removePerson(PersonBean personBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(DELETE_PERSON_QUERY);
			stmt.setInt(1, personBean.getId());
			
			return stmt.executeUpdate();
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
		
	}
	
	@Override
	public int getPersonId(PersonBean personBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		int id = 0;		
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_PERSON_ID_QUERY);
			stmt.setString(1, personBean.getAccount());

			ResultSet r = stmt.executeQuery();
			
			while (r.next()) {
				id = r.getInt(1);
			}
			
			return id;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	@Override
	public List<PersonBean> getAllPersons() throws SQLException {
		
		List<PersonBean> personsList = new ArrayList<>();
		PersonBean person = null;
	
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GETALL_PERSONS_QUERY);
			
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				person = new PersonBean(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getFloat(6), res.getFloat(7));
				personsList.add(person);
			}
			
			res.close();
			
			return personsList;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	@Override
	public int updatePerson(PersonBean personBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(UPDATE_PERSON_RATINGS_QUERY);
			stmt.setFloat(1, personBean.getHostRating());
			stmt.setFloat(2, personBean.getGuestRating());
			stmt.setInt(3, personBean.getId());
			
			return stmt.executeUpdate();
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	@Override
	public PersonBean getPerson(int id) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		PersonBean person = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_PERSON_QUERY);
			stmt.setInt(1, id);
			
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				person = new PersonBean(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getFloat(6), res.getFloat(7));
			}
			
			res.close();
			
			return person;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	@Override
	public PersonBean getPersonFromAccount(AccountBean accountBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		PersonBean person = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_PERSON_ACCOUNT_QUERY);
			stmt.setString(1, accountBean.getCf());
			
			ResultSet res = stmt.executeQuery();
				while(res.next()) {
					person = new PersonBean(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getFloat(6), res.getFloat(7));
				}
				
			res.close();
			
			return person;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	@Override
	public List<PersonBean> getGroupPartecipants(GroupBean groupBean) throws SQLException{
		
		List<PersonBean> groupPartecipants = new ArrayList<>();
		PersonBean person = null;
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_GROUP_PARTECIPANTS_QUERY);
			stmt.setString(1, groupBean.getName());
			stmt.setString(2, groupBean.getAdmin());
			
			ResultSet res = stmt.executeQuery();
				
			while (res.next()) {
				person = getPerson(res.getInt(1));
				groupPartecipants.add(person);
			}
				
			res.close();
			
			return groupPartecipants;
				
		}finally{
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
}