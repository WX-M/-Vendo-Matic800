package com.techelevator;

import com.techelevator.view.Menu;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PurchaseMenu extends Menu {



        //// this is the Main Menu
    private static final String FEED_MONEY = "Feed Money";
    private static final String SELECT_PRODUCT = "Select Product";
    private static final String FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = {FEED_MONEY, SELECT_PRODUCT, FINISH_TRANSACTION};

    private PurchaseMenu pMenu;
    private double CurrentMoneyProvided;

    public PurchaseMenu() {

    }

    public double getCurrentMoneyProvided() {
        return CurrentMoneyProvided;
    }

    public void setCurrentMoneyProvided(double currentMoneyProvided) {
        this.CurrentMoneyProvided = currentMoneyProvided;
    }

    public PurchaseMenu(InputStream input, OutputStream output) {
        super(input, output);
    }


    public void runPurchaseMenu(){
        pMenu = new PurchaseMenu(System.in, System.out);
        DisplayProduct dp = new DisplayProduct();
        dp.stockMachine();

        while (true) {
            System.out.println( "Current Money Provided: " + getCurrentMoneyProvided());
            String choice = (String) pMenu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

            if (choice.equals(FEED_MONEY)) {
                // feed money
                runFeedMoney();


            } else if (choice.equals(SELECT_PRODUCT)) {
                // select product
                // display vending machine items
                dp.display();


                // create a scanner for user input
                Scanner userInput = new Scanner(System.in);
                System.out.println("Please select a product to purchase: ");
                String slotIdSelected = userInput.nextLine();

                Product selectedProduct = dp.getProductBySlotID(slotIdSelected);
                if(selectedProduct == null){
                    System.out.println("The product selected does not exist! ");
                } else if(selectedProduct.getQuantity() > 0){
                    if(getCurrentMoneyProvided() >= selectedProduct.getPurchasePrice()){
                        System.out.println(selectedProduct.getName() + " " + "This item costs " + selectedProduct.getPurchasePrice());
                        System.out.println("Your remaining money is : " + (getCurrentMoneyProvided() - selectedProduct.getPurchasePrice()));
                        System.out.println(dp.getSound(selectedProduct.getType()));
                        setCurrentMoneyProvided(getCurrentMoneyProvided() - selectedProduct.getPurchasePrice());
                        selectedProduct.setQuantity(selectedProduct.getQuantity() - 1);


                        //****************************PURCHASE LOG*************************
                        String filePath ="log";
                        // Create a File object using the filePath
                        File bookFile = new File(filePath);

                        try (PrintWriter writer = new PrintWriter(new FileOutputStream(bookFile, true))) {
                            // Write the message to the output file.
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                            LocalDateTime now = LocalDateTime.now();
                            writer.println(dtf.format(now) + " " + selectedProduct.getName()
                                    + " "
                                    + selectedProduct.getSlotId()
                                    + " "
                                    + getCurrentMoneyProvided()
                                    + " "
                                    + (getCurrentMoneyProvided() - selectedProduct.getPurchasePrice()) );


                        } catch (FileNotFoundException e) {
                            // Could not find the file at the specified path.
                            System.out.println("The file was not found: " +  bookFile.getAbsolutePath());

                        }

                        //****************************************************************************




                    } else {
                        System.out.println("Your money is not enough ! ");
                    }



                } else {
                    System.out.println(" SOLD OUT ");
                }




            } else if (choice.equals(FINISH_TRANSACTION)){
                // finish transaction



                //****************************FINISH TRANSACTION LOG*************************
                String filePath ="log";
                // Create a File object using the filePath
                File bookFile = new File(filePath);

                try (PrintWriter writer = new PrintWriter(new FileOutputStream(bookFile, true))) {
                    // Write the message to the output file.
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    writer.println(dtf.format(now) + " GIVE CHANGE "  + getCurrentMoneyProvided() + " 0.00");


                } catch (FileNotFoundException e) {
                    // Could not find the file at the specified path.
                    System.out.println("The file was not found: " +  bookFile.getAbsolutePath());

                }

                //****************************************************************************



                // Returning customer money
                System.out.println("You return money is " + getCurrentMoneyProvided());
                // Updating machine's current balance to be zero
                setCurrentMoneyProvided(0.0);
                System.out.println("Machine's current balance is updated to be " + getCurrentMoneyProvided());







                Menu mMenu = new Menu(System.in, System.out);
                VendingMachineCLI cli = new VendingMachineCLI(mMenu);
                cli.run();






            }


        }


    }






    public void runFeedMoney() {

        // Create a scanner for user input
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter money: ");
        int j = 0 ;
       try {
           int givenMoney = Integer.parseInt(userInput.nextLine());
           if (this.checkMoney(givenMoney)) {
               this.setCurrentMoneyProvided(this.getCurrentMoneyProvided() + givenMoney);
               j = 1;


               //****************************FEED MONEY LOG*************************
               String filePath ="log";
               // Create a File object using the filePath
               File bookFile = new File(filePath);

               try (PrintWriter writer = new PrintWriter(new FileOutputStream(bookFile, true))) {
                   // Write the message to the output file.
                   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                   LocalDateTime now = LocalDateTime.now();
                   writer.println(dtf.format(now) + " FEED MONEY " + getCurrentMoneyProvided());


               } catch (FileNotFoundException e) {
                   // Could not find the file at the specified path.
                   System.out.println("The file was not found: " +  bookFile.getAbsolutePath());

               }

            //****************************************************************************





           } else {

               System.out.println(" The given money must equal to $1 ,$2 , $5 or $10");
           }
       } catch (NumberFormatException e) {
           // eat the exception, an error message will be displayed below since choice will be null
       }
        if (j == 0) {
            System.out.println(System.lineSeparator() + "*** please enter a valid currency ***" + System.lineSeparator());
        }

    }


    public boolean checkMoney(int givenMoney) {
        if (givenMoney == 1 || givenMoney == 2 || givenMoney == 5 || givenMoney == 10) {
            return true;
        }
        return false;
    }











}
