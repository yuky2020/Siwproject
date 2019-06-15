package siwproject.siwproject.controller;

import java.io.FileNotFoundException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import siwproject.siwproject.client.AmazonClient;
import siwproject.siwproject.form.AlbumForm;
import siwproject.siwproject.model.Album;
import siwproject.siwproject.model.Foto;
import siwproject.siwproject.model.Fotografo;
import siwproject.siwproject.model.HashTag;
import siwproject.siwproject.pg.AlbumService;
import siwproject.siwproject.pg.FotoService;
import siwproject.siwproject.pg.FotografoService;
import siwproject.siwproject.pg.HashTagService;
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
	@Autowired
	private AmazonClient amazonClient;
	@Autowired
	private FotoService fotoService;
	@Autowired
	private HashTagService hashTagService;

	@RequestMapping("/aggiungiAlbum")
	public String aggiungiAlbum(Model model) {
		model.addAttribute("form", new AlbumForm());
		return "newAlbum";
	}

	@PostMapping("/album")
	private String inserisciAlbum(@Valid @ModelAttribute("form") AlbumForm form, Model model, BindingResult errors) {
		albumValidator.validate(form, errors);
		if (!errors.hasErrors()) {
			Fotografo fotografo = fotografoService.fotografoPerNome(form.getNomeFotografo());
			Album album = new Album(form.getNomeAlbum(), fotografo);
			List<HashTag> hashTags = hashTagService.parseHashTag(form.getHashTagList());
			albumService.inserisci(album);
			try {
				for (MultipartFile file : form.getFiles()) {
					String url = "https://s3.eu-west-3" + amazonClient.uploadFile(file).substring(20);
					Foto foto = new Foto(url, fotografo, album);
					hashTagService.linkTags(foto, hashTags);
					fotoService.inserisci(foto);
				}
			} catch (Exception e) {
				// Gestione di un bug di causa sconosciuta quando
				// si prova a caricare un album senza foto
				e.printStackTrace();
			}

			return "paginaAdmin";
		}
		return "newAlbum";
	}

	@GetMapping("/mostraAlbum")
	public String viewAlbums(Model model) {
		List<Album> albums = albumService.tutti();
		model.addAttribute("albums", albums);
		return "mostraAlbum";
	}

	@GetMapping("/paginaAlbum/{id}")
	public String wiewAlbum(Model model, @ModelAttribute("id") Long id) {
		model.addAttribute("album", albumService.albumPerId(id));
		return "paginaAlbum";
	}
}
