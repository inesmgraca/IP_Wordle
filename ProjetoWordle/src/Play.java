class Play {
	
	static Game play()
	{
		Game game = new Game(5);

		// breakpoint
		return game;
	}

	static void testGameUtil()
	{
		ColorImage img = new ColorImage(Constantes.DEFAULT_WIDTH, Constantes.DEFAULT_HEIGHT, Constantes.DEFAULT_BG);
		
		char[][] words = {
				"DOTADO".toCharArray(),
				"ENIGMA".toCharArray(),
				"CONDIZ".toCharArray(),
				new char[6],
				new char[6],
				new char[6]
			};
		
		int[] data = { 2, 0, 2, 1, 3, 0, 1, 0, 3, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 3 };
		
		GameUtil.drawBoard(img, words, "eficaz");
		GameUtil.drawKeyboard(img, Constantes.QWERTY, data);
		
		return;
	}

	static ColorImage testStats()
	{
		Stats s = new Stats(Constantes.MAX_LINES);

		s.addVictory(2);
		s.addVictory(4);
		s.addVictory(6);
		s.addDefeat();
		s.addDefeat();
		s.addVictory(2);
		s.addVictory(2);
		s.addVictory(4);
		s.addVictory(4);
		s.addVictory(4);
		s.addVictory(4);
		s.addVictory(1);
		s.addVictory(5);
		s.addDefeat();
		s.addVictory(6);
		s.addVictory(6);
		s.addVictory(2);
		
		ColorImage img = s.view();
		
		return img;
	}
}