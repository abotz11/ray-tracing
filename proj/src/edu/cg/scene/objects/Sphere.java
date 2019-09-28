package edu.cg.scene.objects;

import edu.cg.algebra.Hit;
import edu.cg.algebra.Ops;
import edu.cg.algebra.Point;
import edu.cg.algebra.Ray;

public class Sphere extends Shape {
	private Point center;
	private double radius;
	
	public Sphere(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Sphere() {
		this(new Point(0, -0.5, -6), 0.5);
	}
	public Point getCenter() {
		return this.center;
	}
	
	public double getRadius() {
		return this.radius;
	}
	
	@Override
	public String toString() {
		String endl = System.lineSeparator();
		return "Sphere:" + endl + 
				"Center: " + center + endl +
				"Radius: " + radius + endl;
	}
	
	public Sphere initCenter(Point center) {
		this.center = center;
		return this;
	}
	
	public Sphere initRadius(double radius) {
		this.radius = radius;
		return this;
	}
	
	@Override
	public Hit intersect(Ray ray) {
		double b = ray.direction().normalize().mult(2).dot(ray.source().sub(this.center));
		double c = ray.source().sub(this.center).norm() * ray.source().sub(this.center).norm() - (this.radius * this.radius);
		double disc = Math.sqrt((b*b) - (4 *c));
		if(Double.isNaN(disc))
			return null;
		
		double t = (-b - disc)/2;
		if (t < Ops.epsilon | t > Ops.infinity)
			return null;

		Point p = ray.add(t);
		Hit hit = new Hit(t,(p.sub(center).normalize()));
		return hit;		
	}
}
