import java.io.*;
import java.util.*;

public class TSP
{
    static Point fp = new Point();
    static int n;   
    static double[][] M1; //Adjacent matrix
  
    static double[][] M2;  //M2
    
  
    static class City
    {
        double x;
        double y;
        
        public City(double x, double y){
            this.x = x;
            this.y = y;
        }
        public double Distance(City other){
            return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
        }
    }
	
	
    public static void main(String[] args)
    {
        
		ArrayList<City> c1 = new ArrayList<City>();
        try{
		File f=new File("D:\\algo\\tsp.txt");
		Scanner s=new Scanner(f);
		 n = s.nextInt();
		
		for(int i = 0; i < n; i++)
            {
                
                double x = s.nextDouble();
                double y = s.nextDouble();
               // System.out.println(i+" "+x+" "+y);
                c1.add(new City(x, y));
            }
        
        }
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		M1 = new double[n][n];
        
        for(int i = 0; i < n; i++)
        {
            City city = c1.get(i);
            for(int j = 0; j < n; j++)
            {
                City otherCity = c1.get(j);
                M1[i][j] = city.Distance(otherCity);
            }
        }
        
        System.out.println(TSP());
    }
    
    
   
    private static double TSP() 
    {
		PriorityQueue<Point> pq = new PriorityQueue<Point>(n, new A());
        fp.l = Double.MAX_VALUE;  //final point
        Point cp = new Point();  //Current point
        cp.ex = new boolean[n][n];
        M2 = new double[n][n];
        cp.compute();
        
        
        
        do{
            do{
                int i = -1;
				PriorityQueue<Point> p1 = new PriorityQueue<Point>(n, new A());
                for(int j = 0; j < n; j++) 
                {
                    if(cp.degree[j] > 2)
					{						
						if(i < 0 || cp.degree[j] < cp.degree[i]){
                        i = j;
                    }
					
                }
                }
                if(i < 0){
                    if(cp.l < fp.l) {
                        fp = cp;
						
                    }
                    break;
                }
                
              
                p1.add(cp.exclude(i, cp.parent[i]));
                
                for(int j = 0; j < n; j++) 
                {
                    if(cp.parent[j] == i) {
                        p1.add(cp.exclude(i, j));
						
                    }
                }
                
                cp = p1.poll();
                pq.addAll(p1);
            } while(cp.l < fp.l);
            
            cp = pq.poll();
        } while (cp != null && cp.l < fp.l);
        
        return fp.l;
    }
    
   
    static class Point 
    {
        boolean[][] ex;
        
       
        double[] pi;
        double l;
        int[] degree;
        int[] parent;
             
        public void compute() 
        {
			double dec = 0.1;
			double rem=1-dec;
            this.pi = new double[n];
            this.l = Double.MIN_VALUE;
            this.degree = new int[n];
            this.parent = new int[n];
            
            
            while(dec > 1e-06) 
            {
                double previousl = this.l;
                computation();
                
                if(!(this.l < fp.l)) {
                    return;
                }
                
                if(!(this.l < previousl)) {
                    dec *= rem;
                }
                
                int denominator = 0;
                for(int i = 1; i < n; i++) 
                {
                    int d = this.degree[i] - 2;
                    denominator += d * d;
                }
                
                if(denominator == 0){
                    return;
                }
                
                double t = dec * this.l / denominator;
                for(int i = 1; i < n; i++){
                    this.pi[i] += t * (this.degree[i] - 2);
                }
            }
        }
           
 
        
        
        private void computation() 
        {
          
            this.l = 0.0;
            Arrays.fill(this.degree, 0);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    M2[i][j] = this.ex[i][j] ? Double.MAX_VALUE : M1[i][j] + this.pi[i] + this.pi[j];
                }
            }

            int n1;
            int n2;

        
            if (M2[0][2] < M2[0][1]) {
                n1 = 2;
                n2 = 1;
            } else {
                n1 = 1;
                n2 = 2;
            }

            for (int j = 3; j < n; j++) {
                if (M2[0][j] < M2[0][n2]) {
                    if (M2[0][j] < M2[0][n1]) {
                        n2 = n1;
                        n1 = j;
                    } else {
                        n2 = j;
                    }
                }
            }

            addPoints(0, n1);
            Arrays.fill(this.parent, n1);
            this.parent[n1] = 0;

           
            double[] min = M2[n1].clone();

            for (int k = 2; k < n; k++) {
                int i;
                for (i = 1; i < n; i++) {
                    if (this.degree[i] == 0) {
                        break;
                    }
                }

                for (int j = i + 1; j < n; j++) {
                    if (this.degree[j] == 0 && min[j] < min[i]) {
                        i = j;
                    }
                }

                addPoints(this.parent[i], i);
                for (int j = 1; j < n; j++) {
                    if (this.degree[j] == 0 && M2[i][j] < min[j]) {
                        min[j] = M2[i][j];
                        this.parent[j] = i;
                    }
                }
            }

            addPoints(0, n2);
            this.parent[0] = n2;
        }
		
		private Point exclude(int i, int j)
        {
            Point p1 = new Point();
            p1.ex = this.ex.clone();
            p1.ex[i] = this.ex[i].clone();
            p1.ex[j] = this.ex[j].clone();
            p1.ex[i][j] = true;
            p1.ex[j][i] = true;

            p1.compute();

            return p1;
        }
        
        private void addPoints(int i, int j)
        {
            double q = this.l;
            this.l += M2[i][j];
            this.degree[i]++;
            this.degree[j]++;
        }
    }

   
    static class A implements Comparator<Point>
    {
        @Override
        public int compare(Point a, Point b) {
            return Double.compare(a.l, b.l);
        }
    }
    
    
    
}