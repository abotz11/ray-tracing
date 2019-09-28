package edu.cg.scene.lightSources;

import java.util.List;

import edu.cg.algebra.Hit;
import edu.cg.algebra.Point;
import edu.cg.algebra.Ray;
import edu.cg.algebra.Vec;
import edu.cg.scene.objects.Surface;

public class PointLight extends Light {
	protected Point position;
	//Decay factors:
	protected double kq = 0.01;
	protected double kl = 0.1;
	protected double kc = 1;
	
	protected String description() {
		String endl = System.lineSeparator();
		return "Intensity: " + intensity + endl +
				"Position: " + position + endl +
				"Decay factors: kq = " + kq + ", kl = " + kl + ", kc = " + kc + endl;
	}
	
	@Override
	public String toString() {
		String endl = System.lineSeparator();
		return "Point Light:" + endl + description();
	}
	
	@Override
	public PointLight initIntensity(Vec intensity) {
		return (PointLight)super.initIntensity(intensity);
	}
	
	public PointLight initPosition(Point position) {
		this.position = position;
		return this;
	}
	
	public PointLight initDecayFactors(double kq, double kl, double kc) {
		this.kq = kq;
		this.kl = kl;
		this.kc = kc;
		return this;
	}
	
	@Override
	public Vec direction(Point hittingPoint) {
		return position.sub(hittingPoint).normalize();
	}

	@Override
	public Vec intensity(Point hittingPoint) {
		double d  = hittingPoint.dist(position);
		return intensity.mult(1 / (kc + (kl + kq*d)*d));
	}
	
	@Override
	public int isOccluded(Ray ray, Hit hit,List<Surface> surfaces) {
		Point pointToCheck = ray.getHittingPoint(hit);
		Ray toLight = new Ray(pointToCheck, direction(pointToCheck));
		Hit gotHit = null;
		for (Surface surface : surfaces) {
			gotHit = surface.intersect(toLight);
			if ((gotHit != null) && gotHit.t() <= pointToCheck.sub(position).length()) 
				return 0;
		}
		return 1;
		
	}
}
