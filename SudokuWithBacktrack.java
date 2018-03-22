public class SudokuWithBacktrack {
	
	public static int[][] grid;
	public static final int ROWS = 9;
	public static final int COLS = 9;
	public static void main(String[] args) {
		grid = new int[ROWS][COLS];
		grid[1][5] = 3;
		grid[1][7] = 8;
		grid[1][8] = 5;
		grid[2][2] = 1;
		grid[2][4] = 2;
		grid[3][3] = 5;
		grid[3][5] = 7;
		grid[4][2] = 4;
		grid[4][6] = 1;
		grid[5][1] = 9;
		grid[6][0] = 5;
		grid[6][7] = 7;
		grid[6][8] = 3;
		grid[7][2] = 2;
		grid[7][4] = 1;
		grid[8][4] = 4;
		grid[8][8] = 9;
		printGrid();
		System.out.println("-------------------");
		solveMySudoku();
		printGrid();
		
	}
	
	public static void printGrid() {
		for(int  i= 0; i < 9 ; i++) {
			for(int j = 0 ; j < 9 ; j++){
				System.out.print(grid[i][j] + " " );
			}
			System.out.println();
		}
	}
	
	public static void solveMySudoku() {
		backtrack(0,0);
	}//solveMySudoku
	
	
	public static int[] nextCell() {
		int[] cell = {9,9};
		for(int row = 0 ; row < ROWS ; row++) {
			for(int col = 0 ; col < COLS ; col++) {
				if(grid[row][col] == 0) {
					cell[0] = row;
					cell[1] = col;
					return cell;
				}
			}
		}
		return cell;
	}
	

	public static boolean backtrack(int r, int c) {
		if( r == 9 && c == 9) {
			return true;
		}
		for(int val =1 ; val <= 9 ; val++) {
			if(valid(r, c, val)) {
				grid[r][c] = val;
				int[] cell = nextCell();
				if(backtrack(cell[0],  cell[1])) {
					return true;
				}else {
					grid[r][c] = 0;
				}
			}
		}
		return false;
	}
	
	public static boolean valid(int row, int col, int value) {
		if(!validRow(row, col, value))
			return false;
		if(!validCol(row, col, value))
			return false;
		if(!validBox(row, col, value))
			return false;
		return true;
	}
	
	public static boolean validCol(int row,int col, int value) {
		for(int t=0 ; t < COLS ; t++) {
			if(t != row)
				if(grid[t][col] == value)
					return false;
		}
		return true;
	}
	
	public static boolean validRow(int row,int col, int value) {
		for(int t=0 ; t < ROWS ; t++) {
			if(t != col)
				if(grid[row][t] == value)
					return false;
		}
		return true;
	}
	
	public static boolean validBox(int row, int col, int value) {
		int startRow = 0, endrow = 0;
		int startCol=0, endCol = 0;
		if(row >= 6 && row <= 8) {
			startRow = 6;
			endrow = 8;
		}else {
			if(row >=3 && row <= 5 ) {
				startRow = 3;
				endrow = 5;
			}else {
				endrow = 2;
			}
		}
		if(col >= 6 && col <= 8) {
			startCol = 6;
			endCol = 8;
		}else {
			if(col >=3 && col <= 5 ) {
				startCol = 3;
				endCol = 5;
			}else {
				endCol = 2;
			}
		}	
        for (int x = startRow; x <= endrow; x++) {
            for (int t = startCol; t <= endCol; t++) {
            	if(x != row && t != col)
            		if (grid[x][t] == value) 
            			return false;
            }
        }
		return true;
	}
	

}
