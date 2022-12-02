package com.proyecto.Gpsi;

public class java {
    public static void main(String[] args) {
		int numguia1 = 2;

        int randomguia = (int) (Math.random()*10);

        

        if(randomguia != numguia1 ){
            System.out.println("ok"+ randomguia );
        }else{
            while(randomguia == numguia1){
                randomguia = (int) (Math.random()*10);
            }
            System.out.println("nuevo randomguia "+ randomguia );
        }
	}
}
