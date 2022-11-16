package kr.kwangan2.springmvcboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.kwangan2.springmvcboard.domain.BoardAttachVO;
import kr.kwangan2.springmvcboard.domain.BoardVO;
import kr.kwangan2.springmvcboard.domain.Criteria;

public interface BoardMapper {

	public List<BoardVO> boardVOList();
	
	public List<BoardVO> boardVOList(Criteria criteria);
	
	public int insertBoardVO(BoardVO board);
	
	public int insertBoardVOSelectKey(BoardVO board);
	
	public BoardVO selectBoardVO(Long bno);
	
	public int deleteBoardVO(Long bno);
	
	public int updateBoardVO(BoardVO boardVO);
	
	public int boardVOTotal(Criteria criteria);
	
	public int updateReplycnt(@Param("bno") long bno, @Param("amount") long amount);
	
	public List<BoardAttachVO> selectAttachList(long bno);
	
	public int selectLastBno();
	
}
