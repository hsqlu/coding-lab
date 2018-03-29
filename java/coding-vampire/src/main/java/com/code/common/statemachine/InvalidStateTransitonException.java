package com.code.common.statemachine;

import java.util.Set;

public class InvalidStateTransitonException extends RuntimeException {
	 private Enum<?> currentState;
	  private Enum<?> event;

	  public InvalidStateTransitonException(Enum<?> currentState, Enum<?> event) {
	    super("Invalid event: " + event + " at " + currentState);
	    this.currentState = currentState;
	    this.event = event;
	  }
	  
	  public InvalidStateTransitonException(Enum<?> currentState, Enum<?> event, Set<?> validPostStates, Enum<?> postState) {
		    super(" Invalid event: " + event + " at " + currentState + " " + postState +" not in " +validPostStates);
		    this.currentState = currentState;
		    this.event = event;
	  }

	  public Enum<?> getCurrentState() {
	    return currentState;
	  }
	  
	  public Enum<?> getEvent() {
	    return event;
	  }
}
