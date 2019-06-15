package siwproject.siwproject.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    @ManyToMany
    @JoinTable(name = "hashTag_foto", joinColumns = @JoinColumn(name = "hashTag_id"), inverseJoinColumns = @JoinColumn(name = "foto_id"))
    private List<Foto> foto;

    public HashTag() {
    }

    public HashTag(String nome) {
        this.nome = nome;
        this.foto = new ArrayList<>();
    }

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

    public void addFoto(Foto foto) {
        this.getFoto().add(foto);
    }

}