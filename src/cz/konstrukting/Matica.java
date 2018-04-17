package cz.konstrukting;

import java.lang.*;

public class Matica
{
    private final int riadky;
    private final int stlpce;
    private final double[][] data;

    public Matica(int riadky, int stlpce)
    {
        this.riadky = riadky;
        this.stlpce = stlpce;
        data = new double[riadky][stlpce];
    }

    public Matica(double[][] data)
    {
        riadky = data.length;
        stlpce = data[0].length;
        this.data = new double[riadky][stlpce];
        for (int i = 0; i < riadky; i++)
        {
            for (int j = 0; j < stlpce; j++)
            {
                this.data[i][j] = data[i][j];
            }
        }
    }

    public void show()
    {
        for (int i = 0; i < riadky; i++)
        {
            for (int j = 0; j < stlpce; j++)
            {
                System.out.printf("%9.4f ", data[i][j]);
            }
            System.out.println();
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public Matica transpose()
    {

        Matica A = new Matica(this.stlpce, this.riadky);
        for (int i = 0; i < riadky; i++)
        {
            for (int j = 0; j < stlpce; j++)
            {
                A.data[j][i] = this.data[i][j];
            }
        }
        return A;
    }

    public Matica normal()
    {
        Matica A = new Matica(riadky, stlpce);

        double[] z;
        z = new double[riadky];
        for (int i = 0; i < riadky; i++)
        {
            int pol = riadky / 2;
            z[i] = i - pol;
        }

        for (int i = 0; i < riadky; i++)
        {
            for (int j = 0; j < stlpce; j++)
            {
                A.data[i][j] = Math.pow(z[i], j);
            }
        }

        return A;
    }

    public Matica times(Matica B)
    {
        Matica A = this;
        Matica C = new Matica(this.riadky, B.stlpce);

        for (int i = 0; i < C.riadky; i++)
        {
            for (int j = 0; j < C.stlpce; j++)
            {
                for (int k = 0; k < this.stlpce; k++)
                {
                    C.data[i][j] += A.data[i][k] * B.data[k][j];
                }
            }
        }
        return C;
    }

    public Matica times(double k)
    {
        Matica C = this;

        for (int i = 0; i < C.riadky; i++)
        {
            for (int j = 0; j < C.stlpce; j++)
            {
                C.data[i][j] *= k;
            }
        }
        return C;
    }

    public Matica minor(Matica B, int i, int j)
    {
        Matica A = new Matica(B.data.length - 1, B.data.length - 1);

        for (int k = 0; k < A.data.length; k++)
        {
            for (int l = 0; l < A.data.length; l++)
            {
                if (k < i && l < j)
                {
                    A.data[k][l] = B.data[k][l];
                }
                else if (k < i && l >= j)
                {
                    A.data[k][l] = B.data[k][l + 1];
                }
                else if (k >= i && l < j)
                {
                    A.data[k][l] = B.data[k + 1][l];
                }
                else if (k >= i && l >= j)
                {
                    A.data[k][l] = B.data[k + 1][l + 1];
                }
            }
        }
        return A;
    }

    public double determinant(Matica A)
    {
        double det = 0.0;
        double sum = 0;
        if (A.data[0].length == 1 && A.data.length == 1)
        {
            return det;
        }
        else if (A.data[0].length == 2 && A.data.length == 2)
        {
            det = (A.data[0][0] * A.data[1][1]) - (A.data[1][0] * A.data[0][1]);
            return det;
        }
        else
        {
            for (int i = 0; i < A.data.length; i++)
            {
                sum += Math.pow(-1, i) * A.data[i][0] * determinant(minor(A, i, 0));
            }
        }
        det = sum;
        return det;
    }

    public Matica cofactor()
    {
        Matica A = new Matica(riadky, stlpce);

        for (int i = 0; i < riadky; i++)
        {
            for (int j = 0; j < stlpce; j++)
            {
                A.data[i][j] = Math.pow(-1, i + j) * determinant(minor(this, i, j));
            }
        }
        return A;
    }

    public Matica inverse()
    {
        Matica a = this.cofactor();
        Matica temp = a.transpose();

        double j = 1 / this.determinant(this);
        temp = temp.times(j);

        return temp;
    }

    public Matica convolutionIndexes()
    {
        Matica J = new Matica(this.riadky, this.stlpce);
        J = J.normal();
        Matica Jt = J.transpose();
        Matica JtJ = Jt.times(J);
        Matica invJtJ = JtJ.inverse();
        Matica C = invJtJ.times(Jt);
        return C;
    }
}
