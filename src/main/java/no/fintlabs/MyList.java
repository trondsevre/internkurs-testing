package no.fintlabs;

import java.util.ArrayList;

public class MyList {

    private ArrayList<String> list;
    private MyListValidator validator;

    public MyList() {
        list = new ArrayList<>();
        validator = new MyListValidator();
    }

    public MyList(MyListValidator validator) {
        list = new ArrayList<>();
        this.validator = validator;
    }

    public int size() {
        return list.size();
    }

    public boolean add(String value) {
        if (!validator.validate(value)) return false;
        list.add(value);
        return true;
    }

    public String peek() {
        return list.get(list.size() - 1);
    }

    public void loadExampleData() {
        list.clear();
        list.addAll(validator.getExampleData());
    }
}
