package model;

public class Category {
    // Member variables
    private int category_id;
    private String name;

    // Constructor
    public Category(int category_id, String name) {
        this.category_id = category_id;
        this.name = name;
    }

    // Getters and setters
    public int getCategoryId() {
        return category_id;
    }

    public void setCategoryId(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Override toString() method
    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", name='" + name + '\'' +
                '}';
    }
}