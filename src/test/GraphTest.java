package test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import SmartNavigationSystem.Graph;

public class GraphTest {
    @Test
    public void dijkstra_case1() {
       	Graph myGraph=Graph.getInstance();
    	myGraph.dijkstra("SP", 0);
    	int[] expectDistances= {0,1,1,1,2,2,3,4,3,4};
    	int[] actualDistances=new int[expectDistances.length];
    	for(int i=0;i<expectDistances.length;i++) {
    		actualDistances[i]=myGraph.getDistance(i);	
    	}
    	assertArrayEquals(expectDistances,actualDistances);
	}
    
    @Test
    public void dijkstra_case2() {
    	Graph myGraph=Graph.getInstance();
    	myGraph.dijkstra("SP", 5);
    	int[] expectDistances= {2,3,1,1,2,0,1,2,3,4};
    	int[] actualDistances=new int[expectDistances.length];
    	for(int i=0;i<expectDistances.length;i++) {
    		actualDistances[i]=myGraph.getDistance(i);	
    	}
    	assertArrayEquals(expectDistances,actualDistances);
	}
    
    @Test
    public void dijkstra_case3() {
    	Graph myGraph=Graph.getInstance();
    	myGraph.dijkstra("SP", 9);
    	int[] expectDistances= {4,3,3,3,2,4,3,6,5,0};
    	int[] actualDistances=new int[expectDistances.length];
    	for(int i=0;i<expectDistances.length;i++) {
    		actualDistances[i]=myGraph.getDistance(i);	
    	}
    	assertArrayEquals(expectDistances,actualDistances);
	}
    
    @Test
    public void dijkstra_case4() {
    	Graph myGraph=Graph.getInstance();
    	myGraph.dijkstra("ST", 0);
    	int[] expectDistances= {0,2,2,2,4,4,6,5,5,3};
    	int[] actualDistances=new int[expectDistances.length];
    	for(int i=0;i<expectDistances.length;i++) {
    		actualDistances[i]=myGraph.getDistance(i);	
    	}
    	assertArrayEquals(expectDistances,actualDistances);
	}
    
    @Test
    public void dfs_case1() {
    	Graph myGraph=Graph.getInstance();
    	ArrayList<Integer> temp=new ArrayList<Integer>();
    	myGraph.dijkstra("SP", 0);
    	myGraph.dfs(0,9,temp);
    	
    	ArrayList<ArrayList<Integer>> expectRoutes=new ArrayList<ArrayList<Integer>>();
    	ArrayList<Integer> expectRoute1= new ArrayList<Integer>( Arrays.asList(0,3,9));
    	ArrayList<Integer> expectRoute2= new ArrayList<Integer>( Arrays.asList(0,1,4,9));
    	ArrayList<Integer> expectRoute3= new ArrayList<Integer>( Arrays.asList(0,2,4,9));
    	expectRoutes.add(expectRoute1);
    	expectRoutes.add(expectRoute2);
    	expectRoutes.add(expectRoute3);
    	ArrayList<ArrayList<Integer>>actualRoutes=new ArrayList<ArrayList<Integer>>();
    	for(int i=0;i<myGraph.getRoutesNumber();i++) {
    		actualRoutes.add(myGraph.getRoute(i));	
    	}
    	assertEquals(expectRoutes,actualRoutes);
	}
    
    @Test
    public void dfs_case2() {
    	Graph myGraph=Graph.getInstance();
    	ArrayList<Integer> temp=new ArrayList<Integer>();
    	myGraph.dijkstra("SP", 9);
    	myGraph.dfs(9,5,temp);
    	
    	ArrayList<ArrayList<Integer>> expectRoutes=new ArrayList<ArrayList<Integer>>();
    	ArrayList<Integer> expectRoute1= new ArrayList<Integer>( Arrays.asList(9,3,5));
    	ArrayList<Integer> expectRoute2= new ArrayList<Integer>( Arrays.asList(9,4,6,5));
    	ArrayList<Integer> expectRoute3= new ArrayList<Integer>( Arrays.asList(9,4,2,5));
    	expectRoutes.add(expectRoute1);
    	expectRoutes.add(expectRoute2);
    	expectRoutes.add(expectRoute3);
    	ArrayList<ArrayList<Integer>>actualRoutes=new ArrayList<ArrayList<Integer>>();
    	for(int i=0;i<myGraph.getRoutesNumber();i++) {
    		actualRoutes.add(myGraph.getRoute(i));	
    	}
    	assertEquals(expectRoutes,actualRoutes);
	}
    
    @Test
    public void dfs_case3() {
    	Graph myGraph=Graph.getInstance();
    	ArrayList<Integer> temp=new ArrayList<Integer>();
    	myGraph.dijkstra("SP", 5);
    	myGraph.dfs(5,0,temp);
    	
    	ArrayList<ArrayList<Integer>> expectRoutes=new ArrayList<ArrayList<Integer>>();
    	ArrayList<Integer> expectRoute1= new ArrayList<Integer>( Arrays.asList(5,2,0));
    	ArrayList<Integer> expectRoute2= new ArrayList<Integer>( Arrays.asList(5,3,0));
    	expectRoutes.add(expectRoute1);
    	expectRoutes.add(expectRoute2);
    	ArrayList<ArrayList<Integer>>actualRoutes=new ArrayList<ArrayList<Integer>>();
    	for(int i=0;i<myGraph.getRoutesNumber();i++) {
    		actualRoutes.add(myGraph.getRoute(i));	
    	}
    	assertEquals(expectRoutes,actualRoutes);
	}
    
    @Test
    public void dfs_case4() {
    	Graph myGraph=Graph.getInstance();
    	ArrayList<Integer> temp=new ArrayList<Integer>();
    	myGraph.dijkstra("SP", 0);
    	myGraph.dfs(0,0,temp);
    	
    	ArrayList<ArrayList<Integer>> expectRoutes=new ArrayList<ArrayList<Integer>>();
    	ArrayList<Integer> expectRoute1= new ArrayList<Integer>( Arrays.asList(0));
    	expectRoutes.add(expectRoute1);
    	ArrayList<ArrayList<Integer>>actualRoutes=new ArrayList<ArrayList<Integer>>();
    	for(int i=0;i<myGraph.getRoutesNumber();i++) {
    		actualRoutes.add(myGraph.getRoute(i));	
    	}
    	assertEquals(expectRoutes,actualRoutes);
	}
    
}
