package nasa.challenge;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Photo implements Serializable {

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("sol")
	@Expose
	private Integer sol;
	@SerializedName("camera")
	@Expose
	private Camera camera;
	@SerializedName("img_src")
	@Expose
	private String imgSrc;
	@SerializedName("earth_date")
	@Expose
	private String earthDate;
	@SerializedName("rover")
	@Expose
	private Rover rover;
	private final static long serialVersionUID = 5050145392392222988L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSol() {
		return sol;
	}

	public void setSol(Integer sol) {
		this.sol = sol;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getEarthDate() {
		return earthDate;
	}

	public void setEarthDate(String earthDate) {
		this.earthDate = earthDate;
	}

	public Rover getRover() {
		return rover;
	}

	public void setRover(Rover rover) {
		this.rover = rover;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("sol", sol).append("camera", camera)
				.append("imgSrc", imgSrc).append("earthDate", earthDate).append("rover", rover).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(sol).append(id).append(camera).append(earthDate).append(rover)
				.append(imgSrc).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Photo) == false) {
			return false;
		}
		Photo rhs = ((Photo) other);
		return new EqualsBuilder().append(sol, rhs.sol).append(id, rhs.id).append(camera, rhs.camera)
				.append(earthDate, rhs.earthDate).append(rover, rhs.rover).append(imgSrc, rhs.imgSrc).isEquals();
	}

}

/*
 * @Override public boolean equals(Object o) { if (this == o) { return true; }
 * if (o == null || getClass() != o.getClass()) { return false; } Photo photo =
 * (Photo) o; return id == photo.id && sol == photo.sol &&
 * earth_date.equals(photo.earth_date); }
 */
