/*
Assignment 1 : Token Dispenser 
Name: David Nallapu
NUID : 001530978
*/
import java.util.Scanner;

class TokenDispenser {
    public static void main(String args[]) {
        //Variables that hold the number of tokens 
        int token1 = 0;
        int token5 = 0;
        int token10 = 0;

        Scanner sc = new Scanner(System.in);
        //Flag variable to exit while loop 
        int flag = 1;

        //2-D array holding the transaction history 
        int[][] transactions = new int[3][3];

        //Loop to accept keyboard input
        while (flag == 1) {
            System.out.println();
            //If statement to add funds if the number of tokens is 0 for $1,$5 and $10 
            if (token1 == 0 && token5 == 0 && token10 == 0) {
                System.out.println("Existing funds are 0!!!\nPlease add funds before proceeding.");
                System.out.println("Enter No. of $1 tokens");
                token1 += sc.nextInt();
                System.out.println("Enter No. of $5 tokens");
                token5 += sc.nextInt();
                System.out.println("Enter No. of $5 tokens");
                token10 += sc.nextInt();
            } else {
                //Variables that hold the input for the number of tokens added/dispensed
                int currentToken1 = 0;
                int currentToken5 = 0;
                int currentToken10 = 0;

                System.out.println("Token Dispenser(Enter your choice) \n 1. Add to existing funds \n 2. Dispense funds \n 3. Print history of last three transactions \n 4. Quit");
                int choice = sc.nextInt();
                System.out.println();
                //Switch case for a Menu to add tokens, dispense tokens, display transaction history and exit 
                switch (choice) {
                    case 1:
                        //Case to add tokens to existing funds
                        System.out.println("Enter No. of $1 tokens");
                        token1 += sc.nextInt();
                        System.out.println("Enter No. of $5 tokens");
                        token5 += sc.nextInt();
                        System.out.println("Enter No. of $5 tokens");
                        token10 += sc.nextInt();
                        break;
                    case 2:
                        //Case to dispense tokens with value not exceeding available funds and update transaction hisotry 
                        System.out.println("Enter No. of $1 tokens (MAX = " + token1 + ")");
                        currentToken1 = sc.nextInt();
                        if (currentToken1 > token1) {
                            System.out.println("Exceeded max limit by " + (currentToken1 - token1) + "\n Please add the required tokens before proceeding.\n");
                            break;
                        }
                        System.out.println("Enter No. of $5 tokens (MAX = " + token5 + ")");
                        currentToken5 = sc.nextInt();
                        if (currentToken5 > token5) {
                            System.out.println("Exceeded max limit by " + (currentToken5 - token5) + "\n Please add the required tokens before proceeding.\n");
                            break;
                        }
                        System.out.println("Enter No. of $10 tokens (MAX = " + token10 + ")");
                        currentToken10 = sc.nextInt();
                        if (currentToken10 > token10) {
                            System.out.println("Exceeded max limit by " + (currentToken10 - token10) + ".\nPlease add the required tokens before proceeding.\n");
                            break;
                        }

                        System.out.println();
                        //Dispensing tokens with denominations ($1, $5, and $10)
                        System.out.println("Dispensing ...");
                        for (int i = 0; i < currentToken1; i++) {
                            System.out.print("$1, ");
                        }
                        System.out.println();
                        for (int i = 0; i < currentToken5; i++) {
                            System.out.print("$5, ");
                        }
                        System.out.println();
                        for (int i = 0; i < currentToken10; i++) {
                            System.out.print("$10, ");
                        }
                        System.out.println();

                        token1 -= currentToken1;
                        token5 -= currentToken1;
                        token10 -= currentToken1;
                        //Method call to update transaction history 
                        transactions = updateTransactionHistory(transactions, currentToken1, currentToken1, currentToken10);
                        break;
                    case 3:
                        //Case to display details of last three transactions along with cost
                        System.out.println("Last three transactions : ");
                        for (int i = 0; i < 3; i++) {
                            System.out.print(i + 1 + ". ");
                            int cost = 0;
                            for (int j = 0; j < 3; j++) {
                                if (j == 0) {
                                    System.out.print("$1 -> " + transactions[i][j] + ",");
                                    cost+=transactions[i][j];
                                } else if (j == 1) {
                                    System.out.print("$5 -> " + transactions[i][j] + ",");
                                    cost+=transactions[i][j]*5;
                                } else {
                                    System.out.print("$10 -> " + transactions[i][j] + ",");
                                    cost+=transactions[i][j]*10;
                                }   
                            }
                            System.out.print("Total Cost : $" + cost);
                            System.out.println();
                        }
                        break;
                    case 4:
                        //Case to exit the while loop and the program subsequently 
                        flag = 0;
                        break;
                    default:
                        System.out.println("Choose the correct option!");

                }
            }
        }
        System.out.println("Exiting ...\n Thank you!");
    }
    
    //Method to update transaction history array to maintain the latest three 
    static int[][] updateTransactionHistory(int[][] transactions, int t1, int t5, int t10) {
        transactions[2][0] = transactions[1][0];
        transactions[2][1] = transactions[1][1];
        transactions[2][2] = transactions[1][2];
        transactions[1][0] = transactions[0][0];
        transactions[1][1] = transactions[0][1];
        transactions[1][2] = transactions[0][2];
        transactions[0][0] = t1;
        transactions[0][1] = t5;
        transactions[0][2] = t10;
        return transactions;
    }
}