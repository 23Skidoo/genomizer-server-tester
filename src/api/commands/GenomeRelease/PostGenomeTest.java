package api.commands.GenomeRelease;

import api.commands.CommandTester;
import api.commands.File.FileTuple;
import api.commands.File.UploadCommandTest;
import api.commands.SuperTestCommand;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import communication.HTTPURLUpload;
import model.Debug;
import model.ErrorLogger;
import requests.AddGenomeReleaseRequest;
import responses.sysadmin.AddGenomeReleaseResponse;
import util.Constants;
import util.RequestException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class PostGenomeTest extends SuperTestCommand {
    private String[] files;
    private String species;
    private String version;
    private Gson gson;

    public PostGenomeTest(String ident, String[] files, String species, String version, boolean expectedResult) {
        super(ident, expectedResult);
        this.files = files;
        this.species = species;
        this.version = version;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new AddGenomeReleaseRequest(files, species, version),
                    CommandTester.token, Constants.JSON);

            String body = CommandTester.conn.getResponseBody();

            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = true;
                GsonBuilder builder = new GsonBuilder();
                gson = builder.create();

                AddGenomeReleaseResponse[] upload = gson.fromJson(body, AddGenomeReleaseResponse[].class);

                for(AddGenomeReleaseResponse file: upload){
                    FileTuple ft = new FileTuple();
                    ft.setUploadPath(file.URLupload);
                    ft.setName("test.fastq");
                    UploadCommandTest uploader = new UploadCommandTest("UPLOAD GENOME TEST " + ft.getName(), ft, true);
                    uploader.execute();
                    if (uploader.finalResult != uploader.expectedResult) {
                        ErrorLogger.log(uploader.toString());
                        super.finalResult = false;
                    }

                }
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);

        }
    }
}
