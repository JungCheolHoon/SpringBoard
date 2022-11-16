package kr.kwangan2.springmvcboard.service;

import java.util.List;

import kr.kwangan2.springmvcboard.domain.BoardAttachVO;
import kr.kwangan2.springmvcboard.domain.BoardVO;
import kr.kwangan2.springmvcboard.domain.Criteria;

public interface BoardService{

	public List<BoardVO> boardVOList(Criteria criteria);

	public int insertBoardVO(BoardVO board);

	public int insertBoardVOSelectKey(BoardVO board);

	public BoardVO selectBoardVO(Long bno);

	public int deleteBoardVO(Long bno);

	public int updateBoardVO(BoardVO boardVO);
	
	public int boardVOTotal(Criteria criteria);
	
	public int updateReplycnt(long bno, long amount);
	
	public List<BoardAttachVO> selectAttachList(long bno);
	
	public int selectLastBno();
	
}
