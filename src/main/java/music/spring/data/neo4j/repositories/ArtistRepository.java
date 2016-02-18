package music.spring.data.neo4j.repositories;

import music.spring.data.neo4j.domain.Artist;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface ArtistRepository extends GraphRepository<Artist>{
	
}
