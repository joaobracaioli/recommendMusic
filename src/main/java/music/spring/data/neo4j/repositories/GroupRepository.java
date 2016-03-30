package music.spring.data.neo4j.repositories;

import java.util.List;
import java.util.Map;

import music.spring.data.neo4j.domain.Group;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends GraphRepository<Group>{


	
	/*
	 * MATCH (g:Genre)-[:OF_TYPE]->(a:Artist) 
		WITH g,count(a) as rels, collect(a) as artists
		WHERE rels > 2
		RETURN g
	 * 
	 * 
	 * MATCH (gr:Group{id:52})
	   MERGE (g:Genre)-[:OF_TYPE]->(a:Artist)
		WITH g,count(a) as rels, collect(a) as artists
		WHERE rels > 2
		RETURN g
		
		MATCH (gr:Group) where gr.name = "tes222"
		MERGE (g:Genre)-[:OF_TYPE]->(a:Artist)
		WITH g,count(a) as rels, collect(a) as artists
		WHERE rels >  2 
		RETURN g
	 */
}
