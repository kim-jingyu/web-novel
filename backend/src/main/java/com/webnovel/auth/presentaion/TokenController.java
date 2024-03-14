package com.webnovel.auth.presentaion;

import com.webnovel.auth.dto.AccessTokenResponse;
import com.webnovel.auth.dto.CreateTokenRequest;
import com.webnovel.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @PostMapping("/auth/token")
    public ResponseEntity<AccessTokenResponse> createNewAccessToken(@RequestBody CreateTokenRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity
                .status(CREATED)
                .body(new AccessTokenResponse(newAccessToken));
    }
}
