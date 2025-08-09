public class Account{

    protected static int option;
    protected static int pin;

    public static void main(String[] args) {
        SavingsAccount obj = new SavingsAccount();
        obj.setAccount();
        obj.getAccount();  
    }
}