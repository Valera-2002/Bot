package bot;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class Bot extends TelegramLongPollingBot {

  private static final String TOKEN = "2135433200:AAH3rSTa68Bv0ZlsZnFEoIgz09QFtavwmfo";
  private static final String BOTNAME = "das_quiz_bot";
  Response responder = new Response();

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
      if (!responder.multiusersGames.isEmpty()) {
        printResultOfBattle(chat_id);
      }
    } else if (update.hasMessage()) {
      long chat_id = update.getMessage().getChatId();
      String text = update.getMessage().getText();
      String res = responder.response(text, chat_id);
      sendMessage(chat_id, res);
    }
  }

  public void printResultOfBattle(Long chat_id) {
    for (MultiusersGame game : responder.multiusersGames) {
      if (game.firstId == chat_id | game.secondId == chat_id) {
        if (!responder.gameMap.get(game.firstId).onGame &
            !responder.gameMap.get(game.secondId).onGame) {
          game.setWinnerId();
          if (game.winnerId == 0L){
            sendMessage(game.firstId, "Ничья!");
            sendMessage(game.secondId, "Ничья!");
          }
          if (game.firstId == game.winnerId){
            sendMessage(game.firstId, "Вы победили!");
            sendMessage(game.secondId, "Вы проиграли!");
          } else {
            sendMessage(game.firstId, "Вы проиграли!");
            sendMessage(game.secondId, "Вы победили!");
          }
        }
        break;
      }
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
}