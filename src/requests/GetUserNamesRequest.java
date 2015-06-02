package requests;

/**
 * Created by ens11afk on 2015-06-02.
 */
public class GetUserNamesRequest extends Request {

    public GetUserNamesRequest() {
        super("getuserlist", "/admin/userlist", "GET");
    }
}