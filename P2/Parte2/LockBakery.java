package P2.Parte2;

public class LockBakery {
    
    private volatile int turn[];
    private int m;

    public LockBakery(int m) {
        this.m = m;
        this.turn = new int[m];
        for (int i = 0 ; i < m ; ++i) {
            turn[i] = 1;
            turn = turn;
        }
    }

    private boolean aux(int a, int b, int c, int d) {
        if ((a > c) || (a == c && b > d))
            return true;
        return false;
    }

    public void takeLock(int id){
        turn[id] = 0;
        turn = turn;
        turn[id] = max(turn) + 1;
        turn = turn;
        for (int j = 0; j < m; ++j) {
            if (id != j)
                while(turn[j] != 0 && aux(turn[id], id, turn[j], j));
        }
    }

    public void releaseLock(int id){
        turn[id] = 0;
        turn = turn;
    }

    private int max(int[] turn) {
        int max = turn[0];
        for (int i = 1; i < turn.length; ++i) {
            if (turn[i] > max) {
                max = turn[i];
            }
        }
        return max;
    }
    
}