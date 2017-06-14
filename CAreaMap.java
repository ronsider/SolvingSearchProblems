
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Zafrir
 */
public class CAreaMap {

    private int algo;
    private int n;
    private Node start;
    private Node finish;
    private char[][] mat;

    public CAreaMap() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("input.txt");
        BufferedReader br = new BufferedReader(fr);
        algo = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        mat = new char[n][n];
        String startFinish[] = br.readLine().replace(")", "").replace("(", "").split(",");
        start = new Node((Integer.parseInt(startFinish[0])), (Integer.parseInt(startFinish[1])), null, 'S');
        finish = new Node((Integer.parseInt(startFinish[2])), (Integer.parseInt(startFinish[3])), null, 'G');
        String line;
        for (int i = 0; i < n; i++) {
            line = br.readLine();
            for (int j = 0; j < n; j++) {
                mat[i][j] = line.charAt(j);
            }
        }
        switch(algo){
            case 1: 
                BFS bfs=new BFS(this);
                break;
            case 2: 
                DFID dfid=new DFID(this);
                break;
            case 3: 
                AStar astar=new AStar(this);
                break;
            case 4: 
                IDAStar idastar=new IDAStar(this);
                break;
            case 5: 
                DFBnB dfbnb=new DFBnB(this);
                break;
           
        }
    }

    public int getAlgoNumber() {
        return algo;
    }

    public int getMatSize() {
        return n;
    }

    public Node getStartPoint() {
        return start;
    }

    public Node getFinishPoint() {
        return finish;
    }

    public char[][] getMat() {
        return mat;
    }

    public ArrayList getAllSons(Node vertex) {
        ArrayList<Node> sons = new ArrayList();
        int x = vertex.getX();
        int y = vertex.getY();
        if ((x + 1) < n && y < n && mat[y][x + 1] != 'X') {
            sons.add(new Node(x + 1, y, null, mat[y][x + 1]));
        }
        if ((x + 1) < n && (y + 1) < n && mat[y + 1][x + 1] != 'X') {
            sons.add(new Node(x + 1, y + 1, null, mat[y + 1][x + 1]));
        }
        if (x < n && (y + 1) < n && mat[y + 1][x] != 'X') {
            sons.add(new Node(x, y + 1, null, mat[y + 1][x]));
        }
        if ((x - 1) >= 0 && (y + 1) < n && mat[y + 1][x - 1] != 'X') {
            sons.add(new Node(x - 1, y + 1, null, mat[y + 1][x - 1]));
        }
        if ((x - 1) >= 0 && y >= 0 && mat[y][x - 1] != 'X') {
            sons.add(new Node(x - 1, y, null, mat[y][x - 1]));
        }
        if ((x - 1) >= 0 && (y - 1) >= 0 && mat[y - 1][x - 1] != 'X') {
            sons.add(new Node(x - 1, y - 1, null, mat[y - 1][x - 1]));
        }
        if (x >= 0 && (y - 1) >= 0 && mat[y - 1][x] != 'X') {
            sons.add(new Node(x, y - 1, null, mat[y - 1][x]));
        }
        if ((x + 1) < n && (y - 1) >= 0 && mat[y - 1][x + 1] != 'X') {
            sons.add(new Node(x + 1, y - 1, null, mat[y - 1][x + 1]));
        }
        return sons;
    }
}
