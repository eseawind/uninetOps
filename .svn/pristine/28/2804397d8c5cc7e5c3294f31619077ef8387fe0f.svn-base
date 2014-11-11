package org.unism.op.domain.reference;

/**
 * 条件关系
 * @author qinglin.mengql
 *
 */
public enum Cond {

	AND {

		@Override
		public String getTitle() {
			return "并且";
		}
		
	},
	
	OR {

		@Override
		public String getTitle() {
			return "或者";
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
