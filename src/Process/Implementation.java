/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import java.util.Scanner;
import Inventory.ListSubject;
import Inventory.ListSudent;
import Inventory.Database;
import dataObject.Student;
import dataObject.Subject;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 *
 * @author nguyenminh
 */
public class Implementation {
    private Database db;
    
    public Implementation(){
        this.db = new Database();
    }
    
    public void start(){
        while(true){
            this.process(this.showMenu());
        }
    }
    
    public String showMenu(){
        System.out.println(" ---------------MENU--------------");
        System.out.println("|    1. Add new student           |");
        System.out.println("|    2. Update student            |");
        System.out.println("|    3. Add new subject           |");
        System.out.println("|    4. Update subject            |");
        System.out.println("|    5. Add grade                 |");
        System.out.println("|    6. Student grade report      |");
        System.out.println("|    7. Subject grade report      |");
        System.out.println("|    8. Quit                      |");
        System.out.println(" --------------*****--------------");
        System.out.print("      Enter you choice: "); 
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        return choice;
    }
    
    public void process(String choice){
        switch(choice){
            case "1":
                this.db.getStudentList().addStd();
                break;
            case "2":
                this.db.getStudentList().update();
                break;
            case "3":
                this.db.getSubjectList().addSub();
                break;
            case "4":
                this.db.getSubjectList().update();
                break;
            case "5":
                this.addGrade();
                break;
            case "6":
                this.sdtReport();
                break;
            case "7":
                this.subReport();
                break;
            case "8":
                System.exit(0);
                break;
            default:
                System.out.println("Not in our menu. Try again");
        }
    }
    
    public ArrayList<Double> overrideMark(){
        try{
            double labs = Double.parseDouble((String)Global.input("Enter labs mark: ", ".*"));
            double pt = Double.parseDouble((String)Global.input("Enter progress test mark: ", ".*"));
            double fe = Double.parseDouble((String)Global.input("Enter final exam mark: ", ".*"));
            
            if(labs<=10 && labs>=0 && pt>=0 && pt<=10 && fe>=0 && fe<=10){
                ArrayList tmpMark = new ArrayList();
                tmpMark.add(labs);
                tmpMark.add(pt);
                tmpMark.add(fe);
                return tmpMark;
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            System.out.println("Some unvalid marks, enter again");
            return this.overrideMark();
        }
    }
    public void addGrade(){
        ListSudent tmpStdList = this.db.getStudentList();
        ListSubject tmpSubList = this.db.getSubjectList();
        
        Scanner scanner = new Scanner(System.in);
        String tmpStdId = Global.processId((String)Global.input("Enter student ID(e.g se162009): ", "^[a-zA-Z]{2}\\d{6}$"));
        if(tmpStdId == null){
            System.out.println("Unvalid student ID");
            this.start();
        }if(tmpStdList.containsKey(tmpStdId)){
            while(true){
                String tmpSubId = Global.processId((String)Global.input("Enter subject ID(e.g pro192): ", "^[a-zA-Z]{3}\\d{3}$"));
                if(tmpSubId == null){
                    System.out.println("Unvalid subject ID");
                }else{
                    if(tmpSubList.containsKey(tmpSubId)){
                        Student<String,ArrayList<Double>> tmpSdt = (Student)tmpStdList.get(tmpStdId);
                        Subject<String,ArrayList<Double>> tmSub = (Subject)tmpSubList.get(tmpSubId);

                        if(tmpSdt.getSubList().containsKey(tmpSubId)){
                            tmpSdt.getSubList().get(tmpSubId).forEach(item -> System.out.print(item+" "));
                            System.out.print("Do you want to override Yes(y) or No(n): ");
                            String overide = scanner.nextLine();

                            if(overide.equals("y")){
                                ArrayList<Double> mark = this.overrideMark();

                                tmpSdt.getSubList().put(tmpSubId,mark);
                                tmSub.getSdtList().put(tmpStdId, mark);
                                System.out.println("Override successfully");
                            }
                        }else{
                            ArrayList mark = this.overrideMark();
                            tmpSdt.getSubList().put(tmpSubId,mark);
                            tmSub.getSdtList().put(tmpStdId, mark);
                            System.out.println("Add grade successfully");
                        }
                    }else{
                        System.out.println("Add fail, Subject "+tmpSubId+" does not exist");
                    }
                }
                System.out.print("Keep adding grade? Yes(y) or No(n): ");
                if(scanner.nextLine().equalsIgnoreCase("n")){
                    break;
                }
            }
        }else{
            System.out.println("Student "+tmpStdId+" does not exist");
        }
    }
    
    public void sdtReport(){
        while(true){
            String id = Global.processId((String)Global.input("Enter student ID(e.g se162009): ", "^[a-zA-Z]{2}\\d{6}$"));
            if(id!=null){
                if(this.db.getStudentList().containsKey(id)){
                    this.db.getStudentReport(id);
                }else{
                    System.out.println("Student "+id+" does not exist");
                }
            }else{
                System.out.println("Unvalid student ID");
            }
            System.out.print("Keep report student grade? Yes(y) or No(n): ");
            if(new Scanner(System.in).nextLine().equals("n")){
                break;
            }
        }
    }
    public void subReport(){
        while(true){
            String id = Global.processId((String)Global.input("Enter subject ID(e.g pro192): ", "^[a-zA-Z]{3}\\d{3}$"));
            if(id!=null){
                if(this.db.getSubjectList().containsKey(id)){
                    this.db.getSubjectReport(id);
                }else{
                    System.out.println("Subject "+id+" does not exist");
                }
            }else{
                System.out.println("Unvalid student ID");
            }
            System.out.print("Keep report subject grade? Yes(y) or No(n): ");
            if(new Scanner(System.in).nextLine().equals("n")){
                break;
            }
        }
    }
}
