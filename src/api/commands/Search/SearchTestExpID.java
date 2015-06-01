package api.commands.Search;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import model.ErrorLogger;
import requests.SearchRequest;
import util.Constants;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-27.
 */
public class SearchTestExpID extends SuperTestCommand {
    public String search;
    public String expected;

    public SearchTestExpID(String ident, String search, String expected, boolean expectedResult) {
        super(ident, expectedResult);
        this.search = search;
        this.expected = expected;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new SearchRequest(this.search), CommandTester.token, Constants.TEXT_PLAIN);

            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = CommandTester.conn.getResponseBody().contains(expected);
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
