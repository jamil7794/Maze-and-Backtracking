import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MazeAndBactracking {
	static boolean found=false;
	static int temp[][] = new int[10][10];
	static int maze[][] = new int[10][10];

	public static int bre=0; // this code keeps track when there is a new backtracking
	static int nr,nc; // these variables keeps track of new column and row
	static int a=0; // a,b takes the value of sc and sc when they backtrack
	static int b=0;
	static int mazeNumber =0;
	public static void main(String[] args) throws IOException {
		//Reading from the file
		FileReader file = new FileReader("src/t.txt");
		BufferedReader read = new BufferedReader(file);
		String line = read.readLine();
		String text="";
		
		int i=0;
		
		while(line!=null){
			if(line.length()!=0){
			text=line;
			String[] numbers =text.split(" ");
			
			for(int s=0;s<maze[i].length;s++){
				//Putting the variables to Maze
				maze[i][s]= Integer.parseInt(numbers[s]);
				
			}
			numbers=null;
			i++;
			if(i>9){
				mazeNumber++;
				System.out.println("Maze Number "+ mazeNumber); // Setting the maze number
				findway(0,0,9,9); // This method checks if there any way that makes the way
				print(); // print the maze if there is a solution
				maze=new int[10][10]; //Setting new Maze and new temporary maze
				temp=new int[10][10];
				i=0;
				found=false;
				System.out.println();
			}
			}
		line = read.readLine(); // Read next line from the file
		}
	}
	
	public static void findway(int sr, int sc, int dr, int dc){
		// this finds out the position 9,9 using recursion
		if(sr==dr&&sc==dc){
			found=true;
		}else{
			temp[sr][sc]=1;
			while(!found&&possibleToMove(sr,sc)){
				bre=0;
				findway(nr,nc,9,9);
			}
		}
		if(found){
			maze[sr][sc]=2;
		}
	}
	public static boolean visited(int sr,int sc){
		// This method makes sure that the numbers don't exceed 9 and doesn't go below 0
		if((!(sr>9||sc>9||sr<0||sc<0))&&temp[sr][sc]==0){
			return true;
		}else{
			return false;
		}
	}

	public static boolean possibleToMove(int sr,int sc){
		// This method check if there are any way that the maze could find. If there is no way, then Backtracking becomes true and it stops executing findway
		if(visited(sr,sc+1)&&maze[sr][sc+1]==0){ //east
			nr=sr;
			nc=sc+1;
			return true;
		}
		
		if(visited(sr+1,sc)&&maze[sr+1][sc]==0){ //south
			nr=sr+1;
			nc=sc;
			return true;
		}
		
		if(visited(sr,sc-1)&&maze[sr][sc-1]==0){ //west
			nr=sr;
			nc=sc-1;
			return true;
		}
		
		if(visited(sr-1,sc)&&maze[sr-1][sc]==0){ //north
			nr=sr-1;
			nc=sc;
			return true;
		}
		bre=bre+1;
		if(bre==1){
			System.out.println(">>> Start Bactracking from "+"["+sr+", "+sc+"]");
			a=sr;
			b=sc;
		}else{
			System.out.println(">>> ...Bactracking from "+"["+a+", "+b+"]"+" to "+"["+sr+", "+sc+"]");
		}
		a=sr;
		b=sc;
		return false;
	}
	
	public static void print(){
		// Print the maze if there is a solution
		System.out.println();
		if(maze[9][9]!=2){
			System.out.println("No Solution!");
		}else{
		System.out.println("Maze: "+mazeNumber);
		for(int f=0;f<maze.length;f++){
			for(int m=0;m<maze[f].length;m++){
				System.out.print(maze[f][m]+" ");
			}
			System.out.println();
		}
		}
	}
}
