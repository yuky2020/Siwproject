package siwproject.siwproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import siwproject.siwproject.pg.HashTagService;

@Controller
public class SearchController {

    @Autowired
    private HashTagService hashTagService;

    @GetMapping("/search")
    public String search(Model model, @RequestParam("toSearch") String toSearch) {
        if (hashTagService.existsByNome(toSearch)) {
            model.addAttribute("hashTag", hashTagService.hashTagPerNome(toSearch));
        }
        return "results";
    }
}