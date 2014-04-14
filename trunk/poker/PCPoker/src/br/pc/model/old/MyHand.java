package br.pc.model.old;

public class MyHand {
	private HandStatus handStatus;
	
	private Card myCard1;
	private Card myCard2;
	
	private Card flopCard1;
	private Card flopCard2;
	private Card flopCard3;
	
	private Card turnCard;
	private Card riverCard;
	
	public MyHand(Card myCard1, Card myCard2) {
		super();
		this.myCard1 = myCard1;
		this.myCard2 = myCard2;
		handStatus = HandStatus.PRE_FLOP;
	}


	public int havePair() {	
		int returnValue = 0;
		switch (handStatus) {
		case PRE_FLOP:
			
			break;

		default:
			break;
		}
		return 0;
	}
}
