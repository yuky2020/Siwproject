package siwproject.siwproject.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

@Entity
@NamedQuery(name = "findAllAlbum", query = "Select a From Album a")
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nome;
	@OneToMany
	private Map<String, Foto> foto;
	@ManyToOne
	private Fotografo fotografo;

	public Album() {
	}

	public Album(String nome) {
		this.nome = nome;
		this.foto = new HashMap<String, Foto>();
	}

	/* METODI GETTER E SETTER */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Map<String, Foto> getFoto() {
		return foto;
	}

	public void setFoto(Map<String, Foto> foto) {
		this.foto = foto;
	}

	public Fotografo getFotografo() {
		return fotografo;
	}

	public void setFotografo(Fotografo fotografo) {
		this.fotografo = fotografo;
	}

}
