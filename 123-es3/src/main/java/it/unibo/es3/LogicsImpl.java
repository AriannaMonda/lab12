package it.unibo.es3;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.random.*;
/**
 * Implementation of Logics.
 */

public class LogicsImpl implements Logics, Serializable {
    
    @Serial 
    private static final long serialVersionUID = 1L;
    private final int size;
    private final Map<Pair<Integer, Integer>, String> map = new LinkedHashMap<>();

    /**
     * Constructor.
     *
     * @param size the size of the logics
     */

    public LogicsImpl(final int size) {
        Objects.requireNonNull(size);
        this.size = size;
        //inizializzo la mappa
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.map.put(new Pair<>(i, j), " ");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
       return this.size;
    }

    /**
    * {@inheritDoc}
    * @param pair 
    */
    @Override
    public String hit(Pair<Integer, Integer> pair) {
        map.put(pair, "*");
        return "*";
    }

    @Override
    public Pair<Integer, Integer> random() {
       Pair<Integer, Integer> pair;
       RandomGenerator rand = new Random();
        do{
            final int x = rand.nextInt(size);
            final int y = rand.nextInt(size);
            pair = new Pair<>(x,y);
        } while("*".equals(map.get(pair)));
        return pair;
    }

    @Override
    public Pair<Integer, Integer> fill() {
        //riempire i bottoni che sono attorno a quelli gia segnati da "*"
        for (Pair<Integer, Integer> pair : this.map.keySet()){
            if("*".equals(map.get(pair))){
                for (int i = pair.x()-1; i <= pair.x()+1; i++){
                    for (int j = pair.y()-1; i <= pair.y()+1; i++){
                        return new Pair<>(i, j);
                    }
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * la quit mi controllla se la mappa è piena di "*" se lo è, ritorno true, altrimenti false.
     */
    @Override
    public Boolean toQuit() {
        for (Pair<Integer, Integer> pair : this.map.keySet()){
            if(!"*".equals(map.get(pair))){
                return false;
            }
        }
        return true;
    }
}
