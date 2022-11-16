package kr.kwangan2.springmvcboard.mapper;

import kr.kwangan2.springmvcboard.domain.MemberVO;

public interface MemberMapper {

	public MemberVO read(String userid);
	
}
