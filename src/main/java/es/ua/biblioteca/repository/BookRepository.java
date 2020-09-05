package es.ua.biblioteca.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.ua.biblioteca.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

}