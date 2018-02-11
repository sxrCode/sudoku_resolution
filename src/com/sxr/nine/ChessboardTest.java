package com.sxr.nine;

public class ChessboardTest {
	private Place[][] places = new Place[9][9];

	public ChessboardTest() {
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				places[row][column] = new Place();
			}
		}

	}

	public static void main(String[] args) {
		ChessboardTest test = new ChessboardTest();
		initData(test);

		Chessboard chessboard = new Chessboard(test.getPlaces());
		System.out.println(chessboard.calculate());
	}

	public void addPlace(int row, int column, int value) {
		this.places[row][column] = new Place(value);
	}

	public Place[][] getPlaces() {
		return places;
	}

	private static void initData(ChessboardTest test) {
		test.addPlace(0, 2, 5);
		test.addPlace(0, 3, 3);

		test.addPlace(1, 0, 8);
		test.addPlace(1, 7, 2);

		test.addPlace(2, 1, 7);
		test.addPlace(2, 4, 1);
		test.addPlace(2, 6, 5);

		test.addPlace(3, 0, 4);
		test.addPlace(3, 5, 5);
		test.addPlace(3, 6, 3);

		test.addPlace(4, 1, 1);
		test.addPlace(4, 4, 7);
		test.addPlace(4, 8, 6);

		test.addPlace(5, 2, 3);
		test.addPlace(5, 3, 2);
		test.addPlace(5, 7, 8);

		test.addPlace(6, 1, 6);
		test.addPlace(6, 3, 5);
		test.addPlace(6, 8, 9);

		test.addPlace(7, 2, 4);
		test.addPlace(7, 7, 3);

		test.addPlace(8, 5, 9);
		test.addPlace(8, 6, 7);

	}

}
