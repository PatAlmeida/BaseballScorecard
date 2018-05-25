public class Lineup {

  private PlayerList playerList;

  public Lineup() {
    playerList = new PlayerList();
  }

  public Player getPlayerByName(String n) {
    return playerList.getPlayerByName(n);
  }

  public PlayerPanel getBatterPanel(int i) {
    return playerList.getBatterPanel(i);
  }

}
