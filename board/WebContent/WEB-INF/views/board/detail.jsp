<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상세보기</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<!-- 댓글 수정 버튼을 눌렀을 때 대화상자로 출력될 영역 -->
	<div id="dialog" title="댓글 수정">
		<p>댓글을 입력하세요</p>
		<input type="text" id="replytext" 
		class="text ui-widjet-content ui-corner-all" size="35" />
		<input type="button" value="댓글수정" onclick='update()' />	
		<input type="button" value="수정취소" onclick='cancel()' />		
	</div>
	
	<section class="content">
	<div class="box">
		<div class="box-header">
			<h3 class="box-title">상세보기</h3>
		</div>
		
		<div class="box-body">
			<div class="form-group">
				<label>제목</label> <input type="text" name="title"
					class="form-control" value="${vo.title}" readonly="readonly" />
			</div>

			<div class="form-group">
				<label>내용</label>
				<textarea name="content" rows="5" readonly="readonly"
					class="form-control">${vo.content}</textarea>
			</div>

			<div class="form-group">
				<label>작성자</label> <input type="text" name="id" class="form-control"
					value="${vo.id}" readonly="readonly" />
			</div>
			
			<div class="form-group">
				<label>HP</label> <input type="text" name="phone" class="form-control"
					value="${vo.phone}" readonly="readonly" />
			</div>
		</div>
		
		
		<div class="box-footer">
				<button id="main" class="btn btn-primary">메인</button>
				<button id="update" class="btn btn-warning">수정</button>
				<button id="delete" class="btn btn-danger">삭제</button>
				<button id="list" class="btn btn-primary">목록</button>
			</div>
			
			<div>
				<button class="btn btn-success" onclick="add()" id="addBtn">댓글
					작성</button>
			</div>
			
			<!-- 댓글 작성 영역 -->
			<div class="box-body" style="display: none;" id="replyform">
				<!-- 원본 글번호 저장 -->
				<input type="hidden" id="bno" value="${vo.bno}" /> <label>작성자</label>
				<input type="text" class="form-control" 
				placeholder="작성자 이름을 입력하세요"
				id="newReplyWriter" /> 
				<label>내용</label> <input type="text"
				class="form-control" placeholder="댓글을 작성하세요" id="newReplyText" />
				<button type="button" class="btn btn-warning" id="addReply">저장</button>
				<button type="button" class="btn btn-danger" id="cancelAdd">취소</button>
			</div>
			
			<!-- 댓글 출력 영역 -->
			<div id="replyDisp" class="box box-primary"></div>
			
			<script>
			var rno = '0';
			
			function update(){
				//alert("수정완료");
				var bno = '${vo.bno}';
				var replytext = $('#replytext').val();
				$('#dialog').dialog('close');
			
				$.ajax({
					url:'../reply/update',
					data:{
						'rno':rno,
						'bno':bno,
						'replytext':replytext
					},
					dataType:'json',
					success:function(data){
						//댓글 영역 삭제
						$('#replyDisp').html('');
						//댓글 영역에 data 출력
						display(data);
					}
				});
				
			}
			
			//댓글 수정 버튼을 눌렀을 때 호출될 함수
				function mod(btn){
					rno = btn.id.substr(3);
					$('#dialog').dialog('open');
	
				}
				//댓글수정 대화상자에서 취소버튼을 눌렀을 때 호출될 함수
				function cancel(){
					$('#dialog').dialog('close');
				}
			
				
				//문서구조파악이 끝나면
				$(function(){
					getReply();
					
					//댓글 수정 대화상자 옵션 설정
					$('#dialog').dialog({
						autoOpen:false,
						show:{
							effect:'blind',
							duration:1000
						},
						hide:{
							effect:'explode',
							duration:1000
						}
					});
					
					
					$('#cancelAdd').on('click', function(){
						$('#addBtn').show('fast');
						$('#replyform').hide('slow');
					});
					
					$('#addReply').on('click', function(){
						var bno= $('#bno').val();
						var replyText = $('#newReplyText').val();
						var replyer = $('#newReplyWriter').val();
						
						
						$.ajax({
							url:"../reply/insert",
							data:{
								'bno':bno,
								'replytext':replyText,
								'replyer':replyer
							},
							dataType:'json',
							success:function(data){
								//댓글작성 버튼을 출력하고
								$('#addBtn').show('fast');
								//댓글작성 영역은 숨기고
								$('#replyform').hide('slow');
								//기존 댓글은 지우고
								$('#replyDisp').html('');
								
								$('#newReplyText').html('');
								$('#newReplyWriter').html('');
								//새로운 댓글 출력
								display(data);
							}
						});
						
					});
					
				});
				
				//댓글작성을 눌렀을 때 호출될 함수
				function add(){
					$('#replyform').show('slow');
					$('#addBtn').show('fast');
				}
				
				//ajax로 댓글을 요청
				function getReply(){
					$.ajax({
						url:"../reply/list",
						data:{bno:'${vo.bno}'},
						dataType:'json',
						success:function(data){
							display(data);
						}
					});
				};
				//가져온 댓글을 출력하는 함수
				function display(data){
					//출력 내용을 저장할 변수
					var disp="";
					//가져온 데이터가 없을 때 출력 내용
					if(data.length == 0){
						
					}
					//data를 순회하기
					//idx 는 순번이고 item이 하나하나의 데이터
					$(data).each(function(idx, item){
						disp += "<div style='width:50%;height:50px;'>";
						disp += "<label>" + item.replyer + ":" + item.replytext;
						disp += "</label>";
						disp += "<button class='btn btn-warning' id='mod";
						disp += item.rno+"' style=float:right;' onclick='mod(this)'>";
						disp += "댓글수정</button>"
						disp += "<button class='btn btn-danger' id='del";
						disp += item.rno+"' style=float:right;' onclick='del(this)'>";
						disp += "댓글삭제</button>"
						
						
						disp += "</div>";
						$("#replyDisp").append(disp);
						disp = "";
					});
				}
				
				
			</script>

	</div>
	</section>
	<%@ include file="../include/footer.jsp"%>
	
	
	<script>
	function del(btn){
		var rno = btn.id.substr(3);
		$.ajax({
			url:"../reply/delete",
			data:{
				'rno':rno,
				'bno':'${vo.bno}'
			},
			dataType:"json",
			success:function(data){
				//기존 댓글은 지우고
				$('#replyDisp').html('');
				//새로운 댓글 출력
				display(data);
		
				}
		});
		
	
	}
	//메인 버튼을 눌렀을 때 처리
	$(function(){
		$("#main").click(function(){
			location.href="../";
		});
	});
	
	//수정버튼을 눌렀을 때
	$(function(){
		$("#update").click(function(){
			location.href="../board/update/" +${vo.bno};
		});
	});
	
	//삭제 버튼을 눌렀을 때 처리
	$(function(){
		$("#delete").click(function(){
			location.href="../board/delete/" + ${vo.bno};
		});
	});
	
	//목록버튼을 눌렀을 때 처리
	$(function(){
		$("#list").click(function(){
			location.href="../board/list?page=${criteria.page}&perPageNum=${criteria.perPageNum}&searchType=${criteria.searchType}&keyword=${criteria.keyword}";
		});
	});
	</script>

</body>
</html>