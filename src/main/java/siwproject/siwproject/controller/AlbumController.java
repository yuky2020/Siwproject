package siwproject.siwproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import siwproject.siwproject.model.*;
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
			return "console";
		} else {
			return "albumForm";
		}
	}
}
