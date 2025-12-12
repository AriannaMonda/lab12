package it.unibo.es3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI for the game.
 */
public final class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final List<JButton> cells = new ArrayList<>();
    private Pair<Integer, Integer> buttonPosition;
    private final Logics logics;
    /**
     * Constructor.
     *
     * @param width the size of the grid
     */
    public GUI(final int width) {
        this.logics = new LogicsImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Create a panel with a grid layout
        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel panelGrid = new JPanel(new GridLayout(width, width));
        final JPanel hitPanel = new JPanel(new BorderLayout()); 
        final JButton hitButton = new JButton(">");
        panelGrid.add(hitButton);
        hitPanel.add(hitButton, BorderLayout.SOUTH);
        this.getContentPane().add(panel);
        panel.add(panelGrid, BorderLayout.NORTH);
        panel.add(hitPanel, BorderLayout.SOUTH);
        // Create buttons and add them to the panel
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                final var pos = new Pair<>(j, i);
                final JButton button = new JButton(" ");
                this.cells.add(button);
                panelGrid.add(button);
            }
        }
        for(int i = 0; i < 3; i++) {
            buttonPosition = logics.random();
            //i button vanno aggiornati
            int index = buttonPosition.y() *width + buttonPosition.x(); //calcolo l'indice del button
            this.cells.get(index).setText(logics.hit(buttonPosition));
        }

        hitButton.addActionListener(e -> {
            if (logics.toQuit()) {
                dispose();
            } else {
                //bisogna aggiornare la griglia
                List<Pair<Integer, Integer>> newList = new ArrayList<>();
                newList = logics.fill();
                for (Pair<Integer, Integer> pair : newList){
                    int index = pair.y() *width + pair.x(); 
                    this.cells.get(index).setText(this.logics.hit(pair));
                }
            }
        });
        pack();
        this.setVisible(true);
    }
}
