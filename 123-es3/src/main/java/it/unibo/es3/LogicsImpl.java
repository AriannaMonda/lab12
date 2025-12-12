package it.unibo.es3;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
    //private List<Pair<Integer, Integer>> list = new ArrayList<>();

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
    public List<Pair<Integer, Integer>> fill() {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        //riempire i bottoni che sono attorno a quelli gia segnati da "*"
        for (final Pair<Integer, Integer> pair : this.map.keySet()){
            if("*".equals(map.get(pair))){
                for (int i = pair.x()-1; i <= pair.x()+1; i++){
                    for (int j = pair.y()-1; j <= pair.y()+1; j++){
                        Pair<Integer, Integer> newpair = new Pair<>(i, j);
                        //voglio controllare che newpair sia dentro i limiti della mappa
                        if (map.containsKey(newpair) && !"*".equals(map.get(newpair))){
                            //map.put(newpair, "*"); È SBAGLIATO QUI, se faccio così riempio quasi tutta la griglia in un colpo solo
                            list.add(newpair);
                        }
                    }
                }
            }
        }
        for (Pair<Integer, Integer> p : list) {
            map.put(p, "*");
        }
        //ritorniamo una list di pair e nella gui per ogni pair nel set chiamiamo hit per aggiungere *
        //voglio ritornare tutte le celle che sono state riempite
        return List.copyOf(list);
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
