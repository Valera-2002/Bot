package bot;

import java.util.Map;
import java.util.TimerTask;


public class MyTimerTask extends TimerTask {
  Bot bot;
  Map<Long, Game> gameMap;
  Long userId;

  public MyTimerTask(Bot bot, Map<Long, Game> gameMap, Long userId) {
    this.bot = bot;
    this.gameMap = gameMap;
    this.userId = userId;
  }

  @Override
  public void run() {
    Long opponentId = gameMap.get(userId).opponentsId;
    gameMap.get(userId).onGame = false;
    gameMap.get(opponentId).onGame = false;
    bot.resultOfBattle(userId);
  }
}