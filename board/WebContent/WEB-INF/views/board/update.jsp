<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 수정</title>
</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<section class="center">
	<div class="box box-primary">
		<div class="box-header">
			<h3 class="box-title">수정해 주세요</h3>
		</div>

		<form role="form" method="post" id="updateForm"
			action="../updateboard">
			<!-- 글번호를 넘겨주기 위해서 숨김 객체로 생성 -->
			<input type="hidden" name="bno" value="${vo.bno}" />

			<div class="box-body">
				<div class="form-group">
					<label>제목</label> <input type="text" name="title"
						class="
							form-control" value="${vo.title}" />
				</div>
				<div class="form-group">
					<label>내용</label>
					<textarea name="content" class="form-control" placeholder="내용 입력"
						rows="5">${vo.content}</textarea>
				</div>
				<div class="form-group">
					<label>작성자</label> <input type="text" name="id"
						class="
							form-control" value="${vo.id}"
						readonly="readonly" />
				</div>
				<div class="form-group">
					<label>HP</label> <input type="text" name="phone"
						class="
							form-control" value="${vo.phone}" />
				</div>
			</div>
		</form>

		<div class="box-footer">
			<button class="btn btn-success">수정완료</button>
			<button class="btn btn-warning">메인으로</button>
			<button class="btn btn-primary">목록보기</button>
		</div>
		
	</div>
	</section>

	<script>
		$(function() {
			//메인 버튼을 눌렀을 때 처리
			$(".btn-warning").click(function() {
				location.href = "../";
			});
			//목록 버튼을 눌렀을 때 처리
			$(".btn-primary")
					.click(
							function() {
								location.href = "../board/list?page=${criteria.page}&perPageNum=${criteria.perPageNum}&searchType=${criteria.searchType}&keyword=${criteria.keyword}";
							});
			//수정 완료 버튼을 눌렀을 때 처리
			$(".btn-success").click(function() {
				$("#updateForm").submit();
			});
		})
	</script> </section>

	<%@ include file="../include/footer.jsp"%>



</body>
</html>