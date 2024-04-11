package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static com.javarush.telegrambot.TelegramBotContent.*;
import static java.lang.Math.toIntExact;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "javacatcanbot"; // TODO: добавьте имя бота в кавычках
    public static final String TOKEN = "6636895420:AAHUUVDzMoL07JZyTZ8lkQcXdAVy09MutNs"; //TODO: добавьте токен бота в кавычках

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update update) {
        // TODO: основной функционал бота будем писать здесь
//        sendTextMessageAsync(" _E boy_ ");

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();


            if (text.contains("/start")) {
                setUserGlory(0);
                sendPhotoMessageAsync("step_1_pic");
                sendTextMessageAsync(STEP_1_TEXT, Map.of("Взлом холодильника", "step_1_btn"));
            }
        }
        if (getCallbackQueryButtonKey().equals("step_1_btn")) {
            addUserGlory(20);
//            long message_id = update.getCallbackQuery().getMessage().getMessageId();
//            long chat_id = update.getCallbackQuery().getMessage().getChatId();
//            String answer = "Updated message text";
//            EditMessageText new_message = new EditMessageText()
//                    .setChatId(chat_id)
//                    .setMessageId(toIntExact(message_id))
//                    .setText(answer);
            sendPhotoMessageAsync("step_2_pic");
            sendTextMessageAsync(STEP_2_TEXT,
                    Map.of("Take sausage + 20 glory", "sausage_btn",
                            "Take fish + 25 glory", "fish_btn",
                            "Take milk + 5 glory", "milk_btn"));



        }
        if (getCallbackQueryButtonKey().equals("sausage_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_3_pic");
            sendTextMessageAsync(STEP_3_TEXT, Map.of("Взломать робот пылесос", "step_3_btn"));
        }

        if (getCallbackQueryButtonKey().equals("milk_btn")) {
            addUserGlory(5);
            sendTextMessageAsync(STEP_3_TEXT, Map.of("Взломать робот пылесос", "step_3_btn"));
        }


        if (getCallbackQueryButtonKey().equals("fish_btn")) {
            addUserGlory(25);
            sendTextMessageAsync(STEP_3_TEXT, Map.of("Взломать робот пылесос", "step_3_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_3_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_4_pic");
            sendTextMessageAsync(STEP_4_TEXT,
                    Map.of("Robot goes me to walk + 20 glory", "step_3_btn_action_done",
                            "Robot goes wash yourself in bathroom + 25 glory", "step_3_btn_action_done",
                            "Robot take care of me + 5 glory", "step_3_btn_action_done"));
        }

//take go pro and go to work
        if (getCallbackQueryButtonKey().equals("step_3_btn_action_done")) {
            addUserGlory(50);
            sendPhotoMessageAsync("step_5_pic");
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Turn on and wear go pro", "go_pro_butt"));
        }

        if (getCallbackQueryButtonKey().equals("go_pro_butt")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_6_pic");
            sendTextMessageAsync(STEP_6_TEXT,
                    Map.of("Jump on the roof  + 20 glory", "step_4_btn_action_done",
                            "Go to diving + 25 glory", "step_4_btn_action_done",
                            "Inter into redbull moto racing team + 5 glory", "step_4_btn_action_done"));
        }


        // hack computer
        if (getCallbackQueryButtonKey().equals("step_4_btn_action_done")) {
            addUserGlory(50);
            sendPhotoMessageAsync("step_7_pic");
            sendTextMessageAsync(STEP_7_TEXT, Map.of("Hack comp", "comp_hack_btn"));
        }

        if (getCallbackQueryButtonKey().equals("comp_hack_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_8_pic");
            sendTextMessageAsync(STEP_8_TEXT,
                    Map.of("Go to dvor ", "step_5_btn_action_done"));

        }
        if (getCallbackQueryButtonKey().equals("step_5_btn_action_done")) {
            addUserGlory(20);
            sendPhotoMessageAsync("final_pic");
            sendTextMessageAsync(FINAL_TEXT);
        }


        if (getMessageText().equals("/get_glory")) {
            sendTextMessageAsync("Your glory " + getUserGlory() + " points");
        }
    }

            public static void main (String[]args) throws TelegramApiException {
                TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
                telegramBotsApi.registerBot(new MyFirstTelegramBot());
            }



}
