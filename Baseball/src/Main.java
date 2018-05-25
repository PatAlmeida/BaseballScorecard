import javax.swing.*;
import java.awt.*;

public class Main {
  public static Scorecard scorecard = new Scorecard();
  public static LineupPicker lineupPicker = new LineupPicker();
  public static void main(String[] args) {
    JFrame frame = new JFrame("Baseball");
    //frame.add(scorecard.getPanel());
    frame.add(lineupPicker);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
