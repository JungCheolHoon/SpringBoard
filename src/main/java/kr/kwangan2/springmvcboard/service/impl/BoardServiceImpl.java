package kr.kwangan2.springmvcboard.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.kwangan2.springmvcboard.domain.BoardAttachVO;
import kr.kwangan2.springmvcboard.domain.BoardVO;
import kr.kwangan2.springmvcboard.domain.Criteria;

public class BoardServiceImpl extends AbstractBoardService {
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

	@Override
	public int updateReplycnt(long bno, long amount) {
		return 0;
	}
	
	public List<BoardAttachVO> selectAttachList(long bno){
		return null;
	}
	
	public int selectLastBno() {
		return 0;
	}

}
