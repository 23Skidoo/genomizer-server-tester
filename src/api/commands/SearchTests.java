package api.commands;

import api.commands.Experiment.GetExperimentTest;
import api.commands.Search.SearchTestExpID;
import model.Debug;

/**
 * Created by c10mjn on 2015-05-27.
 */
public class SearchTests extends TestCollection {


    public SearchTests() {
        super();

        super.commandList.add(new SearchTestExpID("SEARCH EXP ID", "Exp1[expID]", "testExp1", true));
        super.commandList.add(new SearchTestExpID("SEARCH MULTI", "Exp1[ExpID] Human[Species]", "testExp1", true));
        super.commandList.add(new SearchTestExpID("SEARCH INVALID", "OAJHG", "", false));
        super.commandList.add(new SearchTestExpID("SEARCH EMPTY", "", "testExp1", true));
    }

    @Override
    public boolean execute() {
        System.out.println("\n----------------------SEARCH---------------------");
        boolean bigResult = true;
        for (SuperTestCommand stc: super.commandList) {
            stc.execute();
            runTests++;

            boolean succeeded = stc.finalResult == stc.expectedResult;

            if (succeeded){
                succeededTests++;
            } else {
                failedTests++;
                nameOfFailedTests.add(stc.ident);
                bigResult = false;
            }

            Debug.log(stc.toString(), succeeded);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return bigResult;
    }
}
