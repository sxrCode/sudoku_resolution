package com.sxr.nine;

import java.util.Iterator;
import java.util.LinkedList;

public class Place implements Cloneable {

	private int value = 0;
	private boolean empty = true;

	private LinkedList<Integer> availables = new LinkedList<>();

	public Place(Integer value) {
		this.value = value;
		this.empty = false;
	}

	public Place(Iterator<Integer> iterator) {
		while (iterator.hasNext()) {
			this.availables.add(iterator.next());
		}
	}

	public Place() {
		for (int i = 1; i < 10; i++) {
			this.availables.add(i);
		}
	}

	public Place(Place oldPlace) {
		this.empty = oldPlace.empty;
		this.value = oldPlace.value;
		this.availables.addAll(oldPlace.availables);
	}

	public boolean isEmpty() {
		return this.empty;
	}

	public void setValue(int value) {
		empty = false;
		this.value = value;
		availables.clear();
	}

	public Integer getValue() {
		return value;
	}

	public boolean evalue() throws EvalueException {
		if (empty) {
			if (availables.size() > 1) {
				return false;
			} else if (availables.size() == 1) {
				setValue(availables.getFirst());
				return true;
			} else {
				throw new EvalueException();
			}
		}

		return false;

	}

	public void removeAvaiable(Integer ava) {
		availables.remove(ava);
	}

	public int getAvailableNum() {
		return this.availables.size();
	}

	public void addAvaiable(Integer ava) {
		availables.remove(ava);
		availables.add(ava);
	}

	public Iterator<Integer> getAvailableIterator() {
		return this.availables.iterator();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
