/**
 * Ederin Igharoro
 * This class Creates a new genome 
 * that is represented by Triangles.
 * The genome is a single representation
 * of 1 image
 */
package mainPackage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Genome implements Comparable<Genome>
{
  private List<Triangle> triangles;
  private int width;
  private int height;
  public long fitness;
  public long rectFitness;
  public int oldGeneValue = -1;
  public int previousDelta = 0;
  public int previousGene = -1;
  public int successfulClimbs = 0;

  // *******************************************
  // public Genome(List<Triangle>triangles,BufferedImage targetImage)
  // List<Triangle> triangles,BufferedImage targetImage
  // a constructor that takes a List<Triangle> and
  // a BufferedImage
  // List , Image
  // Method's return value: Void
  // This method create a new genome with a target
  // width and height, and list of triangles
  // Method's Algorithm: this. value = constructor value
  // triangles list of triangles that contain the genes
  // targetImage the target image
  // ******************************************
  public Genome(List<Triangle> triangles, BufferedImage targetImage)
  {
    this.triangles = triangles;
    this.width = targetImage.getWidth();
    this.height = targetImage.getHeight();
  }

  // *******************************************
  // public Genome(List<Triangle>triangles,int width, int height)
  // List<Triangle> triangles,int width and height
  // a constructor that takes a List<Triangle> and
  // the int width and height
  // List , int
  // Method's return value: Void
  // This method create a new genome with a target
  // width and height, and list of triangles
  // Method's Algorithm: this. value = constructor value
  // triangles list of triangles that contain the genes
  // width target image width
  // height target image height
  // ******************************************
  public Genome(List<Triangle> triangles, int width, int height)
  {
    this.triangles = triangles;
    this.width = width;
    this.height = height;
  }

  /*****************************************
   * public void drawImage(BufferedImage image)
   * 
   * Graphics2D g2D, BufferedImage image
   * 
   * draw genome image on scene Graphics2D, BufferedImage
   * 
   * Method's return value: Void
   * 
   * This method draws a whole genome on an image
   * 
   * Method's Algorithm: create graphics, setBackground color, clear rectangle,
   * set image image to draw on
   ******************************************/
  public void drawImage(BufferedImage image)
  {
    Graphics2D g2D = image.createGraphics();
    g2D.setBackground(Color.white);
    g2D.clearRect(0, 0, width, height);
    for (int i = 0; i < triangles.size(); i++)
    {
      triangles.get(i).drawTriangle(g2D);
    }
  }

  /*****************************************
   * public void drawImage(BufferedImage image, int numberOfTriangles)
   * 
   * Graphics2D g2D, BufferedImage image
   * 
   * draw genome image on scene Graphics2D, BufferedImage
   * 
   * Method's return value: Void
   * 
   * This method draws genome on an image with specified number of triangles
   * 
   * Method's Algorithm: create graphics, setBackground color, clear rectangle,
   * set image image to draw on set numberOfTriangles and how many triangles to
   * draw
   ******************************************/
  public void drawImage(BufferedImage image, int numberOfTriangles)
  {
    Graphics2D g2D = image.createGraphics();
    g2D.setBackground(Color.white);
    g2D.clearRect(0, 0, width, height);
    for (int i = 0; i < numberOfTriangles; i++)
    {
      triangles.get(i).drawTriangle(g2D);
    }
  }

  /*****************************************
   * public int distance(Color c1, Color c2)
   * 
   * Color c1, c2
   * 
   * distance between colors
   * 
   * Method's return value: Int. returns the relative distance
   * 
   * This method finds the relative distance between two colors
   * 
   * Method's Algorithm: red * red + green * green + blue * blue. c1 first color
   * c2 second color
   ******************************************/
  public int distance(Color c1, Color c2)
  {
    int red = c1.getRed() - c2.getRed();
    int green = c1.getGreen() - c2.getGreen();
    int blue = c1.getBlue() - c2.getBlue();

    return Math.abs(red) + Math.abs(green) + Math.abs(blue);
  }

  /*****************************************
   * public int getWidth()
   * 
   * no Parameters
   * 
   * width of genome
   * 
   * Method's return value: Int. returns the width
   * 
   * This method gets the width of the genome
   * 
   * Method's Algorithm: return width
   ******************************************/
  public int getWidth()
  {
    return width;
  }

  /*****************************************
   * public int getHeight()
   * 
   * no Parameters
   * 
   * height of genome
   * 
   * Method's return value: Int. returns the height
   * 
   * This method gets the height of the genome
   * 
   * Method's Algorithm: return height
   ******************************************/
  public int getHeight()
  {
    return height;
  }

  /*****************************************
   * public int getGene(int n)
   * 
   * int
   * 
   * get gene of the genome
   * 
   * Method's return value: Int. returns the gene value
   * 
   * This method gets the gene of the genome
   * 
   * Method's Algorithm: triangles.get(n / 10).getGene(n % 10)
   ******************************************/
  public int getGene(int n)
  {
    return triangles.get(n / 10).getGene(n % 10);
  }

  /*****************************************
   * public void setGene(int n, int value)
   * 
   * int
   * 
   * set gene of the genome
   * 
   * Method's return value: Void
   * 
   * This method sets the gene of the genome
   * 
   * Method's Algorithm: triangles.get(n / 10).setGene(n % 10,value)
   ******************************************/
  public void setGene(int n, int value)
  {
    triangles.get(n / 10).setGene(n % 10, value);
  }

  /**
   * Check if a gene can change
   * 
   * @param n gene to check
   * @param delta how much
   * @return true if yes
   */
  /*****************************************
   * public boolean canChange(int n, int delta)
   * 
   * int
   * 
   * can change: true/false
   * 
   * Method's return value: Bool. returns true of false
   * 
   * This method checks if a gene can change
   * 
   * Method's Algorithm: triangles.get(n / 10).canChange(n % 10)
   ******************************************/
  public boolean canChange(int n, int delta)
  {
    return triangles.get(n / 10).canChange(n % 10, delta);

  }

  /*****************************************
   * public void changeGene(int n, int delta)
   * 
   * int
   * 
   * can change: true/false
   * 
   * Method's return value: Void
   * 
   * This method changes a gene
   * 
   * Method's Algorithm: triangles.get(n / 10).changeGene(n % 10) int n : gene
   * to change int delta: how much
   ******************************************/
  public void changeGene(int n, int delta)
  {
    triangles.get(n / 10).changeGene(n % 10, delta);
  }

  /*****************************************
   * public int[] getGeneBoundingRect(int n)
   * 
   * int
   * 
   * get gene bounding rectangle
   * 
   * Method's return value: int[]. returns rect
   * 
   * This method gets the bounding rectangle for a triangle that has gene
   * 
   * Method's Algorithm: triangles.get(n / 10).getBoundingRect() int n : gene
   ******************************************/
  public int[] getGeneBoundingRect(int n)
  {

    return triangles.get(n / 10).getBoundingRect();

  }

  /**
   * Create a deep copy of the current genome
   * 
   * @return new genome with all the same values but new addresses, except for
   *         target image
   */
  public Genome deepCopy()
  {
    List<Triangle> trianglesCopy = new ArrayList<>(triangles.size());

    for (Triangle triangle : triangles)
    {
      trianglesCopy.add(triangle.deepCopy());
    }

    return new Genome(trianglesCopy, width, height);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + height;
    result = prime * result + ((triangles == null) ? 0 : triangles.hashCode());
    result = prime * result + width;
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj)
  {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Genome other = (Genome) obj;

    if (width != other.width) return false;

    if (height != other.height) return false;

    if (triangles == null)
    {
      if (other.triangles != null) return false;
    }
    else if (!triangles.equals(other.triangles)) return false;
    return true;
  }

  /**
   * Create a random genome using the random generator in Constants
   * 
   * @param targetImage the target image
   * @return new randomly generated genome
   */
  public static Genome randomGenome(BufferedImage targetImage)
  {
    return randomGenome(targetImage, Constants.RANDOM);
  }

  /**
   * Create a random genome using a random generator
   * 
   * @param targetImage target image
   * @param rand random generator
   * @return new randomly generated genome
   */
  public static Genome randomGenome(BufferedImage targetImage, Random rand)
  {
    int width = targetImage.getWidth();
    int height = targetImage.getHeight();
    return new Genome(genRandomTriangles(width, height, rand), targetImage);
  }

  /**
   * Create a random genome using a Constants.RANDOM and a fixed alpha using
   * 
   * @param targetImage target image
   * @param alpha fixed alpha to use
   * @return new randomly generated genome
   */
  public static Genome randomGenomeFixedAlpha(BufferedImage targetImage, int alpha)
  {
    return randomGenomeFixedAlpha(targetImage, Constants.RANDOM, alpha);
  }

  /**
   * Create a random genome using a random generator and a fixed alpha
   * 
   * @param targetImage target image
   * @param rand random generator
   * @param alpha fixed alpha to use
   * @return new randomly generated genome
   */
  public static Genome randomGenomeFixedAlpha(BufferedImage targetImage, Random rand, int alpha)
  {
    int width = targetImage.getWidth();
    int height = targetImage.getHeight();
    return new Genome(genRandomTrianglesFixedAlpha(width, height, rand, alpha), targetImage);
  }

  /**
   * Helper method to generate random triangles of size TRIANGLE_COUNT
   * 
   * @param width width of the target image
   * @param height height of the target image
   * @param rand random generator to use
   * @return a list of triangles that were randomly generated
   */
  public static List<Triangle> genRandomTriangles(int width, int height, Random rand)
  {
    List<Triangle> triangleList = new ArrayList<>(Constants.TRIANGLE_COUNT);

    for (int i = 0; i < Constants.TRIANGLE_COUNT; i++)
    {
      triangleList.add(Triangle.createRandom(width, height, rand));
    }

    return triangleList;
  }

  /**
   * Helper method to generate random triangles of size TRIANGLE_COUNT with a
   * fixed alpha
   * 
   * @param width width of the target image
   * @param height height of the target image
   * @param rand random generator to use
   * @param alpha fixed alpha to use
   * @return a list of triangles that were randomly generated
   */
  public static List<Triangle> genRandomTrianglesFixedAlpha(int width, int height, Random rand, int alpha)
  {
    List<Triangle> triangleList = new ArrayList<>(Constants.TRIANGLE_COUNT);

    // make triangle for whole image

    int[] xPoints = new int[3];
    int[] yPoints = new int[3];
    int[] colors = new int[4];

    xPoints[0] = 0;
    yPoints[0] = 0;

    xPoints[1] = width - 1;
    yPoints[1] = 0;

    xPoints[2] = width / 2;
    yPoints[2] = height / 2;

    colors[0] = rand.nextInt(Constants.COLOR_LIMIT);
    colors[1] = rand.nextInt(Constants.COLOR_LIMIT);
    colors[1] = rand.nextInt(Constants.COLOR_LIMIT);
    colors[3] = 200;

    triangleList.add(new Triangle(xPoints, yPoints, colors, width, height));
    xPoints[0] = 0;
    yPoints[0] = 0;

    xPoints[1] = 0;
    yPoints[1] = height - 1;
    xPoints[2] = width / 2;
    yPoints[2] = height / 2;

    colors[0] = rand.nextInt(Constants.COLOR_LIMIT);
    colors[1] = rand.nextInt(Constants.COLOR_LIMIT);
    colors[1] = rand.nextInt(Constants.COLOR_LIMIT);
    colors[3] = 200;

    triangleList.add(new Triangle(xPoints, yPoints, colors, width, height));

    xPoints[0] = width - 1;
    yPoints[0] = 0;

    xPoints[1] = width - 1;
    yPoints[1] = height - 1;

    xPoints[2] = width / 2;
    yPoints[2] = height / 2;

    colors[0] = rand.nextInt(Constants.COLOR_LIMIT);
    colors[1] = rand.nextInt(Constants.COLOR_LIMIT);
    colors[1] = rand.nextInt(Constants.COLOR_LIMIT);
    colors[3] = 200;

    triangleList.add(new Triangle(xPoints, yPoints, colors, width, height));

    xPoints[0] = 0;
    yPoints[0] = height - 1;

    xPoints[1] = width - 1;
    yPoints[1] = height - 1;

    xPoints[2] = width / 2;
    yPoints[2] = height / 2;

    colors[0] = rand.nextInt(Constants.COLOR_LIMIT);
    colors[1] = rand.nextInt(Constants.COLOR_LIMIT);
    colors[1] = rand.nextInt(Constants.COLOR_LIMIT);
    colors[3] = 200;

    triangleList.add(new Triangle(xPoints, yPoints, colors, width, height));

    for (int i = 0; i < Constants.TRIANGLE_COUNT - 4; i++)
    {
      triangleList.add(Triangle.createRandomFixedAlpha(width, height, rand, alpha));
    }

    return triangleList;
  }

  /**
   * Change the specified gene value in triangle by 1 or reset to 0
   * 
   * Increases gene by 1 unless it is the maximum value then it sets it to 0
   * 
   * @param g Genome where to change the gene
   * @param triangle triangle at which to change the gene
   * @param gene the number of the gene to change (between 0 and 9)
   */
  public static void changeGene(Genome g, int triangle, int gene)
  {
    Triangle geneTriangle = g.triangles.get(triangle);

    if (gene < 6)
    {
      int index = gene / 2;
      List<Point> vertices = geneTriangle.getVertices();
      Point p = vertices.get(index);
      if (gene % 2 == 0)
      {
        if (p.x < g.width - 1)
        {
          p.x += 1;
        }
        else
        {
          p.x = 0;
        }
      }
      else
      {
        if (p.y < g.height - 1)
        {
          p.y += 1;
        }
        else
        {
          p.y = 0;
        }
      }
    }
    else
    {
      int index = gene - 6;
      List<Integer> colorValues = geneTriangle.getColorsList();
      int val = colorValues.get(index);
      if (val < Constants.COLOR_LIMIT - 1)
      {
        val += 1;
      }
      else
      {
        val = 0;
      }
      colorValues.set(index, val);
      geneTriangle.setColorValues(colorValues);
    }
  }

  /**
   * Checks if a genomes values are all in range
   * 
   * @param g1 genome
   * @return True if all values are correctly in range, false otherwise
   */
  public static boolean validGenome(Genome g1)
  {
    if (g1.triangles.size() != Constants.TRIANGLE_COUNT)
    {
      return false;
    }

    for (Triangle triangle : g1.triangles)
    {
      if (!triangle.valid())
      {
        return false;
      }
    }
    return true;
  }

  /**
   * Finds the Hamming Distance from another genome
   * 
   * @param other genome to find distance from
   * @return Hamming Distance (number of differences)
   */
  public int hammingDistance(Genome other)
  {

    int distance = 0;

    // Loop through all triangles
    for (int i = 0; i <= triangles.size() - 1; i++)
    {
      distance += triangles.get(i).hammingDistance(other.triangles.get(i));
    }

    return distance;
  }

  /**
   * Get the fitness
   * 
   * @return the fitness
   */
  public long getFitness()
  {
    return fitness;
  }

  /**
   * Set the fitness
   * 
   * @param fitness
   */
  public void setFitness(long fitness)
  {
    this.fitness = fitness;
  }

  /**
   * Compare set fitness to other genome
   */
  @Override
  public int compareTo(Genome other)
  {
    return Long.compare(fitness, other.fitness);
  }

  /**
   * Get the triangles that represent genome
   * 
   * @return list of all triangles
   */
  public List<Triangle> getTriangles()
  {
    return triangles;
  }
}
