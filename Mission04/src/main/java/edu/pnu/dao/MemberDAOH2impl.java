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

	Map<String, Object> map;

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
		map = new HashMap<>();
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
			map.put("id", mv.getId() );
			map.put("pass", mv.getPass());
			map.put("name", mv.getName());
			map.put("query", query);
			map.put("data", list );
			map.put("method", "GET");
			System.out.println("모든 데이터 출력 성공");
		
		} catch (Exception e) {
			System.out.println("get방식 오류");
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> getMember(Integer id) {
		map = new HashMap<>();
		String query;
		try {
			//쿼리문 작성
			query = "SELECT * FROM MEMBER WHERE id = "+ id;
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
			map.put("id", mv.getId() );
			map.put("pass", mv.getPass());
			map.put("name", mv.getName());
			map.put("query", query);
			map.put("data", mv);
			map.put("method", "GET");
			System.out.println(id+" 데이터 출력 성공");
		}
		catch(Exception e) {
			System.out.println("get방식2 오류");
			e.printStackTrace();
			map.put("query", e.getMessage());
			map.put("method", "GET");
			map.put("data", mv);
			map.put("id", mv.getId() );
			map.put("pass", mv.getPass());
			map.put("name", mv.getName());
		}
		return map;
	}

	@Override
	public Map<String, Object> addMember(MemberVO member) {
		map = new HashMap<>();
		try {
			//쿼리문 작성
			String query = "INSERT INTO MEMBER (ID, PASS, NAME, REGIDATE) VALUES " + "("+ member.getId() + ", '" + member.getPass() + "', '" + member.getName()  +"', '"+ sqlDate +"')";
			//쿼리문 준비
			stmt = con.createStatement();
			//쿼리문 실행
			stmt.executeUpdate(query);
			map.put("id", member.getId() );
			map.put("pass", member.getPass());
			map.put("name", member.getName());
			map.put("data", member);
			map.put("query", query);
			map.put("method", "POST");
			System.out.println("데이터 추가 성공");
		}
		catch(Exception e) {
			System.out.println("post 방식 오류");
			map.put("data", member);
			map.put("id", member.getId() );
			map.put("pass", member.getPass());
			map.put("name", member.getName());
			map.put("query", e.getMessage());
			map.put("method", "POST");
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> updateMember(MemberVO member) {
		map = new HashMap<>();
	
		try {
			//쿼리문 작성
			String addStr = "";
			//쿼리문 세팅
			if(member.getPass() != null) {
				addStr = ("pass='" + member.getPass() + "'"); 
			}
			if(member.getName() != null) {
				if (!addStr.isEmpty()) addStr += ",";
				addStr += ("name='" + member.getName() + "'");
			}
			//둘다 null이면 db에 에러 메시지 삽입
//			if(member.getPass() == "" && member.getName() == "") { //pass, name 둘 다 null인 경우에는 에러. 근데 ""인 경우에는?
//				
//			}
//			
			String query = "UPDATE MEMBER SET " + addStr + "where id=" + member.getId();
			
			//쿼리문 실행
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			map.put("id", member.getId() );
			map.put("pass", member.getPass());
			map.put("name", member.getName());
			map.put("data", member);
			map.put("query", query);
			map.put("method", "PUT");
			
			System.out.println("업데이트 성공");
		}
		catch(Exception e) { 
			System.out.println("업데이트 오류");
			map.put("id", member.getId() );
			map.put("pass", member.getPass());
			map.put("name", member.getName());
			map.put("data", member);
			map.put("query", e.getMessage());
			map.put("method", "PUT");
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteMember(Integer id) {
		map = new HashMap<>();
		try {
			//쿼리문 작성
			String query = "DELETE FROM MEMBER WHERE id =" + id;
			//쿼리문 준비
			stmt = con.createStatement();
			//쿼리문 실행
			stmt.executeUpdate(query);
			map.put("query", query);
			map.put("method", "DELETE");
			map.put("data", id);
			map.put("id", id);
			map.put("pass", "");
			map.put("name", "");
			
			System.out.println("삭제 성공");
		}
		catch(Exception e) {
			System.out.println("삭제 중 오류 발생");
			map.put("query", e.getMessage());
			map.put("method", "DELETE");
			map.put("data", id);
			map.put("id", "");
			map.put("pass", "" );
			map.put("name", "");
			
			e.printStackTrace();
		}
		return map;
	}

}
