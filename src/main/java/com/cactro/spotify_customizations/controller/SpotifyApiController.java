package com.cactro.spotify_customizations.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/my")
public class SpotifyApiController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/top-tracks")
    public ResponseEntity<?> getTopTracks(HttpSession session) {

        String token = (String) session.getAttribute("access_token");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String url = "https://api.spotify.com/v1/me/tracks?limit=10";

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/now-playing")
    public ResponseEntity<?> getNowPlaying(HttpSession session) {
        String token = (String) session.getAttribute("access_token");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String url = "https://api.spotify.com/v1/me/player/currently-playing";

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}

