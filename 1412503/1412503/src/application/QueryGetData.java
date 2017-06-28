package application;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.String;

import application.ConnectionSQL;

public class QueryGetData {
	private static Connection conn = ConnectionSQL.DBConnection();
	public static boolean checkUserName(String userName) throws ClassNotFoundException, SQLException{
//		Connection connection = ConnectionSQL.DBConnection();
		Statement statement = conn.createStatement();
		String sql = "select * from NHANVIEN where TENDN = '" + userName + "'";
		String sql1 = "select * from SINHVIEN where TENDN = '" + userName + "'";
		//nhanvien
		ResultSet rs = statement.executeQuery(sql);
		int i = 0;
		while(rs.next()){
			i++;
		}		
		if(i != 0){
			return false;
		}
		//sinhvien
		ResultSet rs1 = statement.executeQuery(sql1);
		int i1 = 0;
		while(rs1.next()){
			i1++;
		}		
		if(i1 != 0){
			return false;
		}
		return true;
	}
	
	public static boolean getData(String userName, String pass) throws ClassNotFoundException, SQLException {
		//Connection connection = ConnectionSQL.DBConnection();
		int i =0, i1 = 0;
		// Tạo đối tượng Statement
		Statement statement = conn.createStatement();
		// nhanvien
		String sql = "select MATKHAU from NHANVIEN where TENDN = '" + userName +"' and MATKHAU = HASHBYTES('SHA1', CONVERT( VARBINARY, '" + pass + "'))";
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()){
			i++;
		}
		//sinhvien
		String sql1 = "select MATKHAU from SINHVIEN where TENDN = '" + userName +"' and MATKHAU = HASHBYTES('MD5', CONVERT( VARBINARY, '" + pass + "'))";
		ResultSet rs1 = statement.executeQuery(sql1);
		while(rs1.next()){
			i1++;
		}
		
		if(i!=0)
			return true;
		else if(i1!=0)
			return true;
		return false;
	}
	public static void closeConn(){
		try {
			System.out.println("Close Connect");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
