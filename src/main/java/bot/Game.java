package bot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Game {
  public String[] questions;
  public String[] answers;
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
    if (isCorrect(answers[currentQuestion], text)) {
      result += "Верно\n";
    }
    else {
      result += "Неверно\n";
    }
    if (currentQuestion < 3) {
      result += questions[currentQuestion];
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