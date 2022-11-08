package kr.kwangan2.springmvcboard.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.kwangan2.springmvcboard.domain.Criteria;
import kr.kwangan2.springmvcboard.domain.ReplyVO;
import kr.kwangan2.springmvcboard.mapper.ReplyMapper;
import kr.kwangan2.springmvcboard.service.ReplyService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@Log4j
public class ReplyMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
//	@Test
//	public void testGetList() {
//		mapper.listReplyVO().forEach(reply -> log.info(reply));
//	}
//	
//	@Test
//	public void testGetSelect() {
//		log.info(mapper.selectReplyVO(2L));
//	}
	
//	@Test
//	public void testInsert() {
//		ReplyVO replyVO = new ReplyVO();
//		replyVO.setBno(5);
//		replyVO.setReply("reply");
//		replyVO.setReplyer("replyer");
//		int result = mapper.insertReplyVO(replyVO);
//		assertTrue(result>0);
//	}
	
//	@Test
//	public void testDelete() {
//		int reulst = mapper.deleteReplyVO(7L);
//		assertTrue(result>0);
//	}
	
//	@Test
//	public void testUpdate() {
//		ReplyVO replyVO = new ReplyVO();
//		replyVO.setRno(2);
//		replyVO.setReply("replt");
//		replyVO.setReplyer("replyer");
//		int result = mapper.updateReplyVO(replyVO);
//		assertTrue(result>0);
//		log.info("@update@"+result);
//	}
	
//	@Test
//	public void testCount() {
//		int result = mapper.countReplyVO(40L);
//		log.info("@count@"+result);
//	}

	@Test
	public void testList2() {
		Criteria criteria = new Criteria(1);
		List<ReplyVO> replies = mapper.listReplyVO(criteria, 40L);
		log.info(replies);
	}
	
}
