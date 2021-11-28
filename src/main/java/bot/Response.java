package bot;

import java.util.*;

public class Response {
  public Map<Long, Game> gameMap = new HashMap<>();
  public Map<Long, AddQuestions> add = new HashMap<>();
  public List<MultiusersGame> multiusersGames = new ArrayList<>();
  private List<String> admin_id = new ArrayList<>(Arrays.asList("721126016", "1224972468"));

  public String response(String text, long user_id) {
    if (text.equals("/start")){
      return sayHello();
    }
    if (text.equals("help")) {
      return getHelp();
    }

    if (admin_id.contains(Long.toString(user_id)) && text.equals("add")){
      AddQuestions addQuestions = new AddQuestions();
      add.put(user_id, addQuestions);
      return addQuestions.instruction();
    }

    if (add.containsKey(user_id) && add.get(user_id).getAppend()){
      if (add.get(user_id).getQuestion().equals("")) {
        return add.get(user_id).inputQuestion(text);
      }
      if (add.get(user_id).getVariantQuestions().equals("")) {
        return add.get(user_id).inputVariantQuestion(text);
      }
      if (add.get(user_id).getAnswerQuestions().equals("")) {
        return add.get(user_id).inputAnswerQuestion(text);
      }
    }

    if (text.equals("go")){
      Game newGame = new Game();
      gameMap.put(user_id, newGame);
      return newGame.startGame();
    }

    if (text.equals("multi game")){
      if (multiusersGames.isEmpty()){
        MultiusersGame newMultiusersGame = new MultiusersGame(user_id);
        multiusersGames.add(newMultiusersGame);
        Game newGame = new Game();
        newGame.multiusersGame = newMultiusersGame;
        gameMap.put(user_id, newGame);
        return newGame.startGame();
      }
    }

    if (gameMap.containsKey(user_id) && gameMap.get(user_id).getOnGame()){return gameMap.get(user_id).goGame(text);}
    else {
      return """
              Некорректный ввод
              Для повторного получения справочной информации напиши "help"\s""";
    }
  }

  public String getHelp() {
    return """
            Справочная информация:\s
            Для начала игры напиши команду "go"\s
            Для добавления вопроса напиши "add\"""";
  }

  public String sayHello() {
    return """
Привет, я квиз-бот!
Я могу проводить квиз в одиночном и совместном режиме
Для начала игры напиши команду "go"
Для повторного получения справочной информации напиши "help\"""";
  }
}