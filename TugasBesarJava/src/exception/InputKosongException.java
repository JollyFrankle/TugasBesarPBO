package exception;

import connection.DbConnection;

public class InputKosongException extends Exception{

    public void showMessage(){
        System.out.println(DbConnection.ANSI_RED + "Input tidak boleh kosong");
    }
}
