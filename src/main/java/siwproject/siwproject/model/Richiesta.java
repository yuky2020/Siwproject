package siwproject.siwproject.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.joda.time.DateTime;

@Entity
public class Richiesta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDateTime creazione;
    private String creazioneString;
    @OneToOne(fetch = FetchType.EAGER)
    private Dettagli dettagli;
    @ManyToMany
    private List<Foto> foto;

    public Richiesta() {
    }

    public Richiesta(List<Foto> foto, Dettagli dettagli) {
        this.foto = foto;
        this.dettagli = dettagli;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.creazione = LocalDateTime.now();
        this.creazioneString = creazione.format(formatter);
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

    public LocalDateTime getCreazione() {
        return creazione;
    }

    public void setCreazione(LocalDateTime creazione) {
        this.creazione = creazione;
    }

    public String getCreazioneString() {
        return creazioneString;
    }

    public void setCreazioneString(String creazioneString) {
        this.creazioneString = creazioneString;
    }

}