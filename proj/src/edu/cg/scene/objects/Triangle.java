package edu.cg.scene.objects;

import edu.cg.UnimplementedMethodException;
import edu.cg.algebra.Hit;
import edu.cg.algebra.Ops;
import edu.cg.algebra.Point;
import edu.cg.algebra.Ray;
import edu.cg.algebra.Vec;

public class Triangle extends Shape {
	private Point p1, p2, p3;
	
	public Triangle() {
		p1 = p2 = p3 = null;
	}
	
	public Triangle(Point p1, Point p2, Point p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	
	@Override
	public String toString() {
		String endl = System.lineSeparator();
		return "Triangle:" + endl +
				"p1: " + p1 + endl + 
				"p2: " + p2 + endl +
				"p3: " + p3 + endl;
	}

	@Override
	public Hit intersect(Ray ray) {
		
		Hit hit = null;
		
		Vec v1 = p1.sub(ray.source());
		Vec v2 = p2.sub(ray.source());
		Vec v3 = p3.sub(ray.source());
		
		Vec normal21 = v2.cross(v1).normalize();
		Vec normal32 = v3.cross(v2).normalize();
		Vec normal13 = v1.cross(v3).normalize();
		
		
	
		Vec pToPo = ray.direction();
		double signWithN21 = pToPo.dot(normal21);
		double signWithN32 = pToPo.dot(normal32);
		double signWithN13 = pToPo.dot(normal13);
		if(!((signWithN21 >= 0 && signWithN32 >= 0 && signWithN13 >= 0) || (signWithN21 <= 0 && signWithN32 <= 0 && signWithN13 <= 0))) {
	
			return null;
		}
		
		Vec normToTriangle = (p2.sub(p1).cross(p3.sub(p1))).normalize(); 
		Plain plain = new Plain(normToTriangle,this.p1);
		hit = plain.intersect(ray);
		
		if(hit == null) {
			return null;
		
		}
		return hit;	
	}
}
