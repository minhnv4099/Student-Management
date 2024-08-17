/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventory;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;

/**
 *
 * @author nguyenminh
 */
public class Database{
    private ListSudent stdList;
    private ListSubject subList;
    private HashMap<String,HashMap<String, ArrayList<Double>>> stdGradeReport;
    private HashMap<String,HashMap<String, ArrayList<Double>>> subGradeReport;
    
    public Database(){
        this.stdList = new ListSudent();
        this.subList = new ListSubject();
        this.stdGradeReport = new HashMap<>();
        this.subGradeReport = new HashMap<>();
    }
        
    
    public void example(){
        
    }
    public ListSudent getStudentList(){
        return this.stdList;
    }
    
    public ListSubject getSubjectList(){
        return this.subList;
    }

    public HashMap<String,HashMap<String, ArrayList<Double>>> getStdGradeReport() {
        return stdGradeReport;
    }

    public HashMap<String,HashMap<String, ArrayList<Double>>> getSubGradeReport() {
        return subGradeReport;
    }
    
    
    
    public void getStudentReport(String stdId){
        System.out.println("\n\tStudent ID: "+stdId);
        System.out.println("\tStudent name: "+this.stdList.get(stdId).getFirstName()+" "+this.stdList.get(stdId).getLastName());
        System.out.println("| +++No+++ | ++++++Subject name++++++ | +++++Average mark+++++ | +++Status+++ |");
        HashMap<String,ArrayList<Double>> report = this.stdList.get(stdId).getSubList();
        List<String> not = report.keySet().stream().filter((key)-> !this.subList.containsKey(key)).toList();
        report.keySet().removeAll(not);
        List<String> listKey = report.keySet().stream().sorted((o1, o2) -> {
            return this.subList.get(o1).getSubName().compareTo(this.subList.get(o2).getSubName());
        }).toList();
        int count = 0;
        
        for(String key: listKey){
            double average = (report.get(key).get(0)+report.get(key).get(1)+report.get(key).get(2))/3.0;
            String status = average>=4? "Pass":"Not pass";
            String name = this.subList.get(key).getSubName();
            System.out.println(String.format("%6d %19s %25.1f %20s",++count,name,average,status));
        }
    }
    
    public void getSubjectReport(String subID){
        System.out.println("\n\tSubject ID: "+subID);
        System.out.println("\tSubject name: "+this.subList.get(subID).getSubName());
        System.out.println("| +++ID+++ | ++++++Student name++++++ | +++++Average mark+++++ | +++Status+++ |");
        HashMap<String,ArrayList<Double>> report = this.subList.get(subID).getSdtList();
         List<String> not = report.keySet().stream().filter((key)-> !this.stdList.containsKey(key)).toList();
        report.keySet().removeAll(not);
        List<String> listKey = report.keySet().stream().filter((key)-> this.stdList.containsKey(key)).sorted((o1,o2) ->{
            String f1 = this.stdList.get(o1).getFirstName();
            String l1 = this.stdList.get(o1).getLastName();
            String f2 = this.stdList.get(o2).getFirstName();
            String l2 = this.stdList.get(o2).getLastName();
            return (f1+l1).compareTo(f2+l2);
        }).toList();
        for(String key: listKey){
            double average = (report.get(key).get(0)+report.get(key).get(1)+report.get(key).get(2))/3.0;
            String status = average>=4? "Pass":"Not pass";
            String name = this.stdList.get(key).getFirstName()+" "+this.stdList.get(key).getLastName();
            System.out.println(String.format("%10s %19s %25.1f %16s",key,name,average,status));
        }
    }
}
