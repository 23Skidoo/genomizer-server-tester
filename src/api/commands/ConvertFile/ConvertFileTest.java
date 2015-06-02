package api.commands.ConvertFile;

import api.commands.CommandTester;
import api.commands.ConvertTests;
import api.commands.FileTests;
import api.commands.SuperTestCommand;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.ErrorLogger;
import requests.ConvertFileRequest;
import responses.ResponseParser;
import responses.sysadmin.AddGenomeReleaseResponse;
import util.Constants;
import util.FileData;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-27.
 */
public class ConvertFileTest extends SuperTestCommand {
    private String format;
    private Gson gson;

    public ConvertFileTest(String ident, String format, boolean expectedResult) {
        super(ident, expectedResult);
        this.format = format;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new ConvertFileRequest(CommandTester.fileID.getString(), format),
                    CommandTester.token, Constants.JSON);

            if (CommandTester.conn.getResponseCode() == 200){
                super.finalResult = true;
                GsonBuilder builder = new GsonBuilder();
                gson = builder.create();

                FileData data = gson.fromJson(CommandTester.conn.getResponseBody(), FileData.class);
                ConvertTests.convertedFile.setString(data.id);
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
