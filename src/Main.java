import java.sql.*;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    boolean stop = false;
    Connection conn = null;

    while (!stop) { //Stops the program if user types no at the end
      try {
        String uri = "jdbc:sqlite:/Users/gagekettering/Documents/COP 3710/ATM Project/Database/ATM_Management.db";
        conn = DriverManager.getConnection(uri);
        Statement stmt = conn.createStatement();
        System.out.println("We are successfully connected! \n");

        //stmt.execute();

        System.out.println("Would you like to work with ATM's or Members or "
                + "Transactions? Answer 'a' or 'm' or 't'");
        String firstQuestion = keyboard.nextLine();
//-----------------------------------------------------------------------------
// If user selects 'a'
        if (firstQuestion.equals("a")) {
          System.out.println("Please enter the Account Number that you would "
                  + "like to complete a transaction with: ");

          System.out.println("Would you like to add or delete an ATM? "
                  + "Answer 'a' or 'd'");
          String decide = keyboard.nextLine();
          if (decide.equals("a")) {

            System.out.println("Please enter your ATM ID: ");
            int atm_id = keyboard.nextInt();
            System.out.println("Please enter your Bank ID: ");
            int bank_id = keyboard.nextInt();
            System.out.println("Please enter your ATM Location: ");
            int atm_location = keyboard.nextInt();
            keyboard.nextLine();
            System.out.println("Please enter your ATM Location Name: ");
            String location_name = keyboard.nextLine();
            System.out.println("Please enter your ATM balance: ");
            int balance = keyboard.nextInt();
            System.out.println("Please enter the number of transactions for "
                    + "this ATM: ");
            int num_of_tran = keyboard.nextInt();
            keyboard.nextLine();

            System.out.println("ATM ID: " + atm_id);
            System.out.println("Bank ID: " + bank_id);
            System.out.println("ATM Location: " + atm_location);
            System.out.println("ATM Location Name: " + location_name);
            System.out.println("ATM Balance: " + balance);
            System.out.println("Number of Transactions: " + num_of_tran);

            System.out.println("Is this information correct? "
                    + "Answer 'y' or 'n'");
            String s = keyboard.nextLine();
            if (s.equals("y")) {
              String begin = "BEGIN TRANSACTION;";
              stmt.execute(begin);
              String ATMPlaceHolders = "insert into ATM (atm_id, bank_id, atm_location, " +
                      "location_name, balance, num_of_tran) " +
                      "VALUES (?, ?, ?, ?, ?, ?);";

              PreparedStatement pstmt = conn.prepareStatement(ATMPlaceHolders);
              pstmt.setInt(1, atm_id);
              pstmt.setInt(2, bank_id);
              pstmt.setInt(3, atm_location);
              pstmt.setString(4, location_name);
              pstmt.setInt(5, balance);
              pstmt.setInt(6, num_of_tran);
              pstmt.executeUpdate();

              String commit = "COMMIT TRANSACTION;";
              stmt.execute(commit);

              System.out.println("ATM successfully added!");

            }
          } else if (decide.equals("d")) {
            String begin = "BEGIN TRANSACTION;";
            stmt.execute(begin);

            System.out.println("Please enter the ATM ID that you would like "
                    + "to delete: ");
            int atm_id2 = keyboard.nextInt();
            keyboard.nextLine();
            String deleteATM = "DELETE FROM ATM where atm_id = ?;";
            PreparedStatement pstmt2 = conn.prepareStatement(deleteATM);
            pstmt2.setInt(1, atm_id2);
            pstmt2.executeUpdate();

            String commit = "COMMIT TRANSACTION;";
            stmt.execute(commit);

            System.out.println("ATM successfully deleted!");

          } else {
            System.out.println("The output given was incorrect. "
                    + "Please restart the program.");
          }
//-----------------------------------------------------------------------------
// If user selects 'm'
        } else if (firstQuestion.equals("m")) {
          System.out.println("Would you like to add or delete a member? "
                  + "Answer 'a' or 'd'");
          String a = keyboard.nextLine();
          if (a.equals("a")) {
            boolean active = true;
            System.out.println("Please enter your Member ID: ");
            int mem_id = keyboard.nextInt();
            System.out.println("Please enter your Account ID: ");
            int accnt_id = keyboard.nextInt();
            keyboard.nextLine();
            System.out.println("Please enter your Member First Name: ");
            String mem_f_name = keyboard.nextLine();
            System.out.println("Please enter your Member Last Name: ");
            String mem_l_name = keyboard.nextLine();
            System.out.println("Please enter your Social Security Number: ");
            int ssn = keyboard.nextInt();
            System.out.println("Please enter your Phone Number: ");
            long phone = keyboard.nextLong();
            keyboard.nextLine();
            System.out.println("Please enter your Email: ");
            String email = keyboard.nextLine();
            System.out.println("Please enter your Address: ");
            String address = keyboard.nextLine();
            System.out.println("Please enter your Date of Birth (yyyy-MM-dd): ");
            String birthdate = keyboard.nextLine();
            Date.valueOf(birthdate);
            System.out.println("Please enter the Bank ID: ");
            int bank_id = keyboard.nextInt();
            keyboard.nextLine();
            System.out.println("Please enter the type of account: ");
            String accnt_type = keyboard.nextLine();
            System.out.println("Please enter Account Balance: ");
            int balance = keyboard.nextInt();
            keyboard.nextLine();
            System.out.println("Is the account active? Answer 'y' or 'n'");
            String is_active = keyboard.nextLine();
            if (is_active.equals("y")) {
              active = true;
            } else if (is_active.equals("n")) {
              active = false;
            }

            System.out.println("Member ID: " + mem_id);
            System.out.println("Account ID: " + accnt_id);
            System.out.println("First Name: " + mem_f_name);
            System.out.println("Last Name: " + mem_l_name);
            System.out.println("Social Security number: " + ssn);
            System.out.println("Phone Number: " + phone);
            System.out.println("Email Address: " + email);
            System.out.println("Address: " + address);
            System.out.println("Date of Birth: " + birthdate);
            System.out.println("Bank ID: " + bank_id);
            System.out.println("Account Type: " + accnt_type);
            System.out.println("Account Balance: " + balance);
            System.out.println("Is the account active? " + is_active);
            System.out.println("Is this information correct? Answer 'y' or 'n'");
            String c = keyboard.nextLine();
            if (c.equals("y")) {
              String begin = "BEGIN TRANSACTION;";
              stmt.execute(begin);

              String memPlaceHolders = "insert into Member "
                      + "(mem_id, acct_id, mem_fname, mem_lname, ssn, phone, "
                      + "email, address, birthdate) "
                      + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

              String accntPlaceHolders = "INSERT INTO Account (acct_id, "
                      + "bank_id, acct_type, balance, is_active) "
                      + "VALUES (?, ?, ?, ?, ?);";

              // Prepare statement for Member table
              PreparedStatement pstmt3 = conn.prepareStatement(memPlaceHolders);
              pstmt3.setInt(1, mem_id);
              pstmt3.setInt(2, accnt_id);
              pstmt3.setString(3, mem_f_name);
              pstmt3.setString(4, mem_l_name);
              pstmt3.setInt(5, ssn);
              pstmt3.setLong(6, phone);
              pstmt3.setString(7, email);
              pstmt3.setString(8, address);
              pstmt3.setString(9, birthdate);
              pstmt3.executeUpdate();

              // Prepare statement for Account table
              PreparedStatement pstmt = conn.prepareStatement(accntPlaceHolders);
              pstmt.setInt(1, accnt_id);
              pstmt.setInt(2, bank_id);
              pstmt.setString(3, accnt_type);
              pstmt.setInt(4, balance);
              pstmt.setBoolean(5, active);
              pstmt.executeUpdate();

              String commit = "COMMIT TRANSACTION;";
              stmt.execute(commit);

              String select = "SELECT Account.bank_id AS [Bank ID], "
                      + "sum(Account.balance) AS Total" +
                      " FROM Account GROUP BY Account.bank_id;";
              stmt.execute(select);
              System.out.println("Member successfully added!");
            }
          } else if (a.equals("d")) {
            String begin = "BEGIN TRANSACTION;";
            stmt.execute(begin);

            System.out.println("Please enter the Account ID for the member"
                    + " you'd like to delete.");
            int accnt_id2 = keyboard.nextInt();
            keyboard.nextLine();
            String deleteMember = "DELETE FROM Member WHERE acct_id = ?;";

            PreparedStatement pstmt = conn.prepareStatement(deleteMember);
            pstmt.setInt(1, accnt_id2);
            pstmt.executeUpdate();

            String commit = "COMMIT TRANSACTION;";
            stmt.execute(commit);

            System.out.println("Member successfully deleted!");
          }
//-----------------------------------------------------------------------------
// If user selects 't'
        } else if (firstQuestion.equals("t")) {
          System.out.println("Please enter the Account ID for the "
                  + "account you would like to begin a transaction with: ");
          int acct_id = keyboard.nextInt();
          System.out.println("Please enter the Bank ID for the following"
                  + " account: ");
          int bank_id = keyboard.nextInt();
          keyboard.nextLine();
          System.out.println("Account ID: " + acct_id);
          System.out.println("Bank ID: " + bank_id);
          System.out.println("Is this the correct account you wish to edit? "
                  + "Answer 'y' or 'n'");
          String g = keyboard.nextLine();
          if (g.equals("y")) {

            String money = "SELECT Account.acct_id, Account.balance FROM "
                    + "Account WHERE Account.acct_id = " + acct_id + ";";

            ResultSet rs = stmt.executeQuery(money);
            System.out.println("1. Account ID\n2. Balance");

              System.out.println("-------------------------------------------");
              for (int i = 1; i <= 2; i++) {
                System.out.println(i + ". " + rs.getString(i));
              }
              System.out.println("-------------------------------------------");

            System.out.println("Would you like to Withdraw or Deposit? "
                    + "Answer 'w' or 'd'");

            String w_or_d = keyboard.nextLine();
            System.out.println("Notice: Only one transaction may be performed "
                    + "while program is running. If you would like more "
                    + "transactions please restart the program. Thank you.");
            if (w_or_d.equals("w")) {
              System.out.println("Please enter the amount you would like to "
                      + "withdraw: ");
              int withdraw = keyboard.nextInt();
              keyboard.nextLine();
              String withdrawStatement = "UPDATE Account SET balance = balance "
                      + "- " + withdraw + " WHERE acct_id = " + acct_id +";";
              stmt.execute(withdrawStatement);

              String newBalance = "SELECT balance FROM Account WHERE "
                      + "acct_id = " + acct_id + ";";
              ResultSet rs2 = stmt.executeQuery(newBalance);
              for (int i = 1; i <= 1; i++) {
                System.out.println("New Balance: " + rs2.getString(i));
              }

              String bankBalance = "SELECT Total FROM BankTotal WHERE "
                      + "[Bank ID] = " + bank_id + ";";
              //stmt.execute(bankBalance);
              ResultSet rs3 = stmt.executeQuery(bankBalance);
              for (int i = 1; i <=1; i++) {
                System.out.println("New Bank total: " + rs3.getString(i));
              }

            } else if (w_or_d.equals("d")) {
              System.out.println("Please enter the amount you would like "
                      + "to deposit: ");
              int deposit = keyboard.nextInt();
              keyboard.nextLine();
              String depositStatement = "UPDATE Account SET balance = balance "
                      + "+ " + deposit + " WHERE acct_id = " + acct_id +";";
              stmt.execute(depositStatement);

              System.out.println("New Balance: ");
              String newBalance1 = "SELECT balance FROM Account WHERE "
                      + "acct_id = " + acct_id + ";";
              ResultSet rs1 = stmt.executeQuery(newBalance1);
              for (int i = 1; i <= 1; i++) {
                System.out.println("New Balance: " + rs1.getString(i));
              }

              String bankBalance = "SELECT Total FROM BankTotal WHERE "
                      + "[Bank ID] = " + bank_id + ";";
              //stmt.execute(bankBalance);
              ResultSet rs3 = stmt.executeQuery(bankBalance);
              for (int i = 1; i <=1; i++) {
                System.out.println("New Bank total: " + rs3.getString(i));
              }

            }
          }

        }
        System.out.println("Would you like to run the program again? "
                + "Answer 'y' or 'n'");
        String run = keyboard.nextLine();
        if (run.equals("n")) {
          stop = true;
        }

      } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("No connection");
      }
    }
  }
}
