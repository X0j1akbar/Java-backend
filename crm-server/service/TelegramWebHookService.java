package uz.pdp.srmserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.pdp.srmserver.entitiy.TelegramUser;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.NewsDto;
import uz.pdp.srmserver.repository.TelegramUserRepository;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelegramWebHookService {
    private final String TOKEN = "1896120943:AAHWBHdBa7Lt-RAugfFFHkNwpUbKhbnqJ0Q";
    private final String URL = "https://api.telegram.org/bot" + TOKEN;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FeinClientForWebHook forWebHook;

    @Autowired
    TelegramUserRepository telegramUserRepository;

    public void getUpdate(Update update) {
        long chatId = getChatId(update);
        TelegramUser user = getUser(chatId);
        if (update.hasMessage()) {
            if (update.getMessage().hasContact()) {
                forWebHook.sendMes(getContact(update));
            } else if (update.getMessage().hasLocation()) {
                forWebHook.sendMes(getLocation(update));
            } else {
                String text = update.getMessage().getText();
                if (text.equals("/start")) {
                    forWebHook.sendMes(getStart(update));
                } else {
                    if (user.getBotState().equals(BotState.SHARE_LOCATION_OR_ADDRESS)) {
                        forWebHook.sendMes(getLocation(update));
                    }
                }
            }
        }
    }

    public long getChatId(Update update) {
        return update.getCallbackQuery() != null ?
                update.getCallbackQuery().getMessage().getChatId()
                :
                update.getMessage().getChatId();
    }

    public TelegramUser getUser(long chatId) {
        Optional<TelegramUser> optionalTelegramUser = telegramUserRepository.findByChatId(chatId);
        if (optionalTelegramUser.isPresent()) {
            return optionalTelegramUser.get();
        }
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setChatId(chatId);
        telegramUser.setBotState(BotState.SHARE_CONTACT);
        return telegramUserRepository.save(telegramUser);
    }

    public SendMessage getStart(Update update) {
        long chatId = getChatId(update);
        TelegramUser user = getUser(chatId);
        if (user.getBotState().equals(BotState.SHARE_CONTACT)) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            sendMessage.setText("Hello");
            ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
            List<KeyboardRow> rowList = new ArrayList<>();
            KeyboardRow row = new KeyboardRow();
            KeyboardButton button = new KeyboardButton();
            button.setText("Share Contact");
            button.setRequestContact(true);
            row.add(button);
            rowList.add(row);
            markup.setKeyboard(rowList);
            markup.setSelective(true);
            markup.setResizeKeyboard(true);
            sendMessage.setReplyMarkup(markup);
            return sendMessage;
        }
        else {
            return showMenu(update);
        }
    }

    public SendMessage showMenu(Update update) {
        long chatId = getChatId(update);
        TelegramUser user = getUser(chatId);
        user.setBotState(BotState.SHOW_MENU);
        telegramUserRepository.save(user);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("Choose menu");
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        button.setText("Products");
        row.add(button);
        button = new KeyboardButton();
        button.setText("Basket");
        row.add(button);
        button = new KeyboardButton();
        button.setText("About us");
        row.add(button);
        rowList.add(row);
        markup.setKeyboard(rowList);
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(markup);
        return sendMessage;
    }

    public SendMessage getContact(Update update) {
        long chatId = getChatId(update);
        TelegramUser user = getUser(chatId);
        user.setBotState(BotState.SHARE_LOCATION_OR_ADDRESS);
        telegramUserRepository.save(user);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("Hello");
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        button.setText("Share Location");
        button.setRequestLocation(true);
        row.add(button);
        rowList.add(row);
        markup.setKeyboard(rowList);
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(markup);
        return sendMessage;
    }

    public SendMessage getLocation(Update update) {
        long chatId = getChatId(update);
        TelegramUser user = getUser(chatId);
        if (update.getMessage().getLocation() != null) {
            Location location = update.getMessage().getLocation();
            user.setLon(location.getLongitude());
            user.setLat(location.getLatitude());
        } else {
            user.setAddress(update.getMessage().getText());
        }
        user.setBotState(BotState.SHOW_MENU);
        telegramUserRepository.save(user);
        return showMenu(update);
    }

    public ApiResponse sendNews(NewsDto newsDto)throws Exception {
        try {

                    List<TelegramUser> all = telegramUserRepository.findAll();
                    for (TelegramUser user : all) {
                        SendPhoto sendPhoto = new SendPhoto();
                        sendPhoto.setChatId(user.getChatId().toString());
                        sendPhoto.setCaption(newsDto.getTitle()+"\n"+
                               newsDto.getDescription());
                        InputFile inputFile = new InputFile();
                        inputFile.setMedia(new File(newsDto.getPhotoUrl()),"dsf");
                        sendPhoto.setPhoto(inputFile);
                        restTemplate.postForEntity(URL+"/sendPhoto",sendPhoto,Void.class);
                    }
            return new ApiResponse("Ok",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }
}
