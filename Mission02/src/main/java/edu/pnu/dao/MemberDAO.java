package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDAO {

	MemberVO mv;
	
	public static Date today = new Date();
	public static long timeInMilliSeconds = today.getTime();
	public static java.sql.Date sqlDate = new java.sql.Date(timeInMilliSeconds);
	
	public static String driver = "org.h2.Driver";
	public static String url = "jdbc:h2:tcp://localhost/~/springboot";
	public static String username = "sa";
	public static String password = "";
	
	public static Connection con;
	public static PreparedStatement psmt;
	public static ResultSet rs;
	
	
	//생성자에서 db 연결
	public MemberDAO() {
		
		try {
			//드라이버 로드
			Class.forName(driver);
		}
		catch(Exception e) {
			System.out.println("드라이버 로드 오류");
			e.printStackTrace();
		}
		
		
		try {
			//db 연결
			con = DriverManager.getConnection(url, username, password);
			
		}
		catch(Exception e) {
			System.out.println("db연결 오류");
			e.printStackTrace();
		}
		
	}
	
	//get방식
	public List<MemberVO> getMember() {
		
		List<MemberVO> list = new ArrayList<>();
		
		
		try {
			String query = "SELECT * FROM MEMBER";
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				Date date = rs.getDate("regidate");
				mv = new MemberVO(id, pass, name, date);
				list.add(mv);
			}
			
		}
		catch(Exception e) {
			System.out.println("get방식 오류");
			e.printStackTrace();
		}
		
		return list;
	}
	
	//get 방식 2 -- id로 read
	public MemberVO getMember2(int id) {
		
		try {

			//쿼리문 작성
			String query = "SELECT * FROM MEMBER where id = ?";
			//쿼리문 준비
			psmt = con.prepareStatement(query);
			//쿼리문 세팅
			psmt.setInt(1, id);
			
			//쿼리문 실행
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				int id1 = rs.getInt("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				Date date = rs.getDate("regidate");
				mv = new MemberVO(id1, pass, name, date);
			}
		}
		catch(Exception e) {
			System.out.println("get방식2 오류");
			e.printStackTrace();
		}
		
		return mv;
	}
	
	//post 방식 - create
	public MemberVO addMember(MemberVO m) {
		
		try {
			//쿼리문 작성
			String query = "INSERT INTO MEMBER VALUES(?,?,?,?)";
			//쿼리문 준비
			psmt = con.prepareStatement(query);
			psmt.setInt(1, m.getId());
			psmt.setString(2, m.getPass());
			psmt.setString(3, m.getName());
			psmt.setDate(4, sqlDate);
			
			psmt.executeUpdate();
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return getMember2(m.getId());
	}
	
	//put방식 - update 
	public MemberVO updateMember(MemberVO m) {
		
		try {	
			//쿼리문 작성
			String query = "UPDATE MEMBER SET pass = ?, name = ?  WHERE id = ? ";
			//쿼리문 준비(쿼리문 작성했으면 바로 전달한다고 생각)
			psmt = con.prepareStatement(query);
			if(m.getPass() != "") { // pass 나 name 둘 중 하나만 업데이트할 경우 : 데이터 안 보내면 기존의 값 그대로 출력
				psmt.setString(1, m.getPass());
			}
			else psmt.setString(1, mv.getPass());
			
			if(m.getName() != "") {
				psmt.setString(2, m.getName());
			}
			else psmt.setString(2, mv.getName());

			psmt.setInt(3, m.getId());
			psmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("업데이트 중 오류 발생");
			e.printStackTrace();
		}
		return getMember2(m.getId());
	}
	
	//delete 
	public MemberVO deleteMember(int id) {
		
		try {
			//쿼리문 작성
			String query = "DELETE FROM MEMBER WHERE id = ?";
			//쿼리문 준비
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.executeUpdate();
			
		}
		catch(Exception e) {
			System.out.println("삭제 중 오류 발생");
			e.printStackTrace();
		}
		return getMember2(id); 
	}
	
	
	
}
