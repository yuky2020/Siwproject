package siwproject.siwproject.pg;

import java.util.List;



import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;



import siwproject.siwproject.model.Fotografo;

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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
