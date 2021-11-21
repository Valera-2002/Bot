package bot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import bot.Response;

public class Bot extends TelegramLongPollingBot {

  private static final String TOKEN = "2039749405:AAFzZYZI6SRDWit-6m8rfD5SlG4mKFhP9ug";
  private static final String BOTNAME = "dasQuizBot";
  Response responser = new Response();

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

  @Override
  public void onUpdateReceived(Update update) {
    if (update.getMessage() != null && update.getMessage().hasText()) {
      long chat_id = update.getMessage().getChatId();
      String text = update.getMessage().getText();
      String res = responser.response(text, chat_id);
      sendMessage(chat_id, res);
    }
  }

  public void sendMessage(Long chat_id, String message){
    try {
      if (message.contains("?")){
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        int i = 1;
        String[] options = message.split("\\n");
        while (i < 5){
          buttons.add(Arrays.asList(InlineKeyboardButton.builder().text(options[i]).callbackData(
                  Integer.toString(i)).build()));
          i++;
        }
        System.out.println(1234);
        execute(
                SendMessage.builder()
                        .text(message)
                        .chatId(chat_id.toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                        .build());
      }
      else {
        execute(new SendMessage(Long.toString(chat_id), message));
      }
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }
}