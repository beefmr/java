import java.util.ArrayList;
import java.util.List;

public class Repository<T> {
    private List<T> items;

    public Repository() {
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public void removeItem(T item) {
        items.remove(item);
    }

    public void updateItem(int index, T item) {
        if (index >= 0 && index < items.size()) {
            items.set(index, item);
        }
    }

    public List<T> getItems() {
        return items;
    }
}
