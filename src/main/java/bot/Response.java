package bot;

import java.util.HashMap;
import java.util.Map;

public class Response {

  Map<Long, Game> map = new HashMap<>();
  Map<Long, AddQuestions> add = new HashMap<>();



  public String response(String text, long user_id) {
    if (text.equals("/start")){
      return sayHello();
    }
    if (text.equals("help")) {
      return getHelp();
    }
    if (text.equals("whoami")){
      return "VALERA";
    }if (text.equals("count")){
      Based_of_questions based = new Based_of_questions();
      return based.getCountOfQuestions();
    }
    if(text.equals("add")){
      AddQuestions questions = new AddQuestions();
      questions.append = true;
      add.put(user_id, questions);
      return questions.instruction();

    } else if (add.get(user_id).append){
      if (add.get(user_id).question.equals("")) {
        return add.get(user_id).inputQuestion(text);
      }
      if (add.get(user_id).variantQuestions.equals("")) {
        return add.get(user_id).inputVariantQuestions(text);
      }
      if (add.get(user_id).answerQuestions.equals("")) {
        return add.get(user_id).inputAnswerQuestions(text);
      }
      else {return add.get(user_id).AddQuestions();}}

    if (text.equals("go")){
      Game newGame = new Game();
      newGame.fillArray();
      newGame.onGame = true;
      map.put(user_id, newGame);
      return newGame.startGame();
    } else if (map.get(user_id).onGame){
      return map.get(user_id).goGame(text);

    } else {
      return "Некорректный ввод"
              + "\nДля повторного получения справочной информации напиши \"help\" ";
    }
  }

  public String getHelp() {
    return """
Для получения справочной информации напиши\s
Для начала игры напиши команду "go\"\s
Для добавления вопроса напиши \"add\"""";
  }

  public String sayHello() {
    return """
Привет, я квиз-бот!
Игра состоит из 3 вопросов
Я могу проводить квиз в одиночном и совместном режиме
Для начала игры напиши команду "go"
Ответить на вопрос можно написав номер ответа\s
Для повторного получения справочной информации напиши "help\"""";
  }
}