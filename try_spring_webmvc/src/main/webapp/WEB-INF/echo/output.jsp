<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %><html>
<body>
	<h2>出力画面</h2>
	<div>入力したテキストは・・・</div>
	<div>
		「<span><c:out value="${echoForm.text}" /></span>」
	</div>
	<div>です。</div>
	<br>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
	<div>
		権限: 「管理者」
	</div>
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
	<div>
		メールアドレス: 「<sec:authentication property="principal.account.mail"/>」
	</div>
	</sec:authorize>
	<div>
		<a href="<c:url value='/' />">トップ画面へ戻る</a>
	</div>
	<div>
		<form action="<c:url value='/logout' />" method="post" >
			<sec:csrfInput />
			<button>ログアウト</button>
		</form>
	</div>
</body>
</html>