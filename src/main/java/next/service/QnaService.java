package next.service;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import java.util.List;

public class QnaService {
    private long questionId;
    private String questionWriter;
    private AnswerDao answerDao = new AnswerDao();
    private QuestionDao questionDao = new QuestionDao();

    public QnaService(long questionId, String questionWriter){
        this.questionId =questionId;
        this.questionWriter = questionWriter;
    }

    public boolean delete(){
        List<Answer> answers = answerDao.findAllByQuestionId(questionId);

        for(Answer answer : answers){
            if(!answer.getWriter().equals(questionWriter)) {
                return false;
            }
        }
        if(!answers.isEmpty()) {
            answerDao.deleteByQuestionId(questionId);
        }
        questionDao.delete(questionId);

        return true;
    }
}
