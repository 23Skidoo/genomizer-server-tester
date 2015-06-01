package api.commands;

import communication.Connection;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-26.
 */
public abstract class SuperTestCommand  {
    public String ident;
    public boolean expectedResult;
    public boolean finalResult;

    public SuperTestCommand(String ident, boolean expectedResult){
        this.ident = ident;
        this.expectedResult = expectedResult;
        this.finalResult = false;

    }

    public abstract void execute();

    @Override
    public String toString(){
        return ident;
    }
}
