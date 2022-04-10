package dev.polarian.assignment.objects.containers;

public abstract class Container {
    private final int id;

    public Container(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
