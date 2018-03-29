package com.code.common.statemachine;


public interface SingleTransition<OPERAND, EVENT> {
	
	public void transition(OPERAND operand, EVENT event);

}
