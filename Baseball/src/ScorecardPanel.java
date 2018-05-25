import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScorecardPanel extends JPanel implements MouseListener, ActionListener {

  private static int LAYOUT_INSET = 15;

  private Scorecard scorecard;
  private PlayerPanel[] batters;
  private DiamondPanel[] diamonds;
  private JButton[] buttons;
  private JPanel buttonPanel;
  private int firstInningNum;
  private JLabel[] inningLabels;
  private JPanel[] inningLabelPanels;

  public ScorecardPanel(Scorecard s) {

    scorecard = s;

    addMouseListener(this);

    batters = new PlayerPanel[3];
    for (int i=0; i<3; i++) batters[i] = scorecard.getBatterPanel(i+1);

    diamonds = new DiamondPanel[9];
    for (int i=0; i<3; i++) {
      for (int j=0; j<3; j++) {
        diamonds[i*3+j] = scorecard.getDiamondPanel(i+1, j+1);
      }
    }

    buttons = new JButton[4];
    buttons[0] = new JButton("UP");
    buttons[1] = new JButton("DOWN");
    buttons[2] = new JButton("LEFT");
    buttons[3] = new JButton("RIGHT");
    for (int i=0; i<4; i++) buttons[i].addActionListener(this);

    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(LAYOUT_INSET, LAYOUT_INSET, LAYOUT_INSET, LAYOUT_INSET);

    firstInningNum = 1;
    inningLabels = new JLabel[3];
    inningLabelPanels = new JPanel[3];
    c.gridx = 1; c.gridy = 0;
    for (int i=0; i<3; i++) {
      inningLabels[i] = new JLabel(Integer.toString(i+1));
      inningLabels[i].setFont(new Font("Times", Font.BOLD, 20));
      inningLabelPanels[i] = new JPanel();
      inningLabelPanels[i].setLayout(new GridBagLayout());
      inningLabelPanels[i].add(inningLabels[i]);
      add(inningLabelPanels[i], c);
      c.gridx++;
    }

    c.gridx = 0; c.gridy = 1;
    for (int i=0; i<3; i++) {
      add(batters[i], c);
      c.gridy++;
    }

    c.gridx = 1; c.gridy = 1;
    for (int i=0; i<3; i++) {
      for (int j=0; j<3; j++) {
        add(diamonds[i*3+j], c);
        c.gridy++;
      }
      c.gridy = 1; c.gridx++;
    }

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    for (int i=0; i<buttons.length; i++) {
      buttonPanel.add(buttons[i]);
      if (i != buttons.length - 1) buttonPanel.add(Box.createRigidArea(new Dimension(15, 0)));
    }
    c.gridx = 0; c.gridwidth = 4; c.gridy = 4;
    add(buttonPanel, c);

  }

  public void actionPerformed(ActionEvent e) {
    JButton src = (JButton) e.getSource();
    String name = src.getText();
    if (name.equals("UP") || name.equals("DOWN")) {
      GridBagLayout layout = (GridBagLayout) getLayout();
      GridBagConstraints c = layout.getConstraints(this);
      c.insets = new Insets(LAYOUT_INSET, LAYOUT_INSET, LAYOUT_INSET, LAYOUT_INSET);
      c.gridx = 0; c.gridy = 1;
      for (int i=0; i<3; i++) {
        remove(batters[i]);
        int num = batters[i].getPlayerLineupNum();
        batters[i] = scorecard.getNextBatter(num, name);
        for (int j=0; j<3; j++) {
          int index = i + j*3;
          remove(diamonds[index]);
          int inning = firstInningNum + j;
          if (inning > 9) inning -= 9;
          int batNum = num;
          if (name.equals("DOWN")) {
            batNum++; if (batNum > 9) batNum -= 9;
          } else {
            batNum--; if (batNum < 1) batNum += 9;
          }
          diamonds[index] = scorecard.getDiamondPanel(inning, batNum);
        }
      }
      c.gridx = 0;
      for (int i=0; i<3; i++) {
        c.gridy = i+1;
        add(batters[i], c);
      }
      c.gridx = 1; c.gridy = 1;
      for (int i=0; i<3; i++) {
        for (int j=0; j<3; j++) {
          add(diamonds[i*3+j], c);
          c.gridy++;
        }
        c.gridy = 1; c.gridx++;
      }
      revalidate();
      repaint();
    } else {
      GridBagLayout layout = (GridBagLayout) getLayout();
      GridBagConstraints c = layout.getConstraints(this);
      c.insets = new Insets(LAYOUT_INSET, LAYOUT_INSET, LAYOUT_INSET, LAYOUT_INSET);
      int firstBatterNum = batters[0].getPlayerLineupNum();
      for (int i=0; i<3; i++) {
        int next = 0;
        if (name.equals("RIGHT")) {
          next = firstInningNum+i+1;
          if (next > 9) next -= 9;
        } else {
          int z = i-1;
          next = firstInningNum+z;
          if (next > 9) next -= 9;
          if (next < 1) next += 9;
        }
        inningLabels[i].setText(Integer.toString(next));
        for (int j=0; j<3; j++) {
          int index = i + j*3;
          remove(diamonds[index]);
          int inning = 0; int batter = 0;
          if (name.equals("RIGHT")) {
            inning = firstInningNum + j + 1;
            if (inning > 9) inning -= 9;
            batter = firstBatterNum + i;
            if (batter > 9) batter -= 9;
          } else {
            inning = firstInningNum + j - 1;
            if (inning > 9) inning -= 9;
            if (inning < 1) inning += 9;
            batter = firstBatterNum + i;
            if (batter > 9) batter -= 9;
          }
          diamonds[index] = scorecard.getDiamondPanel(inning, batter);
        }
      }
      if (name.equals("RIGHT")) {
        firstInningNum++; if (firstInningNum > 9) firstInningNum -= 9;
      } else {
        firstInningNum--; if (firstInningNum < 1) firstInningNum += 9;
      }
      c.gridx = 1; c.gridy = 1;
      for (int i=0; i<3; i++) {
        for (int j=0; j<3; j++) {
          add(diamonds[i*3+j], c);
          c.gridy++;
        }
        c.gridy = 1; c.gridx++;
      }
      revalidate();
      repaint();
    }
  }

  public void mousePressed(MouseEvent e) { }
  public void mouseReleased(MouseEvent e) { }
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }
  public void mouseClicked(MouseEvent e) { }

}
