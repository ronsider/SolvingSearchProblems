
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 *
 * @author Zafrir
 */
public class DFID extends Algorithms{

    private CAreaMap cam;
    private Stack<Node> stack;

    public DFID(CAreaMap cam) throws FileNotFoundException {
        path = "";
        numOfVertexExp = 0;
        cost = 0;
        startTime=(double) System.currentTimeMillis();
        this.cam = cam;
        this.mat = cam.getMat();
        this.start = cam.getStartPoint();
        this.finish = cam.getFinishPoint();
        stack = new Stack<Node>();
        DFID();
    }

    public void DFID() throws FileNotFoundException {
        Node g = null;
        for (int maxDepth = 1; maxDepth < mat.length*mat.length; maxDepth++) {
            g = depthFirstSearch(start, maxDepth);
            if (g != null) {
                break;
            }
            stack.clear();
        }
        if(g==null){
            //System.out.println("no path");
            PrintWriter pw=new PrintWriter("output.txt");
            pw.write("no path");
            pw.close();
        }
            
    }

    public Node depthFirstSearch(Node n, int maxDepth) throws FileNotFoundException {
        stack.push(start);
        start.setDepth(0);
        Node g;
        ArrayList<Node> sons;
        ArrayList<Node> visited = new ArrayList<>();
        while (!stack.isEmpty()) {
            n = stack.pop();
            numOfVertexExp++;
            visited.add(n);
            sons = cam.getAllSons(n);
            for (int i=sons.size()-1;i>=0;i--) {
                g=sons.get(i);
                if (!stack.contains(g) && !visited.contains(g)) {
                    g.setParent(n);
                    g.setDepth((g.getParent().getDepth())+1);
                    if (g.equals(finish)) {
                        prints(g);
                        return g;
                    }
                    if (g.getDepth() < maxDepth) {
                        stack.push(g);
                    }    
                }
            }       
        }
        return null;
    }
    private void prints(Node g) throws FileNotFoundException{
        PrintWriter pw=new PrintWriter("output.txt");
        path=makePath(g);
        pw.write(path.substring(0, path.length()-1)+"\n");
        pw.write("Num: "+numOfVertexExp+"\n");
        pw.write("Cost: "+cost+"\n");
        pw.write(((System.currentTimeMillis()-startTime)*0.001)+" seconds"+"\n");
        pw.close();
        //System.out.println("dfid: ");
        //System.out.println("Nums: "+numOfVertexExp);
        //System.out.println(makePath(g));
        //System.out.println("cost: "+cost);
        //System.out.println("time take: "+((System.currentTimeMillis()-startTime)*0.001)+"\n");
    }
}
  

