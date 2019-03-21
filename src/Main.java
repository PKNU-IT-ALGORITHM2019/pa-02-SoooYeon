import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
class location{
	int x,y,index;
	location(int x, int y,int index) {
		this.x = x; this.y=y; this.index = index;
	}
}
public class Main {
	public static int N = 0;
	public static int BUFFER_SIZE = 100;
	public static location [] xy = new location[BUFFER_SIZE];
	public static int [] index = new int[BUFFER_SIZE];
	public static void main(String[] args) {
		int x,y=0;
		try {
			Scanner inFile = new Scanner( new File("input4.txt") );
			N = inFile.nextInt();
			for(int i=0;i<N;i++) {
				x = inFile.nextInt();
				y = inFile.nextInt();
				xy[i] = new location(x,y,i);
			}
			inFile.close();
		}catch (FileNotFoundException e) {
			System.out.println("No file");
		}
		Tour(1,0);
		System.out.print("answer:"+min+" [");
		for(int i=0;i<N;i++) {
			System.out.print(" "+index[i]);
			if(i<N-1) System.out.print(",");
		}
		System.out.println(" ]");
	}
	public static boolean first = true;
	public static boolean flag = false;	
	public static int count=0;
	public static double min=0;
	public static void Tour(int k, double sum)
	{		
		if(flag && sum > min) return;
		else if(k==N) {
			sum += len(k-1,k);
			if(first) {	
				for(int i=0;i<N;i++)
					index[i] = xy[i].index;
				min=sum; first=false; flag=true;}
			else if(min>sum) { min = sum;
				for(int i=0;i<N;i++)
					index[i] = xy[i].index;}
			return;
		}
		for(int i=k;i<N;i++) {
			swap(k,i);	
			Tour(k+1,sum+len(k-1,k));	
			swap(k,i);
		}	
	}
	public static double len(int i,int j)
	{			
		double sum =0;
		if(i>=N-1)j=0;		
		sum = Math.sqrt(Math.pow((xy[i].x -xy[j].x),2)+Math.pow((xy[i].y -xy[j].y),2));
		return sum;
	}
	public static void swap(int a, int b)
	{
		location tmp = xy[a];
		xy[a] = xy[b];
		xy[b] = tmp;
	}
}

