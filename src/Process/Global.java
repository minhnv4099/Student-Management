/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import java.util.Scanner;

/**
 *
 * @author nguyenminh
 */
public class Global {
    public static Object input(String promt, String pat){
        Scanner source = new Scanner(System.in);
        System.out.print(promt);
        String tmp = source.nextLine().trim();
        if(tmp.matches(pat)){
            return tmp;
        }else{
            return null;
        }
    }
    public static String processName(String string){
        if(string != null){
            string = string.trim();
            string = string.replaceAll("\\s{2,}", " ");
            string = string.toLowerCase();
            String[] arr = string.split(" ");
            for(int i=0; i<arr.length;i++){
                String wropcap = String.valueOf(arr[i].charAt(0));
                arr[i] = arr[i].replaceFirst(wropcap, wropcap.toUpperCase());
            }
            return String.join(" ", arr);
        }else{
            return null;
        }
    }
    
    public static String processId(String string){
        if(string != null){
            return string.toUpperCase();
        }else{
            return null;
        }
    }
}
