package kr.kwangan2.springmvcboard.domain;

import lombok.Getter;

@Getter
public class PageDTO {
	private int total; // 전체 게시물 수
	private int startPage; // 블럭 내 시작 페이지 번호
	private int endPage; // 블럭 내 끝 페이지 번호
	private boolean prev; // 이전 링크가 있는지 여부
	private boolean next; // 다음 링크가 있는지 여부

	private Criteria criteria;

	public PageDTO(Criteria criteria, int total) {
		this.criteria = criteria;
		this.total = total;
		
		// 끝 페이지 번호
		this.endPage = (int) (Math.ceil(this.criteria.getPageNum() / 10.0)) * 10;

		// 시작 페이지 번호
		this.startPage = this.endPage - 9;
		int realEnd = (int) (Math.ceil((double)this.total / this.criteria.getAmount()));
		// 블럭 내에 10개 페이지가 안될 때 끝페이지 번호 설정
		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
