package music.spring.data.neo4j.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import music.spring.data.neo4j.domain.Track;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

public interface TrackRepository extends GraphRepository<Track>{
	
	 @Query("MATCH (t:Truck)-[:CREATE_TO]-()-[:OF_TYPE]-(g:Genre {name: {genre} }) return t ")
	 List<Map<String,Object>> findByGenrePopularity(@Param("genre") String genre);

}
