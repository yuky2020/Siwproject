package siwproject.siwproject.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQuery(name = "findAllAlbum", query = "Select a From Album a")
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nome;
	private LocalDateTime creazione;
	@OneToMany(mappedBy = "album")
	private List<Foto> foto;
	@ManyToOne
	private Fotografo fotografo;
	@OneToOne
	private Foto last;

	public Album() {
	}

	public Album(String nomeAlbum, Fotografo fotografo) {
		this.nome = nomeAlbum;
		this.fotografo = fotografo;
		this.foto = new ArrayList<Foto>();
		this.creazione = LocalDateTime.now();
	}

	public Album(String nome) {
		this.nome = nome;
		this.foto = new ArrayList<>();
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

	public Fotografo getFotografo() {
		return fotografo;
	}

	public void setFotografo(Fotografo fotografo) {
		this.fotografo = fotografo;
	}

	@Override
	public boolean equals(Object obj) {
		Album that = (Album) obj;
		return this.nome.equals(that.nome) && this.fotografo.equals(that.fotografo);
	}

	public void addFoto(Foto foto) {
		this.foto.add(foto);
	}

	public LocalDateTime getCreazione() {
		return creazione;
	}

	public void setCreazione(LocalDateTime creazione) {
		this.creazione = creazione;
	}

	public Foto getLast() {
		return last;
	}

	public void setLast(Foto last) {
		this.last = last;
	}

}
