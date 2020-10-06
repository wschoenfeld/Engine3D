import javax.swing.*;

public class Math3D {
    public static Vector2 ScreenConverter(Vector3 mesh, JFrame frame,Camera camera){
        double hw = frame.getWidth() / 2;
        double hh = frame.getHeight() / 2;
        double fl_top = hw / Math.tan(Math.toRadians(camera.fieldOfVeiw)/2);
        double fl_side = hh / Math.tan(Math.toRadians(camera.fieldOfVeiw)/2);
        Vector2 d = new Vector2(
                (mesh.x * fl_top) / (mesh.z + fl_top),
                (mesh.y * fl_top) / (mesh.z + fl_top)
        );


        return d;
    }
    public static Vector2 rotate2D(Vector2 pointToRotate,Vector2 rotateAround,double angle){
        double cos = Math.cos(Math.toRadians(angle));
        double sin = Math.sin(Math.toRadians(angle));
        double x = (pointToRotate.x - rotateAround.x);
        double y = (pointToRotate.y - rotateAround.y);
        double x1 = x;
        x = (x * cos) - (y * sin);
        y = (x1 * sin) + (y * cos);
        x = x + rotateAround.x;
        y = y + rotateAround.y;
        return new Vector2(x,y);
    }
    public static Vector2 rotate3DX(Vector3 pointToRotate,Vector3 rotateAround,double angle){
        double cos = Math.cos(Math.toRadians(angle));
        double sin = Math.sin(Math.toRadians(angle));
        double x = (pointToRotate.x - rotateAround.x);
        double y = (pointToRotate.y - rotateAround.y);
        System.out.println("{X: " + x + " Y: " + y + "}");
        double x1 = x;
        x = (x * cos) - (y * sin);
        y = (x1 * sin) + (y * cos);
        System.out.println("{X2: " + x + " Y2: " + y + "}");
        x = x + rotateAround.x;
        y = y + rotateAround.y;
        System.out.println("{X3: " + x + " Y3: " + y + "}");
        return new Vector2(x,y);
    }

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
    public Vector3 addVector3(Vector3 vector3){
        return new Vector3(x+vector3.x,y+vector3.y,z+vector3.z);
    }
    public void subtract(Vector3 vector3){
        this.x -= vector3.x;
        this.y -= vector3.y;
        this.z -= vector3.z;
    }
    public Vector3 subtractVector3(Vector3 vector3){
        return new Vector3(x-vector3.x,y-vector3.y,z-vector3.z);
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
    public Vector2 addVector2(Vector2 vector2){
        return new Vector2(x + vector2.x,y+vector2.y);
    }
    public Vector2 subtractVector2(Vector2 vector2){
        return new Vector2(x - vector2.x,y-vector2.y);
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