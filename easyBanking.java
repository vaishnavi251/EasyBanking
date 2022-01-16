/*
Project name : easybanking
Insight : This project aims to perform banking operations like adding the details of new member, modify it accordingly and delete it using various data structures.
 */

package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        {
            Scanner sc = new Scanner(System.in);
            int ch;

            //creating an object of class Link
            Link link = new Link();

            //menu driven program to perform the operation :
            do {
                System.out.println("\n*************** MENU ***************");
                System.out.println("1.Create a list of all the customers");
                System.out.println("2.Add new member");
                System.out.println("3.Delete a particular member");
                System.out.println("4.Display customer list");
                System.out.println("5.Deposit amount");
                System.out.println("6.Withdraw amount");
                System.out.println("7.Exit");
                System.out.println("Enter your choice : ");

                //accept the choice from user
                ch = sc.nextInt();

                switch (ch) {

                    case 1:
                        //creates the account of customer
                        link.create();
                        break;

                    case 2:
                        //adds new account to the existing account list
                        int loc;
                        System.out.println("Enter the position where you want to add new account: ");
                        loc = sc.nextInt();
                        link.insert(loc);
                        break;

                    case 3:
                        //deletes the account whose account no. is entered
                        int accNo;
                        System.out.println("Enter the Account number to delete the account : ");
                        accNo = sc.nextInt();
                        link.delete(accNo);
                        break;

                    case 4:
                        //displays the account list
                        link.display();
                        break;

                    case 5:
                        //deposits the money in the account
                        int accN;
                        double deposit;
                        System.out.println("Enter the Account number:");
                        accN = sc.nextInt();
                        System.out.println("Enter the amount you want to deposit :");
                        deposit = sc.nextDouble();
                        link.deposit(accN, deposit);
                        break;

                    case 6:
                        //withdraws money from account
                        int accNum;
                        double withdraw;
                        System.out.println("Enter the Account number:");
                        accNum = sc.nextInt();
                        System.out.println("Enter the amount you want to withdraw : ");
                        withdraw = sc.nextDouble();
                        link.withdraw(accNum, withdraw);
                        break;
                }
            } while (ch != 7);

        }
    }
}

class Account_node
{
    int AccNo;
    String Cust_Name;
    String Cust_address;
    double bal;

    //creating next to link another account with current account
    Account_node next;

    Account_node(int acc,String name,String address,double balance) {

        AccNo=acc;
        Cust_address=address;
        Cust_Name=name;
        bal=balance ;
        //initializing next to null
        next=null;
    }
}


//creating a class Link to perform various operations on bank accounts
class Link {

    Account_node head;
    Scanner sc=new Scanner (System.in);

    //creating method create to create the accounts
    void create(){
        int Acc_no;
        String name;
        String addr;
        double bal_;

        int n;
        System.out.println("\nEnter the number of customers who want to create account : ");
        n=sc.nextInt();

        for (int i=0;i<n ;i++) {
            System.out.println("\nEnter the details of customer " + (i + 1) + " : ");
            System.out.println("Enter the Account no. : ");
            Acc_no = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter Name : ");
            name = sc.nextLine();

            System.out.println("Enter Address : ");
            addr = sc.nextLine();

            do {
                //minimum balance in the account should be Rs.200
                System.out.println("Create new account with minimum balance of Rs.200 !!");
                System.out.println("Enter Balance : ");
                bal_ = sc.nextDouble();

            } while (bal_ < 200);
            sc.nextLine();

            //creating new node to store details of customer
            Account_node new_node = new Account_node(Acc_no, name, addr, bal_);

            //for first node
            if (head == null) {
                head = new_node;
            }

            //node adding at tail
            else {
                Account_node temp = head;

                while (temp.next != null) {

                    //incrementing temp upto last node
                    temp = temp.next;
                }

                new_node.next = temp.next;
                temp.next = new_node;
            }
        }
            System.out.println("*****List created successfully*****");
    }


    //creating method insert to add new account to existing list of accounts
    void insert(int loc){
        int Accno;
        String name;
        String addr;
        double bal_;
        int l=len(head);

        //if insertion done next to null in list, exit
        if(loc>l+1){
            System.out.println("We can't insert new account at position:"+loc);
        }

        else {
            //pointer initialized to head
            Account_node temp = head;
            System.out.println("Enter the details of customer : ");
            System.out.println("Enter Account no. : ");
            Accno = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter Name : ");
            name = sc.nextLine();

            System.out.println("Enter Address : ");
            addr = sc.nextLine();

            do {
                System.out.println("Create new account with minimum balance of Rs.200 !!");
                System.out.println("Enter Balance : ");
                bal_ = sc.nextDouble();
            } while (bal_ < 200);
            sc.nextLine();

            //creating new node to store details of customer
            Account_node new_node = new Account_node(Accno, name, addr, bal_);

            //for first node
            if (head == null) {
                head = new_node;
                System.out.println("Insertion done successfully!!");
            }

            if (loc == 1) {
                new_node.next = head;
                head = new_node;
                System.out.println("Insertion done successfully!!");
            }
            else {
                for (int i = 1; i < loc - 1 && temp != null; i++) {
                    temp = temp.next;
                }

                new_node.next = temp.next;
                temp.next = new_node;
                System.out.println("Insertion done successfully!!");
            }
        }
    }

    //creating method to delete an account
    void delete(int del){

        //pointing to current node that is to be deleted
        Account_node curr=head;
        //pointing to the node which is previous to the one which is to be deleted
        Account_node prev=head;

        //if the list doesn't exist
        if(head==null) {
            System.out.println("Account no."+ del +" not valid!!");
        }

        else {
            //Deletion of 1st Account
            if (del == head.AccNo)
                head = head.next;

            else {
                //Deletion of  other Account
                while (curr != null && curr.AccNo != del) {

                    //the loop will repeat till we reach the node to be deleted
                    prev = curr;
                    curr = curr.next;
                }

                if (curr == null) {
                    //if Account_no is not present
                    System.out.println("Account number " + del + " not present");
                }
                else {
                    //linking previous node to next node
                    prev.next = curr.next;
                    curr.next = null;
                }
            }
            System.out.println("Deletion successfully done!!");
        }
    }


    //creating a method display which displays all the accounts and its details
    void display(){

        //Node current will point to head as pointer
        Account_node current = head;

        //if there is no list formed
        if(head == null) {
            System.out.println("List is empty");
        }

        else {

            //if list is not empty

            System.out.println("\n--------Details of Customer--------  ");
            //loop will continue printing till the list ends
            while (current != null) {
                System.out.println("\nAccount no. : " + current.AccNo);
                System.out.println("Name of customer : " + current.Cust_Name);
                System.out.println("Address of customer : " + current.Cust_address);
                System.out.println("Balance : " + current.bal);

                //incrementing pointer to next node
                current = current.next;
            }
        }
    }


    //creating method deposit to add money to the given account
    void deposit(int acc_no,double dep){
        int flag=0;
        double curr_bal=0.0;

        //pointer to point current node
        Account_node curr=head;

        //if list is empty, exis
        if(head==null){
            System.out.println("Account no. "+acc_no+" is not valid!!");
        }
        else {
            //continue till the current value is not null
            while (curr != null) {
                //searching for required account
                if (curr.AccNo == acc_no) {
                    curr.bal = curr.bal + dep;
                    curr_bal = curr.bal;
                    flag = 1;
                    break;
                }
                curr = curr.next;
            }

            if (flag == 1) {
                //if account is found
                System.out.println("*****Details of the account where Deposition is done*****");
                System.out.println("Account number : " + acc_no);
                System.out.println("Deposition amount : " + dep );
                System.out.println("Current balance : " + curr_bal);
            }
            //if account does not exist in the current list
            else System.out.println("Account number : " + acc_no + " is not valid!!");
        }
    }


    //creating a method withdraw to withdraw the money from account
    void withdraw(int acc_no,double wit)
    {
        int flag=0;
        //pointer to point current node
        Account_node curr = head;

        //if list is empty, exit
        if(head==null){
            System.out.println("Account no. "+acc_no+" is not valid!!");
        }

        //for withdrawing money from given Account
        while(curr!=null){

            //searching for entered account number in list
            if(curr.AccNo==acc_no){

                //if account is present, check for minimum balance condition
                if((curr.bal-200)>wit){
                    curr.bal=curr.bal-wit;
                    System.out.println("*****Details of the account from where th money is Withdrawn*****");
                    System.out.println("Account number : " +acc_no);
                    System.out.println("withdraw amount :" +wit);
                    System.out.println("current balance is : "+curr.bal);
                }

                //if minimum balance condition goes false
                else
                    System.out.println("Minimum balance must be Rs 200, so you can't withdraw");
                flag=1;
                break;
            }

            //incrementing pointer current
            curr=curr.next;
        }

        if(flag==0)
            //if account does not exist
            System.out.println(" Account number:"+acc_no+" is not valid");
    }

    //to finding length of list
    int len(Account_node h){
        Account_node temp=h;
        int l=0;
        if(h==null)
            return 0;
        if(h.next==null)
            return 1;

        while(temp!=null){
            l++;
            temp=temp.next;
        }
        return l;
    }

    Link()
    {
        head=null;
    }
}

/*

Output :

*************** MENU ***************
1.Create a list of all the customers
2.Add new member
3.Delete a particular member
4.Display customer list
5.Deposit amount
6.Withdraw amount
7.Exit
Enter your choice :

1

Enter the number of customers who want to create account :
4

Enter the details of customer 1 :
Enter the Account no. :
123
Enter Name :
Gargi Vatsal
Enter Address :
Indian society, Aurangabad
Create new account with minimum balance of Rs.200 !!
Enter Balance :
4560

Enter the details of customer 2 :
Enter the Account no. :
789
Enter Name :
Rohan Ingole
Enter Address :
Shri homings, Nanded
Create new account with minimum balance of Rs.200 !!
Enter Balance :
0123
Create new account with minimum balance of Rs.200 !!
Enter Balance :
3450

Enter the details of customer 3 :
Enter the Account no. :
678
Enter Name :
Anisha Vaidya
Enter Address :
Ishanya society, Amravati
Create new account with minimum balance of Rs.200 !!
Enter Balance :
9010

Enter the details of customer 4 :
Enter the Account no. :
234
Enter Name :
Ram Dhawal
Enter Address :
Airy housing, Shegaon
Create new account with minimum balance of Rs.200 !!
Enter Balance :
5670
*****List created successfully*****

*************** MENU ***************
1.Create a list of all the customers
2.Add new member
3.Delete a particular member
4.Display customer list
5.Deposit amount
6.Withdraw amount
7.Exit
Enter your choice :
4

--------Details of Customer--------

Account no. : 123
Name of customer : Gargi Vatsal
Address of customer : Indian society, Aurangabad
Balance : 4560.0

Account no. : 789
Name of customer : Rohan Ingole
Address of customer : Shri homings, Nanded
Balance : 3450.0

Account no. : 678
Name of customer : Anisha Vaidya
Address of customer : Ishanya society, Amravati
Balance : 9010.0

Account no. : 234
Name of customer : Ram Dhawal
Address of customer : Airy housing, Shegaon
Balance : 5670.0

*************** MENU ***************
1.Create a list of all the customers
2.Add new member
3.Delete a particular member
4.Display customer list
5.Deposit amount
6.Withdraw amount
7.Exit
Enter your choice :
2
Enter the position where you want to add new account:
890
We can't insert new account at position:890

*************** MENU ***************
1.Create a list of all the customers
2.Add new member
3.Delete a particular member
4.Display customer list
5.Deposit amount
6.Withdraw amount
7.Exit
Enter your choice :
2
Enter the position where you want to add new account:
3
Enter the details of customer :
Enter Account no. :
890
Enter Name :
Harsha Sahay
Enter Address :
Anmol residents, Nanded
Create new account with minimum balance of Rs.200 !!
Enter Balance :
1230
Insertion done successfully!!

*************** MENU ***************
1.Create a list of all the customers
2.Add new member
3.Delete a particular member
4.Display customer list
5.Deposit amount
6.Withdraw amount
7.Exit
Enter your choice :
4

--------Details of Customer--------

Account no. : 123
Name of customer : Gargi Vatsal
Address of customer : Indian society, Aurangabad
Balance : 4560.0

Account no. : 789
Name of customer : Rohan Ingole
Address of customer : Shri homings, Nanded
Balance : 3450.0

Account no. : 890
Name of customer : Harsha Sahay
Address of customer : Anmol residents, Nanded
Balance : 1230.0

Account no. : 678
Name of customer : Anisha Vaidya
Address of customer : Ishanya society, Amravati
Balance : 9010.0

Account no. : 234
Name of customer : Ram Dhawal
Address of customer : Airy housing, Shegaon
Balance : 5670.0

*************** MENU ***************
1.Create a list of all the customers
2.Add new member
3.Delete a particular member
4.Display customer list
5.Deposit amount
6.Withdraw amount
7.Exit
Enter your choice :
3
Enter the Account number to delete the account :
678
Deletion successfully done!!

*************** MENU ***************
1.Create a list of all the customers
2.Add new member
3.Delete a particular member
4.Display customer list
5.Deposit amount
6.Withdraw amount
7.Exit
Enter your choice :
4

--------Details of Customer--------

Account no. : 123
Name of customer : Gargi Vatsal
Address of customer : Indian society, Aurangabad
Balance : 4560.0

Account no. : 789
Name of customer : Rohan Ingole
Address of customer : Shri homings, Nanded
Balance : 3450.0

Account no. : 890
Name of customer : Harsha Sahay
Address of customer : Anmol residents, Nanded
Balance : 1230.0

Account no. : 234
Name of customer : Ram Dhawal
Address of customer : Airy housing, Shegaon
Balance : 5670.0

*************** MENU ***************
1.Create a list of all the customers
2.Add new member
3.Delete a particular member
4.Display customer list
5.Deposit amount
6.Withdraw amount
7.Exit
Enter your choice :
5
Enter the Account number:
234
Enter the amount you want to deposit :
890
*****Details of the account where Deposition is done*****
Account number : 234
Deposition amount : 890.0
Current balance : 6560.0

*************** MENU ***************
1.Create a list of all the customers
2.Add new member
3.Delete a particular member
4.Display customer list
5.Deposit amount
6.Withdraw amount
7.Exit
Enter your choice :
6
Enter the Account number:
234
Enter the amount you want to withdraw :
120
*****Details of the account from where th money is Withdrawn*****
Account number : 234
withdraw amount :120.0
current balance is : 6440.0

*************** MENU ***************
1.Create a list of all the customers
2.Add new member
3.Delete a particular member
4.Display customer list
5.Deposit amount
6.Withdraw amount
7.Exit
Enter your choice :
7

Process finished with exit code 0

 */