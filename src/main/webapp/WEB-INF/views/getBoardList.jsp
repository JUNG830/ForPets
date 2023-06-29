<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>게시판 목록</title>
    <style>
        table {
            width: 100%;
            border: 1px solid #444444;
            border-collapse: collapse;
        }
        table th {
            border: 1px solid #444444;
            text-align: center;
            height: 40px;
            background-color: dodgerblue;
            color: cornsilk;
        }
        table td {
            border: 1px solid #444444;
            text-align: center;
        }
    </style>
</head>
<body>
<div style="text-align: center;">
    <h1>게시판 목록</h1>
    <table style="width: 700px; margin: auto">
        <tr>
            <th style="width: 10%">번호</th>
            <th style="width: 50%">제목</th>
            <th style="width: 15%">작성자</th>
            <th style="width: 15%">등록일</th>
            <th style="width: 10%">조회수</th>
        </tr>
        <c:forEach var="board" items="${boardList}" varStatus="i">
            <tr>
                <td></td>
                <td style="text-align: left"><a href="getBoard?seq=">${board.title}</a></td>
                <td>${board.writer}</td>
                <td><fmt:formatDate value="" pattern="yyyy-MM-dd"></fmt:formatDate> </td>
                <td>${board.cnt}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="insertBoardView">새글 등록</a>
</div>
</body>
</html>