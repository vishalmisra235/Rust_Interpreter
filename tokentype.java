package com.first;

public enum tokentype  {

	ID,
	INT,
	CHAR,
	STRING,
	UNKNOWN,
	
	AND,
	OR,
	EQ,
	NEQ,
	GE,
	LE,
	NOT,
	PLUS,
	MINUS,
	TIMES,
	DIVIDE,
	MOD,
	
	IF,
	ELSE,
	WHILE,
	FOR,
	
	LPAREN,//(
	RPAREN,//)
	LBRAC,//[
	RBRAC,//]
	LBRACE,//{
	RBRACE,//}
	ASSIGN,
	COMMA,
	SEMI,
	COLON,
	RET,//->
	
	MAIN,
	FN,
	KEYWORD
}
