/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actionsInterface;

/**
 *
 * @author nguyenminh
 */
public interface Preprocess {
    default String processName(String string){
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
    
    default String processId(String string,int end){
        if(string != null){
            StringBuilder strB = new StringBuilder(string);
            strB.replace(0, end, strB.substring(0, end).toUpperCase());
            return strB.toString();
        }else{
            return null;
        }
    }
}
