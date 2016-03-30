package music.spring.data.neo4j.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

import music.spring.data.neo4j.domain.Group;
import music.spring.data.neo4j.domain.User;
import music.spring.data.neo4j.repositories.GroupRepository;
import music.spring.data.neo4j.repositories.UserRepository;


@Service("groupService")

public class GroupService extends GenericService<Group> implements GroupInterfaceService{

	@Autowired
	private GroupRepository groupRepository;
	

	@Override
	public GraphRepository<Group> getRepository() {
		// TODO Auto-generated method stub
		return groupRepository;
	}
	

}
