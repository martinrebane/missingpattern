package na;

import java.util.List;

public class ProbabilityItem {
	private List<Boolean> pattern;
	private double probability;
	
	public ProbabilityItem(List<Boolean> pattern, double probability) {
		this.pattern = pattern;
		this.probability = probability;
	}
	
	public List<Boolean> getPattern() {
		return pattern;
	}
	
	public double getProbability() {
		return probability;
	}
}
