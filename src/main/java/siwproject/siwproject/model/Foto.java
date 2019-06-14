package siwproject.siwproject.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Foto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String url;
	@ManyToOne
	private Fotografo fotografo;
	@ManyToOne(cascade = CascadeType.REMOVE)
	private Album album;
	private LocalDateTime creazione;

	public Foto() {

	}

	public Foto(String url, Fotografo fotografo, Album album) {
		this.url = url;
		this.fotografo = fotografo;
		this.album = album;
		this.creazione = LocalDateTime.now();
	}

	public Foto(String url) {
		this.url = url;
	}
	// Metodi getter e setter

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Fotografo getFotografo() {
		return fotografo;
	}

	public void setFotografo(Fotografo fotografo) {
		this.fotografo = fotografo;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public LocalDateTime getCreazione() {
		return creazione;
	}

	public void setCreazione(LocalDateTime creazione) {
		this.creazione = creazione;
	}

}
