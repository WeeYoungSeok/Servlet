##### JSTL ( Jsp Standard Tag Library )

> JSTL이란? JSP는 자신만의 태그를 추가할 수 있는 기능을 제공하고 있다.
>
> `<jsp:include>`나 `<jsp:usebean>`과 같은 커스텀 태그처럼 연산이나 조건문이나 반복문인 if문, for문, DB를 편하게 처리할 수 있는것이 JSTL이다.



- 태그

  > 선언 방법

```html
<%@ taglib prefix="" uri=""%>
```



- 종류

  > Core (prefix : c)
  >
  > > 일반 프로그래밍에서 제공하는 것과 유사한 변수선언
  > >
  > > 실행 흐름의 제어 기능을 제공
  > >
  > > 페이지 이동 기술 제공
  > >
  > > ```html
  > > <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  > > ```
  >
  > Formatting (prefix : fmt)
  >
  > > 숫자, 날짜, 시간을 포매팅하는 기능을 제공
  > >
  > > 국제화, 다국어 지원 기능 제공
  > >
  > > ```html
  > > <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  > > ```
  >
  > DataBase (prefix : sql)
  >
  > > DB의 데이터를 입력 / 수정 / 삭제 / 조회하는 기능을 제공
  > >
  > > ```html
  > > <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
  > > ```
  > >
  > > 



