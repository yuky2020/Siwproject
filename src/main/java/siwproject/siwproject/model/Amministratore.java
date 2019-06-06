package siwproject.siwproject.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "findAllAmministratori", query = "Select a from Amministratore a")
public class Amministratore {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false, unique = true )
	private String username;
	@Column(nullable = false, unique = true )
	private String password;
	
	
    public Amministratore(String username ,String password) {
        this.username = username;
        this.password=password;

    }

    public boolean checkPwd(String actual){
        return username.equals(actual);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}

	