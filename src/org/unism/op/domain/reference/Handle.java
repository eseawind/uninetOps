package org.unism.op.domain.reference;

/**
 * 数据处理方式
 * @author qinglin.mengql
 *
 */
public enum Handle {

	ORA{
		@Override
		public String getTitle() {
			return "原始值";
		}
	},
	
	AVG{
		@Override
		public String getTitle() {
			return "平均值";
		}
	},
	
	MAX{
		@Override
		public String getTitle() {
			return "最大值";
		}
	},
	
	MIN{
		@Override
		public String getTitle() {
			return "最小值";
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
