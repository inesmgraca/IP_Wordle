class GameUtil
{
	static void drawIcon(ColorImage img, int x, int y, int state, char c)
	{		
		for (int i = 0; i < Constantes.ICON_SIZE; i++)
			for (int j = 0; j < Constantes.ICON_SIZE; j++)
				if (state == Constantes.CORRECT_POSITION)
					img.setColor(x + i, y + j, Constantes.CORRECT_BG);
				else if (state == Constantes.EXISTS)
					img.setColor(x + i, y + j, Constantes.EXISTS_BG);
				else if (state == Constantes.NOT_EXISTS)
					img.setColor(x + i, y + j, Constantes.NOT_IN_WORD_BG);
				else
					img.setColor(x + i, y + j, Constantes.EMPTY_BG);
		
		img.drawCenteredText(x + Constantes.ICON_SIZE / 2, y + Constantes.ICON_SIZE / 2, String.valueOf(c), 30, Color.WHITE);
	}
	
	static void drawBoard(ColorImage img, char[][] board, String puzzle)
	{
		boolean firstNull = true;
		int y = Constantes.ICON_SIZE;
		
		for (int a = 0; a < board.length; a++)
		{
			int x = img.getWidth() / 2 - (Constantes.ICON_SIZE + Constantes.ICON_SPACING) * (puzzle.length() / 2)
					- (puzzle.length() % 2 == 0 ? Constantes.ICON_SPACING : Constantes.ICON_SIZE) / 2;
			
			if (board[a] != null)
			{
				for (int b = 0; b < puzzle.length(); b++)
				{
					int state = board[a][b] == puzzle.toCharArray()[b] ? Constantes.CORRECT_POSITION :
						(puzzle.indexOf(board[a][b]) != -1 ? Constantes.EXISTS : Constantes.NOT_EXISTS);
					
					drawIcon(img, x, y, state, board[a][b]);
					x += Constantes.ICON_SIZE + Constantes.ICON_SPACING;
				}
				
				if (new String(board[a]).equals(puzzle))
					firstNull = false;
			}
			else
			{
				for (int b = 0; b < puzzle.length(); b++)
				{
					if (firstNull)
					{
						for (int i = 0; i < Constantes.ICON_SIZE; i++)
							for (int j = 0; j < Constantes.ICON_SIZE; j++)
								img.setColor(x + i, y + j, Constantes.DEFAULT_BG);
						
						for (int i = 0; i < Constantes.ICON_SIZE / 10; i++)
							for (int j = 0; j < Constantes.ICON_SIZE; j++)
							{
								img.setColor(x + i, y + j, Constantes.NOT_IN_WORD_BG);
								img.setColor(x + j, y + i, Constantes.NOT_IN_WORD_BG);
								img.setColor(x - i + Constantes.ICON_SIZE, y - j + Constantes.ICON_SIZE, Constantes.NOT_IN_WORD_BG);
								img.setColor(x - j + Constantes.ICON_SIZE, y - i + Constantes.ICON_SIZE, Constantes.NOT_IN_WORD_BG);
							}
					}
					else
						for (int i = 0; i < Constantes.ICON_SIZE; i++)
							for (int j = 0; j < Constantes.ICON_SIZE; j++)
								img.setColor(x + i, y + j, Constantes.NOT_IN_WORD_BG);
					
					x += Constantes.ICON_SIZE + Constantes.ICON_SPACING;
				}
			}
			
			if (board[a] == null && firstNull)
				firstNull = false;
			
			y += Constantes.ICON_SIZE + Constantes.ICON_SPACING;
		}
	}
	
	static void drawKeyboard(ColorImage img, char[][] keyboard, int[] keyStates)
	{
		int y = img.getHeight() - Constantes.ICON_SIZE * 4 - Constantes.ICON_SPACING * 2;
		
		for (char[] line : keyboard)
		{
			int x = img.getWidth() / 2 - (Constantes.ICON_SIZE + Constantes.ICON_SPACING) * (line.length / 2)
					- (line.length % 2 == 0 ? Constantes.ICON_SPACING : Constantes.ICON_SIZE) / 2;
			
			for (char c : line)
			{
				drawIcon(img, x, y, keyStates[c - 'A'], c);
				x += Constantes.ICON_SIZE + Constantes.ICON_SPACING;
			}
			
			y += Constantes.ICON_SIZE + Constantes.ICON_SPACING;
		}	
	}

	static String replaceSpecialChars(String str)
	{
		char[] chars = str.toCharArray();
		
		for (int i = 0; i < chars.length; i++)
		{
			if (chars[i] >= 'À' && chars[i] <= 'Æ')
					chars[i] = 'A';
			if (chars[i] >= 'È' && chars[i] <= 'Ë')
					chars[i] = 'E';
			if (chars[i] >= 'Ì' && chars[i] <= 'Ï')
					chars[i] = 'I';
			if (chars[i] >= 'Ò' && chars[i] <= 'Ö')
					chars[i] = 'O';
			if (chars[i] >= 'Ù' && chars[i] <= 'Ü')
					chars[i] = 'U';
			if (chars[i] >= 'Ç')
				chars[i] = 'C';
		}
		
		return new String(chars);
	}
}