/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataObject;
import Inventory.ListSudent;
import java.util.HashMap;
import java.util.HashSet;
/**
 *
 * @author nguyenminh
 */
public class Subject<K,V> {
    private String subId;
    private String subName;
    private int credit;
    private HashMap<K,V> stdList;

    public Subject(String subId, String subName, int credit) {
        this.setSubId(subId);
        this.setSubName(subName);
        this.credit = credit;
        this.stdList = new HashMap();
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void addLearner(String idLearner){
        
    }
    
    public HashMap<K,V> getSdtList(){
        return this.stdList;
    }
    
    @Override
    public String toString() {
        return String.join("_", this.subId,this.subName,String.valueOf(this.credit));
    }

    
    
}
