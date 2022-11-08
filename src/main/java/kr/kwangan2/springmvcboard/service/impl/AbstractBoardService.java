package kr.kwangan2.springmvcboard.service.impl;

import java.util.List;

import kr.kwangan2.springmvcboard.domain.BoardVO;
import kr.kwangan2.springmvcboard.domain.Criteria;
import kr.kwangan2.springmvcboard.mapper.BoardMapper;
import kr.kwangan2.springmvcboard.service.BoardService;

public abstract class AbstractBoardService implements BoardService {
	
//	@Override
//	public List<BoardVO> boardVOList() {
//		return null;
//	}
	
	@Override
	public List<BoardVO> boardVOList(Criteria criteria) {
		return null;
	}

	@Override
	public int deleteBoardVO(Long bno) {
		return 0;
	}

	@Override
	public int insertBoardVO(BoardVO board) {
		return 0;
	}

	@Override
	public int insertBoardVOSelectKey(BoardVO board) {
		return 0;
	}

	@Override
	public BoardVO selectBoardVO(Long bno) {
		return null;
	}

	@Override
	public int updateBoardVO(BoardVO boardVO) {
		return 0;
	}
	
	@Override
	public int boardVOTotal(Criteria criteria) {
		return 0;
	}
}
