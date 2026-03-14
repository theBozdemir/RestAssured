package NewProjects.EComm;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    private String userEmail;

    @JsonProperty("userPassword")  // maps to the JSON key "userPassword"
    private String password;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
