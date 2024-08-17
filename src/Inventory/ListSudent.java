/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventory;
import actionsInterface.*;
import java.util.Scanner;
import java.util.regex.*;
import java.util.HashMap;
import dataObject.Student;
import dataObject.Subject;
import java.util.Date;
import java.util.InputMismatchException;
import Process.Global;

/**
 *
 * @author nguyenminh
 */
public class ListSudent extends HashMap<String, Student> implements StudentListActions,Preprocess{
   
    private final String patternID = "^[a-zA-Z]{2}\\d{6}$";
    private final String patternFName = "(?=[a-zA-Z])^[a-zA-Z\\s]+$";
    private final String patternLName = "(?=[a-zA-Z])^[a-zA-Z\\s]+$";
    private final String patternGender = "^[fF]emale|[Mm]ale$";
    private final String patternBirth = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$";
    private final String patternEmail = "^([a-zA-Z0-9_]+)@([a-zA-Z]+)(\\..+){1,3}";
    private final String patternPhone = "^\\d{10,12}+";
    
    private final String[] arrPattern = {patternID,patternFName,patternLName,
            patternGender, patternBirth,patternEmail,patternPhone
    };

    @Override
    public void addStd() {
        String tmpId = Global.processId((String)(Global.input("Enter student Id(e.g se162009): ", patternID)));
        if(tmpId == null){
            System.out.println("Unvalid student ID");
        }else{
            if(this.containsKey(tmpId)){
                System.out.println(String.format("Student ID %s existed", tmpId));
            }else{
                String tmpFname = Global.processName((String)(Global.input("Enter first name: ", arrPattern[1])));
                String tmpLname = Global.processName((String)(Global.input("Enter last name: ", arrPattern[2])));
                String tmpGender = Global.processName((String)(Global.input("Enter gender(male or female): ", arrPattern[3])));
                String tmpBirth = Global.processName((String)(Global.input("Enter birth date(dd/mm/yyyy): ", arrPattern[4])));
                String tmpEmail = (String)Global.input("Enter email: ", arrPattern[5]);
                String tmpNumber = (String)Global.input("Enter number phone: ", arrPattern[6]);
                if(tmpFname == null || tmpLname == null || tmpGender == null || tmpBirth == null ||
                    tmpEmail == null ||tmpNumber == null){
                    System.out.println("Some informations are not valid, can not add");
                }else{
                    this.put(tmpId, new Student(tmpId,tmpFname,tmpLname,tmpGender,tmpBirth,tmpEmail,tmpNumber));
                    System.out.println(String.format("Student ID %s hass been added", tmpId));
                }
            }
        }
    }
    
    @Override
    public void update(){
        String tmpId = Global.processId((String)(Global.input("Enter student Id(e.g se162009): ", patternID)));
        if(tmpId == null){
            System.out.println("Unvalid student ID");
        }else{
            if(!this.containsKey(tmpId)){
                System.out.println(String.format("Student ID %s does not exist", tmpId));
            }else{
                this.updateMenu(tmpId);
            }
        }
    }
    
    public void updateMenu(String id){
        System.out.println("1. Delete a student");
        System.out.println("2. Update a student");
        System.out.println("3. Quit updating");
        System.out.print("     Enter choice(1..3): ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch(choice){
            case "1":
                this.deleteStd(id);
                break;
            case "2":
                this.updateStd(id);
            case "3":
                break;
            default:
                System.out.println("Not in our menu");
        }
    }
    
    private void deleteStd(String id) {
        String confirm = (String)Global.input(String.format("Do you ensure to delete student ID %s: ", id),".*");
        if(confirm.equalsIgnoreCase("y")){
            this.remove(id);
            System.out.println(String.format("Student ID %s has been removed", id));
        }else{
            System.out.println("Delete fail");
            this.updateMenu(id);
        }
        
    }

    public void updateStd(String id) {
        Student tmpStd = this.get(id);
        String[] arrInfo = tmpStd.toString().split("_");
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Update first name");
        System.out.println("2. Update last name");
        System.out.println("3. Update gender");
        System.out.println("4. Update birth date");
        System.out.println("5. Update email");
        System.out.println("6. Update number phone");
        System.out.println("7. Quit update information");
        System.out.print("     Enter choice(1..7): ");
        try{
            int choice = (int)scanner.nextInt();
            if(choice == 7){
                this.updateMenu(id);
                throw new Exception();
            }
            System.out.println("Old informartion: "+ arrInfo[choice]);
            String tmp = (String)Global.input("Enter new information: ",arrPattern[choice]);
            if(tmp == null){
                System.out.println("Update fail, nothing change");
                this.updateStd(id);
            }else{
                switch(choice){
                    case 1:
                        tmpStd.setFirstName(Global.processName(tmp));
                        break;
                    case 2:
                        tmpStd.setLastName(Global.processName(tmp));
                        break;
                    case 3:
                        tmpStd.setGender(Global.processName(tmp));
                        break;
                    case 4:
                        tmpStd.setBirthDay(tmp);
                        break;
                    case 5:
                        tmpStd.setEmail(tmp);
                        break;
                    case 6:
                        tmpStd.setNumberPhone(tmp);
                        break;
                    case 0:
                        break;
                    default:
                        break;
                }
                System.out.println("Update succesfully");
                this.updateStd(id);
            }
        }catch(InputMismatchException e){
            System.out.println("Let enter choie with int, try again");
            this.updateStd(id);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Not in our menu, try again");
            this.updateStd(id);
        }catch(Exception e){
            
        }
        
        
    }
    
    
    

}
