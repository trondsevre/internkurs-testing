package no.fintlabs;

import java.util.List;

public class MyListValidator {

    public boolean validate(String element) {
        if (element == null || element.equals("")) return false;
        return true;
    }

    public List<String> getExampleData() {
        return List.of(
                "This is example one",
                "This is example two",
                "This is example three");
    }
}
