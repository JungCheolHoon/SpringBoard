package kr.kwangan2.springmvcboard.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.kwangan2.springmvcboard.domain.BoardAttachVO;
import kr.kwangan2.springmvcboard.domain.BoardVO;
import kr.kwangan2.springmvcboard.domain.Criteria;
import kr.kwangan2.springmvcboard.domain.PageDTO;
import kr.kwangan2.springmvcboard.mapper.BoardAttachMapper;
import kr.kwangan2.springmvcboard.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

	private BoardAttachMapper attachService;
	private BoardService boardService;

	@GetMapping("/list")
	public String boardVOList(Criteria criteria, Model model) {
		log.info("list");
		if (boardService.boardVOList(criteria).size() == 0) {
			criteria.setPageNum(criteria.getPageNum() - 1);
			model.addAttribute("list", boardService.boardVOList(criteria));
		} else {
			model.addAttribute("list", boardService.boardVOList(criteria));
		}
		model.addAttribute("pageCalc", new PageDTO(criteria, boardService.boardVOTotal(criteria)));
		return "/boardList";
	}

	@GetMapping("/boardInsert")
	@PreAuthorize("isAuthenticated()")
	public String boardInsert() {
		return "/boardInsert";
	}

	@PostMapping("/boardInsertProc")
	@PreAuthorize("isAuthenticated()")
	public String boardInsertProc(BoardVO boardVO, RedirectAttributes redirectAttributes) {

		if (boardService.insertBoardVO(boardVO) > 0) {
			redirectAttributes.addFlashAttribute("result", "success");
		}
		if (boardVO.getAttachList() != null) {
			boardVO.getAttachList().forEach(attach -> log.info(attach));
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
	public String selectBoardVO(@RequestParam("bno") Long bno, Model model) {
		model.addAttribute("boardVO", boardService.selectBoardVO(bno));
		return "/boardUpdate";
	}

	@PreAuthorize("principal.username==#boardVO.writer")
	@PostMapping("/updateProc")
	public String updateBoardVO(BoardVO boardVO, RedirectAttributes rttr, Criteria criteria) {
		System.out.println("@@@pagenum" + criteria.getPageNum());
		long bno = boardVO.getBno();
		if (boardService.updateBoardVO(boardVO) > 0) {
			rttr.addFlashAttribute("result", "success");
		}
		List<BoardAttachVO> attachList = boardService.selectAttachList(bno);
		deleteFiles(attachList);
		return "redirect:/board/list?pageNum=" + criteria.getPageNum();
	}

	@PreAuthorize("principal.username==#writer")
	@GetMapping("/delete")
	public String deleteBoardVO(@RequestParam("bno") Long bno, int pageNum, RedirectAttributes rttr, String writer) {
		List<BoardAttachVO> attachList = boardService.selectAttachList(bno);
		if (boardService.deleteBoardVO(bno) > 0) {
			deleteFiles(attachList);
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/board/list?pageNum=" + pageNum;
	}

	@GetMapping(value = "/selectAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> selectAttachList(Long bno) {
		return new ResponseEntity<List<BoardAttachVO>>(boardService.selectAttachList(bno), HttpStatus.OK);
	}

	private void deleteFiles(List<BoardAttachVO> attachList) {
		if (attachList == null || attachList.size() == 0) {
			return;
		}

		attachList.forEach(attach -> {
			try {
				Path file = Paths.get(
						"c:/upload/" + attach.getUploadPath() + "/" + attach.getUuid() + "_" + attach.getFileName());

				Files.deleteIfExists(file);

				if (Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("c:/upload/" + attach.getUploadPath() + "/thumb_" + attach.getUuid()
							+ "_" + attach.getFileName());
					Files.delete(thumbNail);
				}
			} catch (Exception e) {
				log.error("delete file error" + e.getMessage());
			}
		});
	}

}
