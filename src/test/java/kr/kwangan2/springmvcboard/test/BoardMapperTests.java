package kr.kwangan2.springmvcboard.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.kwangan2.springmvcboard.mapper.BoardMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
//	@Test
//	public void testGetList() {
//		mapper.boardVOList().forEach(board -> log.info(board));
//	}
	
//	@Test
//	public void testBoardVOinsert() {
//		BoardVO boardVO = new BoardVO();
//		boardVO.setContent("내용이다");
//		boardVO.setTitle("제목이다");
//		boardVO.setWriter("작성자다");
//		mapper.insertBoardVO(boardVO);
//	}

//	@Test
//	public void testBoardVOkeyinsert() {
//		BoardVO boardVO = new BoardVO();
//		boardVO.setContent("내용이다key2");
//		boardVO.setTitle("제목이다key2");
//		boardVO.setWriter("작성자다key2");
//		mapper.insertBoardVOSelectKey(boardVO);
//	}
	
//	@Test
//	public void testSelectBoardVO() {
//		BoardVO boardVO = mapper.selectBoardVO(2L);
//		log.info(boardVO);
//	}
	
//	@Test
//	public void testDeleteBoardVO() {
//		int result = mapper.deleteBoardVO(6L);
//		log.info(result);
//	}
	
//	@Test
//	public void testUpdateBoardVO() {
//		BoardVO boardVO = new BoardVO();
//		boardVO.setBno(5L);
//		boardVO.setTitle("수정된 제목");
//		boardVO.setContent("수정된 내용");
//		boardVO.setWriter("수정된 작성자");
//		
//		int count = mapper.updateBoardVO(boardVO);
//		log.info(count);
//	}
	
//	@Test
//	public void testPaging() {
//		Criteria criteria = new Criteria();
//		criteria.setPageNum(2);
//		criteria.setAmount(10);
//		List<BoardVO> list = mapper.boardVOList(criteria);
//		list.forEach(boardVO -> log.info(boardVO));
//	}
	
//	@Test
//	public void TestListPaging() throws Exception{
//		log.info(mockMvc.perform(
//				MockMvcRequestBuilders.get("/board/list")
//				.param("pageNum", "1")
//				.param("amount", "10"))
//				.andReturn().getModelAndView().getModelMap()
//				);
//		
//	}
	
	@Test
	public void test1() {
		List list = new ArrayList();
		list.add(1);
		list.add(1);
		list.add(1);
		log.info("size====>" + list.size());
	}
	
}
