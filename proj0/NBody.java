public class NBody{
	
	public static double readRadius(String filename){
		In in = new In(filename);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] ps = new Planet[num];
		for(int i = 0; i < num; i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFilename = in.readString();
			ps[i] = new Planet(xxPos, yyPos, xxVel, xxVel, mass, imgFilename);
		}
		return ps;
	}

	public static void main(String[] args) {
		double T = new Double(args[0]);
		double dt = new Double(args[1]);
		String filename = args[2];

		double r = readRadius(filename);
		Planet[] ps = readPlanets(filename);

		StdDraw.setXscale(-r, r);
		StdDraw.setYscale(-r, r);
		// draw background picture
		StdDraw.picture(0, 0, "images/starfield.jpg");
		
		StdDraw.enableDoubleBuffering();
		

		int num = ps.length;
		double[] xForces = new double[num];
		double[] yForces = new double[num];
		for(double t = 0.0; t < T; t += dt){
			for(int i =0; i < num; i ++){
				xForces[i] = ps[i].calcNetForceExertedByX(ps);
				yForces[i] = ps[i].calcNetForceExertedByY(ps);
			}
			for(int i =0; i < num; i ++){
				ps[i].update(dt, xForces[i], yForces[i]);
			}
			// draw background picture for each time
			StdDraw.picture(0, 0, "images/starfield.jpg");

			for (Planet p: ps){
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

		}
		StdOut.printf("%d\n", ps.length);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < ps.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
					ps[i].yyVel, ps[i].mass, ps[i].imgFileName);
		}
	}
}