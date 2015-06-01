package api.commands;
import api.commands.Admin.DeleteAdminUserTest;
import api.commands.Admin.PostAdminUserTest;
import api.commands.Admin.PutUserTest;
import api.commands.Login.LoginTest;
import api.commands.Login.LogoutTest;
import model.Debug;

/**
 * Created by c10mjn on 2015-05-27.
 */
public class UserTests extends TestCollection {
    public UserTests(){
        super();
        super.commandList.add(new PostAdminUserTest("POST ADMIN USER", "tempuser1","pass1", "ADMIN", "name", "mail_no@gmail.com", true));
        super.commandList.add(new LogoutTest("SWITCH USER", true));
        super.commandList.add(new LoginTest("SWITCH USER", "tempuser1", "pass1", true));
        super.commandList.add(new PutUserTest("CHANGE USER", "pass1", "pass2", "hejhopp", "mailzzzzzz@stuff.com", true));
        super.commandList.add(new PutUserTest("CHANGE USER", "pass1", "pass3", "hejhopp", "mailzzzzzz@stuff.com", false));
        super.commandList.add(new LogoutTest("SWITCH USER", true));
        super.commandList.add(new LoginTest("SWITCH USER", "tempuser1", "pass2", true));
        super.commandList.add(new LogoutTest("SWITCH USER", true));
        super.commandList.add(new LoginTest("SWITCH USER", "testadmin", "baguette", true));
        super.commandList.add(new DeleteAdminUserTest("DELETE ADMIN USER", "tempuser1", true));
    }
    @Override
    public boolean execute() {
        System.out.println("\n----------------------USER-----------------------");
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
