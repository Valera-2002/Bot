package bot;

public class Game {
  public String[] questions;
  public String[] answers;

  public void fillArray() {
    Based_of_questions based = new Based_of_questions();
    based.fillArray();
    questions = based.fillArrayOfQuestions();
    answers = based.fillArrayOfAnswer();
   }

  public Integer currentQuestion = 0;
  public Boolean onGame = false;

  public int rightCount = 0;
  public int wrongCount = 0;


  public String startGame(){
    currentQuestion += 1;
    return questions[0];
  }

  public String goGame(String text){
    String result = "";
    if (isCorrect(answers[currentQuestion-1], text)) {
      result += "Верно\n";
    }
    else {
      result += "Неверно\n";
    }
    if (currentQuestion < 6) {
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