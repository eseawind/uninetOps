package org.unism.op.domain.reference;

/**
 * 通知方式
 * @author mql
 *
 */
public enum NoticeType {
	
	
	/**
	 * 全部(0)
	 */
	All {
		@Override
		public String getTitle() {
			return "全部";
		}
	},
	
	
	/**
	 * 手机(1)
	 */
	Phone {
		@Override
		public String getTitle() {
			return "手机";
		}
	},
	
	
	/**
	 * 邮件(2)
	 */
	Email {
		@Override
		public String getTitle() {
			return "邮件";
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
