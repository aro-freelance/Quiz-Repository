package com.aro.quiz.model;

public class Question {

    /* each question will have Resource Id because we are going to put all of our questions into
       our strings.xml . So each question needs an id so that we can fetch the question.

       And then for each question it will either be true or false.

       SCALE IDEAS: If we want to scale this to a multiple choice quiz we would need to use something more complex than a bool
       to hold the answer. We could save a char and check against that char for example. Or give each answer an int value and have
       the correct int value for each question stored in this class.
     */
    private int answerResId;
    private boolean answerTrue;

    public Question(int answerResId, boolean answerTrue) {
        this.answerResId = answerResId;
        this.answerTrue = answerTrue;
    }

    public int getAnswerResId() {
        return answerResId;
    }

    public void setAnswerResId(int answerResId) {
        this.answerResId = answerResId;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }
}
