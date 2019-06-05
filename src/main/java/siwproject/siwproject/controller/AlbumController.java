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

import siwproject.siwproject.model.*;
import siwproject.siwproject.pg.Database;

@WebServlet("/albumController")
public class AlbumController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String nextPage;
		HttpSession session = req.getSession();
		if(!nome.equals("")) {
			Album a = new Album(nome);
			Fotografo f = (Fotografo) session.getAttribute("fotografo");
			a.setFotografo(f);
			
			nextPage = "/paginaFotografo.jsp?id="+f.getId();
		}else {
			nextPage = "/newAlbum.jsp";
			req.setAttribute("voidName", "*Campo obbligatorio");
		}
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(nextPage);
		rd.forward(req, resp);
		return;
	}
}
