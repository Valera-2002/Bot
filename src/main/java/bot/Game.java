package bot;

import org.apache.commons.lang.time.StopWatch;

public class Game {
  public String[] questions;
  public String[] answers;
  public int countOfQuestions;

  public void fillArray() {
    BasedOfQuestions based = new BasedOfQuestions();
    based.fillArray();
    questions = based.getArrayOfQuestions();
    answers = based.getArrayOfAnswer();
    countOfQuestions = based.getCountOfQuestions();
  }

  public Boolean onGame;
  public Boolean getOnGame(){return onGame;}

  public int currentQuestion = 0;
  public int rightCount = 0;
  public int getRightCount() {return rightCount;}
  public int wrongCount = 0;
  public long time = 0;
  public long getTime() {return time;}
  StopWatch timer = new StopWatch();

  public String resultOfBattle;
  public String getResultOfBattle() {return resultOfBattle;}
  public void setResultOfBattle(String resultOfBattle) {this.resultOfBattle = resultOfBattle;}

  public String startGame(){
    timer.start();
    onGame = true;
    fillArray();
    currentQuestion += 1;
    return "Игра началась!\n" + questions[0];
  }

  public String goGame(String text){
    String result = "";
    if (isCorrect(answers[currentQuestion-1], text)) {
      result += "Верно\n";
    }
    else {
      result += "Неверно\n";
    }
    if (currentQuestion < countOfQuestions) {
      result += questions[currentQuestion];
      currentQuestion+=1;
    }
    else {
      onGame = false;
      time =  timer.getTime();
      result += "\n" + statistics();
    }

    return result;
  }

  public Boolean isCorrect(String answer, String usersAnswer){
    if (usersAnswer.equals(answer)){
      rightCount += 1;
      return true;
    } else{
      wrongCount += 1;
      return false;
    }
  }

  public String statistics() {
    return "\nВерных ответов:" + rightCount +
        "\nНеверных ответов:" + wrongCount+
            "\nОбщее время: " + time / 1000 + " c";
  }
}