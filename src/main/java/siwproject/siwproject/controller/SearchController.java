package siwproject.siwproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import siwproject.siwproject.pg.AlbumService;
import siwproject.siwproject.pg.FotografoService;
import siwproject.siwproject.pg.HashTagService;

@Controller
public class SearchController {

    @Autowired
    private HashTagService hashTagService;
    @Autowired
    private FotografoService fotografoService;
    @Autowired
    private AlbumService albumService;

    @GetMapping("/search")
    public String search(Model model, @RequestParam("toSearch") String toSearch) {
        model.addAttribute("toSearch", toSearch);
        toSearch = toSearch.toLowerCase();
        if (hashTagService.existsByNome(toSearch)) {
            model.addAttribute("hashTag", hashTagService.hashTagPerNome(toSearch));
        }
        model.addAttribute("fotografi", fotografoService.fotografiPerNome(toSearch));
        model.addAttribute("album", albumService.albumPerNome(toSearch));
        return "results";
    }
}