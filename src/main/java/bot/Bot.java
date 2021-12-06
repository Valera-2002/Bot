package bot;

import java.util.ArrayList;
import java.util.List;

import java.util.Timer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.apache.commons.lang.time. StopWatch;

public class Bot extends TelegramLongPollingBot {

  private static final String TOKEN = "2135433200:AAH3rSTa68Bv0ZlsZnFEoIgz09QFtavwmfo";
  private static final String BOTNAME = "das_quiz_bot";
  Response responder = new Response();
  StopWatch timer = new StopWatch();
  private static final int limit = 2;

  public Bot(DefaultBotOptions options) {
    super(options);
  }

  @Override
  public String getBotUsername() {
    return BOTNAME;
  }

  @Override
  public String getBotToken() {
    return TOKEN;
  }

  public void onUpdateReceived(Update update) {
    if (update.hasCallbackQuery()) {
      long chat_id = update.getCallbackQuery().getMessage().getChatId();
      String data = update.getCallbackQuery().getData();
      String res = responder.response(data, chat_id);
      sendMessage(chat_id, res);
      timer(limit,chat_id);
    }
    else if (update.hasMessage()) {
      long chat_id = update.getMessage().getChatId();
      String text = update.getMessage().getText();
      String res = responder.response(text, chat_id);
      if (res.equals("STARTGAMECODE")){
        Long firstGamer = responder.multigames.get(chat_id);
        sendMessage(firstGamer, responder.response("go", firstGamer));
        sendMessage(chat_id, responder.response("go", chat_id));
        responder.gameMap.get(firstGamer).opponentsId = chat_id;
        responder.gameMap.get(chat_id).opponentsId = firstGamer;
        Timer myTimer = new Timer();
        MyTimerTask myTimerTask = new MyTimerTask(this, responder.gameMap, chat_id);
        myTimer.schedule(myTimerTask, 90000);
        return;
      }
      sendMessage(chat_id, res);
      timer(limit,chat_id);
    }
  }

  public void sendMessage(Long chat_id, String message){
    try {
      if (responder.gameMap.get(chat_id) != null){
        if (responder.gameMap.get(chat_id).onGame) {
          List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
          String[] options = message.split("\\n");
          int i = 2;
          while (i < 6){
            buttons.add(List.of(InlineKeyboardButton.builder().text(options[i]).callbackData(
                    Integer.toString(i - 1)).build()));
            i++;
          }
          execute(
                  SendMessage.builder()
                          .text(options[0] + "\n" + options[1])
                          .chatId(chat_id.toString())
                          .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                          .build());
        }
        else {
          execute(new SendMessage(Long.toString(chat_id), message));}
      } else {
        execute(new SendMessage(Long.toString(chat_id), message));
      }
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }
  public void timer(int limit, long chat_id) {
    if (responder.gameMap.get(chat_id) != null){
      if (responder.gameMap.get(chat_id).onGame) {
        long i = 0;
        timer.reset();
        timer.start();
        while (i < limit * 1000L) {
          i = timer.getTime();
        }
    timer.stop();
    sendMessage(chat_id,responder.response("0", chat_id));
  }}}
  public void resultOfBattle(Long userId){
    MultiuserGame multiuserGame = new MultiuserGame();
    Long opponentId = responder.gameMap.get(userId).opponentsId;
    multiuserGame.gameStat
            (responder.gameMap.get(userId), responder.gameMap.get(opponentId));
    sendMessage(userId, responder.response("result", userId));
    sendMessage(opponentId, responder.response("result", opponentId));
  }
}