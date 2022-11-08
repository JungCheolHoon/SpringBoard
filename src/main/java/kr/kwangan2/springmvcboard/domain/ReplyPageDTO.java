package kr.kwangan2.springmvcboard.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
public class ReplyPageDTO {

	private int replyCnt;
	
	@Setter(onMethod_ = @Autowired)
	private List<ReplyVO> list;
	
	public ReplyPageDTO() {};
}
