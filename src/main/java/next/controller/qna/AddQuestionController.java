package next.controller.qna;

import com.sun.org.apache.xpath.internal.operations.Mod;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
post /qna/create url ->
	http 바디의 글쓴이와 제목 내용으로 Question 인스턴스 생성
	QuestionDao를 통해 DB에 저장
	/ URL 로 리다이렉트
 */
public class AddQuestionController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getMethod().equals("GET")){
            if(!UserSessionUtils.isLogined(request.getSession())){
                return jspView("redirect:/users/loginForm");
            }
            User user = (User)request.getSession().getAttribute("user");
            ModelAndView mav = jspView("/qna/form.jsp");
            mav.addObject("user",user);
            //request.setAttribute("user",user);
            return mav;
        }

        Question question = new Question(request.getParameter("writer"),request.getParameter("title"),request.getParameter("contents"));
        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(question);
        return jspView("redirect:/");
    }
}
