package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDAOH2impl implements MemberInterface {

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
	public List<MemberVO> getMembers() {
		List<MemberVO> list = new ArrayList<>();

		try {
			String query = "SELECT * FROM MEMBER";
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				Date date = rs.getDate("regidate");
				mv = new MemberVO(id, pass, name, date);
				list.add(mv);
			}

		} catch (Exception e) {
			System.out.println("get방식 오류");
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public MemberVO getMember(Integer id) {
		try {
			//쿼리문 작성
			String query = "SELECT * FROM MEMBER WHERE id = ?";
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

	@Override
	public MemberVO addMember(MemberVO member) {
		try {
			//쿼리문 작성
			String query = "INSERT INTO MEMBER VALUES(?,?,?,?)";
			//쿼리문 준비
			psmt = con.prepareStatement(query);
			//쿼리문 세팅
			psmt.setInt(1, member.getId());
			psmt.setString(2, member.getPass());
			psmt.setString(3, member.getName());
			psmt.setDate(4, sqlDate);
			//쿼리문 실행
			psmt.executeUpdate();
			
		}
		catch(Exception e) {
			System.out.println("post 방식 오류");
			e.printStackTrace();
		}
		return getMember(member.getId());
	}

	@Override
	public MemberVO updateMember(MemberVO member) {
		try {
			//쿼리문 작성
			String query = "UPDATE MEMBER SET pass = ?, name = ? WHERE id = ?";
			//쿼리문 준비
			psmt = con.prepareStatement(query);
			//쿼리문 세팅
			if(member.getPass() != "") {
				psmt.setString(1, member.getPass() );
			}
			else psmt.setString(1, mv.getPass());
			
			if(member.getName() != "") {
				psmt.setString(2, member.getName());
			}
			else psmt.setString(2, mv.getName());
		
			psmt.setInt(3, member.getId());
			
			//쿼리문 실행
			psmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("업데이트 오류");
			e.printStackTrace();
		}
		return getMember(member.getId());
	}

	@Override
	public MemberVO deleteMember(Integer id) {
		try {
			//쿼리문 작성
			String query = "DELETE FROM MEMBER WHERE id = ?";
			//쿼리문 준비
			psmt = con.prepareStatement(query);
			//쿼리문 세팅
			psmt.setInt(1, id);
			//쿼리문 실행
			psmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("삭제 중 오류 발생");
			e.printStackTrace();
		}
		return getMember(id);
	}

}
