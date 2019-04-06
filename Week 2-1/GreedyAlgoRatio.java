import java.io.*;
import java.util.*;

abstract class GreedyAlgoRatio
 implements Comparator<int[]>
{	
	public static void main(String ar[])
	{
		try{
			long sum = 0;
		int Length = 0;
			File f=new File("D:\\algo\\jobs.txt");
			Scanner s=new Scanner(f);
			
			int length = s.nextInt();
            
		   int a[][]=new int[10001][3]; 
		   for (int i = 0; i < length; i++) {
			
			a[i][0] = s.nextInt();
			a[i][1] = s.nextInt();
			
			
		}
		// Print the Numbers
		/*for (int i = 0; i < length; i++) {
			System.out.println(a[i][0]+" "+a[i][1]);
		} */
		
		//Calculation of Ratio
		java.util.Arrays.sort(a, 
            new java.util.Comparator<int[]>(){
                
		public int compare(int[] x, int[] y)
	    {
	    	double a = (double) x[0] / (double) x[1];
	    	double b = (double) y[0] / (double) y[1];
	        if (a == b) {
	        	return y[0] - x[0];
	        } 
			else if (b > a){
	        	return 1;
	        } 
			else {
	        	return -1;
	        }
	    }
                
                
                
                
        }); 
		
		
				
		for (int i = 0; i < length; i++) {
			
			Length = Length + a[i][1];
			sum = sum + a[i][0] * Length;
		}
		System.out.println("After Greedy Result is "+sum);
		

       
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
			
			
			
	}
	
	
	
}