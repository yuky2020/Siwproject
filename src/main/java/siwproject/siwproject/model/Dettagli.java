package siwproject.siwproject.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Dettagli {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private String cognome;
    private String cellulare;
    private String codicePay;

    public Dettagli() {
    }

    public Dettagli(String nome, String cognome, String cellulare, String codicePay) {
        this.nome = nome;
        this.cognome = cognome;
        this.cellulare = cellulare;
        this.codicePay = codicePay;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public String getCodicePay() {
        return codicePay;
    }

    public void setCodicePay(String codicePay) {
        this.codicePay = codicePay;
    }

}