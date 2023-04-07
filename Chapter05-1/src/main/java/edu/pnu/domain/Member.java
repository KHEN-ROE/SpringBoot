package edu.pnu.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "boardList")
@Entity
public class Member {
	@Id
	@Column(name="MEMBER_ID")
	private String id;
	private String password;
	private String name;
	private String role;
	
	//양방향 매핑 설정
	//mappedBy는 member 변수에 의해서 매핑됐음을 의미. 연관관계의 주인이 아님.
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER/*, cascade = CascadeType.ALL*/) // 일단 lazy로 두고 필요할 때만 eager로 사용
	private List <Board> boardList = new ArrayList<Board>(); //member 객체를 생성할 때 여기다 저장이 되네. mappedBy="member" 때문에 그런듯
	
}
