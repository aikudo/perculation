import java.util.*;
public class PercolationStatus{
   private double thresholds [];
   private int T;

   public PercolationStatus(int N, int T){
      thresholds = new double[T];
      this.T = T;
      //Random rand = new Random(123456789);
      Random rand = new Random();
      for(int trial = 0; trial < T; trial++){
         Percolation perc = new Percolation(N);
         while(!perc.percolates())
            perc.open(rand.nextInt(N), rand.nextInt(N));
         thresholds[trial] = (double) perc.getNumOpen()/(N*N);
      }

      System.out.printf("mean                     = %f\n", mean());
      System.out.printf("stdev                    = %f\n", stddev());
      System.out.printf("95%c confidence internval = %f %f\n",'%',
            confidenceLo(), confidenceHi());
   }

   public double mean(){
      return StdStats.mean(thresholds);
   }

   public double stddev(){
      return StdStats.stddev(thresholds);
   }

   public double confidenceLo(){
      return mean() - (1.96 * stddev())/Math.sqrt(T);
   }

   public double confidenceHi(){
      return mean() + (1.96 * stddev())/Math.sqrt(T);
   }

   public static void main(String[] args){
      int gsize = Integer.parseInt(args[0]);
      int trials = Integer.parseInt(args[1]);
//      StdOut.printf("gsize %d, trials %d\n", gsize, trials);
      if( gsize <= 0 || trials <= 0 ) 
         throw new IllegalArgumentException("invalid experiment");
      PercolationStatus stats = new PercolationStatus(gsize, trials);
   }
}
