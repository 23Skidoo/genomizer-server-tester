package api.commands.File;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class FileTuple {
    private String id;
    private String name;
    private String type;
    private String metaData;
    private String author;
    private String grVersion;
    private String uploadPath;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public FileTuple(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGrVersion() {
        return grVersion;
    }

    public void setGrVersion(String grVersion) {
        this.grVersion = grVersion;
    }
}
