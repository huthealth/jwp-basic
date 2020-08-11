package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetQuestionListController extends AbstractController {
    private QuestionDao questionDao = new QuestionDao();
    private List<Question> questions;

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        questions = questionDao.findAll();

        return jsonView().addObject("questions",questions);
    }
}
