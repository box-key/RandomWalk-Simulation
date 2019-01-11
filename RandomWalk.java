import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
public class RandomWalk extends Thread {
	private static int eachThread;
	private static int u;
	private static int d;
	private static int k;
	private static int m;
	private static int highest = 0;
	private int[] data;
	private int x;
	private int n;
	private double startTime;
	private double endTime;
	private double finalTime;
    @Override    
    public void run() {
    	startTime = System.currentTimeMillis();
    	data = new int[eachThread];
    		for(int i = 0; i < eachThread; i++) {
             	n = 0;
    			x = k;
            	while ( x > 0) {
            		if(ThreadLocalRandom.current().nextInt(0, 2)==0) x+=u;
            		else x-=d;
            		n++;
            	}
            	data[i] = n;
            	if(n > highest) highest = n;
    		}
    
    	endTime = System.currentTimeMillis();
    	finalTime = (endTime - startTime) *0.001;
    	this.setName(" excecuted " +  eachThread + " # of RandomWalks " + " spent " + finalTime + " secs");
    }

	public static int getHighest() {
		return highest;
	}

	public static void setData(int u, int d, int k, int m, int each) {
		RandomWalk.u = u;
		RandomWalk.d = d;
		RandomWalk.k = k;
		RandomWalk.m = m;
		RandomWalk.eachThread = each;
	} 
	public int[] getData() {
		return data;
	}
}
