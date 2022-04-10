package dev.polarian.assignment.objects;

import dev.polarian.assignment.App;
import dev.polarian.assignment.Quay;
import dev.polarian.assignment.Ship;
import dev.polarian.assignment.objects.containers.Container;

public class Crane extends Thread {
    private final int craneId;

    public Crane(int id) {
        this.craneId = id;
        this.setName("Crane-" + id);
    }

    public int getCraneId() {
        return craneId;
    }

    private void log(String msg) {
        System.out.println("[Crane " + craneId + "]: " + msg);
    }

    @Override
    public void run() {
        while (!Ship.isEmpty()) {
            Container container = Ship.getContainer();
            switch (container.getClass().getSimpleName()) {
                case "CooledContainer" -> this.log("Disconnected cooling element");
                case "HeatedContainer" -> this.log("Disconnected heating element");
            }
            this.log("Picked up container with id " + container.getId());

            try {
                Thread.sleep(App.getRandom().nextInt(1000, 6000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Quay.placeContainer(container);
            log("Placed container " + container.getId() + " on Quay");
        }
    }
}
