import java.util.Scanner;

class BankAccount {
    String name;
    String userName;
    String password;
    String accountNo;
    float balance = 100000f;
    int transactions = 0;
    String transactionHistory = "";

    public void register() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Your Name - ");
        this.name = sc.nextLine();
        System.out.print("\nEnter Your Username - ");
        this.userName = sc.nextLine();
        System.out.print("\nEnter Your Password - ");
        this.password = sc.nextLine();
        System.out.print("\nEnter Your Account Number - ");
        this.accountNo = sc.nextLine();
        System.out.println("\nRegistration completed..kindly login");
    }

    public boolean login() {
        boolean isLogin = false;
        Scanner sc = new Scanner(System.in);
        while (!isLogin) {
            System.out.print("\nEnter Your Username - ");
            String Username = sc.nextLine();
            if (Username.equals(userName)) {
                while (!isLogin) {
                    System.out.print("\nEnter Your Password - ");
                    String Password = sc.nextLine();
                    if (Password.equals(password)) {
                        System.out.print("\nLogin successful!!");
                        isLogin = true;
                    } else {
                        System.out.println("\nIncorrect Password");
                    }
                }
            } else {
                System.out.println("\n Username not found");
            }
        }
        return isLogin;
    }

    public void withdraw() {

        System.out.print("\n Enter amount to withdraw - ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try {

            if (balance >= amount) {
                transactions++;
                balance -= amount;
                System.out.println("\n Withdraw Successfully");
                String str = amount + " Rs Withdraw \n";
                transactionHistory = transactionHistory.concat(str);

            } else {
                System.out.println("\n Insufficient Balance");
            }

        } catch (Exception e) {
        }
    }

    public void deposit() {

        System.out.print("\n Enter amount to deposit - ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();

        try {
            if (amount <= 100000f) {
                transactions++;
                balance += amount;
                System.out.println("\n Successfully Deposited");
                String str = amount + " Rs deposited\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("\n Sorry...Limit is 100000.00");
            }

        } catch (Exception e) {
        }
    }

    public void transfer() {

        Scanner sc = new Scanner(System.in);
        System.out.print("\n Enter Recipients Name - ");
        String receipient = sc.nextLine();
        System.out.print("\n Enter amount to transfer - ");
        float amount = sc.nextFloat();

        try {
            if (balance >= amount) {
                if (amount <= 50000f) {
                    transactions++;
                    balance -= amount;
                    System.out.println("\n Successfully Transferred to " + receipient);
                    String str = amount + " Rs Transferred to " + receipient + "\n";
                    transactionHistory = transactionHistory.concat(str);
                } else {
                    System.out.println("\n Sorry...Limit is 50000.00");
                }
            } else {
                System.out.println("\n Insufficient Balance");
            }
        } catch (Exception e) {
        }
    }

    public void checkBalance() {
        System.out.println("\n" + balance + " Rs");
    }

    public void transHistory() {
        if (transactions == 0) {
            System.out.println("\n Empty");
        } else {
            System.out.println("\n" + transactionHistory);
        }
    }
}

class AtmInterface {

    public static int takeIntegerInput(int limit) {
        int input = 0;
        boolean flag = false;

        while (!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;

                if (flag && input > limit || input < 1) {
                    System.out.println("Choose the number between 1 to " + limit);
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Enter only integer value");
                flag = false;
            }
        }
        return input;
    }

    public static void main(String[] args) {

        System.out.println("\n------------WELCOME TO FBI ATM SYSTEM------------\n");
        System.out.println("1.Register \n2.Exit");
        System.out.print("Enter Your Choice :- ");
        int choice = takeIntegerInput(2);

        if (choice == 1) {
            BankAccount b = new BankAccount();
            b.register();
            while (true) {
                System.out.println("\n 1.Login \n 2.Exit");
                System.out.print("Enter Your Choice - ");
                int ch = takeIntegerInput(2);
                if (ch == 1) {
                    if (b.login()) {
                        System.out.println("\n\n------------WELCOME BACK " + b.name + " ------------\n");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println(
                                    "\n 1.Withdraw \n 2.Deposit \n 3.Transfer \n 4.Check Balance \n 5.Transaction History \n6.Exit");
                            System.out.print("\n Enter Your Choice - ");
                            int c = takeIntegerInput(6);
                            switch (c) {
                                case 1:
                                    b.withdraw();
                                    break;
                                case 2:
                                    b.deposit();
                                    break;
                                case 3:
                                    b.transfer();
                                    break;
                                case 4:
                                    b.checkBalance();
                                    break;
                                case 5:
                                    b.transHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }

    }
}
