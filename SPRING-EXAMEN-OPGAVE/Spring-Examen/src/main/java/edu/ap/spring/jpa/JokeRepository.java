package edu.ap.spring.jpa;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface JokeRepository extends CrudRepository<Joke, Long> {

}