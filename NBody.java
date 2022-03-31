import java.util.Scanner;
public class NBody
{
    /*
    The static method can be used without initialize an instance.
    */
    
    public static double readRadius(String p)
    {
        In in=new In(p);
        int num=in.readInt();
        double Radius=in.readDouble();
        return Radius;

    }
    public static Planet[] readPlanets(String p)
    {
        In in=new In(p);
        int num=in.readInt();
        Planet[] Planetarray=new Planet[num];
        double Radius=in.readDouble();
        for(int i=0;i<=num-1;i=i+1)
        {
        Planetarray[i]=new Planet(0,0,0,0,0,"x");
        Planetarray[i].xxPos=in.readDouble();
        Planetarray[i].yyPos=in.readDouble();
        Planetarray[i].xxVel=in.readDouble();
        Planetarray[i].yyVel=in.readDouble();
        Planetarray[i].mass=in.readDouble();
        Planetarray[i].imgFileName=in.readString();
        }
        return Planetarray;
    }
    public static void main(String[] args)
    {
        Double timerecorder=0.0;
        String imagetodraw="./images/starfield.jpg";
        Scanner input=new Scanner(System.in);
        String Tstr=input.next();
        String dtstr=input.next();
        Double T=Double.valueOf(Tstr).doubleValue();
        Double dt=Double.valueOf(dtstr).doubleValue();
        String planetpath="./data/planets.txt";
        Double radius=NBody.readRadius(planetpath);
        Planet[] allplanet=NBody.readPlanets(planetpath);
        StdDraw.enableDoubleBuffering();
        Double[] netxforce=new Double[5];
        Double[] netyforce=new Double[5];
        while(timerecorder<=T)
        {
        for(int i=0;i<=4;i+=1)
        {
            netxforce[i]=allplanet[i].calcNetForceExertedByX(allplanet);
            netyforce[i]=allplanet[i].calcNetForceExertedByY(allplanet);
        }
        for(int i=0;i<=4;i+=1)
        {
            allplanet[i].update(dt,netxforce[i],netyforce[i]);
        }
        StdDraw.setScale(-300,300);
        StdDraw.clear();
        StdDraw.picture(0,0,imagetodraw);
        for(Planet k:allplanet)
        {
            k.draw();
            StdDraw.show();
        }
        StdDraw.show();
        StdDraw.pause(10);
        timerecorder+=dt;
        }

    
    }

}