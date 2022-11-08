package kr.kwangan2.springmvcboard.dao;

import java.util.List;

import kr.kwangan2.springmvcboard.domain.BoardVO;
import kr.kwangan2.springmvcboard.domain.Criteria;
import kr.kwangan2.springmvcboard.service.BoardService;

public interface BoardDAO extends BoardService{
//	public List<BoardVO> boardVOList();
	
	public List<BoardVO> boardVOList(Criteria criteria);

	public int insertBoardVO(BoardVO board);

	public int insertBoardVOSelectKey(BoardVO board);

	public BoardVO selectBoardVO(Long bno);

	public int deleteBoardVO(Long bno);

	public int updateBoardVO(BoardVO boardVO);
	
	public int boardVOTotal(Criteria criteria);
}