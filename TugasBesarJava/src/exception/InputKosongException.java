package exception;

import connection.DbConnection;

public class InputKosongException extends Exception{

    public String showMessage(){
        return "Input tidak boleh kosong!";
    }
}
