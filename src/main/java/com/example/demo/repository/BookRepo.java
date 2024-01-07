package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepo extends JpaRepository<Book, UUID> {
    @Query(nativeQuery = true, value = "select * from book b where b.name ilike '%' || :name || '%'")
    List<Book> booksByName(@Param("name") String name);

    @Query(nativeQuery = true, value = "select * from book b where b.author ilike '%' || :author || '%'")
    List<Book> booksByAuthor(@Param("author") String author);
}
