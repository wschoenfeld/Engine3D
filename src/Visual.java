import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Visual extends JPanel implements KeyListener {
    ArrayList<Mesh> meshes;
    JFrame frame;
    Mesh box;
    Camera camera;
    Mesh plane;

    public Visual() {
        meshes = new ArrayList<>();
        box = new Cube(50,
                new Transform(
                        new Vector3(250, -50, 250),
                        new Vector3(0, 0, 0),
                        new Vector3(1, 1, 1)
                )
        );
        camera = new Camera(100, new Transform(
                new Vector3(250, 0, 0),
                new Vector3(0, 0, 0),
                new Vector3(1, 1, 1)
        ));
        plane = new Plane(1000, 1000, new Transform(
                new Vector3(0, 0, 0),
                new Vector3(0, 0, 0),
                new Vector3(1, 1, 1)));
        meshes.add(box);
        meshes.add(plane);
        this.frame = new JFrame();
        frame.setSize(500, 500);
        frame.addKeyListener(this);
        frame.add(this);
        frame.setVisible(true);
        camera.speed = 5;

    }

    public void paint(Graphics g) {
        for (int k = 0; k < meshes.size(); k++) {
            meshes.get(k).rotationUpdate();
            g.setColor(Color.black);
            if (meshes.get(k).transform.Position.x >= camera.minX &&
                    meshes.get(k).transform.Position.x <= camera.maxX &&
                    meshes.get(k).transform.Position.y >= camera.minY &&
                    meshes.get(k).transform.Position.y <= camera.maxY &&
                    meshes.get(k).transform.Position.z > camera.minZ &&
                    meshes.get(k).transform.Position.z < camera.maxZ) {

            }
            paintMesh(meshes.get(k), g);
        }
        g.drawString("Camera Position: " +camera.transform.Position.toString(), 0,20);
        g.drawString("Cube Position: " +meshes.get(0).transform.Position.toString(), 0,40);
        g.drawString("Cube Rotation: " +meshes.get(0).transform.Rotation.toString(), 0,60);
    }

    public void paintMesh(Mesh mesh, Graphics g) {
        for (int i = 0; i < mesh.vertices.length; i++) {
            if (i == 0) {
                drawVector2(g, Math3D.ScreenConverter(mesh.visualPosition[i], frame, camera), 8);
            } else {
                drawVector2(g, Math3D.ScreenConverter(mesh.visualPosition[i], frame, camera), 4);
            }
            for (int j = 0; j < mesh.vertices.length; j++) {
                if (mesh.vertices[i].x == mesh.vertices[j].x) {
                    drawLines(g, Math3D.ScreenConverter(mesh.visualPosition[i], frame, camera), Math3D.ScreenConverter(mesh.visualPosition[j], frame, camera));
                } else if (mesh.vertices[i].y == mesh.vertices[j].y) {
                    drawLines(g, Math3D.ScreenConverter(mesh.visualPosition[i], frame, camera), Math3D.ScreenConverter(mesh.visualPosition[j], frame, camera));
                } else if (mesh.vertices[i].z == mesh.vertices[j].z) {
                    drawLines(g, Math3D.ScreenConverter(mesh.visualPosition[i], frame, camera), Math3D.ScreenConverter(mesh.visualPosition[j], frame, camera));
                }
            }
        }

    }

    public Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }

    public void drawVector2(Graphics g, Vector2 vector2, int size) {
        g.fillRect((int) vector2.x, (int) vector2.y, size, size);
    }

    public void drawLines(Graphics g, Vector2 a, Vector2 b) {
        g.drawLine((int) a.x, (int) a.y, (int) b.x, (int) b.y);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C) {
            /*
            for(int i = 1; i < box.verts.length;i++){
                box.verts[i] = Math3D.rotate2D(box.getGlobalVerts()[i],box.position,10);
                box.verts[i] = box.verts[i].subtractVector2(box.position);
            }*/
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            camera.transform.Position.y += camera.speed * 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_E) {
            camera.transform.Position.y -= camera.speed * 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            camera.transform.Position.z += camera.speed * 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            camera.transform.Position.z -= camera.speed * 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            camera.transform.Position.x += camera.speed * 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            camera.transform.Position.x -= camera.speed * 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            meshes.get(0).transform.Rotation.x += 10;
        }
        if (e.getKeyCode() == KeyEvent.VK_T) {
            meshes.get(0).transform.Rotation.y += 10;
        }
        if (e.getKeyCode() == KeyEvent.VK_Y) {
            meshes.get(0).transform.Rotation.z += 10;
        }
        System.out.println("");
        frame.repaint();
        frame.revalidate();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
