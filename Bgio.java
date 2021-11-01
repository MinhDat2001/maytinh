/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maytinh;
import javax.swing.*;
/**
 *
 * @author datd6
 */
public class Bgio extends Thread{
    private JLabel txtMinutes;
    private JLabel txtSeconds;
    private boolean check=false;
    public void setCheck(boolean check) {
        this.check = check;
    }

    public void setV(JLabel txtMinutes, JLabel txtSeconds){
        this.txtMinutes=txtMinutes;
        this.txtSeconds=txtSeconds;
        this.check=false;
    }
    
    @Override
    public void run(){
        int mi=Integer.parseInt(txtMinutes.getText());
        int se=Integer.parseInt(txtSeconds.getText());
        while(true){
            if(check) break;
            String mn=Integer.toString(mi);
            if(mi<10) mn="0"+mn;
            String sc=Integer.toString(se);
            if(se<10) sc="0"+sc;
            txtMinutes.setText(mn);
            txtSeconds.setText(sc);
            if(se<60){
                se++;
                try{
                    sleep(100);
                }catch(InterruptedException e){}
            }
            else {
                mi++;
                se=0;
                try{
                    sleep(100);
                }catch(InterruptedException e){}
            }
        }
    }
    
}
