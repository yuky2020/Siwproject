package siwproject.siwproject.client;
import java.net.URI;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import siwproject.siwproject.model.Fotografo;
public class RestClientUtil {
    public void getFotografoByIdDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/Fotografo/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Fotografo> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Fotografo.class, 1);
        Fotografo Fotografo = responseEntity.getBody();
        System.out.println("Id:"+Fotografo.getId()+", Title:"+Fotografo.getNome()
                 +", Category:"+"cristianogay");      
    }
    public void getAllFotografosDemo() {
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/Fotografos";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Fotografo[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Fotografo[].class);
        Fotografo[] Fotografos = responseEntity.getBody();
        for(Fotografo Fotografo : Fotografos) {
              System.out.println("Id:"+Fotografo.getId()+", Title:"+Fotografo.getNome()
              );
        }
    }
    public void addFotografoDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/Fotografo";
	Fotografo objFotografo = new Fotografo();
	objFotografo.setNome("Spring REST Security using Hibernate");
        HttpEntity<Fotografo> requestEntity = new HttpEntity<Fotografo>(objFotografo, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());    	
    }
    public void updateFotografoDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/Fotografo";
	Fotografo objFotografo = new Fotografo();
	objFotografo.setId(1);
	objFotografo.setNome("Update:Java Concurrency");
        HttpEntity<Fotografo> requestEntity = new HttpEntity<Fotografo>(objFotografo, headers);
        restTemplate.put(url, requestEntity);
    }
    public void deleteFotografoDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/Fotografo/{id}";
        HttpEntity<Fotografo> requestEntity = new HttpEntity<Fotografo>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);        
    }
    public static void main(String args[]) {
    	RestClientUtil util = new RestClientUtil();
        //util.getFotografoByIdDemo();
    	//util.addFotografoDemo();
    	//util.updateFotografoDemo();
    	//util.deleteFotografoDemo();
    	util.getAllFotografosDemo();    	
    }    
} 