public class PlayerList {

  private Player[] list;

  public PlayerList() {
    list = new Player[9];
    list[0] = new Player("Nunez", "4", 1);
    list[1] = new Player("Betts", "9", 2);
    list[2] = new Player("Benintendi", "8", 3);
    list[3] = new Player("Ramirez", "DH", 4);
    list[4] = new Player("Devers", "5", 5);
    list[5] = new Player("Bogaerts", "6", 6);
    list[6] = new Player("Moreland", "3", 7);
    list[7] = new Player("Young", "7", 8);
    list[8] = new Player("Vazquez", "2", 9);
  }

  public Player getPlayerByName(String n) {
    for (Player p : list) {
      if (p.getName().equals(n)) return p;
    }
    return null;
  }

  public PlayerPanel getBatterPanel(int i) {
    return list[i-1].getPanel();
  }

}
