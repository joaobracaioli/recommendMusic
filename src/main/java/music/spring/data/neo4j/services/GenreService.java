package music.spring.data.neo4j.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

import music.spring.data.neo4j.domain.Genre;
import music.spring.data.neo4j.repositories.GenreRepository;

@Service
public class GenreService extends GenericService<Genre> implements GenreInterfaceService{

	@Autowired
	private GenreRepository genreRepository;
	
	
	
	@Override
	public GraphRepository<Genre> getRepository() {
		// TODO Auto-generated method stub
		return genreRepository;
	}
	
	public Genre  findByName(String name){
		
		return genreRepository.findByName(name);
	}

}
