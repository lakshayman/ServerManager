/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author csa
 */
public class Serverc {
    private int Id;
    private float Amount;
    private String Server;
    
    public Serverc ( int pid , float pAmt , String pSer ){
        this.Id = pid;
        this.Amount = pAmt;
        this.Server = pSer;
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
    
}
