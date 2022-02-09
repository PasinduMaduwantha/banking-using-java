package main.java;
import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Bank {
    Customer account=new Customer();    //create new customer
    Exception e=null;   //exception for withdraw
    //create accounts
    public void createAccount() throws IOException {
        //create a scanner object
        Scanner scan = new Scanner(System.in);
        //input name
        System.out.println("Enter Your Name : ");
        String name=scan.nextLine();
        //input id
        System.out.println("Enter Your NIC : ");
        String id=scan.nextLine();
        //input date
        System.out.println("Enter The Date : ");
        String date=scan.nextLine();
        //input gender
        System.out.println("Enter Your Gender : ");
        String gender=scan.nextLine();
        //input age
        System.out.println("Enter Your Age : ");
        int age=scan.nextInt();
        //input balance
        System.out.println("Enter The Balance : ");
        float balance=scan.nextFloat();

        account.setName(name);
        account.setId(id);
        account.setAge(age);
        account.setGender(gender);
        account.setBalance(balance);
        account.setDate(date);

        //set precision up to 2 decimal points
        DecimalFormat df=new DecimalFormat("0.00");

        try {
            //write into the file, an account will create with NIC number
            BufferedWriter writer=new BufferedWriter(new FileWriter(account.id+".txt", true));
            writer.write("Account Created date     : "+account.getDate()+"\n");
            writer.write("Name       : "+account.getName()+"\n");
            writer.write("Account No : "+account.getId()+"\n");
            writer.write("Age        : "+account.getAge()+"\n");
            writer.write("Gender     : "+account.getGender()+"\n\n");
            writer.write("Date       : "+account.getDate()+"\n");
            writer.write("Balance    : "+df.format(account.getBalance())+"\n");
            writer.close(); //close the file
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to find the file");
        }
    }
    //withdraw money
    public void withdraw(String date, String accNo, float amount) throws Exception {
        //status 0 = withdraw
        File file=new File(accNo+".txt");
        String lastLine=lastValue(file); //get the final balance from the file
        float bal = Float.parseFloat(lastLine);
        if (bal < amount) {
            System.out.println("You Cannot Withdraw money.");
            JOptionPane.showMessageDialog(null, "Insufficient Balance");
            throw e;    //throw exception when balance is lesser than amount that going to withdraw
        }

        System.out.println("You are withdrawing : Rs."+amount);
        bal -= amount;  //final value after withdraw

        updateAccount(date, accNo, amount, bal, 0);  //update the account
    }
    //deposit money
    public void deposit(String date, String accNo, float amount) throws Exception {
        //status 1 = deposit
        File file=new File(accNo+".txt");
        String lastLine=lastValue(file); //get the final balance from the file
        float bal=Float.parseFloat(lastLine);
        System.out.println("You are Depositing : Rs."+amount);
        bal += amount;  //final value after withdraw

        updateAccount(date, accNo, amount, bal,1);  //update the account
    }
    //chek the balance of an account
    public void checkBalance(String accNo){
        //show current balance using last line of the file
        File file=new File(accNo+".txt");
        String lastLine=lastValue(file); //get the final balance from the file
        System.out.println("Your Current Balance : "+lastLine); //Output the balance
    }
    //transfer money into another account
    public void transferMoney(String date, String sender, String receiver, float amount) throws Exception {
        File sFile=new File(sender+".txt"); //define the sender file
        File rFile=new File(receiver+".txt");   //define the receiver file
        float currentBalanceOfSender = Float.parseFloat(lastValue(sFile));  //take the senders current balance
        float currentBalanceOfReceiver = Float.parseFloat(lastValue(rFile));  //take the receivers current balance

        if (currentBalanceOfSender<amount){
            System.out.println("You Cannot Send Money, Your Balance Is Less Than Amount");
            JOptionPane.showMessageDialog(null, "Insufficient Balance");
            throw e;    //throw exception when balance is lesser than amount that going to transfer
        }

        System.out.println("You are Transferring Money To : "+receiver+" "+amount+" Of Money.");

        currentBalanceOfSender -= amount;   //update the sender's balance
        updateAccount(date, sender, amount, currentBalanceOfSender, 2); //status 2 : sending money

        currentBalanceOfReceiver +=amount;  //update the receiver's balance
        updateAccount(date, receiver, amount, currentBalanceOfReceiver, 3); //status 3 : receiving money
    }
    //take the balance in the final row
    public String lastValue( File file ) {
        RandomAccessFile fileReader=null;
        try {
            //read the file from the end using RandomAccessFile class
            fileReader = new RandomAccessFile( file, "r" );
            long fileLength = fileReader.length() - 1;     //index if the last character
            StringBuilder stringBuilder = new StringBuilder();  //make new stringBuilder object

            for(long index = fileLength; index != -1; index--){   //read the file from the end
                fileReader.seek( index );
                int readByte = fileReader.readByte();

                if (( char ) readByte == ':')   //according to the format of the file balance appear, after :
                    break;
                stringBuilder.append( ( char ) readByte );  //append the characters together
            }
            String lastLine = stringBuilder.reverse().toString();   ///string should be set reverse order again
            return lastLine;    //return the balance as a string
        } catch( java.io.FileNotFoundException e ) {
            e.printStackTrace();
            return null;
        } catch( java.io.IOException e ) {
            e.printStackTrace();
            return null;
        }
    }
    //update the account using current values
    public void updateAccount(String date, String accountNo, float amount,float bal, int status) throws Exception {
        BufferedWriter writer=null;
        try{
            writer=new BufferedWriter(new FileWriter(accountNo+".txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //read the account file
        if (status==0)
            writer.write("\nYou are withdrawing : \n"); //write the withdraw

        if (status==1){
            writer.write("\nYou are Depositing : \n"); //write the withdraw
        }
        if (status==2){
            writer.write("\nYou are Sending Money : \n"); //write the withdraw
        }
        if (status==3){
            writer.write("\nYou are Receiving Money : \n"); //write the withdraw
        }
        //update the file
        writer.write("Date       : "+date+"\n");
        writer.write("Amount     : "+amount+"\n"); //write the withdraw
        writer.write("Balance    : "+bal+"\n"); //write balance
        writer.close(); //close the file
    }
}

