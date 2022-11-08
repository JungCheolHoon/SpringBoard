package kr.kwangan2.springmvcboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import kr.kwangan2.springmvcboard.domain.Criteria;
import kr.kwangan2.springmvcboard.domain.ReplyPageDTO;
import kr.kwangan2.springmvcboard.domain.ReplyVO;

public interface ReplyMapper {
	
	public int countReplyVO(Long bno);
	public ReplyPageDTO listReplyVOPage(Criteria criteria, Long bno);
	public List<ReplyVO> listReplyVO(
			@Param("criteria") Criteria criteria,
			@Param("bno") Long bno
			);
	public int insertReplyVO(ReplyVO replyVO);
	public ReplyVO selectReplyVO(Long rno);
	public int updateReplyVO(ReplyVO replyVO);
	public int deleteReplyVO(Long rno);
}
