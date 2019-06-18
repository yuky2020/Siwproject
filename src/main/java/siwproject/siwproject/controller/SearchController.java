package siwproject.siwproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import siwproject.siwproject.model.Album;
import siwproject.siwproject.model.Fotografo;
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
        if (toSearch.length() >= 3) {
            model.addAttribute("toSearch", toSearch);
            toSearch = toSearch.toLowerCase();
            if (hashTagService.existsByNome(toSearch)) {
                model.addAttribute("hashTag", hashTagService.hashTagPerNome(toSearch));
                model.addAttribute("results", true);
            }
            List<Fotografo> fotografi = fotografoService.fotografiPerNome(toSearch);
            if (fotografi.size() != 0) {
                model.addAttribute("fotografi", fotografi);
                model.addAttribute("results", true);
            }
            List<Album> albums = albumService.albumsPerNome(toSearch);
            if (albums.size() != 0) {
                model.addAttribute("albums", albums);
                model.addAttribute("results", true);
            }
            return "results";
        } else {
            model.addAttribute("errore", "Inserisci almeno 3 caratteri nel campo di ricerca");
            return "search";
        }
    }
}