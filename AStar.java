
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


/**
 *
 * @author Zafrir
 */
public class AStar extends Algorithms {

    private HashMap<Integer, Node> closedList;
    private Queue<Node> openList;
    private CAreaMap cam;

    public AStar(CAreaMap cam) throws FileNotFoundException {
        path = "";
        numOfVertexExp = 0;
        cost = 0;
        startTime=(double) System.currentTimeMillis();
        this.cam = cam;
        this.mat = cam.getMat();
        this.start = cam.getStartPoint();
        this.finish = cam.getFinishPoint();
        closedList = new HashMap<>();
        openList = new PriorityQueue<>(this.mat.length * this.mat[0].length);
        astar();
    }


    public void astar() throws FileNotFoundException {
        ArrayList<Node> neighbors;
        Node node;
        boolean found=false;
        start.setG(0);
        openList.add(start);
        while (!openList.isEmpty()) {
            node = openList.poll();
            numOfVertexExp++;
            if (node.equals(finish)) {
                found=true;
                prints(node);
            }
            closedList.put(node.hashCode(), node);
            neighbors = cam.getAllSons(node);
            for (Node neighbor : neighbors) {
                  if (closedList.containsKey(neighbor.hashCode())) continue;
                  if(neighbor.getPriceOfMat()+node.getG()<neighbor.getG()){
                      neighbor.setParent(node);
                      neighbor.setG(neighbor.getPriceOfMat()+node.getG());
                      neighbor.calcF(finish);
                      if (!openList.contains(neighbor)) {
                            openList.add(neighbor);
                        }
                  }
            }
        }
        if(!found){
            System.out.println("no path");
            PrintWriter pw=new PrintWriter("output.txt");
            pw.write("no path");
            pw.close();
        }
            
    }
    
        private void prints(Node g) throws FileNotFoundException{
        PrintWriter pw=new PrintWriter("output.txt");
        path=makePath(g);
        pw.write(path.substring(0, path.length()-1)+"\n");
        pw.write("Num: "+numOfVertexExp+"\n");
        pw.write("Cost: "+cost+"\n");
        pw.write(((System.currentTimeMillis()-startTime)*0.001)+" seconds"+"\n");
        pw.close();
        //System.out.println("a*: ");
        //System.out.println("Nums: "+numOfVertexExp);
        //System.out.println(makePath(g));
        //System.out.println("cost: "+cost);
        //System.out.println("time take: "+((System.currentTimeMillis()-startTime)*0.001)+"\n");
    }
}



