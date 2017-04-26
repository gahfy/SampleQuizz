package net.gahfy.samplequizz;

interface QuizzView {
    void showQuestion(String question, String answer1, String answer2, String answer3, String answer4);
    void showMessage(boolean goodAnswer);
}
