/*
 * $Id: Percolation.java,v 1.2 2014-06-27 18:18:04-07 - - $
 */

public class Percolation {
   private byte site[][];
   private int vtop, vbot;
   private int lim;        //index limit: N-1
   QuickFindUF unionset;
   private void checkindex(int row, int col){
      if (!(row >= 0 && row <= lim && col >= 0 && col <= lim))
         throw new IndexOutOfBoundsException("out of bounds");
   }
   private int id(int row , int col){
      //System.out.printf("r %d, c %d, id %d\n", row, col, lim*row+col);
      return (lim+1)*row+col;
   }

   public Percolation(int N) {
      site = new byte[N][N];
      lim = N-1;
      unionset = new QuickFindUF(N*N+2);
      vtop = N*N;
      vbot = N*N+1;
   }

   public boolean isOpen(int i, int j){
      checkindex(i, j);
      return site[i][j] > 0;
   }

   public boolean isFull(int i, int j){
      checkindex(i, j);
      return isOpen(i,j) && unionset.connected(id(i, j), vtop);
   }
   
   public void open(int i, int j){
      int row  = i;
      int col  = j;
      //System.out.printf("r %d, c %d, id %d\n", row, col, (lim+1)*row+col);
      checkindex(row, col);

      //All sites who are one layer inner
      if (row > 0 && isOpen(row - 1, col)) {
         unionset.union(id(row, col), id(row-1, col));
      }
      if (row < lim && isOpen(row + 1, col)) {
         unionset.union(id(row, col), id(row+1, col));
      }

      if (col > 0 && isOpen(row, col - 1)) {
         unionset.union(id(row, col), id(row, col-1));
      }
      if (col < lim && isOpen( row, col + 1 )) {
         unionset.union(id(row, col), id(row, col+1));
      }

      if(row==0)   unionset.union(id(row,col), vtop);
      if(row==lim) unionset.union(id(row,col), vbot);
      site[row][col] = 1;
   }

   public boolean percolates(){
      return unionset.connected(vtop, vbot);
   }

   public static void main(String[] args) {
      int N = StdIn.readInt();
      Percolation percolate = new Percolation(N);
      while (!StdIn.isEmpty()) {
         int p = StdIn.readInt();
         int q = StdIn.readInt();

//         percolate.open(p - 1, q - 1); //uses index zero
      }
   }


}
