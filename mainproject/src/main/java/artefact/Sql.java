package artefact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sql {
	public static void main(String[] args) throws ClassNotFoundException {
		String url = "jdbc:mysql://localhost:3306/userid?useSSL=false";
		String user = "root";
		String password = "root";

		String query0 = "CREATE TABLE users (id integer auto_increment primary key, firstname varchar(30), age integer)";
		String query = "SELECT id, firstname, age from users ";
		String query2 = "UPDATE users SET id = 1111 ";
		String query5 = "INSERT INTO users (firstname, age) VALUES ('Tom', 34)";

		
		try (Connection con = DriverManager.getConnection(url, user, password);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query0);
				ResultSet rs2 = st.executeQuery(query);
				ResultSet rs3 = st.executeQuery(query2);
				ResultSet rs4 = st.executeQuery(query5)) {
			if (rs.next()) {
				System.out.println(rs.getString(1));
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();

		}
	}

}