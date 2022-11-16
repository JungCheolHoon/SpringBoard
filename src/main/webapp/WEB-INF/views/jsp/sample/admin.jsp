<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>
	관리자 접근 허용 admin.jsp  
</h1>

<p>principal : <sec:authentication property="principal"/>
<p>MemberVO : <sec:authentication property="principal.member"/>
<p>사용자이름 : <sec:authentication property="principal.member.userName"/>
<p>사용자아이디 : <sec:authentication property="principal.username"/>
<p>권한리스트 : <sec:authentication property="principal.member.authList"/> 
<br />

<a href="all">Logout</a>

</body>
</html>