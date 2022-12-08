class Game {

	private char[][] board;
	private char[][] keyboard;
	private Stats stats;
	private String puzzle;
	private Dictionary dictionary;
	private int[] keyStates;
	public ColorImage img;

	Game(ColorImage bg)
	{
		board = new char[Constantes.MAX_LINES][6];
		keyboard = Constantes.QWERTY;
		dictionary = new Dictionary("pt_br.txt");
		img = bg;
		
		createPuzzle();
		createKeyStates();
		GameUtil.drawBoard(img, board, puzzle);
		GameUtil.drawKeyboard(img, keyboard, keyStates);
	}
	
	Game(Dictionary dictionary)
	{
		board = new char[Constantes.MAX_LINES][6];
		keyboard = Constantes.QWERTY;
		this.dictionary = dictionary;
		img = new ColorImage(Constantes.DEFAULT_WIDTH, Constantes.DEFAULT_HEIGHT, Constantes.DEFAULT_BG);

		createPuzzle();
		createKeyStates();
		GameUtil.drawBoard(img, board, puzzle);
		GameUtil.drawKeyboard(img, keyboard, keyStates);
	}

	Game(ColorImage bg, int length)
	{
		board = new char[Constantes.MAX_LINES][length];
		keyboard = Constantes.QWERTY;
		dictionary = new Dictionary("pt_br.txt");
		img = bg;

		createPuzzle();
		createKeyStates();
		GameUtil.drawBoard(img, board, puzzle);
		GameUtil.drawKeyboard(img, keyboard, keyStates);
	}
	
	Game(Dictionary dictionary, int length)
	{
		board = new char[Constantes.MAX_LINES][length];
		keyboard = Constantes.QWERTY;
		this.dictionary = dictionary;
		img = new ColorImage(Constantes.DEFAULT_WIDTH, Constantes.DEFAULT_HEIGHT, Constantes.DEFAULT_BG);

		createPuzzle();
		createKeyStates();
		GameUtil.drawBoard(img, board, puzzle);
		GameUtil.drawKeyboard(img, keyboard, keyStates);
	}
	
	Game(int length)
	{
		board = new char[Constantes.MAX_LINES][length];
		keyboard = Constantes.QWERTY;
		this.dictionary = new Dictionary("pt_br.txt");
		img = new ColorImage(Constantes.DEFAULT_WIDTH, Constantes.DEFAULT_HEIGHT, Constantes.DEFAULT_BG);

		createPuzzle();
		createKeyStates();
		GameUtil.drawBoard(img, board, puzzle);
		GameUtil.drawKeyboard(img, keyboard, keyStates);
	}
	
	void createPuzzle()
	{
		puzzle = dictionary.generateSecretWord(board[0].length);
	}
	
	void createKeyStates()
	{
		keyStates = new int[26];
	}
	
	void updateKeyStates(String word, String puzzle)
	{
		for (char c : word.toCharArray())
		{
			switch (keyStates[c - 'A'])
			{
				case 0:
				case 1:
					keyStates[c - 'A'] = word.indexOf(c) == puzzle.indexOf(c) ? 3 : (puzzle.indexOf(c) != -1 ? 2 : 1);
					break;
				case 2:
					keyStates[c - 'A'] = word.indexOf(c) == puzzle.indexOf(c) ? 3 : 2;
					break;
				case 3:
					break;
				default:
					throw new IllegalStateException();
			}
		}
	}
	
	void insertWord(String word)
	{
		if (word == null)
			throw new NullPointerException("A palavra é nula");
		
		if (word.length() > puzzle.length())
			throw new IllegalArgumentException("A palavra é demasiado comprida");
		
		for (int i = 0; i < word.length(); i++)
			if (word.toCharArray()[i] >= ' ' || (word.toCharArray()[i] >= '0' && word.toCharArray()[i] >= '9'))
				throw new IllegalArgumentException("A palavra é inválida");
		
			int line = 0;

		for (int i = 0; i < board.length; i++)
		{
			if (board[i] == null)
			{
				word = GameUtil.replaceSpecialChars(word.toUpperCase());
				line = i;
				break;
			}
		}
		
		updateKeyStates(word, puzzle);
		GameUtil.drawBoard(img, board, puzzle);
		GameUtil.drawKeyboard(img, keyboard, keyStates);

		boolean win = true;
		boolean defeat = false;
		
		for (int i = 0; i < puzzle.length(); i++)
			if (word.toCharArray()[i] != puzzle.toCharArray()[i])
				win = false;

		if (board[board.length - 1] != null)
			defeat = true;

		if (win || defeat)
		{
			if (win)
			{
				stats.addVictory(line);
				System.out.print("Parabéns! ");
			}
			if (defeat)
			{
				stats.addDefeat();
				System.out.print("Perdeu! ");
			}
			
			System.out.println("A palavra original era: " + dictionary.getOriginalWord(puzzle));
		}
	}
}