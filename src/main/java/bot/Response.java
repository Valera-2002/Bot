package main.java.bot;

public class Response {

  public void response(String input) {
    if (input.equals("help")) {
      onDisplay(getHelp());
    } else if (input.equals("go")) {
      Game newGame = new Game();
      newGame.goGame();
    } else if (input.equals("stop")) {
      stop();
    } else {
      onDisplay("Некорректный ввод"
          + "\nДля повторного получения справочной информации напиши \"help\" ");
    }
  }

  public static void onDisplay(String string) {
    System.out.println(string);
  }

  public void stop() {
    System.exit(0);
  }

  public String getHelp() {
    return """
        Для получения справочной информации напиши\s
        Для начала игры напиши команду "go"
        Для завершения работы напиши "stop\"""";
  }

  public String sayHello() {
    return """
        Привет, я квиз-бот!
        Игра состоит из 3 вопросов
        Я могу проводить квиз в одиночном и совместном режиме
        Для начала игры напиши команду "go"
        Ответить на вопрос можно написав номер ответа\s
        Закончить игру можно написав 0\s
        Для повторного получения справочной информации напиши "help"\s
        Для завершения работы напиши "stop\"""";
  }
}