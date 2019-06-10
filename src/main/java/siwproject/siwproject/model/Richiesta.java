package siwproject.siwproject.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Richiesta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Dettagli dettagli;
    @OneToMany
    private List<Foto> foto;

    public Richiesta() {
    }

    public Richiesta(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Dettagli getDettagli() {
        return dettagli;
    }

    public void setDettagli(Dettagli dettagli) {
        this.dettagli = dettagli;
    }

    public List<Foto> getFoto() {
        return foto;
    }

    public void setFoto(List<Foto> foto) {
        this.foto = foto;
    }

}