package algs15;
import stdlib.*;
import algs15.UF;
//Leona Lupulesku
public class hw5 
{
    public class WeightedQuickUnionPathCompression implements UF 
    {
        int id[];
        int size[];
        int height[];
        int count;
        int maxHeight;

        public WeightedQuickUnionPathCompression(int size) 
        {
            this.id = new int[size];
            this.size = new int[size];
            this.height = new int[size];
            this.count = size;
            this.maxHeight = 0;

            for(int i = 0; i < id.length; i++) 
            {
                this.id[i] = i;
                this.size[i] = 1;
                this.height[i] = 0;
            }
        }

        public int count() 
        {
            return count;
        }

        public void toGraphviz() 
        { 
        	GraphvizBuilder.ufToFile (id); 
    	}
        
        // Are two nodes connected
        public boolean connected(int site1, int site2) {
        	return find(site1) == find(site2);
        }
        
      

        // Recursively, find the top-level site ID while using path compression. 
        // Should be amortized O(lg n) performance
        public int find(int site) {
        	if(site != id[site])
        		id[site] = find(id[site]);
        	return id[site];
        }
        
       
        // Implement weighted quick union with path compression. The height of a tree will only
        // increase when uniting with a tree of the same height, otherwise the smaller tree will 
        // join the biggest tree. This guarantees a logarithmic upper bound on the height of the 
        // trees for N sites. Therefore, the height can increase at most lg N times.
	    public void union(int site1, int site2) {
	    	int pid = find(site1);
			int qid = find(site2);
			if(pid == qid) return;
	      
			if (height[pid] < height[qid]) {
				id[pid] = qid;
				height[qid] = Math.max(height[pid]+1, height[qid]);
				maxHeight = height[qid];
		
			}
			else {
				id[qid] = pid;
				height[pid] = Math.max(height[qid]+1, height[pid]);
				maxHeight = height[pid];
			}
	    count--;
    }
    }
   
    public static void main(String[] args) {
       
		StdIn.fromFile ("data/tinyUF.txt"); 		
		int N = StdIn.readInt();
        StdOut.println("\nRunning Union Find for " + N + " nodes...");
        WeightedQuickUnionPathCompression uf = new hw5().new WeightedQuickUnionPathCompression(N);
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (uf.connected(p, q)) continue;
			uf.union(p, q);
		}
		//uf.toGraphviz();
        StdOut.println("Components: " + uf.count() + " -- " + (uf.count() == 2 ? "Correct." : "Incorrect, expecting 2."));
        StdOut.println("Maximum height: " + uf.maxHeight + " -- " + (uf.maxHeight > 0 && uf.maxHeight <= 4 ? "Correct." : "Incorrect, expecting less than lg(10) = 4"));

		
		StdIn.fromFile ("data/mediumUF.txt"); 
		N = StdIn.readInt();
        StdOut.println("\nRunning Union Find for " + N + " nodes...");
		uf = new hw5().new WeightedQuickUnionPathCompression(N);		
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (uf.connected(p, q)) continue;
			uf.union(p, q);
		}
		//uf.toGraphviz();
        StdOut.println("Components: " + uf.count() + " -- " + (uf.count() == 3 ? "Correct." : "Incorrect, expecting 3."));
        StdOut.println("Maximum height: " + uf.maxHeight + " -- " + (uf.maxHeight > 0 && uf.maxHeight <= 10 ? "Correct." : "Incorrect, expecting less than lg(625) = 10"));

    
		StdIn.fromFile ("data/largeUF.txt"); 
		N = StdIn.readInt();
        StdOut.println("\nRunning Union Find for " + N + " nodes...");
		uf = new hw5().new WeightedQuickUnionPathCompression(N);		
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (uf.connected(p, q)) continue;
			uf.union(p, q);
		}
		//uf.toGraphviz();
        StdOut.println("Components: " + uf.count() + " -- " + (uf.count() == 6 ? "Correct." : "Incorrect, expecting 6."));
        StdOut.println("Maximum height: " + uf.maxHeight + " -- " + (uf.maxHeight > 0 && uf.maxHeight <= 20 ? "Correct." : "Incorrect, expecting less than lg(1M) = 20"));
    }

}

