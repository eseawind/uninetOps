package org.unism.op.domain.reference;

/**
 * 控制条件
 * @author qinglin.mengql
 *
 */
public enum Condition {

	GREATER{
		@Override
		public String getTitle() {
			return "大于";
		}
	},
	
	GREATOREQUAL{
		@Override
		public String getTitle() {
			return "大于等于";
		}
	},
	
	EQUAL{
		@Override
		public String getTitle() {
			return "等于";
		}
	},
	
	LESS{
		@Override
		public String getTitle() {
			return "小于";
		}
	},
	
	LESSOREQUAL{
		@Override
		public String getTitle() {
			return "小于等于";
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
