package siwproject.siwproject.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import siwproject.siwproject.model.*;
import siwproject.siwproject.pg.FotografoService;
import siwproject.siwproject.validator.FotografoValidator;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FotografoController extends HttpServlet {
	@Autowired
	private FotografoService fotografoService;
	@Autowired
	private FotografoValidator fotografoValidator;

	private static final long serialVersionUID = 1L;

	@RequestMapping("/aggiungiFotografo")
	public String aggiungiFotografo(Model model) {
		model.addAttribute("fotografo", new Fotografo());
		return "fotografoForm";
	}

	@RequestMapping(value = "/fotografo", method = RequestMethod.POST)
	public String inserisciFotografo(@Valid @ModelAttribute("fotografo") Fotografo fotografo, Model model,
			BindingResult bindingResult, @RequestParam String nome) {
		fotografo.setNome(nome);
		this.fotografoValidator.validate(fotografo, bindingResult);
		if (!bindingResult.hasErrors()) {
			fotografoService.inserisci(fotografo);
			model.addAttribute("fotografoSuccess", fotografo.getNome());
			return "paginaAdmin";
		} else {
			return "newFotografo";
		}

	}

	@RequestMapping(value = { "/mostraFotografi" }, method = RequestMethod.GET)
	public String viewFotografi(Model model) {
		List<Fotografo> fotografi = fotografoService.tutti();
		model.addAttribute("fotografi", fotografi);
		return "mostraFotografi";
	}

}
