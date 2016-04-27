# BS OAuth library for folked from scribe-java

OAuth 1,2 클라이언트.
scribe 포크해서 만들었는데 변경을 심하게 해서 패키지병 라이브러리명 변경.


## 사용법 Usage

사용법은 /test/java/org.beansugar.oauth.examples. ~ 참고


### 스프링에서 쓸 때(예 facebook)

```java
@Bean
public OAuth20Service facebookService(){
	return new OAuth20ServiceSimple(
			new FacebookApi(),
			new OAuth20Config(
					"your_api_key",
					"your_api_secret",
					//your redirect url(풀 주소인 경우도 있고 일부 사이트는 경로만 쓰는 경우도있고)
					"http://sso.beansugar.org/oauth_callback",
					//null해도 되는데 값을 더 받아오려면 추가해야함
					null
			)
	);
}
```

### Small and modular

의존성을 많이 제거했으나 조금 남았음. 개인용 repository도 추가해야함.
bs-tools-java에 의존성
org.beansugar.tools:bs-tools-core:tools-20151118-1-BUILD-SNAPSHOT


### Pull it from Maven!

You can pull scribe from my maven repository, just add these to your __pom.xml__ file:

```xml

<!-- repository -->
<repositories>
  <repository>
    <id>beansugar-release</id>
    <url>http://nexus.beansugar.org/content/repositories/beansugar-release</url>
  </repository>
</repositories>

<!-- dependency -->
<dependency>
  <groupId>org.beansugar.oauth</groupId>
  <artifactId>bs-oauth-java</artifactId>
  <version>2.0.1</version>
</dependency>
```

## Getting started in less than 2 minutes

없음

## Questions?

히히


## Forks

Looking for a scribe variation? check the [Fork List](https://github.com/fernandezpablo85/scribe-java/wiki/Forks)

If you have a useful fork that should be listed there please contact me (see About me).

## About me

몰랑
