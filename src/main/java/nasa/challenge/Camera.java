package nasa.challenge;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Camera implements Serializable {

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("rover_id")
	@Expose
	private Integer roverId;
	@SerializedName("full_name")
	@Expose
	private String fullName;
	private final static long serialVersionUID = -9173734209975190725L;

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

	public Integer getRoverId() {
		return roverId;
	}

	public void setRoverId(Integer roverId) {
		this.roverId = roverId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("name", name).append("roverId", roverId)
				.append("fullName", fullName).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(fullName).append(id).append(roverId).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Camera) == false) {
			return false;
		}
		Camera rhs = ((Camera) other);
		return new EqualsBuilder().append(name, rhs.name).append(fullName, rhs.fullName).append(id, rhs.id)
				.append(roverId, rhs.roverId).isEquals();
	}

}