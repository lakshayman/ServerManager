/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author csa
 */
public class Payment {
    private int Id;
    private float Amount;
    private String Server;
    private String addDate;
    
    public Payment ( int pid , float pAmt , String pSer , String pDate){
        this.Id = pid;
        this.Amount = pAmt;
        this.Server = pSer;
        this.addDate = pDate;
    }
    
    public int getId(){
        return Id;
    }
    
    public float getAmount(){
        return Amount;
    }
    
    public String getServer(){
        return Server;
    }
    
    public String getDate(){
        return addDate;
    }
}
