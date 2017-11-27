package com.cspi.project.domain;

//현재 페이지 번호와 페이지 당 출력개수를 저장할 클래스
public class Criteria {
	private int page;
	private int perPageNum;
	
	//생성자 - 인스턴스 변수에 기본값을 할당	
	public Criteria() {	
		super();
		page = 1;
		perPageNum = 10;
	}
	
	//현재 페이지 번호와 페이지 당 데이터 개수를 가지고
	//데이터의 시작 번호 찾아오는 메서드
	//이 메서드의 호출은 #{pagestart}로 가기
	public int getPageStart(){
		//페이지 당 출력 개수가 10인 경우
		//1페이지 이면 1, 2페이지이면 11, 5페이지면 41
		int start = (page -1) * perPageNum + 1;
		return start;
	}
	
	//접근자 메서드
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	
	//디버깅을 위한 메서드
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	
	
	
}
