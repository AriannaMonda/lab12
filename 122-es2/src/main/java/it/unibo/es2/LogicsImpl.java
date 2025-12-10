package it.unibo.es2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class LogicsImpl implements Logics {
    private final int size;
    private final Map<Pair<Integer, Integer>,String> map;
    /**
     * Constructor.
     *
     * @param size the size of the logics
     */

    public LogicsImpl(final int size) {
        Objects.requireNonNull(size);
        this.size = size;
        this.map = new LinkedHashMap<>();
        //inizializzo la mappa
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.map.put(new Pair<>(i, j), " ");
            }
        }
    }

    @Override
    public int size() {
       return this.size;
    }

    @Override
    public String hit(final Pair<Integer, Integer> elem) {
        if (map.get(elem).equals(" ")) {
            map.put(elem, "*");
            return "*";
        }
        else {
            map.put(elem, " ");
            return " ";
        }
    }

    private Boolean checkCol(Pair<Integer, Integer> elem) {
        for (int i = 0; i < this.size; i++) {
            if (map.get(new Pair<>(elem.x(), i)).equals(" ")) {
                return false;
            }
        }
        return true;
    }

    private Boolean checkRow(Pair<Integer, Integer> elem) {
        for (int i = 0; i < this.size; i++) {
            if (map.get(new Pair<>(i, elem.y())).equals(" ")) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public Boolean toQuit(Pair<Integer, Integer> elem) {
        if (this.map.get(elem).equals(" ")) {
            return false;
        } else {
            return checkCol(elem) || checkRow(elem);
        }
    }
}