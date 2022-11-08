package kr.kwangan2.springmvcboard.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.kwangan2.springmvcboard.dao.BoardDAO;
import kr.kwangan2.springmvcboard.domain.BoardVO;
import kr.kwangan2.springmvcboard.mapper.BoardMapper;
import kr.kwangan2.springmvcboard.service.BoardService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
//	@Test
//	public void testExist() {
//		log.info(service);
//		assertNotNull(service);	
//	}
	
//	@Test
//	public void testInsertBoardVO() {
//		BoardVO boardVO = new BoardVO();
//		boardVO.setContent("내 내용이다");
//		boardVO.setTitle("내 제목이다");
//		boardVO.setWriter("내 작성자다");
//		service.insertBoardVO(boardVO);
//	}
	
//	@Test
//	public void testBoardVOList() {
//		BoardVO boardVO = new BoardVO();
//		service.boardVOList().forEach(board -> log.info(boardVO));
//	}
	
//	@Test
//	public void testSelectBoardVO() {
//		BoardVO boardVO = service.selectBoardVO(2L);
//		log.info(boardVO);
//	}
	
//	@Test
//	public void testDeleteBoardVO() {
//		int result = service.deleteBoardVO(7L);
//		log.info(result);
//	}

//	@Test
//	public void testUpdateBoardVO() {
//		BoardVO boardVO = new BoardVO();
//		boardVO.setBno(4L);
//		boardVO.setTitle("수정된 제목");
//		boardVO.setContent("수정된 내용");
//		boardVO.setWriter("수정된 작성자");
//		
//		int count = service.updateBoardVO(boardVO);
//		log.info(count);
//	}
}
