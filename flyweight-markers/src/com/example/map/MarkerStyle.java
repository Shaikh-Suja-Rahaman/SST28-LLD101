package com.example.map;

/**
 * CURRENT STATE (BROKEN ON PURPOSE):
 * A style object exists, but is mutable and is created per marker,
 * even when thousands of markers share the same config.
 *
 * TODO (student):
 * - Make this an immutable Flyweight (final fields, no setters).
 */
public class MarkerStyle {

    final private String shape;   // e.g., PIN, CIRCLE, SQUARE
    final private String color;   // e.g., RED, BLUE, GREEN
    final private int size;       // e.g., 10..20
    final private boolean filled; // filled vs outline

    public MarkerStyle(String shape, String color, int size, boolean filled) {
        this.shape = shape;
        this.color = color;
        this.size = size;
        this.filled = filled;
    }

    public String getShape() { return shape; }
    public String getColor() { return color; }
    public int getSize() { return size; }
    public boolean isFilled() { return filled; }

    @Override
    public String toString() {
        return shape + "|" + color + "|" + size + "|" + (filled ? "F" : "O");
    }
}
