package bot;

import java.util.HashMap;
import java.util.Map;

public class Response {

  public Map<Long, Game> map = new HashMap<>();

  public String response(String text, long user_id) {
    if (text.equals("/start")){
      return sayHello();
    }
    if (text.equals("help")) {
      return getHelp();
    } else if (text.equals("go")){
      String result = "";
      if (map.containsKey(user_id)){
        result+="Статистика предыдущей игры:\n"+map.get(user_id).statistics();
        map.get(user_id).onGame = false;

      }

      Game newGame = new Game();
      newGame.fillArray();
      newGame.onGame = true;
      map.put(user_id, newGame);
      return result +"\n"+ newGame.startGame();
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
Для начала игры напиши команду "go\"""";
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