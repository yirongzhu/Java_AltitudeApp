package bugQuestion;

import java.util.*;

class Direction {
	boolean up;
	boolean left;
	boolean down;
	boolean right;
}

public class Altitude {

	private int[][] matrix;
	private ArrayList<ArrayList<Integer>> checked;
	private Direction d;
	
	public ArrayList<ArrayList<Integer>> findAllAltitudePoints() {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return null;
		}
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				d.up = false;
				d.left = false;
				d.down = false;
				d.right = false;
				findAllAltitudePointsHelper(d, i, j);
				if ((d.up || d.left) && (d.down || d.right)) {
					ArrayList<Integer> pair = new ArrayList<Integer>(Arrays.asList(i, j));
					if (!result.contains(pair)) {
						result.add(pair);
					}
				}
			}
		}
		return result;
	}
	
	public boolean inBound(int i, int j) {
		return i >= 0 && j >= 0 && i < matrix.length && j < matrix[0].length;
	}
	
	public void findAllAltitudePointsHelper(Direction d, int i, int j) {
		if (i - 1 < 0) {
			d.up = true;
		}
		if (j - 1 < 0) {
			d.left = true;
		}
		if (i + 1 >= matrix.length) {
			d.down = true;
		}
		if (j + 1 >= matrix[0].length) {
			d.right = true;
		}
		if (inBound(i - 1, j)) {
			if (matrix[i - 1][j] <= matrix[i][j]) {
				ArrayList<Integer> check = new ArrayList<Integer>(Arrays.asList(i - 1, j));
				if (!checked.contains(check)) {
					checked.add(check);
					findAllAltitudePointsHelper(d, i - 1, j);
					checked.remove(check);
				}
			}
		}
		if (inBound(i, j - 1)) {
			if (matrix[i][j - 1] <= matrix[i][j]) {
				ArrayList<Integer> check = new ArrayList<Integer>(Arrays.asList(i, j - 1));
				if (!checked.contains(check)) {
					checked.add(check);
					findAllAltitudePointsHelper(d, i, j - 1);
					checked.remove(check);
				}
			}
		}
		if (inBound(i + 1, j)) {
			if (matrix[i + 1][j] <= matrix[i][j]) {
				ArrayList<Integer> check = new ArrayList<Integer>(Arrays.asList(i + 1, j));
				if (!checked.contains(check)) {
					checked.add(check);
					findAllAltitudePointsHelper(d, i + 1, j);
					checked.remove(check);
				}
			}
		}
		if (inBound(i, j + 1)) {
			if (matrix[i][j + 1] <= matrix[i][j]) {
				ArrayList<Integer> check = new ArrayList<Integer>(Arrays.asList(i + 1, j));
				if (!checked.contains(check)) {
					checked.add(check);
					findAllAltitudePointsHelper(d, i, j + 1);
					checked.remove(check);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Altitude a = new Altitude();
		a.matrix = new int[][] {{9, 9, 1, 9, 9}, {9, 9, 2, 9, 9}, {1, 2, 3, 2, 1}, {9, 9, 2, 9, 9}, {9, 9, 1, 9, 9}};
		for (int i = 0; i < a.matrix.length; i++) {
			for (int j = 0; j < a.matrix[0].length; j++) {
				System.out.print(a.matrix[i][j] + " ");
			}
			System.out.println();
		}
		a.checked = new ArrayList<ArrayList<Integer>>();
		a.d = new Direction();
		ArrayList<ArrayList<Integer>> result = a.findAllAltitudePoints();
		for (ArrayList<Integer> array : result) {
			System.out.println("(" + array.get(0) + ", " + array.get(1) + ")");
		}
	}
}
