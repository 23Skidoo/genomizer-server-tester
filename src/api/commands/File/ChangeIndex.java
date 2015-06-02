package api.commands.File;

import api.commands.FileIndices;
import api.commands.SuperTestCommand;

/**
 * Created by ens11afk on 2015-06-02.
 */
public class ChangeIndex extends SuperTestCommand {

    public static String fileID = "";
    private String key;
    private int ind;
    private int checkSize;

    public ChangeIndex(String ident, String key, int ind, int checkIndex, boolean expectedResult) {
        super(ident, expectedResult);
        this.key = key;
        this.ind = ind;
        this.checkSize = checkIndex;
    }

    @Override
    public void execute() {

        super.finalResult = true;
        FileIndices fi = new FileIndices(key);

        if (checkSize >= 0){
            super.finalResult = checkSize == fi.getSize();
        }

        if (ind < fi.getSize()){
            fileID = fi.getFileID(ind);
        }
    }
}
