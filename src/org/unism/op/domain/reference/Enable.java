package org.unism.op.domain.reference;

/**
 * 是否开启
 * @author mql
 *
 */
public enum Enable {
	
	Stop {

		@Override
		public String getTitle() {
			return "停止";
		}
		
	},
	
	Open {

		@Override
		public String getTitle() {
			return "开启";
		}
		
	};
	
	
	@Override
	public String toString() {
		return this.getTitle();
	}

	public abstract String getTitle();   
	
    public int getValue(){   
        return this.ordinal();   
    }
}
