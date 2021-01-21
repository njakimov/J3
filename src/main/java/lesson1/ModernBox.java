package lesson1;
// T - Type
// E - Element
// K - Key
// V - Value
// M - Map
// ? - любой

// new ModernBox<Integer>
// new ModernBox<String>
public class ModernBox<Type> {
    private Type obj;

    public ModernBox(Type obj) {
        this.obj = obj;
    }

    public Type getObj() {
        return obj;
    }

    public void setObj(Type obj) {
        this.obj = obj;
    }
}
