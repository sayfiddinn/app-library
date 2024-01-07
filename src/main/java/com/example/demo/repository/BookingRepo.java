package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Booking;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepo extends JpaRepository<Booking, UUID> {
    Optional<Booking> findByUserAndBookAndBronDate(User user, Book book, LocalDate bronDate);

    @Query(nativeQuery = true, value = "select * from booking b where b.is_success=false and b.user_id=?1")
    List<Booking> newHistory(UUID user_id);

    @Query(nativeQuery = true, value = "select * from booking b where b.is_success=true and b.user_id=?1")
    List<Booking> oldHistory(UUID user_id);

    @Query(nativeQuery = true, value = "select * from booking b where b.is_success=false and b.deadline_date<=:date")
    List<Booking> deadlineDateBron(@Param("date") LocalDate date);

    List<Booking> findByUser(User user);

    List<Booking> findByBook(Book book);
}
