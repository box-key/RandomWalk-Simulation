import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main { 
    public static void main(String[] args) {
    	long startTime = System.currentTimeMillis();
		int u = Data.u;
		int d = Data.d;
		int k = Data.k;
		int m = Data.m;
		int T = Data.T;
        RandomWalk.setData(u,d,k,m,m/T);
        RandomWalk threads[] = new RandomWalk[T];

        for (int i = 0; i < T; ++i){
            threads[i] = new RandomWalk();
            threads[i].start();
        }    
        
        for (int i = 0; i < T; ++i) {
            RandomWalk walk = threads[i];
            try {
            	walk.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        double[] histogram = new double[RandomWalk.getHighest()+1];
        for(int i = 0; i < T; i++) {
        	RandomWalk walk = threads[i];
        	int[] data = walk.getData();
        	for(int z = 0; z < data.length; z++) {
        		histogram[data[z]]++;
        	}
        	System.out.println("Thread # " + (i+1) + walk.getName() );
        }
        
    	for(int i = 0; i < histogram.length; i++) {
    		if(histogram[i] != 0)  histogram[i] /= m; 
    	}
    	
        try {
			PrintWriter output = new PrintWriter("histogram.txt");
			output.println("n	h(n)");
			for(int i = 0; i < histogram.length; i++) {
				output.println( i + "	"  + histogram[i]);
			}
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
        long endTime = System.currentTimeMillis();
        double runTime = (endTime - startTime) * 0.001;
        System.out.println("time (sec) (program) = " + runTime);
        System.out.println("Highest Number of Walks: " + RandomWalk.getHighest());
        
    }

}


