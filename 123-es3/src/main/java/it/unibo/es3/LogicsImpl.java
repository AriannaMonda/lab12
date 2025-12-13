package it.unibo.es3;

import java.io.Serial;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.random.RandomGenerator;
/**
 * Implementation of Logics.
 */

public class LogicsImpl implements Logics {

    @Serial 
    private static final long serialVersionUID = 1L;
    private static final String ASTRX = "*";
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
     * 
     * @param pair change the empty button to "*".
     */
    @Override
    public String hit(final Pair<Integer, Integer> pair) {
        map.put(pair, ASTRX);
        return ASTRX;
    }

    /**
     * {@inheritDoc}
     * random function.
     */
    @Override
    public Pair<Integer, Integer> random() {
       Pair<Integer, Integer> pair;
       final RandomGenerator rand = new Random();
        do {
            final int x = rand.nextInt(size);
            final int y = rand.nextInt(size);
            pair = new Pair<>(x, y);
        } while (ASTRX.equals(map.get(pair)));
        return pair;
    }

    /**
     * {@inheritDoc}
     * fill function.
     */
    @Override
    public List<Pair<Integer, Integer>> fill() {
        final List<Pair<Integer, Integer>> list = new ArrayList<>();
        //riempire i bottoni che sono attorno a quelli gia segnati da "*"
        for (final Map.Entry<Pair<Integer, Integer>, String> entry : this.map.entrySet()) {
            if (ASTRX.equals(entry.getValue())) {
                final Pair<Integer, Integer> pair = entry.getKey();
                for (int i = pair.x() - 1; i <= pair.x() + 1; i++) {
                    for (int j = pair.y() - 1; j <= pair.y() + 1; j++) {
                        final Pair<Integer, Integer> newpair = new Pair<>(i, j);
                        //voglio controllare che newpair sia dentro i limiti della mappa
                        if (map.containsKey(newpair) && !ASTRX.equals(map.get(newpair))) {
                            //map.put(newpair, "*"); È SBAGLIATO QUI, riempirei quasi tutta la griglia in un colpo solo
                            list.add(newpair);
                        }
                    }
                }
            }
        }
        for (final Pair<Integer, Integer> p : list) {
            map.put(p, ASTRX);
        }
        return List.copyOf(list);
    }

    /**
     * {@inheritDoc}
     * la quit mi controllla se la mappa è piena di "*" se lo è, ritorno true, altrimenti false.
     */
    @Override
    public Boolean toQuit() {
        for (final String value : this.map.values()) {
            if (!ASTRX.equals(value)) {
                return false;
            }
        }
        return true;
    }
}
