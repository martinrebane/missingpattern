package na;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClusterDataGen {

	StringBuilder output = new StringBuilder();
	List<ProbabilityItem> probabilities = new ArrayList<>();
	private long currentLineNumber = 0;

	public static void main(String[] args) {
		
		ClusterDataGen processor = new ClusterDataGen();
		
		for (int i = 0; i < 1200; i++) {
			if (i < 400) {
				processor.addLineAndControlProbability("1,1,2,2,3,3,4");
			} else if (i < 800) {
				processor.addLineAndControlProbability("2,2,3,3,4,4,5");
			} else {
				processor.addLineAndControlProbability("6,6,7,7,8,8,9");
			}
		}
		
		System.out.println("Complete");
		System.out.println(processor.getOutput());
	}

	private void addProbability(List<Boolean> probabilityItem, double probability) {
		probabilities.add(new ProbabilityItem(probabilityItem, probability));
	}

	private void addLineAndControlProbability(String str) {
		if (currentLineNumber == 0) {
			// 1st patch
			addProbability(Arrays.asList(true,true,true,true,true,true,true), 0.7);
			addProbability(Arrays.asList(true,true,false,true,true,true,true), 0.15);
			addProbability(Arrays.asList(true,true,false,false,true,true,true), 0.073);
			addProbability(Arrays.asList(true,true,false,false,false,true,true), 0.77);
		} else if (currentLineNumber == 400) {
			this.probabilities = new ArrayList<>();
			addProbability(Arrays.asList(true,true,true,true,true,true,true), 0.6);
			addProbability(Arrays.asList(false,true,true,true,true,true,true), 0.2);
			addProbability(Arrays.asList(false,true,true,true,true,true,false), 0.1);
			addProbability(Arrays.asList(false,true,true,true,true,false,false), 0.1);
		} else if (currentLineNumber == 800) {
			this.probabilities = new ArrayList<>();
			addProbability(Arrays.asList(true,true,true,true,true,true,true), 1);
		}
		addLine(str);
		currentLineNumber++;
	}

	private void addLine(String str) {
		List<String> data = Arrays.asList(str.split(","));
		List<Boolean> pattern = this.getPattern();
		
		StringBuilder naAppliedData = new StringBuilder();
		for (int i = 0; i < pattern.size(); i++) {
			if (pattern.get(i) == true) {
				naAppliedData.append(data.get(i) + ",");
			} else {
				naAppliedData.append("?,");
			}
		}
		
		output.append(naAppliedData.subSequence(0, naAppliedData.length()-1));
		output.append("\n");
	}
	
	private String getOutput() {
		return output.toString();
	}
	
	private List<Boolean> getPattern() {
		double probabilitySum = Math.random();
		double cumulativeProbability = 0.0;
		
		for (ProbabilityItem prob : probabilities) {
		    cumulativeProbability += prob.getProbability();
		    if (probabilitySum <= cumulativeProbability) {
		        return prob.getPattern();
		    }
		}
		throw new IllegalStateException("Did not select probability");
	}
}
