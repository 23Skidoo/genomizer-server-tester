package api.commands;

import api.commands.Admin.DeleteAdminUserTest;
import api.commands.Admin.PostAdminUserTest;
import api.commands.Admin.PutAdminUserTest;
import model.Debug;

/**
 * Created by c10mjn on 2015-05-27.
 */
public class AdminTests extends TestCollection{

    public AdminTests(){
        super();

        super.commandList.add(new PostAdminUserTest("POST ADMIN USER", "tempuser1","pass1", "USER", "name", "mail_no@gmail.com", true));
        super.commandList.add(new PutAdminUserTest("PUT ADMIN USER", "tempuser1", "pass2","GUEST","name2", "new-mail_no@gmail.com", true));
        super.commandList.add(new DeleteAdminUserTest("DELETE ADMIN USER","tempuser1", true));
    }

    @Override
    public boolean execute() {
        System.out.println("\n----------------------ADMIN----------------------");
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
