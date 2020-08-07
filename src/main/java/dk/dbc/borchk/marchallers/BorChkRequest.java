package dk.dbc.borchk.marchallers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BorChkRequest implements Serializable {

    private static final long serialVersionUID = 7588211809961700907L;
    private String serviceRequester;
    private String libraryCode;
    transient private String originalLibraryCode;
    private String userId;
    private String userPincode;

    public String getServiceRequester() {
        return serviceRequester;
    }

    public void setServiceRequester(String serviceRequester) {
        this.serviceRequester = serviceRequester;
    }

    public String getLibraryCode() {
        return libraryCode;
    }

    public void setLibraryCode(String libraryCode) {
        this.originalLibraryCode = libraryCode;
        this.libraryCode = normalizeAgencyId(libraryCode);
    }

    @JsonIgnore
    public String getOriginalLibraryCode() {
        return originalLibraryCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPincode() {
        return userPincode;
    }

    public void setUserPincode(String userPincode) {
        this.userPincode = userPincode;
    }

    @JsonIgnore
    private String normalizeAgencyId(final String agencyId) {
        return StringUtils.trim(agencyId).replaceAll("\\D+", "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BorChkRequest)) return false;
        BorChkRequest that = (BorChkRequest) o;
        return Objects.equals(serviceRequester, that.serviceRequester) &&
                Objects.equals(libraryCode, that.libraryCode) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userPincode, that.userPincode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceRequester, libraryCode, userId, userPincode);
    }

    @Override
    public String toString() {
        return "BorChkRequest{" +
                "serviceRequester='" + serviceRequester + '\'' +
                ", libraryCode='" + libraryCode + '\'' +
                ", originalLibraryCode='" + originalLibraryCode + '\'' +
                ", userId='" + userId + '\'' +
                ", userPincode='pin code removed'" +
                '}';
    }
}
