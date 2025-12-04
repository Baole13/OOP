package coffeeshop.database;

import coffeeshop.model.MenuItem;
import java.util.List;

public interface MenuRepository {
    List<MenuItem> loadMenu();

    void saveMenu(List<MenuItem> menu);

    void addMenuItem(MenuItem item);

    void deleteMenuItem(int id);

    void updateMenuItem(MenuItem updatedItem);

    MenuItem findMenuItem(int id);

    int getNextMenuItemId();
}


