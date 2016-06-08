import com.talentica.doEngine.client.telegram.Callback;
import com.talentica.doEngine.client.telegram.TelegramBot;
import com.talentica.doEngine.client.telegram.TelegramBotAdapter;
import com.talentica.doEngine.client.telegram.request.BaseRequest;
import com.talentica.doEngine.client.telegram.request.SetWebhook;
import com.talentica.doEngine.client.telegram.response.BaseResponse;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by aravindp on 1/6/16.
 */
public class TestTelegramBot {

    @Test
    public void test() {
        String TELEGRAM_BOT_TOKEN = "197123987:AAESOiX9Pl2x_DMbE1P6stXV-OKBGyAE6QU";
        TelegramBot telegramBot = TelegramBotAdapter.build(TELEGRAM_BOT_TOKEN);
        telegramBot.execute(new SetWebhook().url("https://435d603e.ngrok.io"),new Callback() {
            @Override
            public void onResponse(BaseRequest request, BaseResponse response) {
                System.out.println("got response : " + response);
            }
            @Override
            public void onFailure(BaseRequest request, IOException e) {
            }
        });
        //telegramBot.execute(new SendMessage(192591982, ""));
    }
}
