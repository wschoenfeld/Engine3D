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

    void drawMeshPoints(Graphics g,Mesh mesh,Camera camera){
        for(int i = 0;i < mesh.getVertices().length;i++){

            Vector3 meshpos = mesh.returnPosition(mesh.getVertices()[i]);
            //column = X*focal/Z + width/2
            //row = -Y*focal/Z  + height/2
            point pointOnScreen = EDto2D(meshpos,camera);
            pointOnScreen.add(new point(width,height));
            System.out.println(meshpos.toString());
            g.fillRect((int)pointOnScreen.x,(int)pointOnScreen.y,2,2);
            for(int j = 0; j < mesh.getVertices().length;j++){

                point pointOnScreen2 = EDto2D(mesh.returnPosition(mesh.getVertices()[j]),camera);
                pointOnScreen2.add(new point(width,height));
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
        //Here's a very general answer. Say the camera's at (Xc, Yc, Zc) and the point you want to project is P = (X, Y, Z). The distance from the camera to the 2D plane onto which you are projecting is F (so the equation of the plane is Z-Zc=F). The 2D coordinates of P projected onto the plane are (X', Y').
        //
        //Then, very simply:
        //
        //X' = ((X - Xc) * (F/Z)) + Xc
        //
        //Y' = ((Y - Yc) * (F/Z)) + Yc
        double hw = width / 2;
        double hh = height / 2;
        double fl_top = hw / Math.tan(Math.toRadians(camera.FOV)/2);
        double fl_side = hh / Math.tan(Math.toRadians(camera.FOV)/2);

        double f = meshpos.Z - camerapos.Z;
        point a = new point(
                ((mesh.X + camera.FocalLength.X) * camera.FocalLength.Z)/(mesh.Z) + ((width) / 2),
                ((-mesh.Y - camera.FocalLength.Y) * camera.FocalLength.Z)/(mesh.Z) + ((height) / 2));
        point b = new point(
                (mesh.X * distance)/(distance + mesh.Z),
                (mesh.Y* distance)/(distance + mesh.Z));
        point c = new point(
                (mesh.X - camera.FocalLength.X) * (distance / mesh.Z) + camera.FocalLength.X,
                (mesh.Y - camera.FocalLength.Y) * (distance / mesh.Z) + camera.FocalLength.Y);
        point d = new point(
                ((meshpos.X * fl_top) / (meshpos.Z + fl_top)),
                ((meshpos.Y * fl_top) / (meshpos.Z + fl_top)));


        return d;
    }

}

