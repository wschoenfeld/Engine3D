import jdk.dynalink.Operation;

import java.awt.*;
import java.util.TimerTask;
import java.util.function.BinaryOperator;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        JFrameAdaptor jFrameAdaptor = new JFrameAdaptor(1000,1000);
    }
}
class Camera
{
    //position
    public Vector3 Position;
    public Vector3 getPosition(){return Position;}
    public void setPosition(Vector3 position) {Position = position;}
    double FocalLength;
    //target, make sure this is a plane by using Double.Positive_Infinity;
    public void Translate(Vector3 vector3){
       setPosition(getPosition().add(vector3));
    }
    public void Translate(double x,double y, double z){
        getPosition().X += x;
        getPosition().Y += y;
        getPosition().Z += z;
    }
}
class Vector3{
    double X;
    double Y;
    double Z;
    public Vector3(double x,double y,double z){
        X = x;
        Y = y;
        Z = z;
    }

    public Vector3 add(Vector3 a){
      return new Vector3(X+a.X,Y+a.Y,Z+a.Z);
    }
    public Vector3 subtract(Vector3 a){
        return new Vector3(
                X-a.X,
                Y-a.Y,
                Z-a.Z);
    }
    @Override
    public String toString() {
        return "{" +
                "X=" + X +
                ", Y=" + Y +
                ", Z=" + Z +
                '}';
    }
}
class Mesh
{
    private String Name;
    public String getName() {return Name;}
    public void setName(String name) {Name = name;}

    private Vector3[] Vertices;
    public Vector3[] getVertices(){return Vertices;}
    public void setVertices(Vector3[] vertices){Vertices = vertices;}

    //position
    private Vector3 Position;
    public Vector3 getPosition() {return Position;}
    public void setPosition(Vector3 position) {Position = position; }

    //rotation
    private Vector3 Rotation;
    public Vector3 getRotation() {return Rotation;}
    public void setRotation(Vector3 rotation) {Rotation = rotation;}

    public void Translate(Vector3 vector3){
        for (int i = 0; i < getVertices().length;i++){
            getVertices()[i] = getVertices()[i].add(vector3);
        }
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


    public Mesh(String name, int verticesCount)
    {
        Vertices = new Vector3[verticesCount];
        Name = name;
    }
}
class point{
    double x,y;
    public point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public point add(point point){
        return new point(x+point.x,y+point.y);
    }
    public point subtract(point point){
        return new point(x-point.x,y-point.y);
    }
    public boolean equals(Point b) {
        if(x == b.x && y == b.y){
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return "point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
