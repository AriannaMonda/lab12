package it.unibo.es3;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
/**
 * Implementation of Logics.
 */

public class LogicsImpl implements Logics {
    private final int size;
    private final Map<Pair<Integer, Integer>, String> map;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
       return this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String hit(final Pair<Integer, Integer> elem) {
        if (" ".equals(map.get(elem))) {
            map.put(elem, "*");
            return "*";
        } else {
            map.put(elem, " ");
            return " ";
        }
    }

    private Boolean checkCol(final Pair<Integer, Integer> elem) {
        for (int i = 0; i < this.size; i++) {
            if (" ".equals(map.get(new Pair<>(elem.x(), i)))) {
                return false;
            }
        }
        return true;
    }

    private Boolean checkRow(final Pair<Integer, Integer> elem) {
        for (int i = 0; i < this.size; i++) {
            if (" ".equals(map.get(new Pair<>(i, elem.y())))) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean toQuit(final Pair<Integer, Integer> elem) {
        if (" ".equals(map.get(elem))) {
            return false;
        } else {
            return checkCol(elem) || checkRow(elem);
        }
    }
}
