package it.unibo.es1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Implementation of the Logics interface.
 */
public class LogicsImpl implements Logics {
    private final int size;
    private final List<Integer> list;
    /**
     * Constructor.
     *
     * @param size the size of the logics
     */
    public LogicsImpl(final int size) {
        Objects.requireNonNull(size);
        this.size = size;
        list = new ArrayList<>(this.size);
        //inizializzo la lista: 
        for (int i = 0; i < size; i++) {
            list.add(0);
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
    public List<Integer> values() {
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boolean> enabledStates() {
        return this.list.stream().map(e -> e.compareTo(size)).map(e -> e < 0).toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hit(final int elem) {
        return this.list.set(elem, this.list.get(elem) + 1);
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String result() {
        return this.list.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        for (Integer i : this.values()) {
            if (i <= size) {
                return false;
            }
        }
        return true; 
    }
}
