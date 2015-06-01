package responses;

import com.google.gson.annotations.Expose;

public class LoginResponse extends Response {

    @Expose
    public String token;

    @Expose
    public String role;


    public LoginResponse() {
        super("login");
        this.token = token;
        this.role = role;
    }
}
