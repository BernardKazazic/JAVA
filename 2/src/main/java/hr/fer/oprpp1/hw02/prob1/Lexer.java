package hr.fer.oprpp1.hw02.prob1;

/**
 * This class represents Lexical analyser that takes in a string and outputs tokens.
 * It uses escape mechanism. Mechanism allows numbers to be seen as a letter.
 * It has two states. In basic state it generates tokens normally, and in extended it counts every character as one word.
 * State changes when analyser takes in hashtag (#) character.
 */
public class Lexer {
	private char[] data;
	private Token token;
	private int currentIndex;
	private LexerState state;


	/**
	 * Constructs lexical analyser that will analyse given text.
	 *
	 * @param text string of text that is analysed
	 */
	public Lexer(String text) {
		if(text == null) 
			throw new NullPointerException();
		this.data = text.toCharArray();
		this.token = null;
		this.currentIndex = 0;
		this.state = LexerState.BASIC;
	}

	/**
	 * Sets state of this lexical analyser.
	 *
	 * @param state state that lexical analyser is set to
	 */
	public void setState(LexerState state) {
		if(state == null) 
			throw new NullPointerException();
		this.state = state;
	}

	/**
	 * Analyses text and returns next generated token.
	 *
	 * @return token generated from text
	 */
	public Token nextToken() {
		if(token != null && token.getType() == TokenType.EOF) 
			throw new LexerException();
		
		skipBlanks();
		
		if(currentIndex >= data.length) {
			token = new Token(TokenType.EOF, null);
			return getToken();
		}
		
		if(this.state == LexerState.BASIC) {
			if(Character.isLetter(data[currentIndex]) || isValidEscape(data[currentIndex])) {
				StringBuilder sb = new StringBuilder();
				sb.append(data[currentIndex]);
				currentIndex++;
				
				while(currentIndex < data.length) {
					if(Character.isLetter(data[currentIndex]) || isValidEscape(data[currentIndex])) {
						sb.append(data[currentIndex]);
						currentIndex++;
						continue;
					}
					break;
				}
				token = new Token(TokenType.WORD, sb.toString());
				return getToken();
			}
			
			if(Character.isDigit(data[currentIndex])) {
				StringBuilder sb = new StringBuilder();
				sb.append(data[currentIndex]);
				currentIndex++;
				
				while(currentIndex < data.length) {
					if(Character.isDigit(data[currentIndex])) {
						sb.append(data[currentIndex]);
						currentIndex++;
						continue;
					}
					break;
				}
				try {
					token = new Token(TokenType.NUMBER, Long.parseLong(sb.toString()));
					return getToken();
				}
				catch (Exception e) {
					throw new LexerException();
				}
			}
			
			if(isSymbol(data[currentIndex])) {
				Character c = data[currentIndex];
				currentIndex++;
				
				token = new Token(TokenType.SYMBOL, c);
				return getToken();
			}
			
			if(isHashtag(data[currentIndex])) {
				Character c = data[currentIndex];
				currentIndex++;
				changeState();
				
				token = new Token(TokenType.SYMBOL, c);
				return getToken();
			}
		}
		else if(state == LexerState.EXTENDED) {
			if(isHashtag(data[currentIndex])) {
				Character c = data[currentIndex];
				currentIndex++;
				changeState();
				
				token = new Token(TokenType.SYMBOL, c);
				return getToken();
			}
			
			StringBuilder sb = new StringBuilder();
			while(!isHashtag(data[currentIndex]) || currentIndex >= data.length) {
				if(Character.isWhitespace(data[currentIndex])) {
					currentIndex++;
					break;
				}
				sb.append(data[currentIndex]);
				currentIndex++;
			}
			token = new Token(TokenType.WORD, sb.toString());
			return getToken();
		}
		
		
		return null;
	}

	/**
	 * Return current token that analyser is holding.
	 *
	 * @return current token that analyser is holding
	 */
	public Token getToken() {
		return this.token;
	}

	/**
	 * This method skips blank characters in text.
	 */
	private void skipBlanks() {
		while(currentIndex < data.length) {
			char c = data[currentIndex];
			if(c==' ' || c=='\t' || c=='\r' || c=='\n') {
				currentIndex++;
				continue;
			}
			break;
		}
	}

	/**
	 * Check if current character is a symbol.
	 *
	 * @param c current character
	 * @return true if it is a symbol, false if not
	 */
	private boolean isSymbol(char c) {
		return (c >= '!' && c <= '"') || (c >= '$' && c <= '.') || (c >= ':' && c <= '@') || (c >= '[' && c <= '`') || (c >= '{' && c <= '~');
	}

	/**
	 * Cheks if current character is a valid escape sequence.
	 *
	 * @param c current character
	 * @return true if it is valid, false if not
	 */
	private boolean isValidEscape(char c) {
		if(c == '\\') {
			currentIndex++;
			if(currentIndex == data.length) 
				throw new LexerException();
			if(Character.isLetter(data[currentIndex])) 
				throw new LexerException();
			return true;
		}
		return false;
	}

	/**
	 * Checks if current characters is a hashtag.
	 *
	 * @param c current character
	 * @return true if it is valid, false if not
	 */
	private boolean isHashtag(char c) {
		return c == '#';
	}

	/**
	 * Changes this lexical analysers state.
	 */
	private void changeState() {
		if(state == LexerState.BASIC) {
			setState(LexerState.EXTENDED);
		}
		else {
			setState(LexerState.BASIC);
		}
	}
}
