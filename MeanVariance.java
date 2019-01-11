import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class MeanVariance {

	public static void main(String[] args) {
		Scanner input;
		double mean = 0;
		double variance = 0;
		double sum = 0;
		Vector<Double> hn = new Vector<>();
		try {
			input = new Scanner(new File("histogram.txt"));
			input.nextLine();
			while(input.hasNextLine()) {
				if(!input.hasNextDouble()) break;
				input.nextDouble();
				hn.add(input.nextDouble());
			}
			for(int i = 0; i < hn.size(); i++) {
				mean += i * hn.elementAt(i);
			}
			for(int i = 0; i < hn.size(); i++) {
				variance += i*i * hn.elementAt(i);
			}
			variance -= Math.pow(mean, 2);
			for(int i = 0; i < hn.size(); i++) {
				sum += hn.elementAt(i);
			}
			System.out.println("Sum of H(n): " + sum);
			System.out.println("Mean: " + mean);
			System.out.println("Variance: " + variance);
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
