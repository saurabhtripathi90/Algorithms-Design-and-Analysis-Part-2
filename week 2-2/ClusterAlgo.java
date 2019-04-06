import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;
import java.io.*;

public class ClusterAlgo {

 private int Vertex;   
 private int Edges;  
 private int result; 

 
 private List<Edge> mEdgeList;  
 
 public static void main(String[] args) {
  ClusterAlgo ca=new ClusterAlgo();
  ca.read();

 }

 public void read()
 {
	int count=0;
	try{
	File f=new File("D:\\algo\\sa.txt");
	Scanner s=new Scanner(f);
	Vertex=s.nextInt();
	
	mEdgeList=new ArrayList<Edge>();
	while(s.hasNextLine())
	{
		count++;
		//System.out.println("hello");
		int node1=s.nextInt();
		int node2=s.nextInt() ;
		int wt=s.nextInt();
		
		System.out.println(node1+" "+node2+" "+wt);
		mEdgeList.add(new Edge(node1,node2,wt));
		//System.out.println(node1+" "+node2+" "+wt);
		
  //System.out.println(count);
	}
	

	Edges=count;
	System.out.println(Vertex+" "+Edges);
    int Cluster=4;
  
    result=calDistance(Cluster);
    
	if(result!=-1)
    System.out.println("Final Result "+result);
  
	}  
	catch(Exception e)
	{
		System.out.println("hiii"+e);
	}
 }


 public int calDistance(int clusterCount)
 {

  Collections.sort(mEdgeList);
 
  LogicUnion l=new LogicUnion(Vertex);
   
   for(int i=0;i<Vertex;i++)
   {
    Edge edge=mEdgeList.get(i);
      
    if(!l.Connected(edge.node1, edge.node2))
    {
		
     if(l.getCount()==clusterCount)
     {
		 
     
      return result=edge.wt;
     }
     

     int v1 = l.get(edge.node1); 
     int v2 = l.get(edge.node2); 
	 
     l.UnionAll(v1, v2);
	 
    }
   
  } 
  return -1;
 } 

 
}




class Edge implements Comparable<Edge>{
 int node1;
 int node2;
 int wt;

 public Edge(int node1,int node2,int wt) {
  this.node1=node1;
  this.node2=node2;
  this.wt=wt;
 }


 public int compareTo(Edge e) {
  return this.wt - e.wt;
 }
}