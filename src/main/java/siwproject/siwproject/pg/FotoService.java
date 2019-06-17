package siwproject.siwproject.pg;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siwproject.siwproject.client.AmazonClient;
import siwproject.siwproject.model.Foto;
import siwproject.siwproject.model.HashTag;
import siwproject.siwproject.repository.FotoRepository;

@Service
public class FotoService {
    @Autowired
    private FotoRepository fotoRepository;
    @Autowired
    private AmazonClient amazonClient;

    @Transactional
    public Foto fotoPerId(long id) {
        return fotoRepository.findById(id);
    }

    @Transactional
    public List<Foto> tutte() {
        return this.fotoRepository.findAll();
    }

    @Transactional
    public void inserisci(Foto f) {
        f.getFotografo().addFoto(f);
        f.getAlbum().addFoto(f);
        this.fotoRepository.save(f);
    }

    @Transactional
    public List<Foto> ultime30() {
        return fotoRepository.last30Foto();
    }

    @Transactional
    public List<Foto> lastNByIdFotografo(long id, int n) {
        return fotoRepository.last5ByFotografoId(id, n);
    }

    @Transactional
    public void cancella(long id) {
        Foto foto = fotoRepository.findById(id);
        List<HashTag> hashs = foto.getHash();
        for (HashTag hash : hashs)
            hash.removeFoto(foto);
        fotoRepository.delete(foto);
        amazonClient.deleteFileFromS3Bucket(foto.getUrl());
    }

}