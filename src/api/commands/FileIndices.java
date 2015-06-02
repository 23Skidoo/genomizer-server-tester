package api.commands;

import model.ErrorLogger;
import requests.RetrieveExperimentRequest;
import responses.ResponseParser;
import util.Constants;
import util.ExperimentData;
import util.FileData;
import util.RequestException;

import java.util.ArrayList;

/**
 * Created by ens11afk on 2015-06-02.
 */
public class FileIndices {


    private static ArrayList<String> fileID = new ArrayList<>();

    public FileIndices(String key){

        fileID = new ArrayList<>();

        try {
            CommandTester.conn.sendRequest(new RetrieveExperimentRequest(key),
                    CommandTester.token, Constants.TEXT_PLAIN);

            if (CommandTester.conn.getResponseCode() == 200) {

                ExperimentData ed = ResponseParser.parseRetrieveExp(CommandTester.conn.getResponseBody());
                for(FileData fd : ed.files) {
                    fileID.add(fd.id);
                }
            }
        } catch (RequestException e) {

        }
    }


    public static int getSize(){
        return fileID.size();
    }


    public String getFileID(int index){

        if (fileID.isEmpty())
            return "";

        return fileID.get(index);
    }
}
