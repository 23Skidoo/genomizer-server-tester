package api.commands;

import api.commands.Login.LoginTest;
import api.commands.Login.LogoutTest;
import model.Debug;

/**
 * Created by c10mjn on 2015-05-28.
 */
public class LoginTests extends TestCollection{

    public LoginTests(){
        super();

        super.commandList.add(new LoginTest("USER LOGIN", "yuri", "baguette", true));
        super.commandList.add(new LogoutTest("USER LOGOUT", true));
        super.commandList.add(new LogoutTest("USER LOGOUT NOT LOGGED IN", false));
//        super.commandList.add(new LoginTest("ADMIN LOGIN", "testadmin", "baguette", true));
//        super.commandList.add(new LogoutTest("ADMIN LOGOUT", true));
//        super.commandList.add(new LoginTest("GUEST LOGIN", "testguest", "baguette", true));
//        super.commandList.add(new LogoutTest("GUEST LOGOUT", true));
        super.commandList.add(new LoginTest("BAD USERNAME LOGIN", "BLA", "baguette", false));
        super.commandList.add(new LoginTest("BAD PASSWORD LOGIN", "yuri", "BLA", false));
        super.commandList.add(new LoginTest("NO  USERNAME LOGIN", "", "baguette", false));
        super.commandList.add(new LoginTest("NO  PASSWORD LOGIN", "yuri", "", false));
        super.commandList.add(new LoginTest("GARBAGE USERNAME LOGIN", garbage, "baguette", false));
        super.commandList.add(new LoginTest("GARBAGE PASSWORD LOGIN", "yuri", garbage, false));
    }

    @Override
    public boolean execute() {
        System.out.println("\n----------------------LOGIN----------------------");
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
