package com.code.schedule.event;

public enum EnumEngineStatus {
	
	INIT_ENGINE("1","启动引擎"),
    ACTIVE_SCHEME("2","激活方案"),
    EXEC_SCHEME("3","执行方案"),
    SHUTDOWN_SCHEME("4","终止方案"),
    STOP_SCHEME("5","停止方案"),
    STOP("6","停止引擎"),
    EXEC_ENGINE("7","引擎运行中");

	private final String value;
	private final String memo;

    /**
     *
     * @param value 状态值
     * @param memo  状态描述
     */
	EnumEngineStatus(String value, String memo) {
		this.value = value;
		this.memo = memo;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public String getMemo(){
		return this.memo;
	}
	
	public static EnumEngineStatus convert(String value){
		for (int i = 0; i < EnumEngineStatus.values().length; i++) {
			if(EnumEngineStatus.values()[i].getValue().equals(value)){
				return EnumEngineStatus.values()[i];
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return this.memo;
	}
}
