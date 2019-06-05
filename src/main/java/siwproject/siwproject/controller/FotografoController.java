package siwproject.siwproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import siwproject.siwproject.model.*;
import siwproject.siwproject.pg.FotografoServices;
 

@WebServlet("/controllerFotografo")
public class FotografoController extends HttpServlet{
	@Autowired
	private FotografoServices fotografoServices;

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String nextPage;

		if(!nome.equals("")) {
			if(nome.length() > 16){
				req.setAttribute("longNameError", "Sei un deficente diocane");
				nextPage = "/newFotografo";
			}else{
				Fotografo f = new Fotografo(nome);
				List<Fotografo> fotografi = fotografoServices.getAllFotografos();
				if(!fotografi.contains(f)){
					fotografoServices.addFotografo(f);
					
					nextPage = "/mostraFotografi";
				}else{
					req.setAttribute("duplicatedName", "*Nome utente già in uso");
					nextPage = "/newFotografo";
				}
			}
		}else {
			req.setAttribute("voidName", "*Campo obbligatorio");
			nextPage = "/newFotografo";
		}
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(nextPage);
		rd.forward(req, resp);
		return;
	}
}
