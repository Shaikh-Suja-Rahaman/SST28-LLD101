import java.util.LinkedHashMap;
import java.util.Map;

public class Menu {
  private Map<String, MenuItem> menu;
  Menu() {
    menu = new LinkedHashMap<>();
  }

  public void addToMenu(MenuItem i) {
    menu.put(i.id, i);
  }

  public MenuItem get(String itemId){
    return menu.get(itemId);
  }

}
