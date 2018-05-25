import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class PlayerPanel extends JPanel {

  private static Font FONT = new Font("Times", Font.BOLD, 20);
  private static Border BLACK_BORDER = BorderFactory.createLineBorder(Color.black);
  private static Border LABEL_PADDING = new EmptyBorder(10, 10, 10, 10);
  private static CompoundBorder LABEL_BORDER = new CompoundBorder(BLACK_BORDER, LABEL_PADDING);

  private Player player;

  public PlayerPanel(Player p) {
    player = p;
    setBorder(BLACK_BORDER);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    add(Box.createHorizontalStrut(20));
    makeLabel(Integer.toString(player.getLineupNum()), 0);
    addSpace();
    makeLabel(player.getName(), 1);
    addSpace();
    makeLabel(player.getPosition(), 2);
    add(Box.createHorizontalStrut(20));
  }

  public void addSpace() {
    add(Box.createHorizontalStrut(20));
    add(new JSeparator(SwingConstants.VERTICAL));
    add(Box.createHorizontalStrut(20));
  }

  public int getPlayerLineupNum() {
    return player.getLineupNum();
  }

  public void makeLabel(String s, int id) {
    JLabel label = new JLabel(s);
    label.setFont(FONT);
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    if (id == 0) panel.setPreferredSize(new Dimension(30, 60));
    if (id == 1) panel.setPreferredSize(new Dimension(120, 60));
    if (id == 2) panel.setPreferredSize(new Dimension(30, 60));
    panel.add(label);
    add(panel);
  }

}
