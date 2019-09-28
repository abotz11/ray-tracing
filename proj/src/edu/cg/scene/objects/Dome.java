package edu.cg.scene.objects;

import edu.cg.algebra.Hit;
import edu.cg.algebra.Point;
import edu.cg.algebra.Ray;
import edu.cg.algebra.Vec;

public class Dome extends Shape {
	private Sphere sphere;
	private Plain plain;
	
	public Dome() {
		sphere = new Sphere().initCenter(new Point(0, -0.5, -6));
		plain = new Plain(new Vec(-1, 0, -1), new Point(0, -0.5, -6));
	}
	
	@Override
	public String toString() {
		String endl = System.lineSeparator();
		return "Dome:" + endl + 
				sphere + plain + endl;
	}
	
	@Override
	public Hit intersect(Ray ray) {
		Vec normToPlain = plain.abcToVec();
		Hit hitSphere = this.sphere.intersect(ray);
		if(hitSphere == null)
			return null;

		if(normToPlain.dot(ray.getHittingPoint(hitSphere).toVec()) + plain.getD() >= 0) 
			return hitSphere;

		
		Hit hitPlain = this.plain.intersect(ray);
		if (hitPlain == null)
			return null;
		
		Point p = ray.getHittingPoint(hitPlain);
		if(p.sub(sphere.getCenter()).normSqr() - (sphere.getRadius() * sphere.getRadius()) > 0)
			return null;
			
		return hitPlain;
		
		
		
		
		
		
		//TODO: implement this method.
	}
}
