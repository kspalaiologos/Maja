package rocks.palaiologos.maja.expression;

class ReturnError extends Error {
    public Object value;

    public ReturnError(Object value) {
        this.value = value;
    }
}
