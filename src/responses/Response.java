package responses;

public class Response {
  //TODO Inte serialized, beh�ver inte transient CF
    public transient String responseName;

    public Response(String responseName) {
        this.responseName = responseName;
    }

}
