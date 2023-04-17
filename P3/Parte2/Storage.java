package P3.Parte2;

import java.util.ArrayList;

public class Storage implements Almacen{

    private ArrayList<Producto> p;

    public Storage(){
        p = new ArrayList<Producto>();
    }

    @Override
    public void almacenar(Producto producto) {
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
