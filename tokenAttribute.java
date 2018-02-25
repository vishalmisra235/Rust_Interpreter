package com.first;

public class tokenAttribute {
	public int intVal; // int value of the token
	public float floatVal; // float value of the token
	public char charVal; // char value of the token
	public String idVal; // id of the token

	public tokenAttribute() {}

	// construct TokenAttribute with an int value
	public tokenAttribute(int intVal){
		this.intVal = intVal;
	}

	// construct TokenAttribute with a float value
	public tokenAttribute(float floatVal){
		this.floatVal = floatVal;
	}

	// construct TokenAttribute with a char value
	public tokenAttribute(char charVal){
		this.charVal = charVal;
	}

	
	// construct TokenAttribute with an id
	public tokenAttribute(String idVal){
		this.idVal = idVal;
	}

	public int getIntVal() {
		return intVal;
	}

	public void setIntVal(int intVal) {
		this.intVal = intVal;
	}

	public float getFloatVal() {
		return floatVal;
	}

	public void setFloatVal(float floatVal) {
		this.floatVal = floatVal;
	}

	public char getCharVal() {
		return charVal;
	}

	public void setCharVal(char charVal) {
		this.charVal = charVal;
	}

	public String getIdVal() {
		return idVal;
	}
	
	public void setIdVal(String idVal) {
		this.idVal = idVal;
	}
}
