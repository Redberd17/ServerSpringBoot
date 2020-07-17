package com.chugunova.myproject.rest;

import com.chugunova.myproject.model.AdviceDuration;
import com.chugunova.myproject.model.Dream;
import com.chugunova.myproject.model.Message;
import com.chugunova.myproject.service.AdviceDurService;
import com.chugunova.myproject.service.DreamService;
import com.chugunova.myproject.service.MessageService;
import com.chugunova.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping({"/users/"})
public class RestController {

    private final DreamService dreamService;
    private final AdviceDurService adviceDurService;
    private final MessageService messageService;

    @Autowired
    public RestController(DreamService dreamService, UserService userService, AdviceDurService adviceDurService, MessageService messageService) {
        this.dreamService = dreamService;
        this.adviceDurService = adviceDurService;
        this.messageService = messageService;
    }

    @GetMapping({"dreams"})
    public ResponseEntity<List<Dream>> getUserDreams(Authentication authentication) {
        List<Dream> dream = this.dreamService.getUserDreams(authentication.getName());
        if (dream.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(dream, HttpStatus.OK);
        }
    }

    @GetMapping({"adviceDurations/{duration}"})
    public ResponseEntity<AdviceDuration> getAdviceDurations(@PathVariable(name = "duration") Double duration) {
        List<AdviceDuration> adviceDurations = this.adviceDurService.getAdviceDuration();

        AdviceDuration adviceDuration = null;

        if (duration < adviceDurations.get(0).getAdviceDurValue()) {
            adviceDuration = adviceDurations.get(0);
        } else if (adviceDurations.get(0).getAdviceDurValue() <= duration && duration < adviceDurations.get(1).getAdviceDurValue()) {
            adviceDuration = adviceDurations.get(1);
        } else if (adviceDurations.get(1).getAdviceDurValue() <= duration && duration < adviceDurations.get(2).getAdviceDurValue()) {
            adviceDuration = adviceDurations.get(2);
        } else if (adviceDurations.get(2).getAdviceDurValue() <= duration && duration <= adviceDurations.get(4).getAdviceDurValue()) {
            adviceDuration = adviceDurations.get(4);
        } else if (adviceDurations.get(4).getAdviceDurValue() < duration && duration <= adviceDurations.get(3).getAdviceDurValue()) {
            adviceDuration = adviceDurations.get(3);
        } else if (duration > adviceDurations.get(5).getAdviceDurValue()) {
            adviceDuration = adviceDurations.get(5);
        }
        return new ResponseEntity<>(adviceDuration, HttpStatus.OK);
    }

    @PostMapping({"dreams/dream"})
    public ResponseEntity<String> addUserDreams(@RequestBody Dream dream, Authentication authentication) {
        if (dream.getDreamText().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dream is empty");
        }
        dreamService.addUserDreams(dream.getDreamName(), dream.getDreamText(), authentication.getName(), dream.getDreamDuration());
        return new ResponseEntity<>("Dream was send", HttpStatus.OK);
    }

    @DeleteMapping({"dreams/dream/{dreamId}"})
    public ResponseEntity<String> deleteUserDreams(@PathVariable(name = "dreamId") Integer dreamId) {
        dreamService.deleteUserDreams(dreamId);
        return new ResponseEntity<>("Dream was deleted", HttpStatus.OK);
    }

    @GetMapping({"messages"})
    public ResponseEntity<List<Message>> getAllMessage() {
        List<Message> message = this.messageService.getAllMessage();
        if (message == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @PostMapping({"messages/message"})
    public ResponseEntity<String> sendMessage(@RequestBody Message message, Authentication authentication) {
        if (message.getMesText().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message is empty");
        }
        messageService.sendMessage(authentication.getName(), message.getMesText());
        return new ResponseEntity<>("Message was send", HttpStatus.OK);
    }

}
