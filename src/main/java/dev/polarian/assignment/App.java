package dev.polarian.assignment;

import dev.polarian.assignment.objects.Crane;
import dev.polarian.assignment.objects.Truck;
import dev.polarian.assignment.objects.containers.Container;
import dev.polarian.assignment.objects.containers.CooledContainer;
import dev.polarian.assignment.objects.containers.HeatedContainer;
import dev.polarian.assignment.objects.containers.NormalContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

    private static final Random random = new Random();

    public static void main(String[] args) {
        List<Container> containers = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            int type = random.nextInt(1, 3);
            switch (type) {
                case 1 -> containers.add(new NormalContainer(i));
                case 2 -> containers.add(new HeatedContainer(i));
                case 3 -> containers.add(new CooledContainer(i));
            }
        }
        Ship.setContainers(containers);

        for (int i = 1; i < 3; i++) {
            Crane crane = new Crane(i);
            crane.start();
        }

        for (int i = 1; i < 4; i++) {
            Truck truck = new Truck(i);
            truck.start();
        }
    }

    public static Random getRandom() {
        return random;
    }
}
