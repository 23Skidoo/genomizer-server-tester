package api.commands;

import api.commands.Experiment.GetExperimentTest;
import api.commands.Experiment.PostExperimentTest;
import api.commands.File.*;
import model.Debug;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class FileTests extends TestCollection {
    public static StringContainer fileID;
    public FileTests() {
        super();
        this.fileID = new StringContainer("");

        FileTuple ft1 = new FileTuple();
        ft1.setId(CommandTester.EXP_NAME);
        ft1.setName("test.fastq");
        ft1.setAuthor("testuser");
        ft1.setMetaData("default");
        ft1.setType("raw");
        ft1.setGrVersion("hg38");

        FileTuple ft2 = new FileTuple();
        ft2.setId(CommandTester.EXP_NAME);
        ft2.setName("test.fastq");
        ft2.setAuthor("testuser");
        ft2.setMetaData("default");
        ft2.setType("profile");
        ft2.setGrVersion("hg38");

        super.commandList.add(new PostFileTest("POST FILE1", ft1, true));
        super.commandList.add(new GetExperimentTest("GET EXPERIMENT FILE", ft1.getId(), "", true));
        super.commandList.add(new PutFileTest("PUT FILE1", ft2, "", false));
        super.commandList.add(new GetFileTest("GET FILE1", "", true));
        super.commandList.add(new DeleteFileTest("DELETE FILE1", fileID, true));
    }

    @Override
    public boolean execute() {
        System.out.println("\n-----------------------FILE----------------------");
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
            if ( stc instanceof GetExperimentTest) {
                this.fileID.setString(((GetExperimentTest) stc).getFileID());
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return bigResult;
    }
}
