package kr.kwangan2.springmvcboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.kwangan2.springmvcboard.domain.BoardVO;
import kr.kwangan2.springmvcboard.domain.Criteria;
import kr.kwangan2.springmvcboard.domain.PageDTO;
import kr.kwangan2.springmvcboard.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

	private BoardService boardService;
	
	@GetMapping("/list")
	public String boardVOList(Criteria criteria, Model model) {
		log.info("list");
		if(boardService.boardVOList(criteria).size()==0) {
			criteria.setPageNum(criteria.getPageNum()-1);
			model.addAttribute("list", boardService.boardVOList(criteria));
		} 
		else {
			model.addAttribute("list", boardService.boardVOList(criteria));
		}
		model.addAttribute("pageCalc",new PageDTO(criteria, boardService.boardVOTotal(criteria)));
		return "/boardList";
	}

	@GetMapping("/boardInsert")
	public String boardInsert() {
		return "/boardInsert";
	}

	@PostMapping("/boardInsertProc")
	public String boardInsertProc(BoardVO boardVO, RedirectAttributes redirectAttributes) {
		if (boardService.insertBoardVO(boardVO) > 0) {
			redirectAttributes.addFlashAttribute("result", "success");
		}
		return "redirect:/";
	}

	@PostMapping("/insert")
	public String insertBoardVO(BoardVO boardVO, RedirectAttributes rttr) {
		log.info(boardVO);
		boardService.insertBoardVO(boardVO);
		rttr.addFlashAttribute("result", boardVO.getBno());

		return "redirect:/board/list";
	}

	@GetMapping("/select")
	public String selectBoardVO(@RequestParam("bno") Long bno,Model model) {
		model.addAttribute("boardVO", boardService.selectBoardVO(bno));
		return "/boardUpdate";
	}

	@PostMapping("/updateProc")
	public String updateBoardVO(BoardVO boardVO, RedirectAttributes rttr,Criteria criteria) {
		System.out.println("@@@pagenum"+criteria.getPageNum());
		if (boardService.updateBoardVO(boardVO) > 0) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list?pageNum="+criteria.getPageNum();
	}

	@GetMapping("/delete")
	public String deleteBoardVO(@RequestParam("bno") Long bno, int pageNum,RedirectAttributes rttr) {
		if (boardService.deleteBoardVO(bno) > 0) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list?pageNum="+pageNum;
	}
}
