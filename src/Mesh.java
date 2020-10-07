import java.awt.*;


class Transform{
    Vector3 Scale;
    Vector3 Position;
    Vector3 Rotation;
    public Transform( Vector3 position, Vector3 rotation,Vector3 scale) {
        Scale = scale;
        Position = position;
        Rotation = rotation;
    }
}
class Mesh{
    //vertices are local to the position
    Vector3[] vertices;
    Vector3[] visualPosition;
    Transform transform;

    public Mesh(Vector3[] vertices,Transform transform){
        this.vertices = vertices;
        this.transform = transform;
        visualPosition = new Vector3[vertices.length];
        rotationUpdate();
    }
    public void rotationUpdate(){
        if(transform.Rotation.x > 360){
            transform.Rotation.x =transform.Rotation.x - 360;
        }
        if(transform.Rotation.y > 360){
            transform.Rotation.y =transform.Rotation.y - 360;
        }
        if(transform.Rotation.z > 360){
            transform.Rotation.z = transform.Rotation.z - 360;
        }
        for(int i = 0; i < vertices.length;i++){
            visualPosition[i] = getGlobalVerts()[i];
            visualPosition[i].add(getGlobalVerts()[i].subtractVector3(Math3D.rotate3DX(getGlobalVerts()[i],transform.Position,transform.Rotation.x)));
            visualPosition[i].add(getGlobalVerts()[i].subtractVector3(Math3D.rotate3DY(getGlobalVerts()[i],transform.Position,transform.Rotation.y)));
            visualPosition[i].add(getGlobalVerts()[i].subtractVector3(Math3D.rotate3DZ(getGlobalVerts()[i],transform.Position,transform.Rotation.z)));
        }

    }
    Vector3[] getGlobalVerts(){
        Vector3[] out = new Vector3[vertices.length];
        for(int i = 0; i < vertices.length;i++){
            out[i] = vertices[i].addVector3(transform.Position);
        }
        return out;
    }
}
class Rectangle extends Mesh{
    public Rectangle(int width,int length,int height,Transform transform){
        super(new Vector3[]{
                new Vector3(0,0,0),
                new Vector3(width,0,0),
                new Vector3(0,length,0),
                new Vector3(0,0,height),
                new Vector3(width,length,0),
                new Vector3(width,0,height),
                new Vector3(0,length,height),
                new Vector3(width,length,height),
        },transform);
    }
}
class Plane extends Mesh{
    public Plane(int width,int length,Transform transform){
        super(new Vector3[]{
                new Vector3(0,0,0),
                new Vector3(width,0,0),
                new Vector3(0,0,length),
                new Vector3(width,0,length)
        },transform);
    }
}
class Cube extends Mesh{
    public Cube(int size,Transform transform){
         super(new Vector3[]{
                 new Vector3(0,0,0),
                 new Vector3(size,0,0),
                 new Vector3(0,size,0),
                 new Vector3(0,0,size),
                 new Vector3(size,size,0),
                 new Vector3(size,0,size),
                 new Vector3(0,size,size),
                 new Vector3(size,size,size)
         },transform);
    }
}
class Camera {
    double fieldOfVeiw;
    int speed;
    int minX, maxX; //minimum and maximum bounds of X
    int minY, maxY; //minimum and maximum bounds of Y
    int minZ, maxZ; //minimum and maximum bounds of Z
    Transform transform;
    public Camera(double fieldOfVeiw,Transform transform){
        this.fieldOfVeiw = fieldOfVeiw;
        minX = (int) (transform.Position.x - 1000);
        maxX = (int) (transform.Position.x + 1000);
        minY = (int) (transform.Position.y - 500);
        maxY = (int) (transform.Position.y + 500);
        minZ = (int) (transform.Position.z+25);
        maxZ = (int) (transform.Position.z + 500);
        this.transform = transform;
    }
}
class Mesh2DBox{
    public Vector2[] verts;
    public int size;
    Vector2 position;
    double rotation;
    public Mesh2DBox(int size,Vector2 position,double rotation){
        verts = new Vector2[]{
                new Vector2(0,0),
                new Vector2(size,0),
                new Vector2(0,size),
                new Vector2(size,size)
        };
        this.size = size;
        this.position = position;
        this.rotation = rotation;
    }
    Vector2[] getGlobalVerts(){
        Vector2[] out = new Vector2[4];
        for(int i = 0; i < 4; i++){
            out[i] = verts[i].addVector2(position);
        }
        return out;
    }
}