package edu.cg.scene.lightSources;

import java.util.List;

import edu.cg.algebra.Hit;
import edu.cg.algebra.Point;
import edu.cg.algebra.Ray;
import edu.cg.algebra.Vec;
import edu.cg.scene.objects.Surface;

public class DirectionalLight extends Light {
	private Vec direction = new Vec(0, -1, -1);

	public DirectionalLight initDirection(Vec direction) {
		this.direction = direction;
		return this;
	}

	@Override
	public String toString() {
		String endl = System.lineSeparator();
		return "Directional Light:" + endl + super.toString() +
				"Direction: " + direction + endl;
	}

	@Override
	public DirectionalLight initIntensity(Vec intensity) {
		return (DirectionalLight)super.initIntensity(intensity);
	}
	
	@Override
	public Vec intensity(Point hittingPoint) {
		return this.intensity;
	}
	
	@Override
	public Vec direction(Point hittingPoint) {
		return direction.normalize().neg();
	}
	
	@Override
	public int isOccluded(Ray ray, Hit hit,List<Surface> surfaces) {
		Point pointToCheck = ray.getHittingPoint(hit);
		Ray toLight = new Ray(pointToCheck, direction(pointToCheck));
		Hit gotHit = null;
		for (Surface surface : surfaces) {
			gotHit = surface.intersect(toLight);
			if ((gotHit != null)) 
				return 0;
		}
		return 1;
		
	}

	




}
