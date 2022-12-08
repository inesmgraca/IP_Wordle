class Stats {

	private int[] victories;
	private int numGames;
	private int numVictories;
	private int numStreak;
	private int biggestStreak;
	
	Stats(int maxLines)
	{
		victories = new int[maxLines + 1];
		numGames = 0;
		numVictories = 0;
		numStreak = 0;
		biggestStreak = 0;
	}
	
	void addVictory(int line)
	{
		victories[line - 1]++;
		
		numGames++;
		numVictories++;
		numStreak++;
		
		if (numStreak > biggestStreak)
			biggestStreak = numStreak;		
	}
	
	void addDefeat()
	{
		victories[victories.length - 1]++;

		numGames++;
		numStreak = 0;
	}
	
	ColorImage view()
	{
		ColorImage img = new ColorImage(Constantes.DEFAULT_WIDTH / 2, Constantes.DEFAULT_HEIGHT, Constantes.NOT_IN_WORD_BG);
		
		img.drawCenteredText(img.getWidth() / 2, Constantes.ICON_SIZE, "ESTATÍSTICAS", 25, Color.WHITE);
		
		int x = img.getWidth() / 2
				- Constantes.ICON_SPACING * 2
				- Constantes.ICON_SIZE * 2;
		
		int y = Constantes.ICON_SIZE * 2
				+ Constantes.ICON_SPACING / 2;
		
		img.drawCenteredText(x, y, String.valueOf(numGames), 30, Color.WHITE);
		x += Constantes.ICON_SIZE + Constantes.ICON_SIZE / 2;
		img.drawCenteredText(x, y, String.valueOf((int)(numVictories * 100 / numGames)), 30, Color.WHITE);
		x += Constantes.ICON_SIZE + Constantes.ICON_SIZE / 2;
		img.drawCenteredText(x, y, String.valueOf(numStreak), 30, Color.WHITE);
		x += Constantes.ICON_SIZE + Constantes.ICON_SIZE / 2;
		img.drawCenteredText(x, y, String.valueOf(biggestStreak), 30, Color.WHITE);

		x = img.getWidth() / 2
			- Constantes.ICON_SPACING * 2
			- Constantes.ICON_SIZE * 2;
		
		y += Constantes.ICON_SIZE / 2 + Constantes.ICON_SPACING * 2;

		img.drawCenteredText(x, y, "Jogados", 11, Color.WHITE);
		x += Constantes.ICON_SIZE + Constantes.ICON_SIZE / 2;
		img.drawCenteredText(x, y, "% Vitórias", 11, Color.WHITE);
		x += Constantes.ICON_SIZE + Constantes.ICON_SIZE / 2;		
		img.drawCenteredText(x, y, "Sequência", 11, Color.WHITE);
		y += Constantes.ICON_SPACING * 4;
		img.drawCenteredText(x, y, "de Vitórias", 11, Color.WHITE);
		x += Constantes.ICON_SIZE + Constantes.ICON_SIZE / 2;
		y -= Constantes.ICON_SPACING * 4;
		img.drawCenteredText(x, y, "Melhor", 11, Color.WHITE);
		y += Constantes.ICON_SPACING * 4;
		img.drawCenteredText(x, y, "Sequência", 11, Color.WHITE);
		
		y += Constantes.ICON_SIZE + Constantes.ICON_SPACING * 5;
		img.drawCenteredText(img.getWidth() / 2, y, "Distribuição de tentativas", 25, Color.WHITE);
		
		int max = 0;
		
		for (int line : victories)
			if (line > max)
				max = line;
		
		int size = Constantes.ICON_SIZE * 5 / (max + 1);
		
		for (int a = 1; a <= victories.length; a++)
		{
			x = img.getWidth() / 2
				- Constantes.ICON_SPACING * 5
				- Constantes.ICON_SIZE * 2;
			
			y += Constantes.ICON_SPACING * 10;
			img.drawCenteredText(x + Constantes.ICON_SPACING, y + Constantes.ICON_SPACING * 2, (a != victories.length ? String.valueOf(a) : "X"), 20, Color.WHITE);
			
			x += 30;
			
			for (int i = 0; i < size * (victories[a - 1] + 1); i++)
				for (int j = 0; j < 30; j++)
					img.setColor(x + i - Constantes.ICON_SPACING * 2, y + j - Constantes.ICON_SPACING * 2, (victories[a - 1] == 0 ? Constantes.EMPTY_BG : Constantes.ERROR_BG));

			img.drawCenteredText(x + Constantes.ICON_SPACING * 2, y + Constantes.ICON_SPACING * 2, String.valueOf(victories[a - 1]), 20, Color.WHITE);
		}
		
		return img;
	}
}