package com.code.common.statemachine;


public interface StateMachine<
	STATE extends Enum<STATE>,
	EVENTTYPE extends Enum<EVENTTYPE>, 
	EVENT> {
	
	
	/**
	 * 获取当前状态
	 * @return
	 */
	public STATE getCurrentState();
	
	public Object operator();
	
	public STATE doTransition(EVENTTYPE eventType, EVENT event)
			throws InvalidStateTransitonException;
}
