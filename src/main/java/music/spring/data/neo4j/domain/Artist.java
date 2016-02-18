package music.spring.data.neo4j.domain;

import java.util.ArrayList;
import java.util.List;

import music.spring.data.neo4j.domain.Role;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.RelationshipEntity;

@NodeEntity
public class Artist {

	@GraphId Long id;
	
	private String name;
	
	private Double familiarity;
	private Double hotttnesss;
	private String foreignID;
	
	
	@Relationship(type="OF_TYPE", direction = Relationship.INCOMING)
	List<Role> roles=  new ArrayList<Role>();
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(Role role) {
		this.roles.add(role);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getFamiliarity() {
		return familiarity;
	}
	public void setFamiliarity(Double familiarity) {
		this.familiarity = familiarity;
	}
	public Double getHotttnesss() {
		return hotttnesss;
	}
	public void setHotttnesss(Double hotttnesss) {
		this.hotttnesss = hotttnesss;
	}

	
	public String getForeignID() {
		return foreignID;
	}
	public void setForeignID(String foreignID) {
		this.foreignID = foreignID;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((foreignID == null) ? 0 : foreignID.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Artist other = (Artist) obj;
		if (foreignID == null) {
			if (other.foreignID != null)
				return false;
		} else if (!foreignID.equals(other.foreignID))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Artist [name=" + name + ", familiarity=" + familiarity
				+ ", hotttnesss=" + hotttnesss + ", roles=" + roles + "]";
	}

	
	
	
	
	
	

}
