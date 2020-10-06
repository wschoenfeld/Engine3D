class Mesh{
    //vertices are local to the position
    Vector3[] vertices;
    Vector3 Position;
    Vector4 Rotation;

    public Mesh(Vector3[] vertices, Vector3 position, Vector4 rotation){
        this.vertices = vertices;
        Position = position;
        Rotation = rotation;
    }
}
class Rectangle extends Mesh{
    public Rectangle(int width,int length,int height,Vector3 position,Vector4 rotation){
        super(new Vector3[]{
                new Vector3(0,0,0),
                new Vector3(width,0,0),
                new Vector3(0,length,0),
                new Vector3(0,0,height),
                new Vector3(width,length,0),
                new Vector3(width,0,height),
                new Vector3(0,length,height),
                new Vector3(width,length,height),
        },position,rotation);
    }
}
class Plane extends Mesh{
    public Plane(int width,int length,Vector3 position,Vector4 rotation){
        super(new Vector3[]{
                new Vector3(0,0,0),
                new Vector3(width,0,0),
                new Vector3(0,0,length),
                new Vector3(width,0,length)
        },position,rotation);
    }
}
class Cube extends Mesh{
    public Cube(int size,Vector3 position,Vector4 rotation){
         super(new Vector3[]{
                 new Vector3(0,0,0),
                 new Vector3(size,0,0),
                 new Vector3(0,size,0),
                 new Vector3(0,0,size),
                 new Vector3(size,size,0),
                 new Vector3(size,0,size),
                 new Vector3(0,size,size),
                 new Vector3(size,size,size)
         },position,rotation);
    }
}