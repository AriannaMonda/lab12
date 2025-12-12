package it.unibo.es3;

import java.util.List;

/**
 * Interface defining the logic for a slot-based application.
 */
public interface Logics {
    /**
     * The number of slots.
     *
     * @return the number of slots
     */
    int size();

    /**
     *  quit the programm.
     * 
     * @param elem slot 
     * @return true if the program has to quit 
     */
    Boolean toQuit();

    Pair<Integer, Integer> random();

    /**
    * {@inheritDoc}
    * @param pair 
    */
    String hit(Pair<Integer, Integer> pair);

    List<Pair<Integer, Integer>> fill();
}
