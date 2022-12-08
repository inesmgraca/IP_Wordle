class GameUtil {

	static void drawIcon(ColorImage img, int x, int y, int state, char c)
	{
		for (int i = 0; i < Constantes.ICON_SIZE; i++)
			for (int j = 0; j < Constantes.ICON_SIZE; j++)
				if (state == 3)
					img.setColor(x + i, y + j, Constantes.CORRECT_BG);
				else if (state == 2)
					img.setColor(x + i, y + j, Constantes.EXISTS_BG);
				else if (state == 1)
					img.setColor(x + i, y + j, Constantes.NOT_IN_WORD_BG);
				else
					img.setColor(x + i, y + j, Constantes.EMPTY_BG);
		
		img.drawCenteredText(x + Constantes.ICON_SIZE / 2, y + Constantes.ICON_SIZE / 2, String.valueOf(c).toUpperCase(), 30, Color.WHITE);
	}
	
	static void drawBoard(ColorImage img, char[][] board, String puzzle)
	{
		int y = Constantes.ICON_SIZE;
		
		boolean firstNull = true;
		
		for (char[] word : board)
		{
			int x = img.getWidth() / 2
					- Constantes.ICON_SIZE * (word.length / 2)
					- Constantes.ICON_SPACING * (word.length / 2)
					- (word.length % 2 == 0 ? Constantes.ICON_SPACING / 2 : Constantes.ICON_SIZE / 2);
			
			for (char c : word)
			{
				if (c != 0)
				{
					int colorState = String.valueOf(word).indexOf(c) == puzzle.indexOf(c) ? 3 : (puzzle.indexOf(c) != -1 ? 2 : 1);
					drawIcon(img, x, y, colorState, c);
				}
				else
				{
					if (firstNull)
					{
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
				}
				
				x += Constantes.ICON_SIZE + Constantes.ICON_SPACING;
			}
			
			if (word[0] == 0 && firstNull)
				firstNull = false;
			
			y += Constantes.ICON_SIZE + Constantes.ICON_SPACING;
		}
	}
	
	static void drawKeyboard(ColorImage img, char[][] keyboard, int[] keyStates)
	{
		int y = img.getHeight() - Constantes.ICON_SIZE * 4 - Constantes.ICON_SPACING * 2;
		
		for (char[] line : keyboard)
		{
			int x = img.getWidth() / 2
					- Constantes.ICON_SIZE * (line.length / 2)
					- Constantes.ICON_SPACING * (line.length / 2)
					- (line.length % 2 == 0 ? Constantes.ICON_SPACING / 2 : Constantes.ICON_SIZE / 2);
			
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
		
		return chars.toString();
	}
}