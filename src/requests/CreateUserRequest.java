package requests;

/**
 * Created by ens11afk on 2015-05-28.
 */
public class CreateUserRequest extends Request{

    public String username;
    public String password;
    public String privileges;
    public String name;
    public String email;

    public CreateUserRequest(String username,
                             String password, String privileges, String name, String email) {
        super("createuser", "/admin/user", "POST");
        this.username = username;
        this.password = password;
        this.privileges = privileges;
        this.name = name;
        this.email = email;
    }

}
