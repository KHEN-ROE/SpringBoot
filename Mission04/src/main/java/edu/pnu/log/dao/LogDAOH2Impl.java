package edu.pnu.log.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;

import edu.pnu.domain.MemberVO;

public class LogDAOH2Impl implements LogDAO {

	MemberVO mv;

	public static String driver = "org.h2.Driver";
	public static String url = "jdbc:h2:tcp://localhost/~/springboot";
	public static String username = "sa";
	public static String password = "";

	public static Connection con;
	public static Statement stmt;
	public static PreparedStatement psmt;
	public static ResultSet rs;
	
	
	
	//생성자에서 db연결
	public LogDAOH2Impl() {
		try {
			//드라이버 로드
			Class.forName(driver);
			//db연결
			con = DriverManager.getConnection(url,username,password);
		}
		catch(Exception e) {
			System.out.println("db연결 오류");
			e.printStackTrace();
		}
	}
	 
	@Override
	public Map<String, Object> addLog(Map<String, Object> map) {

		//파라미터가 없다. 리턴 값은 필요없고. 뭘 저장할 지 부터 정한다. 파라미터값은 db에 있는 필드
		try {
			//쿼리문 작성
			String query = "INSERT INTO DBLOG(method, SQLSTRING) VALUES(?,?)";
			//쿼리문 준비
			psmt = con.prepareStatement(query);
			//쿼리문 세팅
			psmt.setString(1, (String) map.get("method"));
			psmt.setString(2, (String) map.get("query"));
			//쿼리문 실행
			psmt.executeUpdate();

			map.put("method", map.get("method"));
			map.put("query", map.get("query"));

		}
		catch(Exception e) {
			System.out.println("dblog 오류");
			e.printStackTrace();
		}
		return map;
		
	}
	
	
	
}
