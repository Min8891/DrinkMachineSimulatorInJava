/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.drinkmachinedriver;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DrinkMachine {
    
    public static Scanner keyboard = new Scanner(System.in);
    
    private double totalMoney;
    private double[] eachSale = new double[5];    
    protected Drinks[] drinks = new Drinks[5];
   
    protected class Drinks // Inner class
    {
        private String drinkName;
        private double price;
        private int quantity;

        public void setName(String name)
        {
            drinkName = name;
        }
        
        public String getName()
        {
            return drinkName;
        }
        
        public void setPrice(double cost)
        {
            price = cost;
        }
        
        public double getPrice()
        {
            return price;
        }
    
        public void setQuantity(int number)
        {
            quantity = number;
        }
        
        public int getQuantity()
        {
            return quantity;
        }
    }
    
    public DrinkMachine()  // Constructor to initialize the drinks array
    {
        for(int i = 0; i < drinks.length; i++)
           drinks[i] = new Drinks();
    }
    
    public  void display_Menu() //Funtion to display the menu to users
    {
        System.out.println("\tDrink  Menu");
        System.out.println("************************");
        
        System.out.printf("%-18s","Drink Name"); 
        System.out.printf("%-8s","Cost");
        System.out.println();     
        
        for(int i = 0; i < drinks.length; i++)
        {
            System.out.printf((i+1)+ " %-16s",drinks[i].drinkName);
            System.out.printf("%-10.2f",drinks[i].price);
            System.out.println(); 
        }
        
        System.out.println("************************");
    } 
    
    public double input_Money() //Funtion to accept and validate user input money
    {        
        double userMoney;
        String str_input  = keyboard.nextLine();
        
        while(true)
        {
            try
            {
                userMoney = Double.parseDouble(str_input);

                if(userMoney <= 0)
                    throw new InputMismatchException();
                else
                    break;
            }
            catch(NumberFormatException | InputMismatchException e)
            {
                System.out.println(str_input + " is not a valid number.");
                System.out.println("Input a valid number: ");
                str_input = keyboard.nextLine();   
            }  
        }
        
        return userMoney;
    }
     
    public  void buy_Drink(int choice)  //Funtion to perform the purchase or cancel it      
    {     
        char answer;
        
        System.out.println("The price for the drink is: $" + drinks[choice -1].price);
        System.out.println("Insert your money: ");
        
        
        double moneyReceived = input_Money();
        
        double change = moneyReceived  - drinks[choice-1].price;
        
        if(moneyReceived  >= drinks[choice -1].price) //User input money greater than price
        {
            System.out.println("Make the purchase?(y for yes,n for no)");
            answer = keyboard.next().charAt(0);
            
            while(Character.toLowerCase(answer) != 'y' && Character.toLowerCase(answer) != 'n') //Validate user's answer input
            {
                System.out.println("Enter a valid choice. Purchase the drink? (y for yes,n for no)");
                answer = keyboard.next().charAt(0);
            }
            keyboard.nextLine();
            
            if(Character.toLowerCase(answer)== 'y')//Make the purchase
            {
                if(drinks[choice-1].quantity <= 0) //Sold out
                {
                    System.out.println("Sold out"); 
                    System.out.println("Canceled, and here is your money returned: $" + moneyReceived  + "\n");
                }
                
                else //Quantity is enough, and input money equals the price
                {
                    drinks[choice-1].quantity -= 1;
                    eachSale[choice-1] += drinks[choice-1].price; 
                    System.out.println("Here is your beverage: " +drinks[choice-1].drinkName + "\n");
                }
               
                if(moneyReceived  > drinks[choice-1].price)//Money greater than the price
                {
                    change = moneyReceived  - drinks[choice-1].price;
                    
                    System.out.println("Here is your change is: $" + change + "\n");
                }
                        
            }
            
            else
                System.out.println("Canceled, and here is your money returned: $" + moneyReceived + "\n"); //User chooses not to buy the drink
          }   
        
        else  //Input money less than price    
        {   
            System.out.println("Money is not enough.");
            System.out.println("Canceled, and here is your money returned: $" + moneyReceived + "\n");
        }
                
    }
    
    public double calcTotalMoney(double[]eachSale, int size) // Recursive funtion to calculate the total money collected  
    {
        
        if(size <= 0)
            return 0;
        else
        {
            totalMoney += (calcTotalMoney(eachSale,size-1)+ eachSale[size-1]);
            return totalMoney;
        }
    }
       
    public void daily_Report() //Funtion to print the daily report
    {
        
        System.out.println("\tDaily Report");
        
        System.out.println("####################################");
        System.out.printf("%-18s","Drink Name"); 
        System.out.printf("%-10s","Quantity");
        System.out.println("Sale($)");
        
        
        for(int i = 0; i < 5; i++)
        {
            System.out.printf((i+1)+ " %-16s",drinks[i].drinkName);
            System.out.printf("%-10d",drinks[i].quantity);    
            System.out.printf("%-10.2f%n", eachSale[i]);         
        }
        
        totalMoney = calcTotalMoney(eachSale, eachSale.length);
        
        System.out.println();
        System.out.println("Total money collected is $" + totalMoney );
        System.out.println("####################################");
    }  
}