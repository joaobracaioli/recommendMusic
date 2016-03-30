package music.spring.data.neo4j.repositories;

import java.util.Collection;
import java.util.List;

import music.spring.data.neo4j.domain.User;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GraphRepository<User> {

	
	List<User> findByName(String name);
	

	List<User> findByEmail(String email);
	
	@Query("MATCH (u:User) WHERE u.id_spotify={id_spotify} return u")
	User findbyIdspotify(@Param("id_spotify") String id_spotify);
	


}
