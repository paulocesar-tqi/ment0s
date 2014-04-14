package br.pc.model.old;

public enum Suit {
	HEARTS("hearts"), SPADES("spades"), CLUBS("clubs"), DIAMONDS("diamonds");

	Suit(String name) {
		this.name = name;
	}

	private String name;

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

}