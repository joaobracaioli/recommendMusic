package music.spring.data.neo4j.repositories;

import java.util.List;
import java.util.Map;
import java.util.Set;

import music.spring.data.neo4j.domain.Genre;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends GraphRepository<Genre>{

	Genre findByName(String name);
	
	//trabalhar futuramente nessa query
	@Query("MATCH (gr:Group) where gr.name ={name} "
			+ "MERGE (g:Genre)-[:OF_TYPE]->(a:Artist)"
			+ "WITH g,count(a) as rels, collect(a) as artists"
			+ " WHERE rels > 5"
			+ " RETURN g")
	List<Genre> findBy(@Param("name") String nome);
}
