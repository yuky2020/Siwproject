package siwproject.siwproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import siwproject.siwproject.client.AmazonClient;
import siwproject.siwproject.model.Foto;
import siwproject.siwproject.pg.FotoService;

@Controller
public class BucketController {
	private AmazonClient amazonClient;

	@Autowired
	FotoService fotoService;

	@Autowired
	BucketController(AmazonClient amazonClient) {
		this.amazonClient = amazonClient;
	}

	@PostMapping("/uploadFile")
	public String uploadFile(@RequestPart(value = "file") MultipartFile file, Model model) {

		String url1 = this.amazonClient.uploadFile(file);
		String url = "https://silph.s3.eu-west-3.amazonaws.com" + url1.substring(40);
		Foto foto = new Foto(url);
		fotoService.inserisci(foto);
		System.out.println(url);
		model.addAttribute("foto", foto);
		return "/galleria";
	}

	@DeleteMapping("/deleteFile")
	public String deleteFile(@RequestPart(value = "url") String fileUrl) {
		return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
	}

}
