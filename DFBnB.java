
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;


/**
 *
 * @author Zafrir
 */
public class DFBnB extends Algorithms {

    private CAreaMap cam;
    private double alpha;
    private Stack<Node> stack;

    public DFBnB(CAreaMap cam) throws FileNotFoundException {
        alpha = Double.POSITIVE_INFINITY;
        path = "";
        numOfVertexExp = 0;
        cost = 0;
        startTime = (double) System.currentTimeMillis();
        this.cam = cam;
        this.mat = cam.getMat();
        this.start = cam.getStartPoint();
        this.finish = cam.getFinishPoint();
        stack = new Stack<>();
        dfbnb();
    }

    private int heuristic(Node start) {
        int distance = (int) Math.sqrt(Math.pow(start.getX() - finish.getX(), 2) + Math.pow(start.getY() - finish.getY(), 2));
        return distance * 2;
    }


    private void dfbnb() throws FileNotFoundException {
        String ansPath="";
        int ansCost=0;
        boolean found=false;
        stack.push(start);
        start.setG(0);
        start.setDepth(0);
        Node son, node;
        ArrayList<Node> visited = new ArrayList<>();
        int f;
        ArrayList<Node> sons;
        while (!stack.isEmpty()) {
            node = stack.pop();
            numOfVertexExp++;
            visited.add(node);
            sons = cam.getAllSons(node);
            for (int i = sons.size() - 1; i >= 0; i--) {
                son = sons.get(i);
                if (!stack.contains(son) && !visited.contains(son)) {
                    if(son.getPriceOfMat()+node.getG()<son.getG()){                        
                        son.setParent(node);
                        son.setG(son.getParent().getG() + son.getPriceOfMat());
                        son.setDepth(son.getParent().getDepth()+1);
                    }                   
                    f = son.getG() + heuristic(son);
                    if (son.equals(finish)) {
                        if(f<=alpha){
                            alpha = f;
                            ansPath=makePath(son);
                            ansCost=cost;
                            found=true;
                        }                    
                    }
                    if (f < alpha) {
                        stack.push(son);
                    }
                }
                else{
                    for(int j=0 ; j<visited.size() ; j++){
                        if(visited.get(j).getDepth()>node.getDepth()){
                           visited.remove(j);
                            j--; 
                        }    
                    }
                }

            }
        }
        if(found){
            PrintWriter pw=new PrintWriter("output.txt");
            pw.write(ansPath.substring(0, ansPath.length()-1)+"\n");
            pw.write("Num: "+numOfVertexExp+"\n");
            pw.write("Cost: "+ansCost+"\n");
            pw.write(((System.currentTimeMillis()-startTime)*0.001)+" seconds"+"\n");
            pw.close();
            //System.out.println("dfbnb: ");
            //System.out.println("Nums: " + numOfVertexExp);
            //System.out.println(ansPath);
            //System.out.println("cost: " + ansCost);
            //System.out.println("time take: " + ((System.currentTimeMillis() - startTime) * 0.001) + "\n");
        }
        else{
            //System.out.println("no path");
            PrintWriter pw=new PrintWriter("output.txt");
            pw.write("no path");
            pw.close();
        }
    }
}

