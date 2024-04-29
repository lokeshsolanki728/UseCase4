package in.co.rays.dto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;

//import org.apache.log4j.Logger;

import in.co.rays.exception.DuplicateRecordException;

	/**
	 * JDBC Implementation of UserModel.
	 * 
	 * @author Lokesh Solanki
	 *
	 */


	public class UserModel {
		private static Logger log = Logger.getLogger(UserModel.class);

		public int nextPK() throws Exception {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hibernatenew","root","root");
			log.debug("Model nextPK Started");

			String sql = "SELECT MAX(ID) FROM userdto";
			
			int pk = 0;
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					pk = rs.getInt(1);
				}
				rs.close();
			
			return pk + 1;
		}

		 public long add(UserDTO dto) throws DuplicateRecordException, Exception{
	      		log.debug("Model add Started");
	      		Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hibernatenew","root","root");
				log.debug("Model nextPK Started");

			String sql = "INSERT INTO userdto VALUES(?,?,?,?,?,?,?)";

			int pk = 0;

			UserDTO existbean = findByLogin(dto.getLoginId());                               
			if (existbean != null) {
				throw new DuplicateRecordException("login Id already exists");

			}

				pk = nextPK();

				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, pk);
				pstmt.setString(2, dto.getFirstName());
				pstmt.setString(3, dto.getLastName());
				pstmt.setString(4, dto.getLoginId());
				pstmt.setString(5, dto.getPassword());
				pstmt.setDate(6, new Date(dto.getDate().getTime()));
				pstmt.setString(7, dto.getAddress());
				int a = pstmt.executeUpdate();
				conn.commit();
				pstmt.close();
			return pk;
		}

		public void delete(UserDTO dto) throws Exception {
			log.debug("Model delete start");
			String sql = "DELETE FROM userdto WHERE ID=?";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hibernatenew","root","root");
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, dto.getId());
				pstmt.executeUpdate();
				conn.commit();
				pstmt.close();
			
			log.debug("Model Delete End");
		}

		public UserDTO findByLogin(String login) throws Exception {
			log.debug("Model findByLogin Started");
			String sql = "SELECT * FROM userdto WHERE loginId=?";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hibernatenew","root","root");
			UserDTO bean = null;
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, login);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					bean = new UserDTO();
					bean.setId(rs.getLong(1));
					bean.setFirstName(rs.getString(2));
					bean.setLastName(rs.getString(3));
					bean.setLoginId(rs.getString(4));
					bean.setPassword(rs.getString(5));
					bean.setDate(rs.getDate(6));
					bean.setAddress(rs.getString(7));
				}
				rs.close();

			return bean;
		}

		public UserDTO findByPK(long pk) throws Exception {
			log.debug("Model findBy PK start");
			String sql = "SELECT * FROM userdto WHERE ID=?";
			UserDTO bean = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hibernatenew","root","root");
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, pk);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					bean = new UserDTO();
					bean.setId(rs.getLong(1));
					bean.setFirstName(rs.getString(2));
					bean.setLastName(rs.getString(3));
					bean.setLoginId(rs.getString(4));
					bean.setPassword(rs.getString(5));
					bean.setDate(rs.getDate(6));
					bean.setAddress(rs.getString(7));
				}
				rs.close();
			
			return bean;
		}

		public void update(UserDTO bean) throws Exception {
			log.debug("Model Update Start");
			String sql = "UPDATE userdto SET FIRST_NAME=?,LAST_NAME=?,LOGINID=?,PASSWORD=?,DATE=?,ADDRESS=? WHERE ID=?";
			Connection conn = null;
			UserDTO existBean = findByLogin(bean.getLoginId());
			if (existBean != null && !(existBean.getId() == bean.getId())) {
				throw new DuplicateRecordException("LoginId is Already Exist");
			}
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bean.getFirstName());
				pstmt.setString(2, bean.getLastName());
				pstmt.setString(3, bean.getLoginId());
				pstmt.setString(4, bean.getPassword());
				pstmt.setDate(5, new java.sql.Date(bean.getDate().getTime()));
				pstmt.setString(6, bean.getAddress());
				pstmt.setLong(7, bean.getId());
				pstmt.executeUpdate();
				conn.commit();
				pstmt.close();
			
			log.debug("Model Update End ");
		}

		public List search(UserDTO bean) throws Exception {
			return search(bean, 0, 0);
		}

		public List search(UserDTO bean, int pageNo, int pageSize) throws Exception {
			log.debug("Model Search Start");
			StringBuffer sql = new StringBuffer("SELECT * FROM userdto where 1=1");
			if (bean != null) {
				if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
					sql.append(" AND FIRST_NAME like '" + bean.getFirstName() + "%'");
				}
				
				
			if (pageSize > 0) {
				// Calculate start record index
				pageNo = (pageNo - 1) * pageSize;

				sql.append(" Limit " + pageNo + ", " + pageSize);
				// sql.append(" limit " + pageNo + "," + pageSize);
			}
			}
			System.out.println(sql);
			List list = new ArrayList();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hibernatenew","root","root");
			
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					bean = new UserDTO();
					bean.setId(rs.getLong(1));
					bean.setFirstName(rs.getString(2));
					bean.setLastName(rs.getString(3));
					bean.setLoginId(rs.getString(4));
					bean.setPassword(rs.getString(5));
					bean.setDate(rs.getDate(6));
					bean.setAddress(rs.getString(7));
					
					list.add(bean);

				}
				rs.close();
				return list;
			}
		 public UserDTO authenticate(String login, String password) throws Exception {
			log.debug("Model authenticate Started");
			StringBuffer sql = new StringBuffer("SELECT * FROM userdto WHERE LOGINID =? AND PASSWORD =?");
			UserDTO bean = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hibernatenew","root","root");
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, login);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					bean = new UserDTO();
					bean.setId(rs.getLong(1));
					bean.setFirstName(rs.getString(2));
					bean.setLastName(rs.getString(3));
					bean.setLoginId(rs.getString(4));
					bean.setPassword(rs.getString(5));
					bean.setDate(rs.getDate(6));
					bean.setAddress(rs.getString(7));

		}
				return bean;
		 }
		
		
}
