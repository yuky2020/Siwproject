package siwproject.siwproject.pg;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siwproject.siwproject.model.Album;
@Service
public class AlbumService   {
    @Autowired
    private AlbumRepository rep;
    
    public List<Album> getAlbum() {
        List<Album> list = new ArrayList<>();
        rep.findAll().forEach(a -> list.add(a));
        return list;
    }

    
    public Album getAlbumById(long id) {
        Album a = rep.findById(id);
        return a;
    }
    
}