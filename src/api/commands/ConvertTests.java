package api.commands;


import api.commands.ConvertFile.ConvertFileTest;
import api.commands.ConvertFile.GetConvertedFileTest;
import api.commands.Experiment.GetExperimentTest;
import api.commands.File.DeleteFileTest;
import api.commands.File.FileTuple;
import api.commands.File.PostFileTest;
import model.Debug;

/**
 * Created by c10mjn on 2015-05-27.
 */
public class ConvertTests extends TestCollection{
    public static StringContainer addedFile;
    public static StringContainer convertedFile;

    public ConvertTests() {
        super();

        FileTuple ft1 = new FileTuple();
        ft1.setId(CommandTester.EXP_NAME);
        ft1.setName("test.sgr");
        ft1.setAuthor("testuser");
        ft1.setMetaData("default");
        ft1.setType("profile");
        ft1.setGrVersion("hg38");

        addedFile = new StringContainer("");
        convertedFile = new StringContainer("");

        super.commandList.add(new PostFileTest("POST CONVERT FILE", ft1, true));
        super.commandList.add(new GetExperimentTest("GET FILE TO CONVERT", CommandTester.EXP_NAME, "", true));
        super.commandList.add(new ConvertFileTest("CONVERT FILE", "wig", true));
        super.commandList.add(new GetConvertedFileTest("GET CONVERT FILE", true));
        super.commandList.add(new DeleteFileTest("CLEANUP", addedFile, true));
        super.commandList.add(new DeleteFileTest("CLEANUP", convertedFile, true));
    }

    @Override
    public boolean execute() {
        System.out.println("\n---------------------CONVERT---------------------");

        boolean bigResult = true;
        for (SuperTestCommand stc : super.commandList) {
            stc.execute();
            if (stc instanceof GetExperimentTest) {
                this.addedFile.setString(((GetExperimentTest) stc).getFileID());
            }
            runTests++;

            boolean succeeded = stc.finalResult == stc.expectedResult;

            if (succeeded) {
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
