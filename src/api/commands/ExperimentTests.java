package api.commands;

import api.commands.Experiment.*;
import model.Debug;
import util.AnnotationDataValue;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class ExperimentTests extends TestCollection{

    public ExperimentTests(){
        super();
        AnnotationDataValue[] adv1 = {new AnnotationDataValue(null, "Species", "Fly"),
                new AnnotationDataValue(null, "Sex", "Male"),
                new AnnotationDataValue(null, "Tissue", "Larva")};

        AnnotationDataValue[] adv2 = {new AnnotationDataValue(null, "Sex", "Female")};

        super.commandList.add(new PostExperimentTest("POST EXPERIMENT", CommandTester.EXP_NAME, adv1, true));
        super.commandList.add(new GetExperimentTest("GET EXPERIMENT", CommandTester.EXP_NAME, "", true));
        super.commandList.add(new PutExperimentTest("PUT EXPERIMENT", CommandTester.EXP_NAME, adv2, true));
        super.commandList.add(new PostExperimentTest("POST TO DELETE", "DeleteTestExp", adv1, true));
        super.commandList.add(new DeleteExperimentTest("DELETE EXPERIMENT", "DeleteTestExp", true));
        super.commandList.add(new PostExperimentTest("POST NO NAME", "", adv1, false));
        super.commandList.add(new PostExperimentTest("POST FAIL FORCED", "TestExperiment", adv2, false));
    }

    @Override
    public boolean execute() {
        System.out.println("\n--------------------EXPERIMENT-------------------");
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
