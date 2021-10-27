package sample;

import java.io.Serializable;

public class data implements Serializable {
    public String[] array = {".", ".", ".", ".", ".", ".",};

    public data(String[] array) {
        this.array = array;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }
}
