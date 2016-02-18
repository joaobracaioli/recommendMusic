package music.spring.data.neo4j.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Truck  {
	
	@GraphId Long id;
	@Property(name="name")
	private String title;
	
	@Relationship(type="CREATE_TO", direction = Relationship.INCOMING)
	List<Role_AT> roles = new ArrayList<Role_AT>();
	
	private Double danceability;
	private Double energy;
	private Double liveness;
	private Double speechiness;
	private Double duration;
	private String foreign;
	private Double loudness;
	private int popularity;
	

	public String getTitle() {
		return title;
	}
	public void setTitle(String name) {
		this.title = name;
	}

	public List<Role_AT> getRoles() {
		return roles;
	}
	public void setRoles(Role_AT role) {
		this.roles.add(role);
	}
	public Double getDanceability() {
		return danceability;
	}
	public void setDanceability(Double danceability) {
		this.danceability = danceability;
	}
	public Double getEnergy() {
		return energy;
	}
	public void setEnergy(Double energy) {
		this.energy = energy;
	}
	public Double getLiveness() {
		return liveness;
	}
	public void setLiveness(Double liveness) {
		this.liveness = liveness;
	}
	public Double getSpeechiness() {
		return speechiness;
	}
	public void setSpeechiness(Double speechiness) {
		this.speechiness = speechiness;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	public String getForeign() {
		return foreign;
	}
	public void setForeign(String foreign) {
		this.foreign = foreign;
	}
	
	public int getPopularity() {
		return popularity;
	}
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
	
	public Double getLoudness() {
		return loudness;
	}
	public void setLoudness(Double loudness) {
		this.loudness = loudness;
	}
	@Override
	public String toString() {
		return "Truck [title=" + title 
				+ ", danceability=" + danceability + ", energy=" + energy
				+ ", liveness=" + liveness + ", speechiness=" + speechiness
				+ ", duration=" + duration + ", foreign=" + foreign + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((foreign == null) ? 0 : foreign.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Truck other = (Truck) obj;
		if (foreign == null) {
			if (other.foreign != null)
				return false;
		} else if (!foreign.equals(other.foreign))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	

}
