package siwproject.siwproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import siwproject.siwproject.model.*;
import siwproject.siwproject.pg.*;

@Controller
@RequestMapping("user")
public class FotografoServiceController {
	@Autowired
	private FotografoServices FotografoServices;

	@RequestMapping(value = { "/paginaFotograf/{id}" }, method = RequestMethod.GET)
     public String ControllerMostraFotografo(@PathVariable("id") Integer id, HttpServletRequest request) {
		Fotografo fotografo = FotografoServices.getFotografoById(id);
		request.setAttribute("fotografo", fotografo);
		return "/paginaFotografo";
	}
	@GetMapping("/Fotografos")
	public ResponseEntity<List<Fotografo>> getAllFotografos() {
		List<Fotografo> list = FotografoServices.getAllFotografos();
		return new ResponseEntity<List<Fotografo>>(list, HttpStatus.OK);
	}
	@PostMapping("/fotografo")
	public ResponseEntity<Void> addFotografo(@RequestBody Fotografo fotografo, UriComponentsBuilder builder) {
                boolean flag = FotografoServices.addFotografo(fotografo);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/fotografo/{id}").buildAndExpand(fotografo.getId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("/fotografo")
	public ResponseEntity<Fotografo> updateFotografo(@RequestBody Fotografo fotografo) {
		FotografoServices.updateFotografo(fotografo);
		return new ResponseEntity<Fotografo>(fotografo, HttpStatus.OK);
	}
	@DeleteMapping("/fotografo/{id}")
	public ResponseEntity<Void> deleteFotografo(@PathVariable("id") Integer id) {
		FotografoServices.deleteFotografo(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 
