package kr.kwangan2.springmvcboard.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kwangan2.springmvcboard.domain.Criteria;
import kr.kwangan2.springmvcboard.domain.ReplyPageDTO;
import kr.kwangan2.springmvcboard.domain.ReplyVO;
import kr.kwangan2.springmvcboard.mapper.ReplyMapper;
import kr.kwangan2.springmvcboard.service.ReplyService;
import lombok.Setter;

@Service
public class ReplyDAOImpl implements ReplyService {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Override
	public int countReplyVO(Long bno) {
		return mapper.countReplyVO(bno);
	}

	@Override
	public ReplyPageDTO listReplyVOPage(Criteria criteria, Long bno) {
		return new ReplyPageDTO(mapper.countReplyVO(bno), mapper.listReplyVO(criteria, bno));
	}
	
	@Override
	public List<ReplyVO> listReplyVO(Criteria criteria, Long bno) {
		return mapper.listReplyVO(criteria,bno);
	}

	@Override
	public int insertReplyVO(ReplyVO replyVO) {
		return mapper.insertReplyVO(replyVO);
	}

	@Override
	public ReplyVO selectReplyVO(Long rno) {
		return mapper.selectReplyVO(rno);
	}

	@Override
	public int updateReplyVO(ReplyVO replyVO) {
		return mapper.updateReplyVO(replyVO);
	}

	@Override
	public int deleteReplyVO(Long rno) {
		return mapper.deleteReplyVO(rno);
	}
}
