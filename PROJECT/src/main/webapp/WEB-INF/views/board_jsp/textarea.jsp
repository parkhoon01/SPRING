<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/com/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon"
	href="<%=contPath%>/favicon.ico">


<!--reset 스타일 시트 -->
<!-- link rel="stylesheet" type="text/css" href=contPathath %>/asset/css/reset.css" -->
<link rel="stylesheet" type="text/css"
	href="<%=contPath%>/asset/css/jquery-ui.css">
<!--스타일 시트 -->
<style type="text/css">
</style>
<title>Insert title here</title>
<!-- jquery -->
<script type="text/javascript"
	src="<%=contPath%>/asset/js/jquery-1.12.4.js"></script>
<!-- jQuery UI -->
<script type="text/javascript" src="<%=contPath%>/asset/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=contPath%>/asset/js/common.js"></script>

</head>
<body>
	<h2></h2>
	<hr />
	<div class="form-group col-12">
		<div class="textLengthWrap">
			<p class="textCount">0자</p>
			<p class="textTotal">/200자</p>
		</div>
		<textarea style="height: 300px; resize: none;" maxlength="200"
			placeholder="텍스트를 입력하세요." id="textBox"> </textarea>
	</div>
	<script type="text/javascript">
	
$('#textBox').keyup(function (e) { 
	let content = $(this).val(); // 글자수 세기 
	
	if (content.length == 0 || content == '') { 
		$('.textCount').text('0자'); 
  } else { 
	  $('.textCount').text(content.length + '자');
  } // 글자수 제한 
  
  
  
  if (content.length >= 200) { 
	  
	  // 200자 부터는 타이핑 되지 않도록 
	  $(this).val($(this).val().substring(0, 200)); 
	  // 200자 넘으면 알림창 뜨도록 
	  alert('글자수는 200자까지 입력 가능합니다.'); 
	}; 
	
});


</script>
</body>
</html>