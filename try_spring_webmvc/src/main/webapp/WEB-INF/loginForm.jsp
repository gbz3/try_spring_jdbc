<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Login Form</title>
</head>
<body>
	<div id="wrapper">
		<h3>ログインフォーム</h3>
		<c:if test="${param.containsKey('error')}">
			<span style="color: red;">
				<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"></c:out>
			</span>
		</c:if>
		<c:url var="loginUrl" value="/login" />
		<form:form action="${loginUrl}">
			<table>
				<tr>
					<td><label for="username">ユーザー名</label></td>
					<td><input type="text" id="username" name="username"></td>
				</tr>
				<tr>
					<td><label for="password">パスワード</label></td>
					<td><input type="password" id="password" name="password"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><button>ログイン</button></td>
				</tr>
			</table>
		</form:form>
	<p>Your principal object is....: <%= request.getUserPrincipal() %></p>
	<!--
	org.apache.catalina.authenticator.SingleSignOn を有効化すると、未認証の時は要求先Webアプリの認証に進み、認証後は Principal を要求先Webアプリに渡す？
	[Java開発者向け] シングルサインオンへの対応 - Java ... - Wagby
	https://wagby.com/wdn8/download/WTM2019SingleSignOn.pdf

	Spring securityで学ぶＳＳＯ入門
	https://www.slideshare.net/ishikawahiroshi1/spring-security-94403652

	spring securityで独自の認証を実装する
	https://ishiis.net/2016/08/27/spring-security-custom-authentication/
	 -->
	</div>
</body>
</html>