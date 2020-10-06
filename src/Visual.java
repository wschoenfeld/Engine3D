import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Visual extends JPanel implements KeyListener {
    JFrame frame;
    Mesh box;
    Camera camera;
    Mesh plane;
    public Visual(){
        box = new Cube(50,
                new Transform(
                        new Vector3(250,250,0),
                        new Vector3(0,50,0),
                        new Vector3(1,1,1)
                )
        );
        camera = new Camera(60,new Transform(
                new Vector3(250,0,0),
                new Vector3(0,0,0),
                new Vector3(1,1,1)
        ));
        plane = new Plane(1000,1000,new Transform(
                new Vector3(0,300,-250),
                new Vector3(0,0,0),
                new Vector3(1,1,1)));
        this.frame = new JFrame();
        frame.setSize(500,500);
        frame.addKeyListener(this);
        frame.add(this);
        frame.setVisible(true);

    }
    public void paint(Graphics g){
        int[] planex = new int[plane.vertices.length];
        int[] planey = new int[plane.vertices.length];
        for(int i = 0; i < plane.vertices.length;i++){
            planex[i] = (int) Math3D.ScreenConverter(plane.getGlobalVerts()[i], frame,camera).x;
            planey[i] = (int) Math3D.ScreenConverter(plane.getGlobalVerts()[i], frame,camera).y;
            if(i == 0){
                drawVector2(g,Math3D.ScreenConverter(plane.getGlobalVerts()[i], frame,camera),8);
            }
            else{
                drawVector2(g,Math3D.ScreenConverter(plane.getGlobalVerts()[i], frame,camera),4);
            }
            for(int j = 0; j < plane.vertices.length;j++){
                drawLines(g,Math3D.ScreenConverter(plane.getGlobalVerts()[i],frame,camera),Math3D.ScreenConverter(plane.getGlobalVerts()[j],frame,camera));
            }
        }
        g.setColor(randomColor());
        g.fillPolygon(planex,planey,plane.vertices.length);
        g.setColor(Color.black);
        for(int i = 0; i < box.vertices.length;i++){
            if(i == 0){
                drawVector2(g,Math3D.ScreenConverter(box.getGlobalVerts()[i], frame,camera),8);
            }
            else{
                drawVector2(g,Math3D.ScreenConverter(box.getGlobalVerts()[i], frame,camera),4);
            }
            for(int j = 0; j < box.vertices.length;j++){
                drawLines(g,Math3D.ScreenConverter(box.getGlobalVerts()[i],frame,camera),Math3D.ScreenConverter(box.getGlobalVerts()[j],frame,camera));
            }
        }

    }
    public Color randomColor(){
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }
    public void drawVector2(Graphics g,Vector2 vector2,int size){
        g.fillRect((int)vector2.x,(int)vector2.y,size,size);
    }
    public void drawLines(Graphics g,Vector2 a,Vector2 b){
        g.drawLine((int)a.x,(int)a.y,(int)b.x,(int)b.y);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_C){
            /*
            for(int i = 1; i < box.verts.length;i++){
                box.verts[i] = Math3D.rotate2D(box.getGlobalVerts()[i],box.position,10);
                box.verts[i] = box.verts[i].subtractVector2(box.position);
            }*/
        }
        if(e.getKeyCode() == KeyEvent.VK_W){
            camera.transform.Position.y += 5;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            camera.transform.Position.y -= 5;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            camera.transform.Position.x += 5;
        }if(e.getKeyCode() == KeyEvent.VK_D){
            camera.transform.Position.x -= 5;
        }
        System.out.println("");
        frame.repaint();
        frame.revalidate();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
