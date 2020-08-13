package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateFormQuestionController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(UpdateFormQuestionController.class);

    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String writer = request.getParameter("writer");
        User user = UserSessionUtils.getUserFromSession(request.getSession());

        if(user.getName().equals(writer)){
            Question question = questionDao.findById(Long.parseLong(request.getParameter("questionId")));
            return jspView("/qna/updateForm.jsp").addObject("question",question);
        }
        return jspView("redirect:/");
    }
}
