package earnclient.utils;

public class Vector3<T extends Number>
{
    public T x;
    public T y;
    public T z;
    
    public T getX() {
        return this.x;
    }
    
    public Vector3(final T x, final T y, final T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vector3() {
    }
    
    public Vector3 setX(final T x) {
        this.x = x;
        return this;
    }
    
    public T getY() {
        return this.y;
    }
    
    public Vector3 setY(final T y) {
        this.y = y;
        return this;
    }
    
    public T getZ() {
        return this.z;
    }
    
    public Vector3 setZ(final T z) {
        this.z = z;
        return this;
    }
}
