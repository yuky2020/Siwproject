package siwproject.siwproject.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "findAll", query = "Select f from Fotografo f")
public class Fotografo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false, unique = true)
	private String nome;
	private String picUrl;
	@OneToMany
	private List<Foto> foto;
	@OneToMany
	private List<Album> album;

	public Fotografo() {
	}

	public Fotografo(String nome) {
		this.nome = nome;
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

	public List<Foto> getFoto() {
		return foto;
	}

	public void setFoto(List<Foto> foto) {
		this.foto = foto;
	}

	public List<Album> getAlbum() {
		return album;
	}

	public void setAlbum(List<Album> album) {
		this.album = album;
	}

	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String url) {
		this.picUrl = url;
	}

	@Override
	public boolean equals(Object obj) {
		Fotografo that = (Fotografo) obj;
		return this.nome.equals(that.nome);
	}
}
