package com.gameduos.springboot.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/matching")
public class MatchingController {

    @GetMapping({"", "/"})
    public String matching() {
        return "matching/matching";
    }
}
