public class Math {
}
class Vector4{
    double x,y,z,w;
    public Vector4(double x, double y, double z,double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    public void add(Vector4 vector4){
        this.x += vector4.x;
        this.y += vector4.y;
        this.z += vector4.z;
        this.w += vector4.w;
    }
    public void subtract(Vector4 vector4){
        this.x -= vector4.x;
        this.y -= vector4.y;
        this.z -= vector4.z;
        this.w -= vector4.w;
    }
    public void multiply(Vector4 vector4){
        this.x = (this.x * vector4.x) + (this.x * vector4.y) + (this.x * vector4.z) + (this.x * vector4.w);
        this.y = (this.y * vector4.x) + (this.y * vector4.y) + (this.y * vector4.z) + (this.y * vector4.w);
        this.z = (this.z * vector4.x) + (this.z * vector4.y) + (this.z * vector4.z) + (this.z * vector4.w);
        this.w = (this.w * vector4.x) + (this.w * vector4.y) + (this.w * vector4.z) + (this.w * vector4.w);
    }
    public void divide(Vector4 vector4){
        this.x = (this.x / vector4.x) + (this.x / vector4.y) + (this.x / vector4.z) + (this.x / vector4.w);
        this.y = (this.y / vector4.x) + (this.y / vector4.y) + (this.y / vector4.z) + (this.y / vector4.w);
        this.z = (this.z / vector4.x) + (this.z / vector4.y) + (this.z / vector4.z) + (this.z / vector4.w);
        this.w = (this.w / vector4.x) + (this.w / vector4.y) + (this.w / vector4.z) + (this.w / vector4.w);
    }
    public void scale(double amount){
        x = x * amount;
        y = y * amount;
        z = z * amount;
        w = w * amount;
    }
}
class Vector3{
    double x,y,z;
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public void add(Vector3 vector3){
        this.x += vector3.x;
        this.y += vector3.y;
        this.z += vector3.z;
    }
    public void subtract(Vector3 vector3){
        this.x -= vector3.x;
        this.y -= vector3.y;
        this.z -= vector3.z;
    }
    public void multiply(Vector3 vector3){
        this.x = (this.x * vector3.x) + (this.x * vector3.y) + (this.x * vector3.z);
        this.y = (this.y * vector3.x) + (this.y * vector3.y) + (this.y * vector3.z);
        this.z = (this.z * vector3.x) + (this.z * vector3.y) + (this.z * vector3.z);
    }
    public void divide(Vector3 vector3){
        this.x = (this.x / vector3.x) + (this.x / vector3.y) + (this.x / vector3.z);
        this.y = (this.y / vector3.x) + (this.y / vector3.y) + (this.y / vector3.z);
        this.z = (this.z / vector3.x) + (this.z / vector3.y) + (this.z / vector3.z);
    }
    public void scale(double amount){
        x = x * amount;
        y = y * amount;
        z = z * amount;
    }
}
class Vector2{
    double x,y;
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void add(Vector2 vector2){
        this.x += vector2.x;
        this.y += vector2.y;
    }
    public void subtract(Vector2 vector2){
        this.x -= vector2.x;
        this.y -= vector2.y;
    }
    public void multiply(Vector2 vector2){
        this.x = (this.x * vector2.x) + (this.x * vector2.y);
        this.y = (this.y * vector2.x) + (this.y * vector2.y);
    }
    public void divide(Vector3 vector3){
        this.x = (this.x / vector3.x) + (this.x / vector3.y) + (this.x / vector3.z);
        this.y = (this.y / vector3.x) + (this.y / vector3.y) + (this.y / vector3.z);
    }
    public void scale(double amount){
        x = x * amount;
        y = y * amount;
    }
}
