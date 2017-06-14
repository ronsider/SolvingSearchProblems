
/**
 *
 * @author Zafrir
 */
public class Algorithms {
    protected String path;
    protected int numOfVertexExp;
    protected int cost;
    protected double startTime;
    protected char[][] mat;
    protected Node start;
    protected Node finish;
    
    
    
    public String makePath(Node start) {
        path="";
        cost=0;
        Node parent = start.getParent();
        int x, y;
        while (parent != null) {
            x = start.getX() - parent.getX();
            y = start.getY() - parent.getY();
            switch (mat[start.getY()][start.getX()]) {
                case 'D':
                    cost += 2;
                    break;
                case 'R':
                    cost += 3;
                    break;
                case 'H':
                    cost += 5;
                    break;
                case 'G':
                    cost += 5;
                    break;
            }
            if (y > 0) {
                if (x > 0) {
                    path = "RD-" + path;
                } else if (x == 0) {
                    path = "D-" + path;
                } else {
                    path = "DL-" + path;
                }
            } else if (y < 0) {
                if (x > 0) {
                    path = "RU-" + path;
                } else if (x == 0) {
                    path = "U-" + path;
                } else {
                    path = "UL-" + path;
                }
            } else if (x > 0) {
                path = "R-" + path;
            } else {
                path = "L-" + path;
            }
            start = parent;
            parent = parent.getParent();
        }
        return path;
    }
}
