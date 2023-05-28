package rocks.palaiologos.maja.expression;

import rocks.palaiologos.maja.Complex;
import rocks.palaiologos.maja.Maja;
import rocks.palaiologos.maja.MonadicFunction;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

class Environment {
    private final HashMap<String, Object> variables = new HashMap<>();
    private final Environment parent;

    public Environment() {
        this.parent = null;
    }

    public Environment(Environment parent) {
        this.parent = parent;
    }

    public void set(String name, Object value) {
        variables.put(name, value);
    }

    public Object get(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        } else if (parent != null) {
            return parent.get(name);
        } else {
            throw new RuntimeException("Variable " + name + " not found");
        }
    }

    public boolean has(String name) {
        if (variables.containsKey(name)) {
            return true;
        } else if (parent != null) {
            return parent.has(name);
        } else {
            return false;
        }
    }

    public Environment getParent() {
        return parent;
    }

    public Environment createChild() {
        return new Environment(this);
    }
}
