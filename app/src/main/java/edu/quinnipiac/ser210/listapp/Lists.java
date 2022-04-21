package edu.quinnipiac.ser210.listapp;

public class Lists {
    private long id;
    private String listName;
    private String[] items;
    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String item5;

    public long getId() {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    /*
    public void setItems (int itemNumber, String item) {
        if ()
        items[itemNumber] = item;
    }
    public void setItems (String item) {
        if (item1 != null) {
        item1 = item
        } else if (item2 != null) {
        item2 = item
        }

    }
     */

    public void setItem1 (String item) {
        item1 = item;
    }
    public void setItem2 (String item) {
        item2 = item;
    }
    public void setItem3 (String item) {
        item3 = item;
    }
    public void setItem4 (String item) {
        item4 = item;
    }
    public void setItem5 (String item) {
        item5 = item;
    }

    public String getItem1 () {
        return item1;
    }
    public String getItem2 () {
        return item2;
    }
    public String getItem3 () {
        return item3;
    }
    public String getItem4 () {
        return item4;
    }
    public String getItem5 () {
        return item5;
    }
}
