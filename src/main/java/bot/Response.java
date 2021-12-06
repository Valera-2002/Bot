package bot;


import java.util.*;

public class Response {
  public Map<Long, Game> gameMap = new HashMap<>();
  public Map<Long, AddQuestions> questionsMap = new HashMap<>();
  //public List<MultiusersGame> multiusersGames = new ArrayList<>();
  private final List<String> admin_id = new ArrayList<>(Arrays.asList("721126016", "1224972468"));
  public List<Long> queue = new ArrayList<>();
  public Map<Long, Long> multigames = new HashMap<>();


  public String response(String text, long user_id) {
    switch(text){
      case "/start": return sayHello();
      case "help": return getHelp();
      case "add": if (admin_id.contains(Long.toString(user_id))){
        AddQuestions addQuestions = new AddQuestions();
        questionsMap.put(user_id, addQuestions);
        return addQuestions.instruction();}

      case "search":
        queue.add(user_id);
        if (queue.size() < 2) return "Ожидайте других игроков";
        else{
          multigames.put(user_id, queue.get(0));
          multigames.put(queue.get(0), user_id);
          queue.clear();
          return "STARTGAMECODE";
          }

      case "go":
        Game newGame = new Game();
        gameMap.put(user_id, newGame);
        return newGame.startGame();

      case "result": return gameMap.get(user_id).getResultOfBattle();
    }
    if (questionsMap.containsKey(user_id) && questionsMap.get(user_id).getAppend()){
      if (questionsMap.get(user_id).getQuestion().equals("")) return questionsMap.get(user_id).inputQuestion(text);
      if (questionsMap.get(user_id).getVariantQuestions().equals("")) return questionsMap.get(user_id).inputVariantQuestion(text);
      if (questionsMap.get(user_id).getAnswerQuestions().equals("")) return questionsMap.get(user_id).inputAnswerQuestion(text);
    }
    if (gameMap.containsKey(user_id) && gameMap.get(user_id).getOnGame()) return gameMap.get(user_id).goGame(text);

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