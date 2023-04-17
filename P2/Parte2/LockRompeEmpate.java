package P2.Parte2;

public class LockRompeEmpate {

    private static volatile int[] flag;
    private static volatile int[] turn;
    private static final int m = 2;
    public LockRompeEmpate(int m) {
        flag = new int[m];
        turn = new int[m];
    }
   
    private boolean check(int id, int iteracion){
        for(int i = 0; i < m; ++i){
            if(i == id)
                continue;
            if(flag[i] >= iteracion)
                return true;
        }
        return false;
    }

    public void takeLock(int id){
        for(int i = 0; i < m;i++){
            turn[i] = id;
            turn = turn;
            flag[id] = i;
            flag = flag;
            while(check(id, i)&& turn[i] == id);
        }
    }

    public void releaseLock(int id){
        flag[id] = -1;
        flag = flag;
    }

}