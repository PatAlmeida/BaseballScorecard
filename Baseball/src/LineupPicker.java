import javax.swing.*;
import java.awt.*;

public class LineupPicker extends JPanel {

  private static Font NAME_FONT = new Font("Times", Font.PLAIN, 18);

  private JLabel[] lineupNums;
  private JTextField[] nameFields;
  private JTextField[] posFields;
  private JButton submit;

  public LineupPicker() {
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(0, 5, 0, 5);
    c.gridx = 0; c.gridy = 0;
    add(Box.createHorizontalStrut(1), c);
    c.gridx = 1; c.gridy = 0;
    add(Box.createVerticalStrut(7), c);
    c.gridx = 1; c.gridy = 1;
    add(new JLabel("Lineup"), c);
    c.gridx++;
    add(new JLabel("Player Name"), c);
    c.gridx++;
    add(new JLabel("Position"), c);
    c.gridy++;
    add(Box.createVerticalStrut(7), c);
    c.gridx = 1; c.gridy++;
    lineupNums = new JLabel[9];
    nameFields = new JTextField[9];
    posFields = new JTextField[9];
    for (int i=0; i<9; i++) {
      lineupNums[i] = new JLabel(Integer.toString(i+1));
      nameFields[i] = new JTextField(11);
      nameFields[i].setFont(NAME_FONT);
      posFields[i] = new JTextField(3);
      posFields[i].setFont(NAME_FONT);
      posFields[i].setHorizontalAlignment(SwingConstants.CENTER);
      c.gridx = 1;
      add(lineupNums[i], c);
      c.gridx++;
      add(nameFields[i], c);
      c.gridx++;
      add(posFields[i], c);
      c.gridy++;
    }
    add(Box.createVerticalStrut(7), c);
    c.gridx = 4; c.gridy = 0;
    add(Box.createHorizontalStrut(7), c);
    submit = new JButton("SUBMIT");
    submit.setPreferredSize(new Dimension(100, 25));
    submit.addActionListener((e) -> {
      JFrame frame = new JFrame("Baseball");
      Scorecard scorecard = new Scorecard();
      frame.add(scorecard.getPanel());
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
    });
    c.gridx = 0; c.gridy = 13; c.gridwidth = 5;
    add(submit, c);
    c.gridy++;
    add(Box.createVerticalStrut(7), c);
  }

}
