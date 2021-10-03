public class Response {
  public void response(String input)
  {
    if (input.equals("help"))
    {
      getHelp();
    }

    else if(input.equals("go"))
    {
      Game newGame = new Game();
      newGame.goGame();
    }

    else if (input.equals("stop")){
      stop();
    }

    else {
      onDisplay("Некорректный ввод"
          + "\nДля повторного получения справочной информации напиши \"help\" ");
    }
  }

  public static void onDisplay(String string){
    System.out.println(string);
  }

  public void stop(){
    System.exit(0);
  }

  public static void getHelp(){
    onDisplay(
        "Для получения справочной информации напиши " +
            "\nДля начала игры напиши команду \"go\"" +
            "\nДля завершения работы напиши \"stop\"");


  }
  public void sayHello(){
    onDisplay("Привет, я квиз-бот!" +
        "\nИгра состоит из 3 вопросов" +
        "\nЯ могу проводить квиз в одиночном и совместном режиме" +
        "\nДля начала игры напиши команду \"go\"" +
        "\nОтветить на вопрос можно написав номер ответа " +
        "\nЗакончить игру можно написав 0 " +
        "\nДля повторного получения справочной информации напиши \"help\" " +
        "\nДля завершения работы напиши \"stop\"");
  }
}