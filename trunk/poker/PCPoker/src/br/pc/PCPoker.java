package br.pc;

import java.awt.Toolkit;
import java.io.File;
import java.util.Map;

import mi.poker.calculation.Calculation;
import mi.poker.calculation.HandInfo;
import mi.poker.calculation.MonteCarloSimulation;
import mi.poker.calculation.Result;

import org.apache.commons.io.FilenameUtils;
import org.sikuli.script.Finder;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;

public class PCPoker {

	private static final int cardWidth = 13;
	private static final int cardHeight = 35;

	private static final int xMyCard1 = 356;
	private static final int xMyCard2 = 404;
	private static final int yMyCards = 381;

	private static final int xFlopCard1 = 273;
	private static final int xFlopCard2 = 327;
	private static final int xFlopCard3 = 381;
	private static final int xTurnCard = 435;
	private static final int xRiverCard = 489;
	private static final int yDeskCards = 222;

	private static final Region boardCardsRegion = new Region(271, 220, 266, 4);
	private static final Region buttonsRegion = new Region(384, 519, 411, 59);
	private static final Region player1CardsRegion = new Region(60, 301, 97, 10);
	private static final Region player2CardsRegion = new Region(60, 129, 97, 10);
	private static final Region player3CardsRegion = new Region(357, 63, 97, 10);
	private static final Region player4CardsRegion = new Region(655, 129, 97, 10);
	private static final Region player5CardsRegion = new Region(655, 301, 97, 10);

	private static final String cardsDir = "D:/workspace/PCPoker/imgs/cards";
	private static final String assetsDir = "D:/workspace/PCPoker/imgs/assets";
	private static final String foldButton = assetsDir + "/fold_button.png";
	private static final String checkButton = assetsDir + "/check_button.png";
	private static final String callButton = assetsDir + "/call_button.png";
	private static final String raiseButton = assetsDir + "/raise_button.png";
	private static final String betButton = assetsDir + "/bet_button.png";

	private static final String haveFlop = assetsDir + "/flop.png";
	private static final String haveTurn = assetsDir + "/turn.png";
	private static final String haveRiver = assetsDir + "/river.png";

	private static Screen s = new Screen();

	public enum Action {
		FOLD, CHECK, RAISE;
	};

	public enum Moment {
		PRE_FLOP, FLOP_TURNED, TURN_TURNED, RIVER_TURNED;
	};

	private void letTheShowBegin() {
		try {
			Calculation calculator = new MonteCarloSimulation();
			String boardCards = "";
			String myCards = "";
			while (true) {
				buttonsRegion.wait(foldButton, 40);

				Moment moment = getMoment();
				System.out.println(moment);

				boardCards = fillBoardCards(moment, boardCards);
				
				if(moment == Moment.PRE_FLOP)
					myCards = getMyCards();
				
				String cardsFromOthersPlayers = getCardsFromOthersPlayers();

				System.out.println("Mesa: " + myCards + "," + cardsFromOthersPlayers + " | " + boardCards);

				Result result = calculator.calculate(myCards + "," + cardsFromOthersPlayers, boardCards, "");

				switch (whatToDoNow(result)) {
				case FOLD:
					buttonsRegion.click(foldButton);
					System.out.println("clicou fold");
					break;
				case CHECK:
					try {
						buttonsRegion.click(checkButton);
						System.out.println("clicou check");
					} catch (Exception e) {
						buttonsRegion.click(callButton);
						System.out.println("clicou call");
					}
					break;
				case RAISE:
					try {
						buttonsRegion.click(raiseButton);
						System.out.println("clicou raise");
					} catch (Exception e) {
						buttonsRegion.click(betButton);
						System.out.println("clicou bet");
					}
					break;
				default:
					break;
				}

				Toolkit.getDefaultToolkit().beep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String fillBoardCards(Moment moment, String boardCards) {
		switch (moment) {
		case PRE_FLOP:
			boardCards = "";
			break;
		case FLOP_TURNED:
			boardCards = getFlop();
			break;
		case TURN_TURNED:
			boardCards += getTurn();
			break;
		case RIVER_TURNED:
			boardCards += getRiver();
			break;
		default:
			break;
		}
		return boardCards;
	}

	private Moment getMoment() {
		ScreenImage board = s.capture(boardCardsRegion);
		Finder finder = new Finder(board);
		finder.find(haveFlop, 0.99);
		if (finder.hasNext()) {
			return Moment.FLOP_TURNED;
			// System.out.println("Encontrou player 1!");
		}
		finder.find(haveTurn, 0.99);
		if (finder.hasNext()) {
			return Moment.TURN_TURNED;
			// System.out.println("Encontrou player 1!");
		}
		finder.find(haveRiver, 0.99);
		if (finder.hasNext()) {
			return Moment.RIVER_TURNED;
			// System.out.println("Encontrou player 1!");
		}
		
		return Moment.PRE_FLOP;
	}

	public static void main(String[] args) {
		PCPoker poker = new PCPoker();
		poker.letTheShowBegin();
	}

	private Action whatToDoNow(Result result) {
		Map<Integer, HandInfo> map = result.getMap();
		Double media = 1D / map.size();
		Double myProbabilityToWin = result.getHandInfo(0).getWin();
		System.out.println("win: " + result.getHandInfo(0).getWin() + " | media: " + media);
		if (myProbabilityToWin > media) {
			return Action.CHECK;
		}
		return Action.FOLD;
	}

	private String getMyCards() {
		String retorno = "";

		ScreenImage card1 = s.capture(xMyCard1, yMyCards, cardWidth, cardHeight);
		ScreenImage card2 = s.capture(xMyCard2, yMyCards, cardWidth, cardHeight);
		Finder finderCard1 = new Finder(card1);
		Finder finderCard2 = new Finder(card2);
		File dir = new File(cardsDir);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				finderCard1.find(child.getAbsolutePath(), 0.99);
				if (finderCard1.hasNext())
					retorno += FilenameUtils.removeExtension(child.getName());
				finderCard2.find(child.getAbsolutePath(), 0.99);
				if (finderCard2.hasNext())
					retorno += FilenameUtils.removeExtension(child.getName());
			}
		}

		// System.out.println("My cards: " + retorno);

		return retorno.length() == 4 ? retorno : null;
	}

	private String getFlop() {
		String retorno = "";
		
		ScreenImage card1 = s.capture(xFlopCard1, yDeskCards, cardWidth, cardHeight);
		ScreenImage card2 = s.capture(xFlopCard2, yDeskCards, cardWidth, cardHeight);
		ScreenImage card3 = s.capture(xFlopCard3, yDeskCards, cardWidth, cardHeight);
		Finder finderCard1 = new Finder(card1);
		Finder finderCard2 = new Finder(card2);
		Finder finderCard3 = new Finder(card3);
		File dir = new File(cardsDir);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				finderCard1.find(child.getAbsolutePath(), 0.99);
				if (finderCard1.hasNext())
					retorno += FilenameUtils.removeExtension(child.getName());
				finderCard2.find(child.getAbsolutePath(), 0.99);
				if (finderCard2.hasNext())
					retorno += FilenameUtils.removeExtension(child.getName());
				finderCard3.find(child.getAbsolutePath(), 0.99);
				if (finderCard3.hasNext())
					retorno += FilenameUtils.removeExtension(child.getName());
			}
		}

		System.out.println("resultado flop: " + retorno);
		return retorno.length() == 6 ? retorno : "";
	}

	private String getTurn() {
		String retorno = "";
		
		ScreenImage card1 = s.capture(xTurnCard, yDeskCards, cardWidth, cardHeight);
		Finder finderCard1 = new Finder(card1);
		File dir = new File(cardsDir);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				finderCard1.find(child.getAbsolutePath(), 0.99);
				if (finderCard1.hasNext())
					retorno += FilenameUtils.removeExtension(child.getName());
			}
		}

		 System.out.println("resultado turn: " + retorno);
		return retorno.length() == 2 ? retorno : "";
	}

	private String getRiver() {
		String retorno = "";
		
		ScreenImage card1 = s.capture(xRiverCard, yDeskCards, cardWidth, cardHeight);
		Finder finderCard1 = new Finder(card1);
		File dir = new File(cardsDir);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				finderCard1.find(child.getAbsolutePath(), 0.99);
				if (finderCard1.hasNext())
					retorno += FilenameUtils.removeExtension(child.getName());
			}
		}

		System.out.println("resultado river: " + retorno);
		return retorno.length() == 2 ? retorno : "";
	}

	private String getCardsFromOthersPlayers() {
		String retorno = "";

		ScreenImage player1Cards = s.capture(player1CardsRegion);
		ScreenImage player2Cards = s.capture(player2CardsRegion);
		ScreenImage player3Cards = s.capture(player3CardsRegion);
		ScreenImage player4Cards = s.capture(player4CardsRegion);
		ScreenImage player5Cards = s.capture(player5CardsRegion);

		Finder finderPlayer1 = new Finder(player1Cards);
		Finder finderPlayer2 = new Finder(player2Cards);
		Finder finderPlayer3 = new Finder(player3Cards);
		Finder finderPlayer4 = new Finder(player4Cards);
		Finder finderPlayer5 = new Finder(player5Cards);

		String otherPlayerCards = assetsDir + "/other_player_cards.png";
		finderPlayer1.find(otherPlayerCards, 0.99);
		if (finderPlayer1.hasNext()) {
			retorno += "XxXx,";
			// System.out.println("Encontrou player 1!");
		}
		finderPlayer2.find(otherPlayerCards, 0.99);
		if (finderPlayer2.hasNext()) {
			retorno += "XxXx,";
			// System.out.println("Encontrou player 2!");
		}
		finderPlayer3.find(otherPlayerCards, 0.99);
		if (finderPlayer3.hasNext()) {
			retorno += "XxXx,";
			// System.out.println("Encontrou player 3!");
		}
		finderPlayer4.find(otherPlayerCards, 0.99);
		if (finderPlayer4.hasNext()) {
			retorno += "XxXx,";
			// System.out.println("Encontrou player 4!");
		}
		finderPlayer5.find(otherPlayerCards, 0.99);
		if (finderPlayer5.hasNext()) {
			retorno += "XxXx,";
			// System.out.println("Encontrou player 5!");
		}

		//System.out.println("Number of players: " + numberOfPlayers + " | " + retorno);

		return retorno.length() > 1 ? retorno.substring(0, retorno.length() - 1) : null;
	}

}
