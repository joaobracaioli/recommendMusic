package music.spring.data.neo4j.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;


@JsonIdentityInfo(generator=JSOGGenerator.class)
@RelationshipEntity(type = "OF_TYPE")
public class Role {

	 @GraphId Long id;

	 @StartNode
	 private Genre genere;
	 @EndNode
	 private Artist artist;
	 
	 
	 private Double afinidade;
	 
	 public Role() {
	 
	 }
	 
	public Double getAfinidade() {
		return afinidade;
	}


	public void setAfinidade(Double afinidade) {
		this.afinidade = afinidade;
	}


	public Genre getGenere() {
		return genere;
	}

	public void setGenere(Genre genere) {
		this.genere = genere;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	 
	public Artist getArtist() {
		return artist;
	}



	 
	 
}
