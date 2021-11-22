package bot;

import java.util.HashMap;
import java.util.Map;

public class Response {
  public Map<Long, Game> map = new HashMap<>();
  Map<Long, AddQuestions> add = new HashMap<>();

  public String response(String text, long user_id) {
    if (text.equals("/start")){
      return sayHello();
    }
    if (text.equals("help")) {
      return getHelp();
    }

    if ((Long.toString(user_id).equals("721126016") | (Long.toString(user_id).equals("1224972468"))) && (text.equals("add"))){
      AddQuestions questions = new AddQuestions();
      questions.append = true;
      add.put(user_id, questions);
      return questions.instruction();
    }

    if (add.containsKey(user_id) && add.get(user_id).append){
      if (add.get(user_id).question.equals("")) {
        return add.get(user_id).inputQuestion(text);
      }
      if (add.get(user_id).variantQuestions.equals("")) {
        return add.get(user_id).inputVariantQuestions(text);
      }
      if (add.get(user_id).answerQuestions.equals("")) {
        return add.get(user_id).inputAnswerQuestions(text);
      }
    }

    if (text.equals("go")){
      Game newGame = new Game();
      newGame.fillArray();
      map.put(user_id, newGame);
      return newGame.startGame();
    }

    if (map.containsKey(user_id) && map.get(user_id).onGame){return map.get(user_id).goGame(text);}
    else {
      return "Некорректный ввод"
              + "\nДля повторного получения справочной информации напиши \"help\" ";
    }
  }

  public String getHelp() {
    return """
Справочная информация:\s
Для начала игры напиши команду "go\"\s
Для добавления вопроса напиши \"add\"""";
  }

  public String sayHello() {
    return """
Привет, я квиз-бот!
Я могу проводить квиз в одиночном и совместном режиме
Для начала игры напиши команду "go"
Для повторного получения справочной информации напиши "help\"""";
  }
}