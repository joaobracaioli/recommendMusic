package music.spring.data.neo4j.controller;

import music.spring.data.neo4j.repositories.GroupRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController("group")
public class GroupController {
	
	private static final Logger log = LoggerFactory.getLogger(GroupController.class);
	
	@Autowired
	private GroupRepository groupRepository;
	
	@RequestMapping(value="/list",method = RequestMethod.GET, produces = "application/json")
	public String getGroupPage() {
		return "users";
	}

}
