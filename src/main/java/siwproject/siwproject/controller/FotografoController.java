package siwproject.siwproject.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import siwproject.siwproject.client.AmazonClient;
import siwproject.siwproject.model.Fotografo;
import siwproject.siwproject.pg.FotografoService;
import siwproject.siwproject.validator.FotografoValidator;

@Controller
public class FotografoController extends HttpServlet {
	@Autowired
	private FotografoService fotografoService;
	@Autowired
	private FotografoValidator fotografoValidator;
	@Autowired
	AmazonClient amazonClient;

	private static final long serialVersionUID = 1L;

	@RequestMapping("/aggiungiFotografo")
	public String aggiungiFotografo(Model model) {
		model.addAttribute("fotografo", new Fotografo());
		return "newFotografo";
	}

	@PostMapping("/fotografo")
	public String inserisciFotografo(@Valid @ModelAttribute("fotografo") Fotografo fotografo, Model model,
			BindingResult errors, @RequestPart("pic") MultipartFile pic) {
		this.fotografoValidator.validate(fotografo, errors);
		if (!errors.hasErrors()) {
			if (!pic.getOriginalFilename().equals("")) {
				fotografo.setPicUrl("https://s3.eu-west-3" + amazonClient.uploadFile(pic).substring(20));
			}
			fotografoService.inserisci(fotografo);
			return "paginaAdmin";
		} else {
			return "newFotografo";
		}

	}

	@GetMapping("/mostraFotografi")
	public String viewFotografi(Model model) {
		List<Fotografo> fotografi = fotografoService.tutti();
		model.addAttribute("fotografi", fotografi);
		return "mostraFotografi";
	}

	@GetMapping("/paginaFotografo/{id}")
	public String getPaginaFotografo(Model model, @PathVariable("id") Long id) {
		model.addAttribute("fotografo", fotografoService.fotografoPerId(id));
		return "paginaFotografo";
	}

	@GetMapping("cancellaFotografo/{id}")
	public String modifica(Model model, @ModelAttribute("id") Long id) {
		fotografoService.cancella(id);
		return "paginaAdmin";
	}

	@GetMapping("/listaFotografi")
	public String listaFotografi(Model model) {
		model.addAttribute("fotografi", fotografoService.tutti());
		return "listaFotografi";
	}

	@GetMapping("/modificaFotografo/{id}")
	public String modificaFotografo(Model model, @PathVariable("id") long id) {
		model.addAttribute("fotografo", fotografoService.fotografoPerId(id));
		return "modificaFotografo";
	}

	@PostMapping("/modifica")
	public String modifica(Model model, @ModelAttribute("fotografo") Fotografo fotografo,
			@RequestParam("newName") String newName, @RequestPart("newPic") MultipartFile newPic) {
		if (!newName.equals("")) {
			fotografo.setNome(newName);
		}
		try {
			String newUrl = "https://s3.eu-west-3" + amazonClient.uploadFile(newPic).substring(20);
			fotografo.setPicUrl(newUrl);

		} catch (Exception e) {

		}

		fotografoService.aggiorna(fotografo);
		return "listaFotografi";
	}

}
