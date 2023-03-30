package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

public class MemberDAOH2impl implements MemberInterface {

	MemberVO mv;
	Map<String, Object> map = new HashMap<>();
	
	public static Date today = new Date();
	public static long timeInMilliSeconds = today.getTime();
	public static java.sql.Date sqlDate = new java.sql.Date(timeInMilliSeconds);

	public static String driver = "org.h2.Driver";
	public static String url = "jdbc:h2:tcp://localhost/~/springboot";
	public static String username = "sa";
	public static String password = "";

	public static Connection con;
	public static Statement stmt;
	public static PreparedStatement psmt;
	
	public static ResultSet rs;

	// 생성자에서 db 연결
	public MemberDAOH2impl() {

		try {
			// 드라이버 로드
			Class.forName(driver);
			
		} catch (Exception e) {
			System.out.println("드라이버 로드 오류");
			e.printStackTrace();
		}
		try {
			// db 연결
			con = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			System.out.println("db연결 오류");
			e.printStackTrace();
		}

	}

	@Override
	public Map<String, Object> getMembers() {
		List<MemberVO> list = new ArrayList<>();
		try {
			String query = "SELECT * FROM MEMBER";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				Date date = rs.getDate("regidate");
				mv = new MemberVO(id, pass, name, date);
				list.add(mv);
			}
			map.put("query", query);
			map.put("list", list );
			map.put("method", "GET");
		
		} catch (Exception e) {
			System.out.println("get방식 오류");
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> getMember(Integer id) {
		try {
			//쿼리문 작성
			String query = "SELECT * FROM MEMBER WHERE id = "+ id;
			//쿼리문 준비
			stmt = con.createStatement();
			//쿼리문 실행
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int id1 = rs.getInt("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				Date date = rs.getDate("regidate");
				mv = new MemberVO(id1, pass, name, date);
			}
			map.put("query", query);
			map.put("method", "GET");
			map.put("data", mv);
			
		}
		catch(Exception e) {
			System.out.println("get방식2 오류");
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> addMember(MemberVO member) {
		try {
			//쿼리문 작성
			String query = "INSERT INTO MEMBER (ID, PASS, NAME, REGIDATE) VALUES " + "("+ member.getId() + ", '" + member.getPass() + "', '" + member.getName()  +"', '"+ sqlDate +"')";
			//쿼리문 준비
			stmt = con.createStatement();
			//쿼리문 실행
			stmt.executeUpdate(query);
			
			map.put("query", query);
			map.put("method", "POST");
			
		}
		catch(Exception e) {
			System.out.println("post 방식 오류");
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> updateMember(MemberVO member) {
		try {
			//쿼리문 작성
			String query = "UPDATE MEMBER SET pass = '" + member.getPass() + "'," + "name = '" + member.getName() + "' WHERE id = " + member.getId();
			//쿼리문 준비
			stmt = con.createStatement();
			//쿼리문 세팅
			if(member.getPass() == "") {
				query = "UPDATE MEMBER SET pass = '" + mv.getPass() + "'," + "name = '" + member.getName() + "' WHERE id = " + member.getId(); //이거 안되는거 수정
			}
			
			if(member.getName() == "") {
				query = "UPDATE MEMBER SET pass = '" + member.getPass() + "' ," + "name = '" + mv.getName() + "' WHERE id = " + member.getId();
			}	
			//쿼리문 실행
			stmt.executeUpdate(query);
			map.put("query", query);
			map.put("method", "PUT");
			
		}
		catch(Exception e) {
			System.out.println("업데이트 오류");
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteMember(Integer id) {
		try {
			//쿼리문 작성
			String query = "DELETE FROM MEMBER WHERE id =" + id;
			//쿼리문 준비
			stmt = con.createStatement();
			//쿼리문 실행
			stmt.executeUpdate(query);
			map.put("query", query);
			map.put("method", "DELETE");
		}
		catch(Exception e) {
			System.out.println("삭제 중 오류 발생");
			e.printStackTrace();
		}
		return map;
	}

}
