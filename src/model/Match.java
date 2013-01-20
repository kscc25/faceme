/**
 * Copyright 2013 heroandtn3 (@sangnd.info)
 */
/*
 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package model;

import java.util.Observable;

import model.chess.Advisor;
import model.chess.Bishop;
import model.chess.Cannon;
import model.chess.Chess;
import model.chess.King;
import model.chess.Knight;
import model.chess.Pawn;
import model.chess.Rook;

/**
 * @author heroandtn3
 * @date Jan 7, 2013
 */
public class Match extends Observable {

	private Level level;
	private GameState state;
	private Board board;
	private Chess[] chess;
	private Player playerBlack, playerRed;
	
	private int[][] table = {
			{6,  4,  3,  2,  7,  2,  3,  4,  6},
			{0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  5,  0,  0,  0,  0,  0,  5,  0},
			{1,  0,  1,  0,  1,  0,  1,  0,  1},
			{0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0},
			{-1, 0, -1,  0, -1,  0, -1,  0, -1},
			{0, -5,  0,  0,  0,  0,  0, -5,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0},
			{-6,-4, -3, -2, -7, -2, -3, -4, -6} 
		};

	/**
	 * 
	 */
	public Match() {
		board = new Board(table);
		initChess();
	}
	
	private void initChess() {
		chess = new Chess[8];
		chess[0] = null;
		chess[1] = new Pawn(board);
		chess[2] = new Advisor(board);
		chess[3] = new Bishop(board);
		chess[4] = new Knight(board);
		chess[5] = new Cannon(board);
		chess[6] = new Rook(board);
		chess[7] = new King(board);
		
		
	}
	
	/**
	 * Ham thuc hien di chuyen quan co tu vi tri cu sang vi tri moi
	 * @param oldPos
	 * @param newPos
	 */
	public void move(ChessPosition oldPos, ChessPosition newPos) {
		int[][] mt = board.getTable();
		
		int tmp = mt[oldPos.getRow()][oldPos.getCol()];
		mt[oldPos.getRow()][oldPos.getCol()] = 0; // vi tri cu chuyen ve 0
		mt[newPos.getRow()][newPos.getCol()] = tmp;
		
		// danh dau la da thay doi va gui yeu cau update den cac observers
		setChanged();
		notifyObservers();
	}

	/* get, set -------------------------------------------------------------*/
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Chess[] getChess() {
		return chess;
	}

	public void setChess(Chess[] chess) {
		this.chess = chess;
	}

	public Player getPlayerBlack() {
		return playerBlack;
	}

	public void setPlayerBlack(Player playerBlack) {
		this.playerBlack = playerBlack;
	}

	public Player getPlayerRed() {
		return playerRed;
	}

	public void setPlayerRed(Player playerRed) {
		this.playerRed = playerRed;
	}

}
