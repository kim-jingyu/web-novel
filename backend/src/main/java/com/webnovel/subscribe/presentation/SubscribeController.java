package com.webnovel.subscribe.presentation;

import com.webnovel.subscribe.dto.SubscribeActivateDto;
import com.webnovel.subscribe.dto.SubscribeDeactivateDto;
import com.webnovel.subscribe.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscribe/")
public class SubscribeController {
    @Autowired
    private SubscribeService subscribeService;

    @GetMapping("do")
    public ResponseEntity<String> doSubscribe(@RequestParam SubscribeActivateDto request) {
        subscribeService.doSubscribe(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("true");
    }

    @GetMapping("undo")
    public ResponseEntity<String> unSubscribe(@RequestParam SubscribeDeactivateDto request) {
        subscribeService.unSubscribe(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("true");
    }

}
