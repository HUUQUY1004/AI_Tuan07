package lab7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo {
	public static final int POP_SIZE = 100;// Population size
	public static final double MUTATION_RATE = 0.03;
	public static final int MAX_ITERATIONS = 1000;
	List<Node> population = new ArrayList<Node>();
	Random rd = new Random();

	// initialize the individuals of the population
	public void initPopulation() {
		for (int i = 0; i < POP_SIZE; i++) {
			Node ni = new Node();
			ni.generateBoard();
			population.add(ni);
		}
	}

	public Node execute() {
		// Enter your code here
		int k =0;
		while(k < MAX_ITERATIONS) {
			List<Node> new_population = new ArrayList<Node>();
			for(int i = 0; i< population.size(); i++) {
				Node x = getParentByRandomSelection();
				Node y = getParentByRandomSelection();
				Node child = reproduce(x, y);
				if(MUTATION_RATE < rd.nextDouble()) {
					mutate(child);
					new_population.add(child);
				}
			}
			population = new_population;
			k++;
		}
		Collections.sort(population);
		return population.get(0);
		
	}

	// Mutate an individual by selecting a random Queen and //move it to a random
	// row.
	public void mutate(Node node) {
		// Enter your code here
//		return null;
		 int i = rd.nextInt(Node.N);
		 int row  = rd.nextInt(Node.N);
		 node.setRow(i, row);
	}

	// Crossover x and y to reproduce a child
	public Node reproduce(Node x, Node y) {
		// Enter your code here
		int c = rd.nextInt(Node.N);
		Node n = new Node();
//		n.generateBoard();
		for(int i=1 ; i<= c ; i++) {
			n.setRow(i,x.getRow(i));
		}
		for(int j = c+1 ; j<Node.N; j++) {
			n.setRow(j, y.getRow(j));
		}
		return n;
	}

	// Select K individuals from the population at random and //select the best out
	// of these to become a parent.
	public Node getParentByTournamentSelection() {
		// Enter your code here
		int k = rd.nextInt(Node.N);
		Node best = population.get(k).getBestCandidate();
		
		return best;
	}

	// Select a random parent from the population
	public Node getParentByRandomSelection() {
		// Enter your code here
		int  k = rd.nextInt(Node.N);
		Node ran = population.get(k);
		return ran;
	}
}
