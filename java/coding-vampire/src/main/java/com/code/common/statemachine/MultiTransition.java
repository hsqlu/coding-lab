package com.code.common.statemachine;

public interface MultiTransition<
	OPERAND, 
	EVENT, 
	STATE extends Enum<STATE>> {

	public STATE transition(OPERAND operand, EVENT event);

}
