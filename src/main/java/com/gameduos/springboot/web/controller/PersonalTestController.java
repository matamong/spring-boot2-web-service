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
        return "personalTest/ow/heroMatching";
    }

    @GetMapping("/heroMatching/result")
    public String getHeroMatchingResult(@RequestParam(value = "heroName", defaultValue = "0")String heroName,
                                        @RequestParam(value="x", defaultValue = "0") int x,
                                        @RequestParam(value = "y", defaultValue = "0") int y, Model model){
        String ogImg = "http://raw.githubusercontent.com/gameduosdev/gameduosdev.github.io/master/images/PsersonalTest/ow/heros/"
                + heroName + ".PNG";
        String ogTitle = "나는 " + heroName + "와(과) 잘 어울려요!";
        String ogUrl = "www.gameduos.net/personalTest/ow/heroMatching/result?heroName="+ heroName +"&x="+ x + "&y=" + y;
        model.addAttribute("heroName", heroName);
        model.addAttribute("x", x);
        model.addAttribute("y", y);
        model.addAttribute("ogImg", ogImg);
        model.addAttribute("ogTitle", ogTitle);
        model.addAttribute("ogUrl", ogUrl);
        return "personalTest/ow/heroMatchingResult";
    }
}
