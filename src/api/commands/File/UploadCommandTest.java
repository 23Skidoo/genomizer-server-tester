package api.commands.File;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import communication.HTTPURLUpload;
import model.ErrorLogger;

import java.io.IOException;

/**
 * Created by c10mjn on 2015-05-27.
 */
public class UploadCommandTest extends SuperTestCommand {
    FileTuple ft;


    public UploadCommandTest(String ident, FileTuple ft, boolean expectedResult) {
        super(ident, expectedResult);
        this.ft = ft;
    }

    @Override
    public void execute() {


        HTTPURLUpload upload = new HTTPURLUpload(ft.getUploadPath(), ft.getName(), ft.getName());
        try {
            upload.sendFile(CommandTester.token);
            if (upload.getResponseCode() == 200) {
                super.finalResult = true;

            }
        } catch (IOException e) {
            if (super.expectedResult) ErrorLogger.log(e);

        }

    }
}
