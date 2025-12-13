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
     * @return true if the program has to quit 
     */
    Boolean toQuit();

    /**
     * random for set the first three buttons.
     * 
     * @return the pairs.
     */
    Pair<Integer, Integer> random();

    /**
     * when a button is pressed, 'hit' turnes it to "*".
     * 
     * @param pair pairs.
     * @return change the empty button to "*"
     */
    String hit(Pair<Integer, Integer> pair);

    /**
     * fill the buttons when the ">" button is pressed.
     * 
     * @return the filled buttons.
     */
    List<Pair<Integer, Integer>> fill();
}
