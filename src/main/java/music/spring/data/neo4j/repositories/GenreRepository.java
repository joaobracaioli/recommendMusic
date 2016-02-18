package music.spring.data.neo4j.repositories;

import java.util.List;

import music.spring.data.neo4j.domain.Genre;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface GenreRepository extends GraphRepository<Genre>{

	Genre findByName(String name);
}
