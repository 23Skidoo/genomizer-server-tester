package util;

import java.util.ArrayList;

/**
 * Created by ens11afk on 2015-06-02.
 */
public class UserList {

    public ArrayList<String> username;

    public UserList() {
        username = new ArrayList<>();
    }


    public boolean userExist(String username){
        return this.username.contains(username);
    }


    public int getSize(){
        return username.size();
    }


    public ArrayList<String> getUserList(){
        return username;
    }

    public void print(){

        System.out.print("Size: " + username.size() + "; ");
        for(String s : username){
            System.out.print(s + ", ");
        }

        System.out.print("\n");
    }

}
