package music.spring.data.neo4j;


import music.util.ArrayAdapterFactory;
import music.model.Tracks;
import music.spring.data.neo4j.domain.Artist;
import music.spring.data.neo4j.domain.Genre;
import music.spring.data.neo4j.domain.Role;
import music.spring.data.neo4j.domain.Role_AT;
import music.spring.data.neo4j.domain.Track;
import music.spring.data.neo4j.domain.User;
import music.spring.data.neo4j.repositories.UserRepository;
import music.spring.data.neo4j.services.ArtistService;
import music.spring.data.neo4j.services.GenreService;
import music.spring.data.neo4j.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mh
 * @since 06.10.14
 */
@Configuration
@Import(MyNeo4jConfiguration.class)
@RestController("/")
public class Application extends WebMvcConfigurerAdapter {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	private static final String API_KEY = "TTJBBYCF2LTQ7RLTL";

	EchoNestAPI echoNest ;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }


    @Autowired
    UserService userService;


    @Autowired
    GenreService genreService;

    //METODO PARA TESTE GERAL
    @RequestMapping(path="/usuario")
    public String usuario (){


    	log.info("entrei aqui");
    	List<User> user;
		if((user = userService.findByEmail("joaobracaioli@gmail.com")).isEmpty())
			return "nao funcionou";

		return user.toString();
    }

    @RequestMapping(path = "/teste2", method = RequestMethod.POST, consumes="application/json")
    public String teste(@RequestBody String nome){
    	log.debug("entrei aqui");
    	log.info("entrei pra informar ");
    	log.info(nome);



    	conversor(nome);

    	return "ok   aeeeee ";

    }

    private void conversor(String caracteristicas){


    	log.info("=================  entrei para converter ===============");

		Gson gson = new GsonBuilder().registerTypeAdapterFactory(new music.util.ArrayAdapterFactory()).create();

		    JsonParser parser = new JsonParser();
		    JsonArray jArray = parser.parse(caracteristicas).getAsJsonArray();
			ArrayList<Tracks> listTrack = new ArrayList<Tracks>() ;

			//Cria usuario
			music.model.User u = gson.fromJson(jArray.get(jArray.size()-1), music.model.User.class);

			if(userService.findByEmail(u.getEmail()).isEmpty()){
			log.info("==========  Usuario não existente  ===============");
			// JSON transforma objetos em Truck
		    for(JsonElement obj : jArray){

		    	Tracks tck = gson.fromJson(obj, Tracks.class);
		    	listTrack.add(tck);
		    }


	    	log.info("==========  Terminei de converter ===============");


	    	log.info("================= ECHONEST / NEO4J =====================");

	        log.info(u.toString());

			//Cria usuario para base de dados
	    	User userNeo4j = new User();
	    	userNeo4j.setName(u.getDisplayName());


			if(u.getDisplayName() == null || u.getDisplayName().isEmpty()){
				userNeo4j.setName(u.getId());
			}

			userNeo4j.setName(u.getDisplayName());
			userNeo4j.setEmail(u.getEmail());
			userNeo4j.setId_spotify(u.getId());
			userNeo4j.setUri(u.getUri());

		    echoNest = new EchoNestAPI(API_KEY);


		    try {

			    //	String md5 = "07a096fd8880931695723d19b1a11611";
			    	for(Tracks tk : listTrack){
			    		if(tk.getTrack()!= null){
			    		//Cria Artista

			    		log.info("================= Recuperando musica na EchoNest =====================");

			    		String id = tk.getTrack().getUri();
				    	com.echonest.api.v4.Track trkAnal = echoNest.newTrackByID(id);

				    	com.echonest.api.v4.Artist art = echoNest.newArtistByName(trkAnal.getArtistName());


				    	Artist artNeo4j = new Artist();

				    	artNeo4j.setName(art.getName());
				 //isso corresponde a quanto o artiesta e conecido  fontes, incluindo menciona na web, menciona em blogs de música, resenhas de música, contagens de jogo, etc.
						artNeo4j.setFamiliarity(art.getFamiliarity());
			    		artNeo4j.setHotttnesss(art.getHotttnesss());
			    		artNeo4j.setForeignID(art.getID());

			    		//Genero
			    		 Genre g1;
			    		 log.info(" =========  Generos ======== ");
					      List<com.echonest.api.v4.Term> term = art.getTerms();

					       for (com.echonest.api.v4.Term t : term){


							if(  (g1 = genreService.findByName(t.getName()) )==null ){
					    		  g1 = new Genre();
					    		  g1.setName(t.getName());
							}

					    	   Role r = new Role();

					    	   r.setArtist(artNeo4j);
					       	   r.setAfinidade(t.getFrequency());
					       	   r.setGenere(g1);
					       	   //seta relacionamento
					       	   artNeo4j.setRoles(r);


					       }

					     //truck
					       log.info("================= Criando Musica =====================");
			    		Track tkNeo4j = new Track();
			    		tkNeo4j.setTitle(tk.getTrack().getName());
			    		tkNeo4j.setDanceability(trkAnal.getDanceability());
			    		tkNeo4j.setDuration(trkAnal.getDuration());
			    		tkNeo4j.setEnergy(trkAnal.getEnergy());
			    		tkNeo4j.setForeign(trkAnal.getForeignID());
			    		tkNeo4j.setLiveness(trkAnal.getLiveness());
			    		tkNeo4j.setLoudness(trkAnal.getLoudness());
			    		tkNeo4j.setPopularity(tk.getTrack().getPopularity());
			    		tkNeo4j.setSpeechiness(trkAnal.getSpeechiness());

			    		//relaciona Musica com artista
			    		Role_AT rel_music_art = new Role_AT();
			    		rel_music_art.setArtist(artNeo4j);
			    		rel_music_art.setTruck(tkNeo4j);
			    		rel_music_art.setDate_salve(tk.getAdded_at());

			    		//seta relacionamento
			    		tkNeo4j.setRoles(rel_music_art);

			    		userNeo4j.setTrucks(tkNeo4j);

			    		log.info("================= Informações coletadas =====================");

			    		Thread.sleep(32000);

			    		}


			    	}

				} catch (EchoNestException e) {
					log.info("==== ERRO ECHONEST ===== "+e.getMessage());

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					log.info("==== ERRRROOOOO ===== "+e.getMessage());
				}
			    //echoNest.showStats();
			  log.info("================= usuario salvo =====================");
		    	userService.createOrUpdate(userNeo4j);
		    	return;
			}
			 log.info("================= usuario já existente =====================");
    }



    @RequestMapping("/createArtist")
    public User create(){


    	//create user
    	User u = new User();

    	u.setEmail("joao@joao");
    	u.setName("joao teste");

    	//cria artista Los Hemanos
    	Artist a = new Artist();
    	//a.setFamiliarity("0,639");
    	//a.setHotttnesss("0,600");
    	a.setName("Los Hermanos");

    	//cria artista Paralamas
    	Artist paralamas = new Artist();
    	//paralamas.setFamiliarity("0.800");
    	//paralamas.setHotttnesss("0,70");
    	paralamas.setName("Paralamas do Sucesso");







    	//cria relacionamento dos generos e afinidade
    	Role r = new Role();
    	Role r2 = new Role();
    	Role r3 = new Role();

    	//cria generos

    	Genre g1 = new Genre();
    	g1.setName("Samba");

    	Genre g2 = new Genre();
    	g2.setName("Rock");

    	//seta valor de avinidade e relacionamento
    	//r3.setAfinidade("100");
    	r3.setArtist(paralamas);
    	r3.setGenere(g2);


    	r.setArtist(a);
    	//r.setAfinidade("06");
    	r.setGenere(g1);

    	r2.setArtist(a);
    	r2.setGenere(g2);
    	//r2.setAfinidade("100");

    	//seta relacionamento com artista
    	a.setRoles(r);
    	a.setRoles(r2);
    	paralamas.setRoles(r3);

    	//Cria musica
    	Track t1 = new Track();
    	t1.setTitle("A Flor");
    	t1.setDanceability(0.76222);
    	t1.setEnergy(0.34);
    	t1.setDuration(173.333);



    	Track t2 = new Track();
    	t2.setTitle("Vencedor");
    	t2.setDanceability(0.76929292);
    	t2.setEnergy(0.3241);
    	t2.setDuration(18.89333);
    	t2.setForeign("2");

    	Track t3 = new Track();
    	t3.setTitle("Meu erro");
    	t3.setDanceability(0.7699292);
    	t3.setEnergy(0.30484);
    	t3.setDuration(178.89333);
    	t3.setForeign("3");
    	//seta relacionamento musica artista
    	Role_AT rel_los_hermanos = new Role_AT();
    	rel_los_hermanos.setArtist(a);
    	rel_los_hermanos.setTruck(t1);
    	//rel_los_hermanos.setDate_salve(new java.util.Date());

    	Role_AT rel_los_hermanos2 = new Role_AT();
    	rel_los_hermanos2.setArtist(a);
    	rel_los_hermanos2.setTruck(t2);
    	//rel_los_hermanos2.setDate_salve(new java.util.Date());

    	Role_AT rel_paralamas = new Role_AT();
    	rel_paralamas.setArtist(paralamas);
    	rel_paralamas.setTruck(t3);
    	//rel_paralamas.setDate_salve(new java.util.Date());

    	//atribui relacionamento a musica
    	t1.setRoles(rel_los_hermanos);
    	t2.setRoles(rel_los_hermanos2);
    	t3.setRoles(rel_paralamas);

    	//seta musica para usuarios
    	u.setTrucks(t2);
    	u.setTrucks(t1);
    	u.setTrucks(t3);


    	//return artistService.createOrUpdate(a);
    	return userService.createOrUpdate(u);

    }

}
