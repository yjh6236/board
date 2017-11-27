<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>목록보기</title>
</head>
<body>
	<%@include file="../include/header.jsp"%><br />
	<br />
	<div class="box">
		<div class="box-header with border">
			<h3 class="box-title">실종동물을 찾아주세요</h3><br/><br/>
			<h3 class="box-title">${msg}</h3>
		</div>

		<div class="box-header with-border">
			<span>목록개수</span> <select id="count">
				<option value="5"
					<c:out value=
						"${pageMaker.criteria.perPageNum==5?'selected':'' }"/>>5개씩
					보기</option>
				<option value="10"
					<c:out value=
						"${pageMaker.criteria.perPageNum==10?'selected':'' }"/>>10개씩
					보기</option>
				<option value="15"
					<c:out value=
						"${pageMaker.criteria.perPageNum==15?'selected':'' }"/>>15개씩
					보기</option>
				<option value="20"
					<c:out value=
						"${pageMaker.criteria.perPageNum==20?'selected':'' }"/>>20개씩
					보기</option>
			</select>
		</div>

		<script>
			//자바스크립트에서 페이지의 내용을 전부 메모리에 로드 한 후
			//하나의 페이지에서 두 번 이상 작성하면 나중에 것만 동작
			window.onload = function(){
				
			}
			//jquery에서 문서 구조 파악이 끝나고 나면
			$(function(){
				//선택상자에 선택이 변경이 되면
				var cnt = document.getElementById("count");
				var searchType = document.getElementById("searchType");
				var keyword = document.getElementById("keyword");
				
				cnt.onchange = function(){
					//board/list 요청을 보내고 이 때 page=1(1페이지)
					//페이지 당 데이터 출력개수는 선택한 항목의 값으로 설정
					location.href= "../board/list?page=1&perPageNum="+ cnt.value + "&searchType" + searchType.value+ "&keyword=" + keyword.value;
				}
			})
			</script>

		<div class="box-body">
			<table class="table table-bordered">
				<tr>
					<th width="70">글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th width="70">조회수</th>
				</tr>
				<c:forEach var="vo" items="${list }">
					<tr>
						<td align="center">${vo.bno}&nbsp;</td>
						<td>&nbsp; <a
							href="detail?bno=${vo.bno}
								&page=${pageMaker.criteria.page}
								&perPageNum=${pageMaker.criteria.perPageNum}
								&searchType=${criteria.searchType}
								&keyword=${criteria.keyword}">
								${vo.title}&nbsp;&nbsp;&nbsp;[${vo.replyCnt}]</a>
						</td>
						<td>&nbsp;${vo.id}</td>
						<td>&nbsp;${vo.regdate}</td>
						<td align="center"><span class="badge bg-blue">
								${vo.readcnt}</span>&nbsp;</td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div class="box-footer">
			<div class="text-center">
				<ul class="pagination">
					<c:if test="${pageMaker.prev}">
						<li><a
							href="../board/list?page=${pageMaker.startPage-1}
							&perPageNum=${pageMaker.criteria.perPageNum}
							&searchType=${criteria.searchType}
							&keyword=${criteria.keyword}">
								이전 </a></li>
					</c:if>

					<c:forEach var="idx" begin="${pageMaker.startPage}"
						end="${pageMaker.endPage}">
						<li
							<c:out value=
							"${pageMaker.criteria.page==idx?'class=active':''}"/>>
							<a
							href="../board/list?page=${idx}
							&perPageNum=${pageMaker.criteria.perPageNum}
							&searchType=${criteria.searchType}
							&keyword=${criteria.keyword}">
								${idx} </a>
						</li>
					</c:forEach>

					<c:if test="${pageMaker.next}">
						<li><a
							href="../board/list?page=${pageMaker.endPage+1}
							&perPageNum=${pageMaker.criteria.perPageNum}
							&searchType=${criteria.searchType}
							&keyword=${criteria.keyword}">
								다음 </a></li>
					</c:if>

				</ul>


			</div>

			<div class="box-body">
				<select name="searchType" id="searchType">
					<option value="n"
						<c:out value="${criteria.searchType==null?'selected':''}"/>>
						---</option>
					<option value="t"
						<c:out value="${criteria.searchType eq 't'?'selected':''}"/>>
						제목</option>
					<option value="c"
						<c:out value="${criteria.searchType eq 'c'?'selected':''}"/>>
						내용</option>
					<option value="w"
						<c:out value="${criteria.searchType eq 'w'?'selected':''}"/>>
						작성자</option>
					<option value="tc"
						<c:out value="${criteria.searchType eq 'tc'?'selected':''}"/>>
						제목 또는 내용</option>
				</select> <input type="text" name="keyword" id="keyword" size='40'
					value="${criteria.keyword}" />
				<button class="btn-primary" id="searchBtn">검색</button>

			</div>

			<button class="btn-warning" id="newBtn">글쓰기</button>
			<button class="btn-warning" id="mainBtn">메인으로</button>

		</div>

		<script>
				$(function(){
					var newBtn = document.getElementById("newBtn");
					newBtn.onclick = function(){
						location.href="register";
					};
					
					var mainBtn = document.getElementById("mainBtn");
					mainBtn.onclick = function(){
						location.href="../";
					};
					
					var searchBtn = document.getElementById("searchBtn");
					var searchType = document.getElementById("searchType");
					var keyword = document.getElementById("keyword");
					searchBtn.onclick = function(){
						location.href="../board/list?page=1&perPageNum=${pageMaker.criteria.perPageNum}&searchType=" 
								+ searchType.value + 
								"&keyword=" + keyword.value;
					};
					
				});	
			
			</script>

	</div>

	<%@include file="../include/footer.jsp"%>

</body>
</html>










