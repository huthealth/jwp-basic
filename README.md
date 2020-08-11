#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* 톰켓 서버 시작 시 ServeltContextListener 인터페이스를 구현하고 @WebListener 애노테이션 설정이 된 ContextLoaderListener 
의 contextInitialized() 메소드를 호출해 초기화 작업을 한다.
* 이 작업은 서블릿 초기화보다 먼저 진행된다.

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* webservlet 어노테이션과 loadonstartup 속성이 1로 설정된 DispatcherServlet이 어느 url을 선택하던지 호출됨(인스턴스화됨?)
* 처음 접근 시 init 호출로 RequestMapping 인스턴스화
* service 메소드 호출
   * HttpServletRequest 요청의 uri과 매핑된 컨트롤러(서블릿)을 반환.
   * 반환된 컨트롤러의 execute 메소드를 호출해 ModelAndView를 반환
   * 반환된 mav의 getView와 getModel 메소드를 통해 클라이언트에 응답할 View와 View에서 사용할 Model을 얻음
   * View 인터페이스의 render 메소드 호출을 통해 Json 또는 Jsp를 클라이언트에 반환
   
#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 
