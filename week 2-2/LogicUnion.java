import java.util.*;
import java.io.*;



public class LogicUnion {
 
 private Node[] node;
 int vertex;
 
 private int count;

 public LogicUnion(int n) {
  

  count = n;
  vertex=n;
  node = new Node[n];
  for (int i = 0; i < n; i++) {
	  
   node[i] = new Node(i, 1); 
          
  }
 }

 
 public void UnionAll(int v1, int v2) {
  int i = get(v1);
  int j = get(v2);

  if (i == j)
   return;

  if (node[i].r >= node[j].r) {
	  node[j].p = i;
   node[i].r = node[i].r + node[j].r;
   
  } else {
     node[i].p = j;
   node[j].r = node[j].r + node[i].r;
  }
  count--;
 }
 
 public int get(int v) {
  

  if (node[v].p != v)
   node[v].p = get(node[v].p);
   
  return node[v].p;
 }
 public int getCount() {
  return count;
 }

 public boolean Connected(int v1, int v2) {
  return get(v1) == get(v2);
 }

}





class Node {
 int p; 
 int r; 
 
 Node(int p, int r) {
  this.p = p;
  this.r = r;
 
 }
}