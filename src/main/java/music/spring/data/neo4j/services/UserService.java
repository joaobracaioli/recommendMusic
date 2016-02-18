package music.spring.data.neo4j.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

import music.spring.data.neo4j.domain.User;
import music.spring.data.neo4j.repositories.UserRepository;

@Service
public class UserService extends GenericService<User> implements UserInterfaceService {

	@Autowired
	private UserRepository userRepository;

	
	@Override
	public GraphRepository<User> getRepository() {

		return userRepository;
	}
	
	public List<User> findByName(String email){
		
		return userRepository.findByName(email);
	}
	
	public List<User> findByEmail(String email){
		
		return userRepository.findByEmail(email);
	}
	
}
