package Problem1;

import org.testng.annotations.Test;

import java.util.Stack;

import org.testng.annotations.DataProvider;

public class NewTest {
  @Test(dataProvider = "dp")
public void test(int N,int[][] arr,int starti,int startj,int endi,int endj) {
	Stack<Integer> stki=new Stack<>(),stkj=new Stack<>();
    stki.add(starti);stkj.add(startj);
    arr[endi][endj]=2;
    
    int i,j;
    while(true){
        
        i=stki.peek();j=stkj.peek();
        arr[i][j]=3;
        
        if(arr[i][j]==2) break;
        
        if(  j+1 < N && arr[i][j+1]!=0 && arr[i][j+1]!=3){
            stki.add(i);stkj.add(j+1);
            if(arr[i][j+1]==2) break;
        }
        else if( j-1 >=0 && arr[i][j-1]!=0 && arr[i][j-1]!=3){
            stki.add(i);stkj.add(j-1);
            if(arr[i][j-1]==2) break;
            
        }
        else if( i+1 < N && arr[i+1][j]!=0 && arr[i+1][j]!=3){
            stki.add(i+1);stkj.add(j);
            if(arr[i+1][j]==2) break;
        }
        else if( i-1 >= 0 && arr[i-1][j]!=0 && arr[i-1][j]!=3){
            stki.add(i-1);stkj.add(j);
            if(arr[i-1][j]==2) break;
        }
        else{
            stki.pop();stkj.pop();
        }
        
    }
    System.out.println("------------------The path for start to end point is -------------") ;
    while(!(stki.isEmpty() && stkj.isEmpty())){
        System.out.print(" ("+stki.pop()+" , "+stkj.pop()+") <- ");
    }
    
}
  @DataProvider
  public Object[][] dp() {
	  int[][] test1= {	{1,1,1},{0,1,0},{0,1,1}  };
	  int[][] test2= {	{1,1,0,0},{1,0,1,1},{1,1,0,1},{0,1,1,1}  };
	  int[][] test3= {	{1,1},{1,1}  };
    return new Object[][] {
    	{ 3, test1,0,0,2,2 },
    	{ 4, test2,1,0,1,2 },
    	{ 2, test3,0,0,1,1 },
    };
  }
}
