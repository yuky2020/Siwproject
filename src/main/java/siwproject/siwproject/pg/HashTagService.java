package siwproject.siwproject.pg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siwproject.siwproject.model.Foto;
import siwproject.siwproject.model.HashTag;
import siwproject.siwproject.repository.HashTagRepository;

@Service
public class HashTagService {
    @Autowired
    private HashTagRepository hashTagRepository;

    @Transactional
    public void inserisci(HashTag hashTag) {
        hashTagRepository.save(hashTag);
    }

    @Transactional
    public HashTag hashTagPerNome(String nome) {
        return hashTagRepository.findByNome(nome);
    }

    @Transactional
    public boolean existsByNome(String nome) {
        return hashTagRepository.existsByNome(nome);
    }

    public List<HashTag> parseHashTag(String hashTagString) {
        List<HashTag> hashTags = new ArrayList<HashTag>();
        Scanner sc = new Scanner(hashTagString);
        sc.useDelimiter("\\s*#\\s*");
        while (sc.hasNext()) {
            String tag = sc.next();
            if (!this.existsByNome(tag)) {
                this.inserisci(new HashTag(tag));
            }
            hashTags.add(this.hashTagPerNome(tag));
        }
        sc.close();
        return hashTags;
    }

    public void linkTags(Foto foto, List<HashTag> HashTags) {
        for (HashTag hashTag : HashTags) {
            hashTag.addFoto(foto);
        }
    }
}