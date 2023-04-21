package com.example.phamthiyennhi;

public class MyChude {
    String MACD, TENCD;
    public MyChude(String MACD, String TENCD){
        this.MACD=MACD;
        this.TENCD=TENCD;
    }
    public String toString(){
        String msg="";
        msg+="Ma chu de: "+this.MACD+"\n";
        msg+="Ten chu de: "+this.TENCD;
        return msg;
    }
}
