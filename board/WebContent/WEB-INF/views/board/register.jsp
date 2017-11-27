<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<section class="content">
	<div class="box-header">
		<h3 class="box-title">실종동물 등록하기</h3>
	</div>

	<form role="form" method="post" >
		<div class="box-body">
			<div class="form-group">
				<label>제목</label> <input type="text" name='title'
					class="form-control" placeholder="제목을 입력하세요">
			</div>
			<div class="form-group">
				<label>내용(위치, 종류를 알려주세요)</label>
				<textarea class="form-control" name="content" rows="3"
					placeholder="내용을 입력하세요"></textarea>
			</div>
			
			
			<div class="form-group">
				<label>작성자</label> <input type="text" name="id" 
				class="form-control" placeholder="작성자를 입력하세요"
				value="${LOGIN.id}" readonly="readonly">
			</div>
			
			<div class="form-group">
				<label>HP</label> <input type="text" name="phone" 
				class="form-control" placeholder="핸드폰번호를 입력하세요">
			</div>
			
			
		</div>

		<div class="box-footer">
			<button type="submit" class="btn btn-primary">작성완료</button>
		</div>
	</form>
</section>
<%@include file="../include/footer.jsp"%>
