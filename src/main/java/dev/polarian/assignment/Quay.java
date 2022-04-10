package dev.polarian.assignment;

import dev.polarian.assignment.objects.containers.Container;
import dev.polarian.assignment.objects.containers.CooledContainer;
import dev.polarian.assignment.objects.containers.HeatedContainer;

import java.util.ArrayList;
import java.util.List;

public class Quay {
    private static final List<Container> containers = new ArrayList<>();
    private static boolean isRemaining = true;

    public static void placeContainer(Container container) {
        synchronized (containers) {
            while (containers.size() >= 5) {
                try {
                    containers.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            containers.add(container);
            log("Container " + container.getId() + " placed in location " + containers.size());
            containers.notifyAll();
        }
    }

    public static Container getContainer() {
        synchronized (containers) {
            while (containers.isEmpty()) {
                try {
                    containers.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Container container = null;
            for (Container c : containers) {
                if (c instanceof CooledContainer || c instanceof HeatedContainer) {
                    container = c;
                }
            }
            if (container == null) {
                container = containers.get(0);
            }
            containers.remove(container);
            log("Container " + container.getId() + " collected");
            containers.notifyAll();

            if (container.getId() == 100) {
                isRemaining = false;
            }

            return container;
        }
    }

    public static boolean isRemaining() {
        return isRemaining;
    }

    private static void log(String msg) {
        System.out.println("[Quay]: " + msg);
    }
}
