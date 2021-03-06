/**
 * CSE 403 AA
 * Project Nonogram
 * Due Friday June 15, 2013
 * @author  HyeIn Kim
 * @version v1.0, University of Washington 
 * @since   Spring 2013 
 */

import java.awt.Color;
import java.util.*;

/**
 * 
 *
 */
public class NonoPuzzle {
	private static int ID_COUNTER = 0;
	private List<NonoNum>[] rowNonoNums;
	private List<NonoNum>[] colNonoNums;
	private Color[][] nonoPicArr;
	private Color backgroundColor;
	private PuzzleInfo puzzleInfo;
	
	//
	@SuppressWarnings("unchecked")
	private NonoPuzzle(Color[][] array, Color bgColor, PuzzleInfo info) {
		rowNonoNums = (List<NonoNum>[]) new List[info.nonoPicRowSize];
		colNonoNums = (List<NonoNum>[]) new List[info.nonoPicColSize];
		nonoPicArr = array;
		backgroundColor = bgColor;
		puzzleInfo = info;
		for(int i=0; i<rowNonoNums.length; i++) {
			rowNonoNums[i] = new LinkedList<NonoNum>();
		}
		for(int i=0; i<colNonoNums.length; i++) {
			colNonoNums[i] = new LinkedList<NonoNum>();
		}
	}
	
	public static NonoPuzzle makeNonoPuzzle(Color[][] array, Color bgColor, String name) {
		PuzzleInfo info = new PuzzleInfo(ID_COUNTER ++, name, array.length, array[0].length, 0, 0);
		NonoPuzzle puzzle = new NonoPuzzle(array, bgColor, info);
		
		int maxNonoNumRowSize = 0;
		for(int i=0; i<puzzle.getNonoPicRowSize(); i++) {
			int blockLength = 1;
			for(int j=0; j<puzzle.getNonoPicColSize()-1; j++) {
				if(!array[i][j].equals(bgColor)) {
					if(array[i][j].equals(array[i][j+1])) {
						blockLength ++;
					}else{
						puzzle.rowNonoNums[i].add(new NonoNum(blockLength, array[i][j]));
						blockLength = 1;
					}
				}
			}
			maxNonoNumRowSize = Math.max(maxNonoNumRowSize, puzzle.rowNonoNums[i].size());
		}
		
		int maxNonoNumColSize = 0;
		for(int j=0; j<puzzle.getNonoPicColSize(); j++) {
			int blockLength = 1;
			for(int i=0; i<puzzle.getNonoPicRowSize()-1; i++) {
				if(!array[i][j].equals(bgColor)) {
					if(array[i][j].equals(array[i][j+1])) {
						blockLength ++;
					}else{
						puzzle.colNonoNums[j].add(new NonoNum(blockLength, array[i][j]));
						blockLength = 1;
					}
				}
			}
			maxNonoNumColSize = Math.max(maxNonoNumColSize, puzzle.colNonoNums[j].size());
		}
		
		info.nonoNumRowSize = maxNonoNumRowSize;
		info.nonoNumColSize = maxNonoNumColSize;
		
		return puzzle;
	}
	
	public Iterator<NonoNum> getRowNonoNumItrator(int row) {
		return rowNonoNums[row].iterator();
	}
	
	public Iterator<NonoNum> getcolNonoNumItrator(int col) {
		return colNonoNums[col].iterator();
	}
	
	public int getPuzzleID() {
		return puzzleInfo.puzzleID;
	}
	
	public String getPuzzleName() {
		return puzzleInfo.puzzleName;
	}

	public int getNonoPicRowSize() {
		return puzzleInfo.nonoPicRowSize;
	}
	
	public int getNonoPicColSize() {
		return puzzleInfo.nonoPicColSize;
	}
	
	public int getNonoNumRowSize() {
		return puzzleInfo.nonoNumRowSize;
	}
	
	public int getNonoNumColSize() {
		return puzzleInfo.nonoNumColSize;
	}
	
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	
	public boolean isBackgroundColor(Color color) {
		return backgroundColor.equals(color);
	}
	
	public Color getColor(int row, int col) {
		return nonoPicArr[row][col];
	}
	
	public boolean isSameColor(int row, int col, Color color) {
		return nonoPicArr[row][col].equals(color);
	}
	
	
	public static class NonoNum {
		public final int number;
		public final Color color;
		
		public NonoNum(int number, Color color) {
			this.number = number;
			this.color = color;
		}
	}
	
	private static class PuzzleInfo {
		private int puzzleID;
		private String puzzleName;
		private int nonoPicRowSize;
		private int nonoPicColSize;
		private int nonoNumRowSize;
		private int nonoNumColSize;
		
		public PuzzleInfo(int id, String name, int picRow, int picCol, int numRow, int numCol) {
			this.puzzleID = id;
			this.puzzleName = name;
			this.nonoPicRowSize = picRow;
			this.nonoPicColSize = picCol;
			this.nonoNumRowSize = numRow;
			this.nonoNumColSize = numCol;
		}
	}

}
