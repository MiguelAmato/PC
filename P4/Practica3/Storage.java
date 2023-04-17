package P4.Practica3;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage implements Almacen{

    private ArrayList<Producto> p;
    private final Lock l;
    private final Condition vacio;
    private final Condition lleno;
    private volatile int cont;
    public Storage(){
        l = new ReentrantLock(true);
        vacio = l.newCondition();
        lleno = l.newCondition();
        p = new ArrayList<Producto>();
    }

    @Override
    public void almacenar(Producto producto) {
        l.lock();
        try{
            while(cont == )
        }
        p.add(producto);
    }

    @Override
    public Producto extraer() {
        Producto ret = p.get(0);
        p.remove(0);
        return ret;
    }

    public int size(){
        return p.size();
    }
}
