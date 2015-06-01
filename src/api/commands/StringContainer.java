package api.commands;

/**
 * Created by c12slm on 2015-06-01.
 */
public class StringContainer {
    private String s;

    public StringContainer(String s){
        this.s = s;
    }

    public String getString() {
        return s;
    }

    @Override
    public String toString() {
        return s;
    }

    public void setString(String s) {
        this.s = s;
    }
}
