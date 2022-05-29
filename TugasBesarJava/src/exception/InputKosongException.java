package exception;

public class InputKosongException extends Exception{
    public static final String ANSI_RED = "\u001B[31m";

    public void showMessage(){
        System.out.println(ANSI_RED + "Input tidak boleh kosong");
    }
}
