import javax.swing.*;
import java.awt.*;

public class JFrameAdaptor{
    public JFrame frame;
    public Graphics g;
    public int width;
    public int height;
    public float aspect;
    public Vector3 worldOrigin = new Vector3(0,-110,0);


    public JFrameAdaptor(int width,int height){
        this.width = width;
        this.height = height;
        this.aspect = (float)width / (float)height;
        JFrameGraphics graphics = new JFrameGraphics(600,600,this);



    }
    public void drawFace(Face face,Graphics g,Color color,Camera camera){
        int xs[] = new int[4];
        int ys[] = new int[4];
        for(int i = 0; i < face.edges.length; i++){
            xs[i] = (int)EDto2D(face.edges[i],camera).x;
            ys[i] = (int)EDto2D(face.edges[i],camera).y;
        }
        g.setColor(color);
        g.fillPolygon(xs,ys,4);
    }
    void drawMeshPoints(Graphics g,Mesh mesh,Camera camera){
        for(int i = 0;i < mesh.getVertices().length;i++){
            Vector3 meshpos = mesh.getVertices()[i];
            //column = X*focal/Z + width/2
            //row = -Y*focal/Z  + height/2
            point pointOnScreen = EDto2D(meshpos,camera);
            System.out.println(meshpos.toString());
            g.fillRect((int)pointOnScreen.x,(int)pointOnScreen.y,2,2);
            for(int j = 0; j < mesh.getVertices().length;j++){
                point pointOnScreen2 = EDto2D(mesh.getVertices()[j],camera);
                Vector3 meshpos2 = mesh.getVertices()[j];
                if(meshpos2.Z > camera.Position.Z && meshpos.Z > camera.Position.Z){
                    g.drawLine((int)pointOnScreen.x,(int)pointOnScreen.y,(int)pointOnScreen2.x,(int)pointOnScreen2.y);
                }
                if(meshpos.X == meshpos2.X ||meshpos.Y == meshpos2.Y || meshpos.Z == meshpos2.Z){

                }
            }

            //System.out.println(pointOnScreen.toString());
        }
    }
    public point EDto2D(Vector3 mesh,Camera camera){
        Vector3 camerapos = camera.Position;
        Vector3 meshpos = mesh;
        //column = X*focal/Z + width/2
        //row = -Y*focal/Z  + height/2
        double x2 =  (meshpos.X - camerapos.X) * (meshpos.X - camerapos.X);
        double y2 =  (meshpos.Y - camerapos.Y) * (meshpos.Y - camerapos.Y);
        double z2 =  (meshpos.Z - camerapos.Z) * (meshpos.Z - camerapos.Z);
        double distance = Math.sqrt(x2 + y2 + z2);
        //System.out.println(distance);
        point a = new point(
                (-mesh.X * camera.FocalLength.Z)/(mesh.Z) + (width / 2),
                (-mesh.Y * camera.FocalLength.Z)/(mesh.Z) + (height / 2));
        point b = new point(
                (mesh.X * distance)/(distance + mesh.Z),
                (mesh.Y* distance)/(distance + mesh.Z));
        return a;
    }

}

