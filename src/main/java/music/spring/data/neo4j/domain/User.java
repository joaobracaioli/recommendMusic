package music.spring.data.neo4j.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


@NodeEntity
public class User {
	
	@GraphId Long id;
	private String name;
	@Index(unique=true)
	private String email;
	private String id_spotify;
	private String uri;
	
    @Relationship(type = "LISTENED_TO")
	private Set<Truck> trucks = new HashSet<>();
	
    
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId_spotify() {
		return id_spotify;
	}
	public void setId_spotify(String id_spotify) {
		this.id_spotify = id_spotify;
	}
	

	public Set<Truck> getTrucks() {
		return trucks;
	}

	public void setTrucks(Truck truck) {
		this.trucks.add(truck);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", id_spotify="
				+ id_spotify + ", trucks=" + trucks + "]";
	}


	
	
	

}
