import javax.swing.*;

public class Math3D {
    public static Vector2 ScreenConverter(Vector3 mesh, JFrame frame,Camera camera){
        double hw = (frame.getWidth()) / 2;
        double hh = (frame.getHeight()) / 2;
        double fl_top = hw / Math.tan(Math.toRadians(camera.fieldOfVeiw)/2);
        double fl_side = hh / Math.tan(Math.toRadians(camera.fieldOfVeiw)/2);
        Vector2 d = new Vector2(
                (((mesh.x + camera.transform.Position.x) * fl_top) / ((mesh.z + camera.transform.Position.z) + fl_top)) + hw,
                (((mesh.y + camera.transform.Position.y) * fl_side) / ((mesh.z + camera.transform.Position.z) + fl_side)) + hh
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
    public static Vector3 rotate3DZ(Vector3 pointToRotate,Vector3 rotateAround,double angle){
        double cos = Math.cos(Math.toRadians(angle));
        double sin = Math.sin(Math.toRadians(angle));
        double x = (pointToRotate.x - rotateAround.x);
        double y = (pointToRotate.y - rotateAround.y);
        double x1 = x;
        x = (x * cos) - (y * sin);
        y = (x1 * sin) + (y * cos);
        x = x + rotateAround.x;
        y = y + rotateAround.y;
        return new Vector3(x,y, pointToRotate.z);
    }
    public static Vector3 rotate3DY(Vector3 pointToRotate,Vector3 rotateAround,double angle){
        double cos = Math.cos(Math.toRadians(angle));
        double sin = Math.sin(Math.toRadians(angle));
        double x = (pointToRotate.x - rotateAround.x);
        double z = (pointToRotate.z - rotateAround.z);
        double x1 = x;
        x = (x * cos) + (z * sin);
        z = (-x1 * sin) + (z * cos);
        x = x + rotateAround.x;
        z = z + rotateAround.z;
        return new Vector3(x, pointToRotate.y, z);
    }
    public static Vector3 rotate3DX(Vector3 pointToRotate,Vector3 rotateAround,double angle){
        double cos = Math.cos(Math.toRadians(angle));
        double sin = Math.sin(Math.toRadians(angle));
        double y = (pointToRotate.y - rotateAround.y);
        double z = (pointToRotate.z - rotateAround.z);
        double y1 = y;
        y = (y * cos) - (z * sin);
        z = (y1 * sin) + (z * cos);
        y = y + rotateAround.y;
        z = z + rotateAround.z;
        return new Vector3(z,y, z);
    }
    //Rotation Equations
    //Z axis
    //      |cos θ   −sin θ   0| |x|   |x cos θ − y sin θ|   |x'|
    //      |sin θ    cos θ   0| |y| = |x sin θ + y cos θ| = |y'|
    //      |  0       0      1| |z|   |        z        |   |z'|

    //Y axis
    //      | cos θ    0   sin θ| |x|   | x cos θ + z sin θ|   |x'|
    //      |   0      1       0| |y| = |         y        | = |y'|
    //      |−sin θ    0   cos θ| |z|   |−x sin θ + z cos θ|   |z'|

    //X axis
    //      |1     0           0| |x|   |        x        |   |x'|
    //      |0   cos θ    −sin θ| |y| = |y cos θ − z sin θ| = |y'|
    //      |0   sin θ     cos θ| |z|   |y sin θ + z cos θ|   |z'|

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

    @Override
    public String toString() {
        return "Vector3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
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

    @Override
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}