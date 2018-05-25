public class DiamondList {

  private Diamond[] diamonds;

  public DiamondList() {
    diamonds = new Diamond[9];
    for (int i=0; i<9; i++) diamonds[i] = new Diamond();
  }

  public DiamondPanel getDiamondPanel(int i) {
    return diamonds[i-1].getPanel();
  }

}
