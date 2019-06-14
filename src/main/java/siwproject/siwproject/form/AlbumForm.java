package siwproject.siwproject.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class AlbumForm {
    private String nomeFotografo;
    private String nomeAlbum;
    private List<MultipartFile> files;
    private String hashTagList;

    public AlbumForm() {
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

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getHashTagList() {
        return hashTagList;
    }

    public void setHashTagList(String hashTagsList) {
        this.hashTagList = hashTagsList;
    }

}