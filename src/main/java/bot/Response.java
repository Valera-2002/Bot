package bot;


import org.telegram.telegrambots.meta.api.objects.Update;

public class Response {

  public String response(Update update) {
    long chat_id = update.getMessage().getChatId();
    String text = update.getMessage().getText();
    if (text.equals("help")) {
      return getHelp();
    } else if (text.equals("go")) {
      Game newGame = new Game();
      newGame.goGame(Update update);
      return "0";
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