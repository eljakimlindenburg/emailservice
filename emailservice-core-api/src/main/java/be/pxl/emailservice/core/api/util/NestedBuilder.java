package be.pxl.emailservice.core.api.util;

public abstract class NestedBuilder<T> {

    private final T instance;
    private boolean isBuilt = false;

    protected NestedBuilder() {
        this.instance = createInstance();
    }

    protected abstract T createInstance();

    protected void completeInstance() {
    }

    protected final T instance() {
        bailIfAlreadyBuilt();
        return instance;
    }

    public final T build() {
        bailIfAlreadyBuilt();
        completeInstance();
        isBuilt = true;
        return instance;
    }

    private void bailIfAlreadyBuilt() {
        if (isBuilt) {
            throw new IllegalStateException("instance has already been built");
        }
    }
}
