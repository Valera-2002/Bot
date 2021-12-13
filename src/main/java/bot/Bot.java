package bot;

import java.io.IOException;
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

public class Bot extends TelegramLongPollingBot {

  private static String TOKEN;
  private static final String BOTNAME = "das_quiz_bot";
  Response responder = new Response();

  public Bot(DefaultBotOptions options) {
    super(options);
    TOKEN = getBotToken();
  }

  @Override
  public String getBotToken() {
    return System.getenv("TOKEN");
  }

  @Override
  public String getBotUsername() {
    return BOTNAME;
  }

  public void onUpdateReceived(Update update) {
    if (update.hasCallbackQuery()) {
      long chat_id = update.getCallbackQuery().getMessage().getChatId();
      String data = update.getCallbackQuery().getData();
      String res = null;
      try {
        res = responder.response(data, chat_id);
      } catch (IOException e) {
        e.printStackTrace();
      }
      sendMessage(chat_id, res);
    }
    else if (update.hasMessage()) {
      long chat_id = update.getMessage().getChatId();
      String text = update.getMessage().getText();
      String res = null;
      try {
        res = responder.response(text, chat_id);
      } catch (IOException e) {
        e.printStackTrace();
      }
      if (res.equals("STARTGAMECODE")) {
        try {
          startMultiuserGame(chat_id);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      sendMessage(chat_id, res);
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

  public void startMultiuserGame(long chat_id) throws IOException {
    Long firstGamer = responder.multigames.get(chat_id);
    sendMessage(firstGamer, responder.response("go", firstGamer));
    sendMessage(chat_id, responder.response("go", chat_id));
    responder.gameMap.get(firstGamer).opponentsId = chat_id;
    responder.gameMap.get(chat_id).opponentsId = firstGamer;
    Timer myTimer = new Timer();
    MyTimerTask myTimerTask = new MyTimerTask(this, responder.gameMap, chat_id);
    myTimer.schedule(myTimerTask, 10000);
  }
  public void resultOfBattle(Long userId) throws IOException {
    MultiuserGame multiuserGame = new MultiuserGame();
    Long opponentId = responder.gameMap.get(userId).opponentsId;
    multiuserGame.gameStat
            (responder.gameMap.get(userId), responder.gameMap.get(opponentId));
    sendMessage(userId, responder.response("result", userId));
    sendMessage(opponentId, responder.response("result", opponentId));
  }
}