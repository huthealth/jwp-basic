package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.service.QnaService;
import next.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuestionApiController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long questionId = Long.parseLong(request.getParameter("questionId"));
        String questionWriter = request.getParameter("writer");

        QnaService qnaService = new QnaService(questionId,questionWriter);
        if(!qnaService.delete()){
            return jsonView().addObject("result", Result.fail("fail to delete question"));
        }

        return jsonView().addObject("result",Result.ok());
    }
}
