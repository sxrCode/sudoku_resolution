package com.sxr.nine;

import java.util.Iterator;

public class Chessboard {

	private Place[][] places = new Place[9][9];

	public Chessboard(Place[][] infos) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Place oldPlace = infos[i][j];
				Place newPlace = new Place();
				if (!oldPlace.isEmpty()) {
					newPlace.setValue(oldPlace.getValue());
				} else {
					newPlace = new Place(oldPlace.getAvailableIterator());
				}

				this.places[i][j] = newPlace;
			}
		}
	}

	public boolean calculate() {
		init();
		try {
			while (updatePlaces()) {
			}
		} catch (EvalueException e) {
			// e.printStackTrace();
			return false;
		}

		if (check()) {
			print();
			return true;
		}

		return guess();

	}

	private void init() {
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				Place place = this.places[row][column];
				if (!place.isEmpty()) {
					int value = place.getValue();
					notifyRow(row, value);
					notifyColumn(column, value);
					notifyFrame(row, column, value);
				}
			}
		}
	}

	private boolean updatePlaces() throws EvalueException {
		boolean hasUpdate = false;
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				Place place = this.places[row][column];

				if (place.evalue()) {
					int value = place.getValue();
					notifyRow(row, value);
					notifyColumn(column, value);
					notifyFrame(row, column, value);
					hasUpdate = true;
				}

			}
		}
		return hasUpdate;

	}

	private boolean check() {
		boolean result = true;
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				Place place = this.places[row][column];
				if (place.isEmpty()) {
					result = false;
					break;
				}
			}
		}

		return result;
	}

	private boolean guess() {
		int availableNum = 9;
		int rowRecord = 0, columnRecord = 0;

		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				Place place = this.places[row][column];
				if (place.isEmpty() && availableNum > place.getAvailableNum()) {
					availableNum = place.getAvailableNum();
					rowRecord = row;
					columnRecord = column;
				}
			}
		}

		Iterator<Integer> availables = this.places[rowRecord][columnRecord].getAvailableIterator();
		while (availables.hasNext()) {

			int guessValue = availables.next();
			Place guessPlace = new Place(guessValue);
			places[rowRecord][columnRecord] = guessPlace;
			Chessboard nextBoard = new Chessboard(places);
			if (nextBoard.calculate()) {
				return true;
			}
		}

		return false;
	}

	private void notifyRow(int row, int value) {
		for (int i = 0; i < 9; i++) {
			Place place = places[row][i];
			if (place.isEmpty()) {
				place.removeAvaiable(value);
			}
		}
	}

	private void notifyColumn(int column, int value) {
		for (int i = 0; i < 9; i++) {
			Place place = places[i][column];
			if (place.isEmpty()) {
				place.removeAvaiable(value);
			}
		}
	}

	private void notifyFrame(int row, int column, int value) {
		int rgroupIndex = row / 3;
		int cgroupIndex = column / 3;
		for (int rowIndex = rgroupIndex * 3; rowIndex < rgroupIndex * 3 + 3; rowIndex++) {
			for (int columnIndex = cgroupIndex * 3; columnIndex < cgroupIndex * 3 + 3; columnIndex++) {
				Place place = this.places[rowIndex][columnIndex];
				if (place.isEmpty()) {
					place.removeAvaiable(value);
				}
			}
		}
	}

	private void print() {

		for (int row = 0; row < 9; row++) {
			StringBuilder builder = new StringBuilder();
			for (int column = 0; column < 9; column++) {
				Place place = this.places[row][column];
				builder.append(place.getValue() + " ");
			}
			System.out.println(builder.toString());
		}
	}

	public void printAvailable() {
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				Place place = this.places[row][column];
				StringBuilder builder = new StringBuilder();
				if (place.isEmpty()) {
					Iterator<Integer> iterator = place.getAvailableIterator();
					builder.append("place[" + row + "][" + column + "] ava:");
					while (iterator.hasNext()) {
						builder.append(" " + iterator.next());
					}
				} else {
					builder.append("place[" + row + "][" + column + "] val: " + place.getValue());
				}
				System.out.println(builder.toString());

			}
		}
	}
}
