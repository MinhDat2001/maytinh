/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maytinh;

import java.util.Calendar;
import java.util.TimeZone;
import javax.swing.*;
/**
 *
 * @author datd6
 */
public class clock extends Thread{
    private String chon;
    private JLabel jlbTime;
    private JLabel jlbDate;
    private boolean exit=false;
    
    public clock() {
    }
    public void setV(String chon, JLabel jlbTime, JLabel jlbDate) {
        this.chon = chon;
        this.jlbTime = jlbTime;
        this.jlbDate = jlbDate;
        this.exit=false;
    }
    
    public void setExit(boolean exit) {
        this.exit = exit;
    }
    
    @Override
    public void run(){
        while(true){
            if(exit) break;
            TimeZone zone=TimeZone.getTimeZone(chon);
            TimeZone.setDefault(zone);
            Calendar cal=Calendar.getInstance();
            String tmp=cal.getTime().toString();
            String time[]=tmp.split(" ");
            jlbTime.setText(time[3]);
            jlbDate.setText(time[0]+"/"+time[1]+"/"+time[2]+"/"+time[5]);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                System.out.println("loi roi loi roi!");
            }
        }
    }
}
