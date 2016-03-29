package br.unibh.escola.util;

public class CharacterToBooleanUtil {
	
	public static Boolean getBoolean(Character character){
		return character == null ? null: character.equals('S')?
				Boolean.TRUE : Boolean.FALSE;
	}
	public static Character getCharacter(Boolean bool){
		return bool == null ? null: bool?'S' : 'N'; 
	}

}
