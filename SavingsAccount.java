import javax.swing.*;
import java.text.DecimalFormat;

public class SavingsAccount extends JOptionPane{

    DecimalFormat df = new DecimalFormat("0.00");
    private int acctNumber;
    private double cash = 0;
    Object[] options = {"Exit", "Cancel"};

    public void setAccount(){
    boolean iterate = false;
    boolean iterate2 = false;
        while (!iterate){
            String acctNo = JOptionPane.showInputDialog(null, "Enter valid account number:\n", "Register an Account", JOptionPane.PLAIN_MESSAGE);
            
            if (acctNo == null){
                JOptionPane.showMessageDialog(null, "Program closed...", "Program Closed   ", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }

        try{
        int acctNumber = Integer.parseInt(acctNo);   

            if(isNumeric(acctNo) == true){
                this.acctNumber = acctNumber;
                    while(!iterate2){
                        String pinNo = JOptionPane.showInputDialog(null, "Enter your PIN:\n", "Register an Account", JOptionPane.PLAIN_MESSAGE);

                        if (pinNo == null){
                            JOptionPane.showMessageDialog(null, "Program closed...", "Program Closed", JOptionPane.INFORMATION_MESSAGE);
                            System.exit(0);
                        }

                        try{
                            Account.pin = Integer.parseInt(pinNo);
                                if(isNumeric(pinNo) == true){
                                    JOptionPane.showMessageDialog(null, "Account Successfully Registered!", "Account Registered", JOptionPane.INFORMATION_MESSAGE);
                                    iterate = true;
                                    iterate2 = true;
                                }
                        }
                        catch(NumberFormatException num_invalid){
                            JOptionPane.showMessageDialog(null, "Account number invalid. Please enter Account number correctly.\n\tTry again.");
                            }
                    }
                }
            }
            
        catch(NumberFormatException num_invalid){
            JOptionPane.showMessageDialog(null, "Account number invalid. Please enter Account number correctly.\nTry again.");
            }
        }
    }

    private static boolean isNumeric(String check){
        return check != null && check.matches("\\d+"); 
    }

    public void getAccount(){
        boolean iterate3 = false;
        String selector_login = JOptionPane.showInputDialog(null, "[1] Access your Account\n[2] Exit", "Automated Teller Machince", JOptionPane.PLAIN_MESSAGE);

            if (isNumeric(selector_login) == false){
                getAccount();
            }

            if (selector_login == null){
                JOptionPane.showMessageDialog(null, "Terminating program...", "Program Closed", JOptionPane.INFORMATION_MESSAGE); System.exit(0);
            }

            int option = Integer.parseInt(selector_login);   
        
            switch (option){
                case 1:
                while(!iterate3){
                String enterPINstr = JOptionPane.showInputDialog(null, "Access your account by entering your PIN number:\n", "Automated Teller Machine", JOptionPane.PLAIN_MESSAGE);
                    if (enterPINstr == null){
                        getAccount();
                    }
                    if (isNumeric(enterPINstr) == false){
                        JOptionPane.showMessageDialog(null, "Please enter a valid PIN", "Invalid PIN", JOptionPane.WARNING_MESSAGE);
                        getAccount();
                    }
                try{
                    int enterPINint = Integer.parseInt(enterPINstr);
                    if (enterPINint == Account.pin){
                        iterate3 = true;
                        MainMenu();
                        }
                    else{
                        JOptionPane.showMessageDialog(null, "No matching PIN", "Automated Teller Machine", JOptionPane.WARNING_MESSAGE);
                    }
                }
                catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(null, "Please enter a valid PIN", "Invalid PIN", JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;
            
                case 2: JOptionPane.showMessageDialog(null, "Program closed...", "Program Closed", JOptionPane.INFORMATION_MESSAGE); System.exit(0);
                    break; 
            }
            
    }

    private void MainMenu(){
        
        String selector_login = JOptionPane.showInputDialog(null, "[1] Balance\n[2] Deposit\n[3] Withdraw\n[4] Exit", "Automated Teller Machine", JOptionPane.PLAIN_MESSAGE);

        if (isNumeric(selector_login) == false){
            MainMenu();
        }

        if (selector_login == null){
                JOptionPane.showMessageDialog(null, "You're logged out...", "Logged Out", JOptionPane.INFORMATION_MESSAGE);
                getAccount();
            }

        if (!"".equals(selector_login)){
            Account.option = Integer.parseInt(selector_login);
            if (Account.option == 1){
                getBalance();   
            }
            else if (Account.option == 2){
                Deposit();
            }
            else if (Account.option == 3){
                Withdraw();
            }
            else if ((Account.option >= 4) && (Account.option < 1)){
                exit();
            }
            else{
                exit();
            }
        }
}

    private void getBalance(){
        JOptionPane.showMessageDialog(null, "Current Balance: ₱ "+ df.format(cash), "Automated Teller Machine", 1);
        MainMenu();
    }

    private void Deposit(){
        String Depositor = JOptionPane.showInputDialog(null, "Enter amount to deposit:", "Automated Teller Machine", 3);
        if ("".equals(Depositor)){
            MainMenu();
        }
        else if(Depositor == null){
            MainMenu();
        }
        else if(isNumeric(Depositor) == false){
            JOptionPane.showMessageDialog(null, "Enter valid numeric amount", "Invalid Deposit", JOptionPane.WARNING_MESSAGE);
            MainMenu();
        }
        else{ 
            double deposit = Double.parseDouble(Depositor);
            double balance = deposit;
                JOptionPane.showMessageDialog(null, "Current Balance: ₱ "+df.format(cash+=balance), "Automated Teller Machine", 1);
                    MainMenu();
        }

    }

    private void Withdraw(){
        String Withdrawer = JOptionPane.showInputDialog(null, "Enter amount to withdraw:", "Automated Teller Machine", 3);
        if ("".equals(Withdrawer)){
            MainMenu();
        }
        else if(Withdrawer == null){
            MainMenu();
        }
        else if(isNumeric(Withdrawer) == false){
            JOptionPane.showMessageDialog(null, "Enter valid numeric amount", "Invalid Deposit", JOptionPane.WARNING_MESSAGE);
            MainMenu();
        }
        else{
        double withdraw = Double.parseDouble(Withdrawer);
            if(cash >= withdraw){
                cash -= withdraw;
                    JOptionPane.showMessageDialog(null, "Current Balance: ₱ "+ df.format(cash), "Automated Teller Machine", 1);
                    MainMenu();
        }   
        else{
            JOptionPane.showMessageDialog(null, "Insufficient Fund!", "Automated Teller Machine", 0);
                MainMenu();
        }
    }
    }
    
    private void exit(){
        JOptionPane.showMessageDialog(null, "Account logged out...", "Automated Teller Machine", JOptionPane.INFORMATION_MESSAGE);
        getAccount();
    }
}
