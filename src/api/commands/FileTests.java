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
    public static GetExperimentTest exp;
    public FileTests() {
        super();
        fileID = new StringContainer("");

        FileTuple ft1 = new FileTuple();
        ft1.setId(CommandTester.EXP_NAME);
        ft1.setName("test.fastq");
        ft1.setAuthor("testuser");
        ft1.setMetaData("f1");
        ft1.setType("raw");
        ft1.setGrVersion("hg38");

        FileTuple ft2 = new FileTuple();
        ft2.setId(CommandTester.EXP_NAME);
        ft2.setName("test.fastq");
        ft2.setAuthor("testuser");
        ft2.setMetaData("f2");
        ft2.setType("profile");
        ft2.setGrVersion("hg38");

        FileTuple ft3 = new FileTuple();
        ft3.setId(CommandTester.EXP_NAME);
        ft3.setName("test.fastq");
        ft3.setAuthor("testuser");
        ft3.setMetaData("f3");
        ft3.setType("profilist");
        ft3.setGrVersion("hg38");

        FileTuple ft4 = new FileTuple();
        ft4.setId(CommandTester.EXP_NAME);
        ft4.setName("test.fastq");
        ft4.setAuthor("testuser");
        ft4.setMetaData("f4");
        ft4.setType("profile");
        ft4.setGrVersion("hg1337");

        super.commandList.add(new PostFileTest("POST FILE1", ft1, true));
        super.commandList.add(exp = new GetExperimentTest("GET EXPERIMENT FILE", ft1.getId(), "", true));
        super.commandList.add(new PutFileTest("PUT FILE F2", ft2, "", false));
        super.commandList.add(new GetFileTest("GET FILE1", "f2", true));
        super.commandList.add(new DeleteFileTest("DELETE FILE1", fileID, true));
        super.commandList.add(new DeleteFileTest("DELETE NONEXISTING FILE", fileID, false));

        super.commandList.add(new PostFileTest("POST FILE WRONG TYPE", ft3, true));
        super.commandList.add(exp = new GetExperimentTest("GET FILE TYPE OTHER", CommandTester.EXP_NAME, "Other", true));
        super.commandList.add(new DeleteFileTest("CLEANUP", fileID, true));

        super.commandList.add(new PostFileTest("POST FILE WRONG GR", ft4, false));
        super.commandList.add(exp = new GetExperimentTest("GET NONEXISTING EXPERIMENT FILE", CommandTester.EXP_NAME, "testuser", false));
        super.commandList.add(new DeleteFileTest("CLEANUP", fileID, false));
    }

    @Override
    public boolean execute() {
        System.out.println("\n-----------------------FILE----------------------");
        boolean bigResult = true;
        for (SuperTestCommand stc: super.commandList) {
            stc.execute();


            if ( stc instanceof GetExperimentTest) {
                fileID.setString(((GetExperimentTest) stc).getFileID(0));
                continue;
            }

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
