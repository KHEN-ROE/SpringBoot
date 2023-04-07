package edu.pnu.domain;

import java.util.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "member")
@Entity
public class Board { // 다대일 관계에서 Board가 '다'이다. 맴버테이블의 필드를 여기다 가져온다고 생각
	@Id @GeneratedValue
	private Long seq;
	private String title;
//	private String writer
	private String content;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;
	private Long cnt;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID", nullable = false) // nullable이 false 면 null인 데이터 조회 불가능
	private Member member;
	
//	public void setMember(Member member) {
//		this.member = member;
//		member.getBoardList().add(this);
//	}
//	
	
}
