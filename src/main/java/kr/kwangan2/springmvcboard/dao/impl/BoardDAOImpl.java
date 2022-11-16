package kr.kwangan2.springmvcboard.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kwangan2.springmvcboard.domain.BoardAttachVO;
import kr.kwangan2.springmvcboard.domain.BoardVO;
import kr.kwangan2.springmvcboard.domain.Criteria;
import kr.kwangan2.springmvcboard.mapper.BoardAttachMapper;
import kr.kwangan2.springmvcboard.mapper.BoardMapper;
import kr.kwangan2.springmvcboard.mapper.ReplyMapper;
import lombok.Setter;

@Service
public class BoardDAOImpl extends AbstractBoardDAO {
	
	@Setter(onMethod_= @Autowired)
	private BoardMapper boardMapper;
	
	@Setter(onMethod_= @Autowired)
	private ReplyMapper replyMapper; 
	
	@Setter(onMethod_= @Autowired)
	private BoardAttachMapper attachMapper;
	
	@Override
	public int boardVOTotal(Criteria criteria) {
		return boardMapper.boardVOTotal(criteria);
	}
	
	@Override
	public List<BoardVO> boardVOList(Criteria criteria) {
		return boardMapper.boardVOList(criteria);
	}

	@Transactional
	@Override
	public int insertBoardVO(BoardVO board) {
		boardMapper.insertBoardVO(board);
		
		if(board.getAttachList() == null || board.getAttachList().size() <=0) {
			return 0;
		}
		board.getAttachList().forEach(attach -> {
			attach.setBno((long)boardMapper.selectLastBno());
			attachMapper.attachInsert(attach);
		});
		return 0;
	}

	@Override
	public int insertBoardVOSelectKey(BoardVO board) {
		boardMapper.insertBoardVOSelectKey(board);
		return 0;
	}

	@Override
	public BoardVO selectBoardVO(Long bno) {
		return boardMapper.selectBoardVO(bno);
	}

	@Override
	public int deleteBoardVO(Long bno) {
		attachMapper.deleteAll(bno);
		return boardMapper.deleteBoardVO(bno);
	}

	@Override
	public int updateBoardVO(BoardVO boardVO) {
		return boardMapper.updateBoardVO(boardVO);
	}

	@Override
	public int updateReplycnt(long bno, long amount) {
		return boardMapper.updateReplycnt(bno, amount);
	}

	@Override
	public List<BoardAttachVO> selectAttachList(long bno) {
		return attachMapper.attachFindBno(bno);
	}

	@Override
	public int selectLastBno() {
		return boardMapper.selectLastBno();
	}

	
	
}
