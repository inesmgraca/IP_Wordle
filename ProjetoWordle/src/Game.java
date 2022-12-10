class Game
{
	private Stats stats = new Stats(Constantes.MAX_LINES);
	private char[][] keyboard = Constantes.QWERTY;
	private Dictionary dictionary = new Dictionary("pt_br.txt");
	private String puzzle;
	private char[][] board;
	private int[] keyStates;
	private int line;
	public ColorImage img = new ColorImage(Constantes.DEFAULT_WIDTH, Constantes.DEFAULT_HEIGHT, Constantes.DEFAULT_BG);

	Game(ColorImage bg)
	{
		img = bg;
		startGame(6);
	}
	
	Game(Dictionary dictionary)
	{
		this.dictionary = dictionary;
		startGame(6);
	}
	
	Game(int length)
	{
		startGame(length);
	}
	
	private void startGame(int length)
	{
		line = 0;
		board = new char[Constantes.MAX_LINES][];
		createPuzzle(length);
		createKeyStates();
		GameUtil.drawBoard(img, board, puzzle);
		GameUtil.drawKeyboard(img, keyboard, keyStates);
	}
	
	private void createPuzzle(int length)
	{
		puzzle = dictionary.generateSecretWord(length);
	}
	
	private void createKeyStates()
	{
		keyStates = new int[26];
	}
	
	public void insertWord(String word)
	{		
		if (line == board.length || (line != 0 && new String(board[line - 1]).equals(puzzle)))
			throw new IllegalStateException("O jogo já terminou");
		
		if (word.isEmpty() || word.equals(null) || word.equals(""))
			throw new NullPointerException("A palavra é nula");
		
		if (word.length() != puzzle.length())
			throw new IllegalArgumentException("A palavra não tem o comprimento correto");

		word = GameUtil.replaceSpecialChars(word.toUpperCase());

		for (int i = 0; i < board.length; i++)
			if (board[i] != null && new String(board[i]).equals(word))
				throw new IllegalArgumentException("A palavra já foi inserida");
		
		for (int i = 0; i < word.length(); i++)
			if (word.toCharArray()[i] == ' ' || (word.toCharArray()[i] >= '0' && word.toCharArray()[i] <= '9'))
				throw new IllegalArgumentException("A palavra é inválida");

		if (!dictionary.exists(word))
			throw new IllegalArgumentException("A palavra não existe");
		
		updateGame(word);

		if (word.equals(puzzle) || line == board.length)
			endGame();
	}

	private void updateGame(String word)
	{
		board[line] = word.toCharArray();
		line++;
				
		updateKeyStates(word);
		GameUtil.drawBoard(img, board, puzzle);
		GameUtil.drawKeyboard(img, keyboard, keyStates);
	}
	
	private void updateKeyStates(String word)
	{
		for (char c : word.toCharArray())
		{
			if (keyStates[c - 'A'] == Constantes.UNTESTED)
				keyStates[c - 'A'] = word.indexOf(c) == puzzle.indexOf(c) ? Constantes.CORRECT_POSITION : (puzzle.indexOf(c) != -1 ? Constantes.EXISTS : Constantes.NOT_EXISTS);
			
			if (keyStates[c - 'A'] == Constantes.EXISTS)
				keyStates[c - 'A'] = word.indexOf(c) == puzzle.indexOf(c) ? Constantes.CORRECT_POSITION : Constantes.EXISTS;
		}
	}
	
	private void endGame()
	{
		if (new String(board[line - 1]).equals(puzzle))
		{
			stats.addVictory(line - 1);
			System.out.print("Parabéns! ");
		}
		else if (line == board.length)
		{
			stats.addDefeat();
			System.out.print("Perdeu! ");
		}
		
		System.out.println("A palavra original era: " + dictionary.getOriginalWord(puzzle));
	}
	
	public ColorImage viewStats()
	{
		return stats.view();
	}
	
	public void startNewGame()
	{
		if (!(line == board.length || (line != 0 && new String(board[line - 1]).equals(puzzle))))
			throw new IllegalStateException("O jogo ainda não terminou");
		
		startGame(puzzle.length());
	}
}