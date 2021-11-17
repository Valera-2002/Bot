package bot;

import java.util.Map;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Response {

  Map<Long, Game> map;

  public String response(String text, long user_id) {
    if (text.equals("/start")){
      return sayHello();
    }
    if (text.equals("help")) {
      return getHelp();
    } else if (text.equals("go")){
      Game newGame = new Game();
      //newGame.fillArray();
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