package rvt;

import java.util.ArrayList;

public class Box implements Packable {
    private ArrayList<Packable> items;
    private double maximumCapacity;

    public Box(double maximumCapacity) {
        this.items = new ArrayList<>();
        this.maximumCapacity = maximumCapacity;
    }

    public void add(Packable item) {
        if (this.weight() + item.weight() <= this.maximumCapacity) {
            this.items.add(item);
        }
    }

    @Override
    public double weight() {
        double totalWeight = 0;

        for (Packable item : this.items) {
            totalWeight += item.weight();
        }

        return totalWeight;
    }

    @Override
    public String toString() {
        return "Box: " + this.items.size() + " items, total weight " + this.weight() + " kg";
    }
}