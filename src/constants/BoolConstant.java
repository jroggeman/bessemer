package constants;

public class BoolConstant extends Constant {
    public boolean value;

    public BoolConstant(boolean value) {
        this.value = value;
    }

    public String toString() {
        return Boolean.toString(value);
    }
}
