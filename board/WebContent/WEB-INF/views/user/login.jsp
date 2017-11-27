<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>

<style>
.msg {
	color: red
}
;
</style>
</head>

<body>
	<%@ include file="../include/header.jsp"%>
	<div class="login-box">
		<div class="login-logo">
			<h2>실종동물 찾기</h2>
		</div>
		
		<!-- 로그인 실패했을 때 메시지 출력 영역 -->
		<div>
			<h3>${fail}</h3>
		</div>
		
		<!-- 로그인 영역 -->
		<div class="login-box-body">
			<p class="login-box-msg">아이디와 비밀번호를 입력하세요</p>
			
			<form action="loginpost" method="post">
				<label>아이디</label> <input type="text" class="form-control"
					placeholder="아이디를 입력하세요" required="required" name="id" /> <br />
				<label>비밀번호</label> <input type="password" class="form-control"
					placeholder="비밀번호를 입력하세요" required="required" name="pw" /> <br />

				<input type="checkbox" name="useCookie" /> <label>자동로그인</label> <input
					type="submit" value="로그인"
					class="btn btn-primary btn-block btn-flat" />
			</form>
			<br /> <a href="/user/join" class="text-center">회원가입</a>
		</div>
	</div>
	</div>
	<%@ include file="../include/footer.jsp"%>


</body>
</html>