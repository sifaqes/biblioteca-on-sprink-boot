package es.ua.biblioteca.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    private String author;
    
    private String disc;
    
    private String refe;
    


    public Book() {
    }
    
    public Book(long id, String title, String author, String disc, String refe, String impr ) {
    	this.id = id;
        this.title = title;
        this.author = author;
        // new Elements
        this.disc = disc;
        this.refe = refe;

        
    }

    public Book(Long id, String title, String author, String disc, String refe, String impr ) {
    	this.id = id;
        this.title = title;
        this.author = author;
        // new Elements
        this.disc = disc;
        this.refe = refe;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.title != other.title) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        // New Eliments
        if (!Objects.equals(this.refe, other.refe)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {

        var builder = new StringBuilder();
        builder.append("Book{id=").append(id).append(", title=")
                .append(title).append(", author=")
                .append(author).append(", disc=").append("disc").append(", refe=")
                .append("refe").append("}");

        return builder.toString();
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
    
	
	// new Eliments
	public void setDisc(String disc) {
		this.disc = disc;
	}
	
	public String getDisc() {
		return disc;
	}
	
	public void setRefe(String refe) {
		this.refe = refe;
	}
	
	public String getRefe() {
		return refe;
	}
	
}