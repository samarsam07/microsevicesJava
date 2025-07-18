package com.samar.quizapp.service;

import com.samar.quizapp.dao.QuestionDao;
import com.samar.quizapp.dao.QuizDao;
import com.samar.quizapp.model.Question;
import com.samar.quizapp.model.QuestionWrapper;
import com.samar.quizapp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> all=questionDao.findRandomQuestionsByCategory(category);
        Collections.shuffle(all);
        List<Question> questions = all.stream().limit(numQ).collect(Collectors.toList());
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz=quizDao.findById(id);
        List<Question> questionsFormDB=quiz.get().getQuestions();
        List<QuestionWrapper> questionForUsers=new ArrayList<>();
        for(Question q :questionsFormDB){
            QuestionWrapper questionWrapper=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUsers.add(questionWrapper);
        }
        return new ResponseEntity<>(questionForUsers,HttpStatus.OK);
    }
}
