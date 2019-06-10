package siwproject.siwproject.pg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /*
     * public static List<HashTag> parseHashTag(String hashTags) { List<HashTag>
     * toReturn = new ArrayList<HashTag>(); Scanner sc = new
     * Scanner(hashTags).useDelimiter("\\s*#\\s*"); while (sc.hasNext()) { String
     * nome = sc.next(); if (hashTagService.existsByNome(nome)) {
     * toReturn.add(hashTagService.hashTagPerNome(nome)); } else { toReturn.add(new
     * HashTag(nome)); } } sc.close(); return toReturn; }
     */

}