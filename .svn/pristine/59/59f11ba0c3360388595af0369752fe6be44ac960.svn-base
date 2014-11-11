package org.unism.op.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.op.dao.Op_ChtypeOperateDao;
import org.unism.op.domain.Op_ChtypeOperate;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.service.BaseService;

@Service
public class Op_ChtypeOperateService extends BaseService<Op_ChtypeOperate, String> {
	
	@Autowired Op_ChtypeOperateDao op_ChtypeOperateDao;
	
	@Override
	protected IBaseDao<Op_ChtypeOperate, String> getEntityDao() {
		// TODO Auto-generated method stub
		return op_ChtypeOperateDao;
	}
	
	/**
	 * 数据汇总
	 * @return json
	 * 			[	
	 * 				{
	 * 					scene_gtype:'',
	 * 					head:
	 * 						[
	 * 							'xx','xxx','xxxx'
	 * 						],
	 * 					scenes:
	 * 						[ 	
	 * 							null,					
	 * 							{
	 * 								scene_name:'',
	 * 								time:'yyyy-MM-dd HH:mm:ss',
	 * 								data:
	 * 									[
	 * 										'21','23','','23'
	 * 									]
	 * 							},
	 * 							...
	 * 						]
	 * 				},
	 *				...
	 * 			]
	 * @author Wang_Yuliang
	 */
	public String data_collect(List<String> scene_id_arr){
		return this.op_ChtypeOperateDao.data_collect(scene_id_arr);
	}
	
	/**
	 * 数据汇总 0708
	 * @return json
	 * 			[	
	 * 				{
	 * 					scene_gtype:'',
	 * 					head:
	 * 						[
	 * 							'xx','xxx','xxxx'
	 * 						],
	 * 					scenes:
	 * 						[ 	
	 * 							null,					
	 * 							{
	 * 								scene_name:'',
	 * 								time:'yyyy-MM-dd HH:mm:ss',
	 * 								data:
	 * 									[
	 * 										'21','23','','23'
	 * 									]
	 * 							},
	 * 							...
	 * 						]
	 * 				},
	 *				...
	 * 			]
	 * @author Wang_Yuliang
	 */
	public String data_collect_0708(List<String> scene_id_arr){
		return this.op_ChtypeOperateDao.data_collect_0708(scene_id_arr);
	}
}
