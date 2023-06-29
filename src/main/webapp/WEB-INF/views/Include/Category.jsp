<%@ page language="java" contentType="text/html; charset=UTF-8
    pageEncoding="UTF-8"%>

//request로 받은 contextPath를 path라는 변수에 넣는 jstl태그
<c:set var="path" value="${pageContext.request.contextPath}" />

<div style="text-align: center;">
    <a href="${path}/">Home</a>
    <a href="${path}/SignUp">회원가입</a>
    <a href="${path}/test.do">테스트</a>
</div>