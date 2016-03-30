package music.spring.data.neo4j.services;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import music.spring.data.neo4j.domain.Track;
import music.spring.data.neo4j.repositories.TrackRepository;


@Service("trackService")
public class TrackService extends GenericService<Track> implements TrackInterfaceService {

	@Autowired
	private TrackRepository trackRepository;
	
	
	@Override
	public GraphRepository<Track> getRepository() {
		// TODO Auto-generated method stub
		return trackRepository;
	}
	
	public List<Map<String,Object>>findbyGenre(String genre){
		
		
		return trackRepository.findByGenrePopularity(genre);
	}

}
