package dev.polarian.assignment;

import dev.polarian.assignment.objects.containers.Container;

import java.util.List;

public class Ship {
    private static List<Container> containers;

    public static void setContainers(List<Container> containerList) {
        containers = containerList;
    }

    public static synchronized Container getContainer() {
        Container container = containers.get(0);
        containers.remove(0);
        log("Container " + container.getId() + " removed");
        return container;
    }

    public static boolean isEmpty() {
        return containers.isEmpty();
    }

    private static void log(String msg) {
        System.out.println("[Ship]: " + msg);
    }
}
