<%--
/**
        Class Name:
        Description:
        Modification information
        
        수정일     수정자      수정내용
    -----   -----  -------------------------------------------
    2022. 6. 23.        최초작성 
    
    author ehr 개발팀
    since 2022.01.27
    Copyright (C) by KandJang All right reserved.
*/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<c:set var="resources" value="/resources"/>
<c:set var="CP_RES"    value="${CP}${resources}" />
<!DOCTYPE html>
<html>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>부트 스트랩-boot_list</title>
    <!-- 부트스트랩 -->
    <link href="${CP_RES}/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="${CP_RES}/js/jquery-1.12.4.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${CP_RES}/js/bootstrap.min.js"></script>
    <!-- jquery bootstrap paging -->
    <scriptsrc="${CP_RES}/js/jquery.bootpag.js"></script>  
    <!-- 사용자 정의 function, ISEmpty -->
    <script src="${CP_RES}/js/eUtil.js"></script>
    <!-- 사용자 정의 function, callAjax -->
    <script src="${CP_RES}/js/eclass.js"></script>
      
    <script type="text/javascript">
      $(document).ready(function(){
        console.log("document.ready");  
          
        $("#doLogin").on("click", function(){
        	console.log("doLogin");
        	
        	if(eUtil.ISEmpty($("#uId").val())){
        		alert("아이디를 입력하세요");
        		$("#uId").focus();
        		return;
        	}
        	
        	if(eUtil.ISEmpty($("#passwd").val())){
                alert("비밀번호를 입력하세요");
                $("#passwd").focus();
                return;
            }
        	
        	if(confirm("로그인 하시겠습니까?") == false)return;
        	
            let url ="${CP}/login/doLogin.do";
            let method = "POST";
            let async = true;
            let parameters = {
            		"uId" : $("#uId").val(),
            		"passwd" : $("#passwd").val()
            };
            
            EClass.callAjax(url, parameters, method, async, function(data) {
            	if("10" == data.msgId){    //ID 확인
            		alert(data.msgContents);
            		$("#uId").focus();
            	}
            	else if("20" == data.msgId){   // 비번 확인
            		alert(data.msgContents);
                    $("#passwd").focus();
            	}
            	else if("30" == data.msgId){   // ID 비번 통과
                    alert(data.msgContents);
                    // 특정 페이지 이동
                    window.location.href = "${CP}/main/mainView.do";
                }
            	else{
            		alert(data.msgContents);
                    $("#uId").focus();
            	}
            });
        	
        });
        
      });
      
      
    </script>
</head>
<body>
    
    <h2>로그인</h2>
    <hr/>
    <div class="container">
	    <div class="row">
	        <input type="button" id="doLogin" class="btn btn-primary btn-sm" value="로그인" />
	    </div>
	    <form action="${CP}/lgoin/doLogin.do" method="post">
		    <div class="form-group">
		        ID:<input type="text" name="uId" id="uId"><br/>
		        PassWord:<input type="password" name="passwd" id="passwd"><br/>
	        </div>
	    </form>
    </div>
</body>
</html>