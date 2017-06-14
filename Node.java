



/**
 *
 * @author Zafrir
 */
    public class Node implements Comparable<Node>{
        private int x;
        private int y;
        int f,g,h;
        private Node parent;
        private int depth;
        private char letter;

        public Node(int x, int y, Node parent,char letter) {
            this.x = x;
            this.y = y;
            this.parent = parent;
            this.letter=letter;
            this.g=Integer.MAX_VALUE;
        }

        public char getLetter()
        {
            return letter;
        }
        
        public void setLetter(char letter){
            this.letter=letter;
        }
        
        public int getDepth() {
            return depth;
        }

        public int getG() {
            return g;
        }
        
        public void setDepth(int depth) {
            this.depth = depth;
        }
        
        public void setG(int g) {
            this.g = g;
        }
        
        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }
        

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
        
        private int heuristic(Node finish) {
            int distance = (int) Math.sqrt(Math.pow(this.getX() - finish.getX(), 2) + Math.pow(this.getY() - finish.getY(), 2));
            return distance;
        }
        
        public void calcF(Node finish){
            f=heuristic(finish)+this.getG();
        }
        
        private int getF(){
            return f;
        }
        @Override
        public boolean equals(Object o){
            if(o==null || getClass()!=o.getClass())return false;
            if(((Node) o).x==this.x && ((Node)o).y==this.y )
                return true;
            return false;
        }
        
        @Override
        public String toString(){
            return "("+x+","+y+")";
        }
        
        @Override
        public int hashCode() {
            int result =13;
            result=31*result+x;
            result=31*result+y;
            return result;
        }
        
        public int getPriceOfMat(){
            int ans=0;
            switch (this.letter){
                case 'D': ans=2;
                          break;
                case 'R': ans=3;
                          break;
                case 'H': ans=5;
                          break;
                case 'G': ans=5;
                          break;                  
            }
            return ans;
        }
       

    @Override
    public int compareTo(Node o) {
        if(this.getF()<o.getF())
            return -1;
        else if(o.getF()<this.getF())
            return 1;
        return 0;
    }
}