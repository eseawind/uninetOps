package org.unism.op.domain.reference;

/**
 * 是或者否
 * @author MengQL
 *
 */
public enum IsNot {

	NOT {

		@Override
		public String getTitle() {
			return "否";
		}
		
	},
	
	IS {

		@Override
		public String getTitle() {
			return "是";
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
