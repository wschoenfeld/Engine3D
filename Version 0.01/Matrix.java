public class Matrix {
    double[][] Quarien= new double[4][4];

    public Vector3 Rotate(Vector3 Rotated,Vector3 Axis,double angle) {
        double[] v1 = {Rotated.X,Rotated.Y,Rotated.Z};
        double[] v2 = {Axis.X,Axis.Y,Axis.Z};
        double c = Math.cos(angle);
        double s = Math.sin(angle);
        double C = 1.0 - c;

        double[][] Q = new double[3][3];
        Q[0][0] = v2[0] * v2[0] * C + c;
        Q[0][1] = v2[1] * v2[0] * C + v2[2] * s;
        Q[0][2] = v2[2] * v2[0] * C - v2[1] * s;

        Q[1][0] = v2[1] * v2[0] * C - v2[2] * s;
        Q[1][1] = v2[1] * v2[1] * C + c;
        Q[1][2] = v2[2] * v2[1] * C + v2[0] * s;

        Q[2][0] = v2[0] * v2[2] * C + v2[1] * s;
        Q[2][1] = v2[2] * v2[1] * C - v2[0] * s;
        Q[2][2] = v2[2] * v2[2] * C + c;

        v1[0] = v1[0] * Q[0][0] + v1[0] * Q[0][1] + v1[0] * Q[0][2];
        v1[1] = v1[1] * Q[1][0] + v1[1] * Q[1][1] + v1[1] * Q[1][2];
        v1[2] = v1[2] * Q[2][0] + v1[2] * Q[2][1] + v1[2] * Q[2][2];
        return new Vector3(v1[0],v1[1],v1[2]);
    }
    //[v] = [v]x{[i] + sin(a)/d*[L] + ((1 - cos(a))/(d*d)*([L]x[L]))}
    //let
    //    [v] = [vx, vy, vz]      the vector to be rotated.
    //    [l] = [lx, ly, lz]      the vector about rotation
    //          | 1  0  0
    //    [i] = | 0  1  0|           the identity matrix
    //          | 0  0  1|
    //
    //          |   0  lz -ly |
    //    [L] = | -lz   0  lx |
    //          |  ly -lx   0 |
    //
    //    d = sqrt(lx*lx + ly*ly + lz*lz)
    //    a                       the angle of rotation
}
class Quaternion{
    double x,y,z,w;

    public Quaternion(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
}
