package music.spring.data.neo4j.controller;

import java.util.List;
import java.util.Map;

import music.spring.data.neo4j.domain.User;
import music.spring.data.neo4j.services.Service;
import music.spring.data.neo4j.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/v1/user")
public class UserController extends Controller<User> {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	
	@Autowired
	private UserService userService;

	@Override
	public Service<User> getService() {
		// TODO Auto-generated method stub
		return userService;
	}
	
	
	@RequestMapping(value="/teste",method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<List<Map<String, Object>>> teste(@RequestBody User email){
		
		log.info("list por email "+email.getEmail());
		
		List<User> list = userService.findByEmail(email.getEmail());
		 
				 
		log.info("Saiu isso no list"+list.toString());
		
		return new ResponseEntity< List<Map<String,Object>>>(HttpStatus.OK);
	}
	
	
}
