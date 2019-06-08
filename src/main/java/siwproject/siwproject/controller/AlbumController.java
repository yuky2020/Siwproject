package siwproject.siwproject.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import siwproject.siwproject.model.Album;
import siwproject.siwproject.pg.AlbumService;
import siwproject.siwproject.pg.FotografoService;
import siwproject.siwproject.validator.AlbumValidator;

@Controller
public class AlbumController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AlbumService albumService;
	@Autowired
	private AlbumValidator albumValidator;
	@Autowired
	private FotografoService fotografoService;

	@RequestMapping("/aggiungiAlbum")
	public String aggiungiAlbum(Model model) {
		model.addAttribute("newAlbum", new Album());
		return "albumForm";
	}

	@RequestMapping(value = "/album", method = RequestMethod.POST)
	private String inserisciAlbum(@Valid @ModelAttribute("album") Album album, Model model, BindingResult bindingResult,
			@RequestParam String nomeFotografo, @RequestParam String nomeAlbum) {
		album.setNome(nomeAlbum);
		album.setFotografo(this.fotografoService.fotografoPerNome(nomeFotografo));
		this.albumValidator.validate(album, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.albumService.inserisci(album);
			model.addAttribute("album success", "Album inserito con successo");
			return "paginaAdmin";
		} else {
			return "newAlbum";
		}
	}

	@RequestMapping(value = { "/mostraAlbum" }, method = RequestMethod.GET)
	public String viewAlbum(Model model) {
		List<Album> albums = albumService.tutti();
		model.addAttribute("albums", albums);
		return "paginaA";
	}
}
