package it.unibo.es2;

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
    Boolean toQuit(Pair<Integer, Integer> elem);

    /**
     * press the button.
     * 
     * @param elem slot
     * @return " " or * when a button is pressed 
     */
    String hit(Pair<Integer, Integer> elem);
}
