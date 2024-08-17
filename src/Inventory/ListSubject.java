/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventory;

import java.util.HashMap;
import actionsInterface.SubjectActions;
import actionsInterface.Preprocess;
import java.util.Scanner;
import dataObject.Subject;
import java.util.InputMismatchException;
import Process.Global;
/**
 *
 * @author nguyenminh
 */
public class ListSubject extends HashMap<String, Subject> implements SubjectActions,Preprocess {
    private final String patternID = "^[a-zA-Z]{3}\\d{3}$";
    private final String patternName = "(?=[a-zA-Z])^[a-zA-Z\\s]+$";
    private final String patternCredit = "\\d+";

    private final String[] arrPat = {patternID,patternName,patternCredit};
    
    @Override
    public void addSub() {
        String tmpId = Global.processId((String)Global.input("Enter id subject(e.g pro192): ", patternID));
        if(tmpId == null){
            System.out.println("Unvalid ID");
        }else{
            if(this.containsKey(tmpId)){
                System.out.println("Subject "+tmpId+" existed");
            }else{
                String tmpName = Global.processName((String)Global.input("Enter subject name: ", patternName));
                try{
                    Integer tmpCredit = Integer.parseInt((String)Global.input("Enter credit: ", patternCredit));
                    if(tmpName != null && tmpCredit != null){
                        this.put(tmpId, new Subject(tmpId, tmpName, tmpCredit));
                        System.out.println("Subject "+tmpId+" has been added successfully");
                    }else{
                        System.out.println("Some informations are unvalid");
                    }
                }catch(NumberFormatException e){
                    System.out.println("Unvalid credit");
                }
                
                
            }
        }
    }
     
    @Override
    public void update() {
        String tmpId = Global.processId((String)Global.input("Enter id subject(e.g pro192): ", patternID));
        if(tmpId == null){
            System.out.println("Unvalid ID");
        }else{
            if(!this.containsKey(tmpId)){
                System.out.println("Subject "+tmpId+" does not exist");
            }else{
                this.updateMenu(tmpId);
            }
        }
    }
    
    private void updateMenu(String id){
        System.out.println("1. Delete a subject");
        System.out.println("2. Update info a subject");
        System.out.println("3. Quit updating");
        System.out.print("\tEnter choice: ");
        
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        switch(choice){
            case "1":
                this.deleteSub(id);
                break;
            case "2":
                this.updateSub(id);
                break;  
            case "3":
            default:
                break; 
        }
    }
    
    private void updateSub(String id){
        Subject select = (Subject) this.get(id);
        String[] arrInfo = select.toString().split("_");
        System.out.println("1. Update name");
        System.out.println("2. Update credit");
        System.out.println("3: Back to update menu");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("\t\tEnter choie(1..3): ");
        try{
            int choice = scanner.nextInt();
            if(choice == 3){
                this.updateMenu(id); 
            }else{
                System.out.println("Old informations: "+arrInfo[choice]);
                Object tmp = Global.input("Enter new information: ", this.arrPat[choice]);
                if(tmp!=null){
                    switch(choice){
                        case 1:
                            select.setSubName(Global.processName((String)tmp));
                            break;
                        case 2:
                            select.setCredit((int)tmp);
                            break;
                    }
                    System.out.println("Subject "+id+" has been updated successfully");
                    this.updateSub(id);
                }else{
                    System.out.println("Not thing change");
                    this.updateSub(id);
                }
            }
        }catch(InputMismatchException valueErr){
            System.out.println("Please enter choie with int");
            this.updateSub(id);
        }catch(IndexOutOfBoundsException outIndex){
            System.out.println("Not in our choices");
            this.updateSub(id);
        }
    }
    
    public void deleteSub(String id){
        System.out.print("Do you ensure to delete subject "+id+" Yes(y) or No(n): ");
        String confirm = new Scanner(System.in).next();
        if(confirm.equalsIgnoreCase("y")){
            this.remove(id);
            System.out.println("Subject "+id+" has been removed successfully");
        }else{
            System.out.println("Subject "+id+"has not been remove");
            this.updateMenu(id);
        }
    }
    
   
    
    
}
