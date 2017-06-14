
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author Zafrir
 */
public class IDAStar extends Algorithms {

    public class ansNode {

        int t;
        Node node;

        public ansNode(int t, Node node) {
            this.t = t;
            this.node = node;
        }

        public int getT() {
            return t;
        }

        public Node getNode() {
            return node;
        }
    }

    private CAreaMap cam;

    public IDAStar(CAreaMap cam) throws FileNotFoundException {
        path = "";
        numOfVertexExp = 0;
        cost = 0;
        startTime = (double) System.currentTimeMillis();
        this.cam = cam;
        this.mat = cam.getMat();
        this.start = cam.getStartPoint();
        this.finish = cam.getFinishPoint();
        idastar();
    }

    private int heuristic(Node start) {
        int distance = (int) Math.sqrt(Math.pow(start.getX() - finish.getX(), 2) + Math.pow(start.getY() - finish.getY(), 2));
        return distance * 2;
    }

    private void idastar() throws FileNotFoundException {
        ansNode t;
        boolean found = false;
        int bound = heuristic(start);
        while (!found && (System.currentTimeMillis()-startTime<100)) {
            t = search(start, 0, bound);
            if (t.getT() == -1) {
                prints(t.getNode());
                found = true;
            }
            if (t.getT() >= cam.getMatSize() * cam.getMatSize() * 5) {
                found = true;
                System.out.println("no path");
                PrintWriter pw = new PrintWriter("output.txt");
                pw.write("no path");
                pw.close();
            }
            bound = t.getT();
        }
    }

    private ansNode search(Node node, int g, int bound) {
        numOfVertexExp++;
        ansNode ans, t;
        ArrayList<Node> neighbors;
        int f = g + heuristic(node);
        if (f > bound) {
            ans = new ansNode(f, node);
            return ans;
        }
        if (node.equals(finish)) {
            ans = new ansNode(-1, node);
            return ans;
        }
        int min = cam.getMatSize() * cam.getMatSize() * 5;
        neighbors = cam.getAllSons(node);
        for (Node neighbor : neighbors) {
            neighbor.setParent(node);
            neighbor.setG(neighbor.getParent().getG() + neighbor.getPriceOfMat());
            t = search(neighbor, neighbor.getG(), bound);
            if (t.getNode().equals(finish)) {
                ans = new ansNode(-1, t.getNode());
                return ans;
            }
            if (t.getT() < min) {
                min = t.getT();
            }
        }
        ans = new ansNode(min, node);
        return ans;
    }

    private void prints(Node node) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("output.txt");
        path = makePath(node);
        pw.write(path.substring(0, path.length() - 1) + "\n");
        pw.write("Num: " + numOfVertexExp + "\n");
        pw.write("Cost: " + cost + "\n");
        pw.write(((System.currentTimeMillis() - startTime) * 0.001) + " seconds" + "\n");
        pw.close();
        //System.out.println("IDA*: ");
        //System.out.println("Nums: " + numOfVertexExp);
        //System.out.println(makePath(node));
        //System.out.println("cost: " + cost);
        //System.out.println("time take: " + ((System.currentTimeMillis() - startTime) * 0.001) + "\n");
    }
}

