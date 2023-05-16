/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.drinkmachinedriver;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author minji
 */

public class DrinkMachineDriver {
    
    public static Scanner keyboard = new Scanner(System.in); 
    
    public static int IntegerInputValidation(String str_input)
    {
        int userChoice;
        
        while(true)
        {
            try
            {
                userChoice = Integer.parseInt(str_input);
                
                if(userChoice >= 0 && userChoice <= 5)  // Validate the range of the input
                    break;
                
                else
                   throw new InputMismatchException();
            }
            
            catch(NumberFormatException | InputMismatchException e)
            {
                System.out.println(str_input + " is not a valid choice.");
                System.out.println("Input 1 to 5 or 0 to quit: ");
                str_input = keyboard.nextLine();
            }
        }
        
        return userChoice;   
    }

    public static void main(String[] args) {
        
        int choice;        
        DrinkMachine machine = new DrinkMachine();
        
        machine.drinks[0].setName("Cola");
        machine.drinks[1].setName("Root beer");
        machine.drinks[2].setName("Orange soda");
        machine.drinks[3].setName("Grape soda");
        machine.drinks[4].setName("Bottle water");
        
        for(int i = 0; i < 4; i++)
            machine.drinks[i].setPrice(1.0);
        machine.drinks[4].setPrice(1.5);
        
        for(int i = 0; i < 5; i++)
            machine.drinks[i].setQuantity(20);
        
        do
        {  
            machine.display_Menu();
            System.out.println("Enter your choice 1 - 5 to select a drink or 0 to quit: ");          
            String str_input  = keyboard.nextLine();
            choice = IntegerInputValidation(str_input);
            
            switch(choice)
            {
                case 0 -> System.out.println("QUIT");
                case 1 -> machine.buy_Drink(choice);
                case 2 -> machine.buy_Drink(choice);
                case 3 -> machine.buy_Drink(choice);
                case 4 -> machine.buy_Drink(choice);
                case 5 -> machine.buy_Drink(choice);
            }
            
        }while(choice != 0 && choice >= 1 && choice <= 5);
       
        machine.daily_Report();  
        
    }
}
