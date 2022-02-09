package main.java;
import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Bank bank = new Bank();
        Scanner scn = new Scanner(System.in);   //make a scanner to input
        char condition=0;
        String date, accNo, receiver;
        float amount;

        do {
            System.out.println("Welcome\nMenu \nChose A service Using Relevant Service Number\n");
            System.out.println("1. Creating Account\n2. Withdraw Money\n3. Deposit Money\n4. Check The Balance\n5. Money transfer");
            System.out.println("Input Service Number : ");

            int number = scn.nextInt();  //input number
            switch (number)
            {
                case 1: {
                    String ch = "z";
                    do {
                        try {
                            bank.createAccount();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //we can make account again
                        System.out.println("If You Wan To Create Another Account input y");

                        scn.nextLine();
                    }while (ch.toLowerCase()=="y");
                }break;

                case 2: {
                    System.out.println("Enter the date:");
                    date = scn.nextLine();
                    System.out.println("Enter the Account Number : ");
                    accNo=scn.nextLine();
                    System.out.println("Enter Amount : ");
                    amount=scn.nextInt();
                    try{
                        bank.withdraw(date, accNo,amount );    //parameters string:date, string:id, float:amount
                    }catch (Exception e){
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Something Wrong");
                    }

                }break;

                case 3: {
                    System.out.println("Enter the date:");
                    date = scn.nextLine();
                    System.out.println("Enter the Account Number : ");
                    accNo=scn.nextLine();
                    System.out.println("Enter Amount : ");
                    amount=scn.nextInt();
                    bank.deposit(date, accNo,amount);   //parameters string:date, string:id, float:amount
                    break;
                }

                case 4: {
                    System.out.println("Enter the Account Number : ");
                    accNo=scn.nextLine();
                    bank.checkBalance(accNo);
                    break;
                }

                case 5: {
                    System.out.println("Enter the date:");
                    date = scn.nextLine();
                    System.out.println("Enter the Account Number : ");
                    accNo=scn.nextLine();
                    System.out.println("Enter the Receiver : ");
                    receiver=scn.nextLine();
                    System.out.println("Enter Amount : ");
                    amount=scn.nextInt();
                    try{
                        bank.transferMoney(date, accNo,receiver,amount );    //parameters string:date, string:id, float:amount
                    }catch (Exception e){
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Something Wrong");
                    }
                    break;
                }
            }
            System.out.println("Do You want To Proceed Again?  [y/n]\n");
        }while (condition=='y' || condition=='Y');

        System.out.println("Thank You Have A Nice Day!");
    }
}
