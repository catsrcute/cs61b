public class Planet
{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public double G=6.67e-11;
    public Planet(double xP,double yP,double xV,double yV,double m,String img)
    {
        this.xxPos=xP;
        this.yyPos=yP;
        this.xxVel=xV;
        this.yyVel=yV;
        this.mass=m;
        this.imgFileName=img;
    }
    public Planet(Planet p)
    {
        this.xxPos=p.xxPos;
        this.yyPos=p.yyPos;
        this.xxVel=p.xxVel;
        this.yyVel=p.yyVel;
        this.mass=p.mass;
        this.imgFileName=p.imgFileName;
    }
    public double calcDistance(Planet p)
    {
        double xa=this.xxPos;
        double ya=this.yyPos;
        double xb=p.xxPos;
        double yb=p.yyPos;
        return Math.sqrt((xa-xb)*(xa-xb)+(ya-yb)*(ya-yb));
    }
    public double calcForceExertedBy(Planet p)
    {
        double r=this.calcDistance(p);
        double m1=this.mass;
        double m2=p.mass;
        return G*m1*m2/(r*r);
    }
    public double calcForceExertedByX(Planet p)
    {
        double a=p.xxPos-this.xxPos;
        return this.calcForceExertedBy(p)*a/this.calcDistance(p);
    }
    public double calcForceExertedByY(Planet p)
    {
        double a=p.yyPos-this.yyPos;
        return this.calcForceExertedBy(p)*a/this.calcDistance(p);
    }
    public double calcNetForceExertedByX(Planet[] k)
    {
        double ans=0;
        for(Planet s:k)
        {
            if(this.equals(s))
            {
                continue;
            }
            else
            {
                ans=ans+this.calcForceExertedByX(s);
            }
        }
        return ans;
    }
    public double calcNetForceExertedByY(Planet[] k)
    {
        double ans=0;
        for(Planet s:k)
        {
            if(this.equals(s))
            {
                continue;
            }
            else
            {
                ans=ans+this.calcForceExertedByY(s);
            }
        }
        return ans;
    }
    public void update(double dt,double fx,double fy)
    {
        double ax;
        double ay;
        ax=fx/this.mass;
        ay=fy/this.mass;
        this.xxVel=this.xxVel+ax*dt;
        this.yyVel=this.yyVel+ay*dt;
        this.xxPos=this.xxPos+this.xxVel*dt;
        this.yyPos=this.yyPos+this.yyVel*dt;
    }
    public void draw()
    {
        StdDraw.picture(this.xxPos/1e9,this.yyPos/1e9,this.imgFileName);
    }
    /*
    Some tests for the code above.
    public static void main(String[] args)
    {
        Planet test=new Planet(1,0,10,5.0,10,"sss");
        Planet test1=new Planet(3,3,5,5.0,5,"kkk");
        Planet test2=new Planet(5,-3,2,2,50,"aaa");
        Planet[] k ={test,test1,test2};

        
        System.out.println(test.calcNetForceExertedByY(k));
    }
    */


}