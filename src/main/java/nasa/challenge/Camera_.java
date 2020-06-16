package nasa.challenge;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Camera_ implements Serializable {

	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("full_name")
	@Expose
	private String fullName;
	private final static long serialVersionUID = -5797773215232901666L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", name).append("fullName", fullName).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(fullName).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Camera_) == false) {
			return false;
		}
		Camera_ rhs = ((Camera_) other);
		return new EqualsBuilder().append(name, rhs.name).append(fullName, rhs.fullName).isEquals();
	}

}
