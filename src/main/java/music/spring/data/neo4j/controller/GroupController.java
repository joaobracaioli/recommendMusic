package music.spring.data.neo4j.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import music.spring.data.neo4j.domain.Genre;
import music.spring.data.neo4j.domain.Group;
import music.spring.data.neo4j.domain.Track;
import music.spring.data.neo4j.domain.User;
import music.spring.data.neo4j.controller.Controller;
import music.spring.data.neo4j.services.GenreService;
import music.spring.data.neo4j.services.GroupService;
import music.spring.data.neo4j.services.Service;
import music.spring.data.neo4j.services.TrackService;
import music.spring.data.neo4j.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.echonest.api.v4.DynamicPlaylistParams;
import com.echonest.api.v4.DynamicPlaylistSession;
import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;
import com.echonest.api.v4.Playlist;
import com.echonest.api.v4.PlaylistParams;
import com.echonest.api.v4.Song;

@RestController
@RequestMapping(value = "/v1/group")
public class GroupController extends Controller<Group>{
	
	private static final Logger log = LoggerFactory.getLogger(GroupController.class);
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TrackService trackService;
	
	@Autowired
	private GenreService genreService;
	
	
	private static final String API_KEY = "TTJBBYCF2LTQ7RLTL";
	
	EchoNestAPI echoNest = new EchoNestAPI(API_KEY);
	
	@Override
	public Service<Group> getService() {
		// TODO Auto-generated method stub
		return groupService;
	}
	
	
	@RequestMapping(value="/groupCreate/{idUser}",method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Group> groupCreate(@PathVariable("idUser") String idUser, @RequestBody Group p){
		
		log.info("=============+++TESTE  21 ========================");
		Group group =  new Group();
		User userAdd = userService.findByIdSpotify(idUser);
		if (userAdd ==null) {
	           log.info("Group or User with id  / "+idUser+"not found");
	            return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
	        }
		
		group.setMembers(userAdd);
		group.setName(p.getName());
		group.setCaracteristicas(p.getCaracteristicas());
		 log.info("=============+++TESTE ========================");
		groupService.createOrUpdate(group);
		return new ResponseEntity<Group>(group, HttpStatus.OK);
	}



	@RequestMapping(value="/add/{idGroup}/{idUser}",method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Group> addGroup(@PathVariable("idGroup") Long idGroup, @PathVariable("idUser") Long idUser) throws EchoNestException{
		
		Group group = groupService.find(idGroup);
		User userAdd = userService.find(idUser);
		if (group==null || userAdd ==null) {
	           log.info("Group or User with id " + idGroup + " / "+userAdd+"not found");
	            return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
	        }
		
		group.setMembers(userAdd);
		List<Genre> genres = genreService.findByNameGroup(group.getName());
		group.setGenres(genres);
		
		
		
		DynamicPlaylistParams params = new DynamicPlaylistParams();
		params.addIDSpace("spotify-WW");
		params.setType(PlaylistParams.PlaylistType.GENRE_RADIO);
		
		for(Genre g : genres)
		params.addGenre(g.getName());
		params.addGenre("dance pop");
		  params.includeTracks();
		  params.setLimit(true);
		  Playlist playlist=echoNest.createStaticPlaylist(params);
		  
		  for ( Song song : playlist.getSongs()) {
			  com.echonest.api.v4.Track track=song.getTrack("spotify-WW");
			  group.setTracks(track);
			  log.info(track.getForeignID() + " " + song.getTitle()+ " by "+ song.getArtistName());
		  }
		  
		  
		groupService.createOrUpdate(group);
		return new ResponseEntity<Group>(group, HttpStatus.OK);
	}

	@RequestMapping(value="/genero/{idGroup}",method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Group> getGenero(@PathVariable("idGroup") Long idGroup) throws EchoNestException{
		
		Group groupAux = new Group();
		Group group = groupService.find(idGroup);

		
		if (group==null ) {
	           log.info("Não achei o GRUUUUPO "+idGroup+"not found");
	            return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
	     }
		
		log.info("Nommmme:   "+group.getName());
		
		groupAux.setName(group.getName());
		groupAux.setCaracteristicas(group.getCaracteristicas());
		groupAux.setMembers(group.getMembers());
		
		
		List<Genre> genres = genreService.findByNameGroup(group.getName());

		groupAux.setGenres(genres);
		
		
		PlaylistParams params = new PlaylistParams();
		params.addIDSpace("spotify-WW");
		params.setType(PlaylistParams.PlaylistType.GENRE_RADIO);
		
		for(Genre g : genres){
	  
		    if(g.getName() != null || !g.getName().isEmpty()){
		    	params.addGenre(g.getName());
		    	log.info("Adicionando Generos ------ :"+g.getName());
		    	}
		}
		  params.addIDSpace("id:spotify-WW");
		  params.setMinEnergy(.6f);
		  params.setMinDanceability(.6f);
          params.includeAudioSummary();
		  params.includeTracks();
		  params.setResults(10);

		  //params.setLimit(true);
		  echoNest.setTraceSends(true);
		  
		  Playlist playlist = echoNest.createStaticPlaylist(params);
		  try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  for ( Song song : playlist.getSongs()) {
			  log.info(song.getTitle());
			  log.info(song.getArtistName());
			  com.echonest.api.v4.Track track = song.getTrack("spotify-WW");
			  group.setTracks(track);
			 // log.info(track.getForeignID() + " " + song.getTitle() + " by " + song.getArtistName());
		  }
		  
		  
		groupService.createOrUpdate(groupAux);
		return new ResponseEntity<Group>(groupAux, HttpStatus.OK);
	}



}
