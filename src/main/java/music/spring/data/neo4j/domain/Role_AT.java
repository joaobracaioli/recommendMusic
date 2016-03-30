package music.spring.data.neo4j.domain;

import java.util.Date;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;


@JsonIdentityInfo(generator=JSOGGenerator.class)
@RelationshipEntity(type = "CREATE_TO")
public class Role_AT {
	
	
	 @GraphId Long id;
	 
	 @StartNode
	 private Track truck;
	 
	 @EndNode
	 private Artist artist;
	 
	 private String date_salve;

	public Role_AT() {
	
	}

	public Track getTruck() {
		return truck;
	}

	public void setTruck(Track truck) {
		this.truck = truck;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public String getDate_salve() {
		return date_salve;
	}

	public void setDate_salve(String date_salve) {
		this.date_salve = date_salve;
	}
	 
	 

}
