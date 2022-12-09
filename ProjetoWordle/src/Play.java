class Play {
	
	static Game play()
	{
		Game game = new Game(new ColorImage("bg.jpg"));
		
		// breakpoint
		return game;
	}

	static void testGameUtil()
	{
		ColorImage img = new ColorImage(Constantes.DEFAULT_WIDTH, Constantes.DEFAULT_HEIGHT, Constantes.DEFAULT_BG);
		char[][] words = new char[Constantes.MAX_LINES][];

		words[0] = "SELVA".toCharArray();
		words[1] = "AREAL".toCharArray();
		words[2] = "ALEPO".toCharArray();
		
		int[] keyStates = { 2, 0, 2, 1, 3, 0, 1, 0, 3, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 3 };
		
		GameUtil.drawBoard(img, words, "APELE");
		GameUtil.drawKeyboard(img, Constantes.QWERTY, keyStates);
		
		return;
	}
	
	static ColorImage testStats()
	{
		Stats s = new Stats(Constantes.MAX_LINES);

		s.addVictory(2);
		s.addVictory(4);
		s.addVictory(5);
		s.addDefeat();
		s.addDefeat();
		s.addVictory(2);
		s.addVictory(2);
		s.addVictory(4);
		s.addVictory(0);
		s.addVictory(4);
		s.addVictory(4);
		s.addVictory(1);
		s.addVictory(3);
		s.addDefeat();
		s.addVictory(5);
		s.addVictory(5);
		s.addVictory(2);
		
		ColorImage img = s.view();
		
		return img;
	}
}