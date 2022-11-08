package kr.kwangan2.springmvcboard.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kwangan2.springmvcboard.domain.BoardVO;
import kr.kwangan2.springmvcboard.domain.Criteria;
import kr.kwangan2.springmvcboard.mapper.BoardMapper;
import lombok.Setter;

@Service
public class BoardDAOImpl extends AbstractBoardDAO {
	
	@Setter(onMethod_= @Autowired)
	private BoardMapper mapper;
	
	@Override
	public int boardVOTotal(Criteria criteria) {
		return mapper.boardVOTotal(criteria);
	}
	
	@Override
	public List<BoardVO> boardVOList(Criteria criteria) {
		return mapper.boardVOList(criteria);
	}

	@Override
	public int insertBoardVO(BoardVO board) {
		mapper.insertBoardVO(board);
		return 0;
	}

	@Override
	public int insertBoardVOSelectKey(BoardVO board) {
		mapper.insertBoardVOSelectKey(board);
		return 0;
	}

	@Override
	public BoardVO selectBoardVO(Long bno) {
		return mapper.selectBoardVO(bno);
	}

	@Override
	public int deleteBoardVO(Long bno) {
		return mapper.deleteBoardVO(bno);
	}

	@Override
	public int updateBoardVO(BoardVO boardVO) {
		return mapper.updateBoardVO(boardVO);
	}
}
