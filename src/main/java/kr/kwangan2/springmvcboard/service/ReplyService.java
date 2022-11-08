package kr.kwangan2.springmvcboard.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kwangan2.springmvcboard.dao.BoardDAO;
import kr.kwangan2.springmvcboard.domain.BoardVO;
import kr.kwangan2.springmvcboard.domain.Criteria;
import kr.kwangan2.springmvcboard.domain.ReplyPageDTO;
import kr.kwangan2.springmvcboard.domain.ReplyVO;

public interface ReplyService{

	public int countReplyVO(Long bno);
	public ReplyPageDTO listReplyVOPage(Criteria criteria, Long bno);
	public List<ReplyVO> listReplyVO(Criteria criteria, Long bno);
	public int insertReplyVO(ReplyVO replyVO);
	public ReplyVO selectReplyVO(Long rno);
	public int updateReplyVO(ReplyVO replyVO);
	public int deleteReplyVO(Long rno);
}
