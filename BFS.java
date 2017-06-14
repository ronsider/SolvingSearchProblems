
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;


/**
 *
 * @author Zafrir
 */
public class BFS extends Algorithms{


    private HashMap<Integer, Node> closedList;
    private Queue<Node> openList;
    private CAreaMap cam;


    public BFS(CAreaMap cam) throws FileNotFoundException {
        path = "";
        numOfVertexExp = 0;
        cost = 0;
        startTime=(double) System.currentTimeMillis();
        this.cam = cam;
        this.mat = cam.getMat();
        this.start = cam.getStartPoint();
        this.finish = cam.getFinishPoint();
        closedList = new HashMap<>();
        openList = new LinkedList<Node>();
        bfs();
    }

 
    
    public void bfs() throws FileNotFoundException {
        boolean found=false;
  
        openList.add(start);
        Node node;
        ArrayList<Node> sons;
        while (!openList.isEmpty() && !found) {
            node = openList.poll();
            closedList.put(node.hashCode(), node);
            numOfVertexExp++;
            sons = cam.getAllSons(node);
            for (Node son : sons) {
                son.setParent(node);
                if (!closedList.containsKey(son.hashCode()) && !openList.contains(son)) {
                    if (son.equals(finish)) {  
                        prints(son);
                        found=true;
                        break;
                    }
                    openList.add(son);
                }
            }
        }
        if(!found)
            System.out.println("no path");
    }
    
    private void prints(Node node) throws FileNotFoundException{
        PrintWriter pw=new PrintWriter("output.txt");
        path=makePath(node);
        pw.write(path.substring(0, path.length()-1)+"\n");
        pw.write("Num: "+numOfVertexExp+"\n");
        pw.write("Cost: "+cost+"\n");
        pw.write(((System.currentTimeMillis()-startTime)*0.001)+" seconds"+"\n");
        pw.close();
        //System.out.println("bfs:");
        //System.out.println("Nums: "+numOfVertexExp);
        //System.out.println(makePath(node));
        //System.out.println("cost: "+cost);
        //System.out.println("time take: "+((System.currentTimeMillis()-startTime)*0.001)+"\n");
    }
}




