## 参考URL
- [Spring Security の基本](https://qiita.com/opengl-8080/items/c105152c9ca48509bd0c)
- [フォーム認証とBasic認証の混在設定](https://qiita.com/shibafu/items/26c9a3a48fe32ba0b647)
- [Spring Security の認証](https://terasolunaorg.github.io/guideline/5.1.0.RELEASE/ja/Security/Authentication.html)
- [Spring Security の認可](https://terasolunaorg.github.io/guideline/5.1.0.RELEASE/ja/Security/Authorization.html)

	※xml 設定例
	<sec:http pattern="/resources/**" security="none" />
	<sec:http auto-config="false" use-expressions="true"
		entry-point-ref="authenticationEntryPoint" >
		<!-- API用セキュリティ設定 -->
		<sec:intercept-url pattern="/api/**" access="isAuthenticated()" />
		<sec:http-basic />
		<sec:csrf disabled="true" />
		<!-- 都度認証を行う。セッションを作成しない、セッションが存在しても使用しない -->
		<sec:session-management create-session="stateless" />
		<!-- AccessDeniedHandler(権限エラー)のカスタマイズ -->
		<sec:access-denied-handler ref="accessDeniedHandler" />
	</sec:http>
	<sec:http auto-config="false" use-expressions="true" >
		<sec:intercept-url pattern="/login" access="permitAll" />
		<sec:intercept-url pattern="/anyPath" access="isAuthenticated()" />
		<sec:intercept-url pattern="/**" access="denyAll" />
		...
	</sec:http>

	<sec:authentication-manager>
		<!-- omitted -->
	</sec:authentication-manager>

	<!-- AutheticationEntryPoint(未認証エラー)のカスタマイズ -->
	<bean id="authenticationEntryPoint" class="org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint">
		<!-- omitted -->
	</bean>
	<!-- AccessDeniedHandler(権限エラー)のカスタマイズ -->
	<bean id="accessDeniedHandler" class="com.example.web.security.JsonDelegatingAccessDeniedHandler">
		<!-- omitted -->
	</bean>
