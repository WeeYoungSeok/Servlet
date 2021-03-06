##### EL ( Expression Language )

> EL은 해석 그대로 표현 언어를 이해하고 속성 값들을 편리하게 출력하기 위해 제공된 언어이며, JSTL은 표준 액션태그로 처리하기 힘든 부분을 담당한다.
>
> JSP 2.0버전에서 새로 추가된 스크립트 언어인 EL은 <%= abc%>를 $(abc)로 간단하게 사용할 수 있게 하였고, JSTL의 Core에서 c를 이용해 <%= if%>문을 `<c:if>`, <%=for%>문을 `<c:forEach>`로 대체하여 사용한다.

- 사용목적

  > <%= %>, out.println()과 같은 자바코드를 더 이상 사용하지 않고 좀더 간편하게 출력을 지원하기 위한 도구, 배열이나 컬렉션에서도 사용되고, JavaBean의 프로피터에서도 사용됩니다.

- 문법

  > Attribute형식에서는 <%= cnt + 1%>를 사용하지 않고 ${cnt + 1}로 쓰고 Parameter형식에서는 ${Param.abc}로 쓴다.
  >
  > 자바에서는 cnt를 변수라고 하고, EL에서는 Atrribute의 이름으로 해석
  >
  > 값을 찾을때 Attribute는 작은 Scope에서 큰 Scope로 찾는다.
  >
  > ( page -> request -> session -> application )



**attribute**란? 메소드를 통해 저장되고 관리되는 데이터

##### PageContext / Request에서 사용될때

> setAttribute("key", value) -> 값을 넣는다.
>
> getAttribute("key") -> 값을 가져온다.
>
> removeAttribute("key") -> 값을 지운다.

##### session에서 사용될때

> set / get / remove 동일하고 추가로++
>
> invalidate() -> 값을 전부 지운다.



#### 연산자

| 단어 연산자 |      기호 연산자       |
| :---------: | :--------------------: |
|      +      |           +            |
|      -      |           -            |
|      *      |           *            |
|      /      |          div           |
|      %      |          mod           |
|     &&      |          and           |
|    \|\|     |           or           |
|      !      |          not           |
|      >      |    lt ( less than )    |
|      <      |  gt ( greater than )   |
|     >=      |  le ( less or equal )  |
|     <=      | ge ( greater or equal) |
|     ==      |      eq ( equal )      |
|     !=      |    ne ( not equal )    |



#### 내장객체

> ##### pageScope -> 페이지 scope에 접근
>
> ##### request Scope -> 리퀘스트Scope에 접근
>
> ##### sessionSocope -> 세션Scope에 접근
>
> ##### applicationScope -> 어플리케이션Scope에 접근
>
> ##### param -> 파라미터값 얻어올때 ( 1개의 Key의 1개의 Value )
>
> ##### paramValues -> 파라미터값 배열로 얻어올때 ( 1개의 Key에 여러개의 Value )
>
> ##### header -> 헤더값 얻어올떄 ( 1개의 Key에 1개의 Value )
>
> ##### headerValues -> 헤더값 배열로 얻어올때 ( 1개의 Key에 여러개의 Value )
>
> ##### cookie -> ${cookie. key값. value값}으로 쿠키값 조회
>
> ##### initParam -> 초기 파라미터 조회
>
> ##### pageContext -> 페이지컨택스트 객체를 참조할떄



#### paramValues 나 headerValues 사용법

> 1. ${ paramValues . boadDto [0]}
>
> 2. ${ paramValues ["bardDto"] [1] }
>
> Values 옆에 점을 찍는 방법과 대괄호로 묶어 사용하는 2가지 방법이 있다.
>
> 대신 1번에서는 인덱스가 0부터 시작하고 2번에서는 인덱스가 1부터 시작한다.

