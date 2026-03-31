package com.example.quiztime.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quiztime.model.Question;
import java.util.List;

public class QuizViewModel extends ViewModel {

    private final QuizRepository repository;

    private final MutableLiveData<Question> currentQuestion = new MutableLiveData<>();
    private final MutableLiveData<Integer> questionIndex = new MutableLiveData<>();
    private final MutableLiveData<Integer> score = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isAnswered = new MutableLiveData<>();
    private final MutableLiveData<Integer> selectedAnswer = new MutableLiveData<>();
    private final MutableLiveData<Boolean> quizFinished = new MutableLiveData<>();

    private List<Question> questionList;

    public QuizViewModel() {
        repository = new QuizRepository();

        questionList = repository.getQuestions();

        questionIndex.setValue(0);
        score.setValue(0);
        isAnswered.setValue(false);
        selectedAnswer.setValue(-1);
        quizFinished.setValue(false);

        loadQuestion();
    }

    private void loadQuestion() {
        int index = questionIndex.getValue();
        currentQuestion.setValue(questionList.get(index));
        isAnswered.setValue(false);
        selectedAnswer.setValue(-1);
    }

    public LiveData<Question> getCurrentQuestion() {
        return currentQuestion;
    }

    public LiveData<Integer> getScore() {
        return score;
    }

    public LiveData<Integer> getQuestionIndex() {
        return questionIndex;
    }

    public LiveData<Boolean> getIsAnswered() {
        return isAnswered;
    }

    public LiveData<Boolean> isQuizFinished() {
        return quizFinished;
    }

    public int getTotalQuestions() {
        return questionList.size();
    }

    public void selectAnswer(int index) {
        if (Boolean.TRUE.equals(isAnswered.getValue())) return;
        selectedAnswer.setValue(index);
    }

    public void submitAnswer() {

        if (selectedAnswer.getValue() == -1) return;

        isAnswered.setValue(true);

        int correct = currentQuestion.getValue().getCorrectAnswerIndex();

        if (selectedAnswer.getValue() == correct) {
            score.setValue(score.getValue() + 1);
        }
    }

    public int getCorrectAnswer() {
        return currentQuestion.getValue().getCorrectAnswerIndex();
    }

    public int getSelectedAnswer() {
        return selectedAnswer.getValue();
    }

    public void nextQuestion() {

        int index = questionIndex.getValue();

        if (index < questionList.size() - 1) {
            questionIndex.setValue(index + 1);
            loadQuestion();
        } else {
            quizFinished.setValue(true);
        }
    }

}
