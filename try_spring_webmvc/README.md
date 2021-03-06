## 参考URL
- [Spring Security の基本](https://qiita.com/opengl-8080/items/c105152c9ca48509bd0c)
- [フォーム認証とBasic認証の混在設定](https://qiita.com/shibafu/items/26c9a3a48fe32ba0b647)
- [Spring Security の認証](https://terasolunaorg.github.io/guideline/5.1.0.RELEASE/ja/Security/Authentication.html)
- [Spring Security の認可](https://terasolunaorg.github.io/guideline/5.1.0.RELEASE/ja/Security/Authorization.html)
- [Multiple Entry Points in Spring Security](https://www.baeldung.com/spring-security-multiple-entry-points)
  [※機械翻訳版](https://www.codeflow.site/ja/article/spring-security-multiple-entry-points)
- [Basic認証とForm認証を混在させる例](http://www.ne.jp/asahi/hishidama/home/tech/java/spring/boot/rest/basic-auth.html)

```
※xml 設定例
<!-- 認証不要なリソース類 -->
<sec:http pattern="/resources/**" security="none" />

<!-- API は Basic認証 -->
<sec:http pattern="/api/**" auto-config="false" use-expressions="true">
	<!-- API用セキュリティ設定 -->
	<sec:intercept-url pattern="/api/**" access="hasRole('ROLE_SYSADMIN')" />
	<sec:http-basic entry-point-ref="apiAuthenticationEntryPoint" />
	<sec:csrf disabled="true" />
	<!-- 都度認証を行う。セッションを作成しない、セッションが存在しても使用しない -->
	<sec:session-management create-session="stateless" />
	<!-- AccessDeniedHandler(権限エラー)のカスタマイズ -->
	<sec:access-denied-handler ref="accessDeniedHandler" />
</sec:http>

<!-- その他は Form認証 -->
<sec:http auto-config="false" use-expressions="true" >
	<sec:intercept-url pattern="/login" access="permitAll" />
	<sec:intercept-url pattern="/anyPath" access="isAuthenticated()" />
	<sec:intercept-url pattern="/**" access="denyAll" />
	<sec:form-login />
	<!-- omitted -->
</sec:http>

<sec:authentication-manager>
	<!-- omitted -->
</sec:authentication-manager>

<!-- AutheticationEntryPoint(未認証エラー)のカスタマイズ -->
<bean id="apiAuthenticationEntryPoint" class="org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint">
	<!-- omitted -->
</bean>
<!-- AccessDeniedHandler(権限エラー)のカスタマイズ -->
<bean id="accessDeniedHandler" class="com.example.web.security.JsonDelegatingAccessDeniedHandler">
	<!-- omitted -->
</bean>
```
