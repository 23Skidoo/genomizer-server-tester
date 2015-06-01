package requests;

/**
 * Created by c10mjn on 2015-05-27.
 */
public class ConvertFileRequest extends Request{
    private String fileid;
    private String toformat;

    public ConvertFileRequest(String fileID, String format) {
        super("convertfile", "/convertfile", "PUT");
        this.fileid = fileID;
        this.toformat = format;
    }
}
