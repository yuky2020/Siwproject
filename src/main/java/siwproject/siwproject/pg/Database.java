package siwproject.siwproject.pg;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siwproject.*;
import siwproject.siwproject.model.Album;
import siwproject.siwproject.model.Foto;
import siwproject.siwproject.model.Fotografo;
import siwproject.siwproject.pg.*;
@EnableJpaRepositories
@Service
public class Database {
       public FotografoServices a= new FotografoServices();

		private static FotoRepository repFoto;
		private static AlbumRepository repAlbum;
		
		public void caricaFotografo(Fotografo f){
			a.addFotografo(f);
			
		}

		

		public  List<Fotografo> getAllFotografi() {
			return a.getAllFotografos();
			
		}

		public void getAllFoto(){
			repFoto.findAll();
		}

		public static void getallAlbum(){
			repAlbum.findAll();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
