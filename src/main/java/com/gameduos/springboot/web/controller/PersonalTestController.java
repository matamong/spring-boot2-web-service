package com.gameduos.springboot.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/personalTest/ow")
public class PersonalTestController {
    @GetMapping("/heroMatching")
    public String getHeroMatching(){
        return "/personalTest/ow/heroMatching";
    }

    @GetMapping("/heroMatching/result")
    public String getHeroMatchingResult(@RequestParam(value = "heroName", defaultValue = "0")String heroName,
                                        @RequestParam(value="x", defaultValue = "0") int x,
                                        @RequestParam(value = "y", defaultValue = "0") int y, Model model){
        model.addAttribute("heroName", heroName);
        model.addAttribute("x", x);
        model.addAttribute("y", y);
        System.out.println("받은 파라미터 >>>>>> " + heroName + "/" + x + "/" + y);
        return "/personalTest/ow/heroMatchingResult";
    }
}
