package requests;

/**
 * Created by c10mjn on 2015-05-27.
 */
public class UpdateFileRequest extends Request {

    public UpdateFileRequest(String fileID) {
        super("updatefilerequest", "/file/" + fileID, "PUT");
    }
}
