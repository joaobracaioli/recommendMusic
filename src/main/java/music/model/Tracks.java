package music.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Tracks {

	@SerializedName("added_at")
	String added_at;
	
    @SerializedName("track")
    private Track track;

    final public String getAdded_at() {
		return added_at;
	}

	final public void setAdded_at(String added_at) {
		this.added_at = added_at;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

    
}
