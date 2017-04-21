package labs.lab5;

public interface ListI {
    public int size();

    public boolean isEmpty();

    public boolean contains(String item);

    public void add(String item);

    public boolean remove(String item);

    public void clear();

    public String get(int index);

    public void set(int index, String element);

    public void add(int index, String item);

    public String remove(int index);

    public int indexOf(String item); // return -1 if item is not in the list
}