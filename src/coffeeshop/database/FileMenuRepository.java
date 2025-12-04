package coffeeshop.database;

import coffeeshop.model.MenuItem;
import java.util.List;

public class FileMenuRepository implements MenuRepository {

    @Override
    public List<MenuItem> loadMenu() {
        return FileDatabase.loadMenu();
    }

    @Override
    public void saveMenu(List<MenuItem> menu) {
        FileDatabase.saveMenu(menu);
    }

    @Override
    public void addMenuItem(MenuItem item) {
        FileDatabase.addMenuItem(item);
    }

    @Override
    public void deleteMenuItem(int id) {
        FileDatabase.deleteMenuItem(id);
    }

    @Override
    public void updateMenuItem(MenuItem updatedItem) {
        FileDatabase.updateMenuItem(updatedItem);
    }

    @Override
    public MenuItem findMenuItem(int id) {
        return FileDatabase.findMenuItem(id);
    }

    @Override
    public int getNextMenuItemId() {
        return FileDatabase.getNextMenuItemId();
    }
}


