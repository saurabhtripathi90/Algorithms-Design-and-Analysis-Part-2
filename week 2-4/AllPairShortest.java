import java.io.*;
import java.util.*;

public class AllPairShortest {
	
	public static void main(String[] args) {
		
	int [][][] array;
    int n;
	int max=999999;

	
		for(int m=1;m<4;m++)
		{
			int count=0;
			try {
				File f=new File("D:\\algo\\g"+m+".txt");
				Scanner s=new Scanner(f);
				n = s.nextInt();
				int e=  s.nextInt();
				
				// link all the vertex to infinite length and 
				//for self loop make 0
				
				array = new int[n][n][2];  
				for (int i = 0; i < n; i++)
				{
					for (int j =0; j < n; j++)
					{
						if (i != j)
						{
							array[i][j][0] =max;
						}
						else{
							array[i][j][0] = 0;
						}
					}	
				}	

				//read the graph and insert into array
						
				String str;
				int x,y,z;;
				while(s.hasNextInt()){
					x = s.nextInt();
					y = s.nextInt();
					z = s.nextInt();
						
					array[x-1][y-1][0] = z;


					
				}
			
				//loic for all pair as given in question hint

				for (int k = 0; k < n; k++){
					for (int i = 0; i < n; i++)
						for (int j = 0; j < n; j++){
							array[i][j][1] = Math.min(array[i][j][0],array[i][k][0]+array[k][j][0]);							
						}
					
					for (int i = 0; i < n; i++)
						for (int j = 0; j<n;j++){
							array[i][j][0] = array[i][j][1];
						}
				}  
				
				//print the result 
				int i=0;
				for (; i <n;i++){
					if(array[i][i][1] < 0){   //has -ve value means -ve cycle
						
						System.out.print("NULL ");
						break;
					}
				}
				if (i == n){
					
					int min = 0;
					for (int c = 0; c < n; c++)
						for (int j =0;j<n;j++){
							min = Math.min(min,array[c][j][1]);
						}
						System.out.print(min);
									
				}  

			} catch (Exception e)
			{
				System.out.println(e);
			}
			}
			
		}
	
}