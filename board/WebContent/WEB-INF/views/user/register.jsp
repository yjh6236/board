<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>join</title>
</head>
<body>
	<%@ include file="../include/header.jsp" %>
		<div class="box-header with-border">
			<form method="post" id="regForm">
				<p align="center">
					<table  width="60%" align="center">
						<tr>
							<td colspan="3" align="center">
								<h2>회원가입</h2>
							</td>
						</tr>
						
						<tr>
							<td>아이디</td>
							<td>&nbsp;<input type="text" name="id" id="id"
							placeholder="아이디를 입력하세요"
							required="required"
							class="form-control" />
							<div id="idmsg"></div>
							</td>
						</tr>
						
						<tr>
							<td>비밀번호</td>
							<td>&nbsp;<input type="password" name="pw" id="pw"
							placeholder="비밀번호를 입력하세요"
							required="required"
							class="form-control"
							 />
							</td>
						</tr>
						
						<tr>
							<td>이름</td>
							<td>&nbsp;<input type="text" name="name" id="name"
							placeholder="이름을 입력하세요"
							required="required"
							pattern="([a-z,A-Z,가-힣]){2,}"
							class="form-control" />
							</td>
						</tr>
						
						<tr>
							<td>H.P</td>
							<td>&nbsp;<input type="text" name="phone" id="phone"
							placeholder="HP 입력하세요"
							required="required"
							
							class="form-control" />
							</td>
						</tr>
						<!--  regNumber = /^[0-9]*$/;
							pattern="([^0-9]*$){11,}"-->
							
						<tr>
							<td colspan="3" align="center">
							<input type="submit" class="btn btn-warning" 
							value="회원가입" />&nbsp;
							<input type="button" class="btn btn-primary" 
							value="메인으로" />
							</td>		
						</tr>
						
					</table>
				</p>
			
			</form>
		</div>
		
			<script>	
			var chk;
			$(function(){
				//id라는 id를 가진 input에서 포커스가 떠나면
				$('#id').on('blur', function(){
					chk = 1
					//id 값을 서버로 전송해서 있는지 없는지 확인
					//ajax로 확인
					
					var v = $('#id').val();
					
					$.ajax({
						url:"/project/user/idcheck",
						data:{"id":v},
						dataType:"json",
						success:function(data){
							if(data.result =='true'){
								$('#idmsg').html("사용 가능한 아이디 입니다.");
								chk=0
							}
							else{
								$('#idmsg').html("사용 불가능한 아이디 입니다.");
							}
						
						}
					});
					
				});
				
				$('#regForm').on('submit', function(){
					if(chk==0){
						return true;
					}
					else{
						$("#idmsg").html('아이디 중복검사를 하세요!');
						return false;
					}
				});
				
				$(".btn-primary").on('click', function(){
					location.href="/"
				});
				
			});
		</script>
	
	<%@ include file="../include/footer.jsp" %>
</body>
</html>