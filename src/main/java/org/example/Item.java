package org.example;

public class Item{
    private String group;
    private String type;
    private Long number;
    private Long weight;

    public Item() {
    }

    public Item(String group, String type, Long number, Long weight) {
        this.group = group;
        this.type = type;
        this.number = number;
        this.weight = weight;
    }

    public String grouping(){
        return String.format("%-15s %-15s", group, type);
    }


    public String getGroup() {
        return group;
    }
    public String getType() {
        return type;
    }
    public Long getNumber() {
        return number;
    }
    public Long getWeight() {
        return weight;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }
}
