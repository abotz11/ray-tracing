package edu.cg.scene.lightSources;

import java.util.List;

import edu.cg.algebra.Hit;
import edu.cg.algebra.Point;
import edu.cg.algebra.Ray;
import edu.cg.algebra.Vec;
import edu.cg.scene.objects.Surface;

public abstract class Light {
	protected Vec intensity = new Vec(1, 1, 1); //white color
	
	
	@Override
	public String toString() {
		String endl = System.lineSeparator();
		return "Intensity: " + intensity + endl;
	}
	
	public Light initIntensity(Vec intensity) {
		this.intensity = intensity;
		return this;
	}

	
	public abstract Vec intensity(Point hittingPoint); 
	
	public abstract Vec direction(Point hittingPoint);
	
	public abstract int isOccluded(Ray ray, Hit hit, List<Surface> surfaces);
	//TODO: add some methods
}
