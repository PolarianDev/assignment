package dev.polarian.assignment.objects;

import dev.polarian.assignment.App;
import dev.polarian.assignment.Quay;
import dev.polarian.assignment.objects.containers.Container;

public class Truck extends Thread {
    private final int id;

    public Truck(int id) {
        this.id = id;
    }

    private final void log(String msg) {
        System.out.println("[Truck " + this.id + "]: " + msg);
    }

    @Override
    public void run() {
        while (Quay.isRemaining()) {
            Container container = Quay.getContainer();
            switch (container.getClass().getSimpleName()) {
                case "HeatedContainer" -> this.log("Attached heating element to container");
                case "CooledContainer" -> this.log("Attached cooling element to container");
            }
            log("Collected container " + container.getId());
            try {
                Thread.sleep(App.getRandom().nextInt(1000, 4000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log("Delivered container " + container.getId());
        }
    }
}
