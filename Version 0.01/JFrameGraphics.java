import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JFrameGraphics extends JPanel implements KeyListener {
    public int width,hight;
    public Graphics graphics;
    public JFrame frame;
    public JFrameAdaptor adaptor;
    public Mesh mesh = newCube(50,new Vector3(300,300,0));
    public static Vector3 Origin = new Vector3(0,0,0);
    Camera camera = new Camera();
    public void paint(Graphics g){
        //g.drawRect(10,10,100,100);
        g.drawString("Box Pos: "+mesh.getVertices()[0].toString(),5,20);
        g.drawString("Camera Pos: "+camera.Position.toString(),5,40);
        g.drawString("Focal Length: " + camera.FocalLength,5,60);
        g.drawString("Box Rot: "+ mesh.getRotation().toString(),5,80);
        graphics = g;
        //adaptor.worldOrigin = plane.getPosition();

        adaptor.drawMeshPoints(g,mesh, camera);
        for(int i = 0; i < mesh.faces.length; i++){
            int xs[] = new int[4];
            int ys[] = new int[4];
            for(int j = 0; j < mesh.faces[i].edges.length; j++){
                xs[j] = (int)adaptor.EDto2D(mesh.faces[i].edges[j],camera).x;
                ys[j] = (int)adaptor.EDto2D(mesh.faces[i].edges[j],camera).y;
            }
            g.setColor(mesh.faces[i].color);
            g.fillPolygon(xs,ys,4);
        }
    }


    public void setUpJFrame(){
        camera.setPosition(new Vector3(0,-20,0));
        camera.FocalLength = new Vector3(0,20,70);
        camera.FOV = 60;
        JFrame frame= new JFrame("");
        this.frame = frame;
        frame.getContentPane().add(this);
        frame.setSize(width, hight);
        frame.setVisible(true);
        frame.addKeyListener(this);
        mesh.setRotation(new Vector3(0,0,0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        final boolean[] d = {false};
        int a = 0;
        Runnable helloRunnable = new Runnable() {
            public void run() {
                camera.FocalLenthUpdate();
                frame.revalidate();
                frame.repaint();


            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 60, TimeUnit.MILLISECONDS);
    }
    public JFrameGraphics(int width,int hight,JFrameAdaptor adaptor){
        this.width = width;
        this.hight = hight;
        this.adaptor = adaptor;
        setUpJFrame();
    }
    public Mesh newPyramid(int basesize,int height,Vector3 pos){
        Mesh mesh = new Mesh("Pyramid",8);
        mesh.setPosition(pos);
        mesh.setVertices(new Vector3[]{
                new Vector3(pos.X,pos.Y,pos.Z),
                new Vector3(pos.X+basesize,pos.Y,pos.Z),
                new Vector3(pos.X,pos.Y,pos.Z+basesize),
                new Vector3(pos.X+basesize,pos.Y,pos.Z+basesize),
                new Vector3(pos.X+(basesize / 2),pos.Y+height,pos.Z + (basesize /2)),
        });
        return mesh;
    }
    public Mesh newCube(int size,Vector3 pos){
        Mesh mesh = new Mesh("Cube",8);
        mesh.setPosition(pos);
        mesh.setVertices(new Vector3[]{
                new Vector3(0,0,0),
                new Vector3(size,0,0),
                new Vector3(0,size,0),
                new Vector3(0,0,size),
                new Vector3(size,size,0),
                new Vector3(0,size,size),
                new Vector3(size,0,size),
                new Vector3(size,size,size),
        });
        mesh.faces = new Face[]{
                new Face(new Vector3[]{
                        mesh.getVertices()[0],
                        mesh.getVertices()[1],
                        mesh.getVertices()[2],
                        mesh.getVertices()[4]
                }, Color.BLUE),
                new Face(new Vector3[]{
                        mesh.getVertices()[0],
                        mesh.getVertices()[2],
                        mesh.getVertices()[3],
                        mesh.getVertices()[7]
                },Color.GREEN)
        };
        return mesh;

    }
    public Mesh newPlane(int width,int length,Vector3 pos){
        Mesh mesh = new Mesh("Plane",4);
        mesh.setPosition(pos);
        mesh.setVertices(new Vector3[]{
                new Vector3(pos.X,pos.Y,pos.Z),
                new Vector3(pos.X + width,pos.Y,pos.Z),
                new Vector3(pos.X,pos.Y,pos.Z + length),
                new Vector3(pos.X + width,pos.Y,pos.Z + length)
        });
        mesh.faces = new Face[]{
                new Face(new Vector3[]{
                        mesh.getVertices()[0],
                        mesh.getVertices()[1],
                        mesh.getVertices()[2],
                        mesh.getVertices()[3]
                },Color.RED)};
        return mesh;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_B){
            mesh.getRotation().Y += 5;
        }
        if(e.getKeyCode() == KeyEvent.VK_N){
            mesh.getRotation().Z += 5;
        }
        if(e.getKeyCode() == KeyEvent.VK_V){
            mesh.getRotation().X += 5;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            camera.getPosition().X += 5;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            camera.getPosition().X -= 5;
        }
        if(e.getKeyCode() == KeyEvent.VK_W){
            camera.getPosition().Y += 5;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            camera.getPosition().Y -= 5;
        }
        if(e.getKeyCode() == KeyEvent.VK_Z){
            camera.FocalLength.Z += 5;
        }
        if(e.getKeyCode() == KeyEvent.VK_X){
            camera.FocalLength.Z -= 5;
        }
        if(e.getKeyCode() == KeyEvent.VK_T){
            mesh.Translate(new Vector3(5,0,0));
            //System.out.println("ROTATED");
        }
        if(e.getKeyCode() == KeyEvent.VK_G){
            mesh.Translate(new Vector3(-5,0,0));
            //System.out.println("ROTATED");
        }
        if(e.getKeyCode() == KeyEvent.VK_Y){
            mesh.Translate(new Vector3(0,5,0));
            //System.out.println("ROTATED");
        }
        if(e.getKeyCode() == KeyEvent.VK_H){
            mesh.Translate(new Vector3(0,-5,0));
            //System.out.println("ROTATED");
        }
        if(e.getKeyCode() == KeyEvent.VK_U){
            mesh.Translate(new Vector3(0,0,5));
            //System.out.println("ROTATED");
        }
        if(e.getKeyCode() == KeyEvent.VK_J){
            mesh.Translate(new Vector3(0,0,-5));
            //System.out.println("ROTATED");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}