package com.grupo5.microservicoresena.repositorios;

import com.grupo5.microservicoresena.entidades.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,String> {

    List<Review> findAllByIdLibro(String idLibro);

    Review findByIdReview(String id);
}
