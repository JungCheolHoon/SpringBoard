package kr.kwangan2.springmvcboard.service;

import java.util.List;

import kr.kwangan2.springmvcboard.dao.BoardDAO;
import kr.kwangan2.springmvcboard.domain.BoardVO;
import kr.kwangan2.springmvcboard.domain.Criteria;

public interface BoardService{
//	public List<BoardVO> boardVOList();
	public List<BoardVO> boardVOList(Criteria criteria);

	public int insertBoardVO(BoardVO board);

	public int insertBoardVOSelectKey(BoardVO board);

	public BoardVO selectBoardVO(Long bno);

	public int deleteBoardVO(Long bno);

	public int updateBoardVO(BoardVO boardVO);
	
	public int boardVOTotal(Criteria criteria);
}
