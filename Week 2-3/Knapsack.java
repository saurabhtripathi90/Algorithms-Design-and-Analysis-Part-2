import java.io.*;
import java.util.*; 


public class Knapsack {  
    static int Objects;  
    static int Size;
	
    static class Obj{  
        int v; 
        int w;   
        public Obj(int v, int w){  
            this.v= v;  
            this.w = w;  
        }  
    }  
    
    public static void main(String[] args) {  
        
		ArrayList<Obj> al = new ArrayList<Obj>();  
        int A[][];  
        
        try {  
			File f=new File("D:\\algo\\Knapsack.txt");
			Scanner s=new Scanner(f);
			
            Size = s.nextInt();
			Objects = s.nextInt();
			
			A = new int[2][Size+1];  
            for(int x =0;x<Size+1;x++){  
                A[0][x] = 0;                  
            } 
			
			int row=0;
			while (s.hasNextInt()){  
               
                int v = s.nextInt();
                int w = s.nextInt();
                al.add(new Obj(v,w));  
				//System.out.println(v+" "+w);
            } 
			             
            for (int i =0;i<Objects;i++){  
                for(int x =0;x<Size+1;x++){  
                    int j = 0;  
                    if (x<al.get(i).w){                         
                        A[1][x] = A[j][x];    
                    }else{  
                        A[1][x] = Math.max(A[j][x], A[j][x-al.get(i).w]+al.get(i).v);  
                    }  
                }  
                
                for(int k = 0; k<Size+1;k++)  
                    A[0][k] = A[1][k];  
            }  
  
  
            System.out.println(A[1][Size]);  
        } catch (Exception e) {  
            
            System.out.println(e);
        }  
  
    }  
  
}  