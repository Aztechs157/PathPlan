public class Rectangle
{
    private int x;
    private int y;
    private int width;
    private int height;
    private int angle;
    public Rectangle(int x, int y, int width, int height, int angle, int robotRadius)
    {
        this.x = x;
        this.y = y;
        this.width = width+robotRadius;
        this.height = height+robotRadius;
        this.angle = angle;
    }
    
    public boolean intersect(int robotX, int robotY)
    {
        boolean retVal = false;
        if (robotX < this.x-this.width/2)
        {
            if (robotX > this.x+this.width/2)
            {
                if (robotY < this.y-this.height/2)
                {
                    if (robotY > this.y+this.height/2)
                    {
                        retVal = true;
                    }
                }
            }
        }
        return retVal;
    }
    public double[] nearestCorner(int robotX, int robotY)
    {
        double r = Math.sqrt(Math.pow(this.width/2,  2)+ Math.pow(this.height/2, 2));
        double corners[][] = 
            {
                {
                    this.x+(r*Math.toDegrees(Math.cos(this.angle+Math.toDegrees(Math.atan(this.height/this.width))))), 
                    this.y+(r*Math.toDegrees(Math.sin(this.angle+Math.toDegrees(Math.atan(this.height/this.width)))))
                },
                {
                    this.x+(r*Math.toDegrees(Math.cos(this.angle+Math.toDegrees(Math.atan(this.height/this.width))))), 
                    this.y-(r*Math.toDegrees(Math.sin(this.angle+Math.toDegrees(Math.atan(this.height/this.width)))))
                    },
                {
                    this.x-(r*Math.toDegrees(Math.cos(this.angle+Math.toDegrees(Math.atan(this.height/this.width))))), 
                    this.y+(r*Math.toDegrees(Math.sin(this.angle+Math.toDegrees(Math.atan(this.height/this.width)))))
                },
                {
                    this.x-(r*Math.toDegrees(Math.cos(this.angle+Math.toDegrees(Math.atan(this.height/this.width))))), 
                    this.y-(r*Math.toDegrees(Math.sin(this.angle+Math.toDegrees(Math.atan(this.height/this.width)))))
                }
            };
        int lowestIndex = 0;
        double lowestDist = 0;
        for (int i = 0; i < corners.length; i++)
        {
            double dist = Math.sqrt(Math.pow(robotX-(corners[i][0]), 2)+Math.pow(robotY-(corners[i][1]), 2));
            if (lowestDist > dist)
            {
                lowestDist = dist;
                lowestIndex = i;
            }
        }
        double[] retVal = {corners[lowestIndex][0], corners[lowestIndex][1]};
        return retVal;
    }
}
