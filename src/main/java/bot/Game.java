package bot;

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
  public int wrongCount = 0;


  public String startGame(){
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
            "\nНеверных ответов:" + wrongCount;
  }



}