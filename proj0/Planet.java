public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double g = 6.67e-11;

	
	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
		
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
    	yyPos = p.yyPos;
    	xxVel = p.xxVel;
    	yyVel = p.yyVel;
    	mass = p.mass;
    	imgFileName = p.imgFileName;

	}

	public double calcDistance(Planet p){
		double r_squre = (this.xxPos - p.xxPos) * (this.xxPos - p.xxPos ) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos);
		double r = Math.sqrt(r_squre);
		return r;

	}

	public double calcForceExertedBy(Planet p){
		double r = this.calcDistance(p);
		double f = (this.g * this.mass * p.mass) / (r * r);
		return f;
	}

	public double calcForceExertedByX(Planet p){
		double dx = p.xxPos - xxPos;
		double r = calcDistance(p);
		return calcForceExertedBy(p) * dx / r;
	}
	public double calcForceExertedByY(Planet p){
		double dy = p.yyPos - yyPos;
		double r = calcDistance(p);
		return calcForceExertedBy(p) * dy / r;
	}

	public double calcNetForceExertedByX(Planet[] ps){
		double Fx = 0;
		for(Planet p : ps){
			if(this.equals(p)){
				continue;
			}else{
				Fx += calcForceExertedByX(p);
			}
			//Fx += fx不能写在这里？
		}
		return Fx;
	}

	public double calcNetForceExertedByY(Planet[] ps){
		double Fy = 0;
		for(Planet p : ps){
			if(this.equals(p)){
				continue;
			}else{
				Fy += calcForceExertedByY(p);
			}
			//Fy += fy不能写在这里？
		}
		return Fy;
	}

	public void update(double time, double fx, double fy){
		double ax = fx / this.mass;
		double ay = fy / this.mass;

		this.xxVel += ax * time;
		this.yyVel += ay * time; 

		this.xxPos += time * this.xxVel;
		this.yyPos += time * this.yyVel;
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}

}