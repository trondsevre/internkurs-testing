package no.fintlabs;

public class MyUtil {

    private int value;

    public void set(int i){
        value = i;
    }

    public int add(int i) {
        value += i;
        return value;
    }
}
