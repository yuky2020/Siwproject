package siwproject.siwproject.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FotoForm {
    private List<MultipartFile> files;
    private String nomeFotografo;
    private String nomeAlbum;
    private String hashTagList;

    public FotoForm() {
        this.files = new ArrayList<>();
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getNomeFotografo() {
        return nomeFotografo;
    }

    public void setNomeFotografo(String nomeFotografo) {
        this.nomeFotografo = nomeFotografo;
    }

    public String getNomeAlbum() {
        return nomeAlbum;
    }

    public void setNomeAlbum(String nomeAlbum) {
        this.nomeAlbum = nomeAlbum;
    }

    public String getHashTagList() {
        return hashTagList;
    }

    public void setHashTagList(String hashTagList) {
        this.hashTagList = hashTagList;
    }

}