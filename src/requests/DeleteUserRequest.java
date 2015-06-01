package requests;

/**
 * Created by ens11afk on 2015-05-28.
 */
public class DeleteUserRequest extends Request{

    public DeleteUserRequest(String User) {
        super("deleteuser", "/admin/user/" + User, "DELETE");
    }

}
