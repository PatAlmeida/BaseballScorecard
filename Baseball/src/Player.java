public class Player {

  private String name;
  private String pos;
  private int num;
  private PlayerPanel playerPanel;

  public Player(String n, String p, int a) {
    name = n; pos = p; num = a;
    playerPanel = new PlayerPanel(this);
  }

  public String getName() { return name; }
  public String getPosition() { return pos; }
  public int getLineupNum() { return num; }
  public PlayerPanel getPanel() { return playerPanel; }

}
