package cz.konstrukting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Filer
{
public double[][] data;

  public Filer()
  {
    try
    {
      Scanner s = new Scanner(new File("input.txt"));
      List<Double> time = new ArrayList<>();
      List<Double> cof = new ArrayList<>();

      // Skip column headings.
      s.nextLine();
      s.nextLine();
      s.nextLine();
      s.nextLine();
      s.nextLine();
      // Read each line, ensuring correct format.
      while (s.hasNext())
      {
        time.add(s.nextDouble());         // r
        cof.add(s.nextDouble()); // r
      }

      /*for (Double t: time)
      {
        System.out.println(t);
      }*/
      data = new double[time.size()][2];
      for (int i = 0; i < time.size(); i++)
      {
        this.data[i][0] = time.get(i);
        this.data[i][1] = cof.get(i);
      }


    } catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
  }
}
