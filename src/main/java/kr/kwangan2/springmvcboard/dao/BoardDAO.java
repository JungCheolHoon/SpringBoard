package kr.kwangan2.springmvcboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kwangan2.springmvcboard.domain.BoardAttachVO;
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
	
	public int updateReplycnt(long bno, long amount);
	
	public List<BoardAttachVO> selectAttachList(long bno);
	
	public int selectLastBno();
	
}
