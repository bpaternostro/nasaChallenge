package nasa.challenge;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Rover implements Serializable {

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("landing_date")
	@Expose
	private String landingDate;
	@SerializedName("launch_date")
	@Expose
	private String launchDate;
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("max_sol")
	@Expose
	private Integer maxSol;
	@SerializedName("max_date")
	@Expose
	private String maxDate;
	@SerializedName("total_photos")
	@Expose
	private Integer totalPhotos;
	@SerializedName("cameras")
	@Expose
	private List<Camera_> cameras = null;
	private final static long serialVersionUID = 7976563420800781165L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLandingDate() {
		return landingDate;
	}

	public void setLandingDate(String landingDate) {
		this.landingDate = landingDate;
	}

	public String getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(String launchDate) {
		this.launchDate = launchDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getMaxSol() {
		return maxSol;
	}

	public void setMaxSol(Integer maxSol) {
		this.maxSol = maxSol;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	public Integer getTotalPhotos() {
		return totalPhotos;
	}

	public void setTotalPhotos(Integer totalPhotos) {
		this.totalPhotos = totalPhotos;
	}

	public List<Camera_> getCameras() {
		return cameras;
	}

	public void setCameras(List<Camera_> cameras) {
		this.cameras = cameras;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("name", name).append("landingDate", landingDate)
				.append("launchDate", launchDate).append("status", status).append("maxSol", maxSol)
				.append("maxDate", maxDate).append("totalPhotos", totalPhotos).append("cameras", cameras).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(cameras).append(maxSol).append(name).append(maxDate).append(totalPhotos)
				.append(id).append(launchDate).append(landingDate).append(status).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Rover) == false) {
			return false;
		}
		Rover rhs = ((Rover) other);
		return new EqualsBuilder().append(cameras, rhs.cameras).append(maxSol, rhs.maxSol).append(name, rhs.name)
				.append(maxDate, rhs.maxDate).append(totalPhotos, rhs.totalPhotos).append(id, rhs.id)
				.append(launchDate, rhs.launchDate).append(landingDate, rhs.landingDate).append(status, rhs.status)
				.isEquals();
	}

}