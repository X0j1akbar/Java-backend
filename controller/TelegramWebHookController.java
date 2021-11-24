package uz.pdp.srmserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.NewsDto;
import uz.pdp.srmserver.service.TelegramWebHookService;

@RestController
@RequestMapping("/api/webhook")
public class TelegramWebHookController {
    @Autowired
    TelegramWebHookService service;

    @PostMapping
    public void webhook(@RequestBody Update update){
        service.getUpdate(update);
    }

    @PostMapping("/sendNews")
    public ApiResponse sendNewsToTelegramUsers(@RequestBody NewsDto newsDto) throws Exception {
        return service.sendNews(newsDto);
    }
}
