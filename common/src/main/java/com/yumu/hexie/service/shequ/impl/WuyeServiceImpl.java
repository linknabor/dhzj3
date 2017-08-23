package com.yumu.hexie.service.shequ.impl;

import javax.inject.Inject;
import javax.xml.bind.ValidationException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yumu.hexie.integration.wuye.WuyeUtil;
import com.yumu.hexie.integration.wuye.resp.BaseResult;
import com.yumu.hexie.integration.wuye.resp.BillListVO;
import com.yumu.hexie.integration.wuye.resp.HouseListVO;
import com.yumu.hexie.integration.wuye.resp.PayWaterListVO;
import com.yumu.hexie.integration.wuye.vo.HexieHouse;
import com.yumu.hexie.integration.wuye.vo.HexieUser;
import com.yumu.hexie.integration.wuye.vo.PayResult;
import com.yumu.hexie.integration.wuye.vo.PaymentInfo;
import com.yumu.hexie.integration.wuye.vo.WechatPayInfo;
import com.yumu.hexie.model.user.User;
import com.yumu.hexie.model.user.UserRepository;
import com.yumu.hexie.service.exception.BizValidateException;
import com.yumu.hexie.service.shequ.WuyeService;

@Service("wuyeService")
public class WuyeServiceImpl implements WuyeService {

	@Inject
	private UserRepository userRepository;
	
	@Override
	public HouseListVO queryHouse(String userId) {
		return WuyeUtil.queryHouse(userId).getData();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)//如果有事务，那么加入事务，没有的话新创建一个
	public HexieUser bindHouse(User user, String stmtId, HexieHouse house) {
		
		User currUser = userRepository.findOne(user.getId());
		
		BaseResult<HexieUser> r= WuyeUtil.bindHouse(currUser.getWuyeId(), stmtId, house.getMng_cell_id());
		if ("04".equals(r.getResult())){
			throw new BizValidateException("当前用户已经认领该房屋!");
		}
		if ("05".equals(r.getResult())) {
			throw new BizValidateException("用户当前绑定房屋与已绑定房屋不属于同个小区，暂不支持此功能。");
		}
		if ("01".equals(r.getResult())) {
			throw new BizValidateException("账户不存在");
		}

		HexieUser u = r.getData();
		
		if("1".equals(u.getIs_house()))//绑了房子，则记录
		{
			currUser.setBind_bit("1");
			currUser.setSect_id(u.getSect_id());
			currUser.setSect_name(house.getSect_name());
			currUser.setCell_id(house.getMng_cell_id());
			currUser.setCell_addr(house.getCell_addr());
			userRepository.save(currUser);
			
		}
		
		return r.getData();
	}

	@Override
	public boolean deleteHouse(String userId, String houseId) {
		BaseResult<String> r = WuyeUtil.deleteHouse(userId, houseId);
		return r.isSuccess();
	}

	@Override
	public HexieHouse getHouse(String userId, String stmtId) {
		return WuyeUtil.getHouse(userId, stmtId).getData();
	}

	@Override
	public HexieUser userLogin(String openId) {
		return WuyeUtil.userLogin(openId).getData();
	}

	@Override
	public PayWaterListVO queryPaymentList(String userId, String startDate,
			String endDate) {
		return WuyeUtil.queryPaymentList(userId, startDate, endDate).getData();
	}

	@Override
	public PaymentInfo queryPaymentDetail(String userId, String waterId) {
		return WuyeUtil.queryPaymentDetail(userId, waterId).getData();
	}

	@Override
	public BillListVO queryBillList(String userId, String payStatus,
			String startDate, String endDate,String currentPage, String totalCount) {
		return WuyeUtil.queryBillList(userId, payStatus, startDate, endDate, currentPage, totalCount).getData();
	}

	@Override
	public PaymentInfo getBillDetail(String userId, String stmtId,
			String anotherbillIds) {
		return WuyeUtil.getBillDetail(userId, stmtId, anotherbillIds).getData();
	}

	@Override
	public WechatPayInfo getPrePayInfo(String userId, String billId,
			String stmtId, String openId, String couponUnit, String couponNum, 
			String couponId,String mianBill,String mianAmt, String reduceAmt) throws ValidationException {
		return WuyeUtil.getPrePayInfo(userId, billId, stmtId, openId, couponUnit, couponNum, couponId,mianBill,mianAmt, reduceAmt)
				.getData();
	}

	@Override
	public PayResult noticePayed(String userId, String billId, String stmtId, String tradeWaterId, String packageId) {
		return WuyeUtil.noticePayed(userId, billId, stmtId, tradeWaterId, packageId).getData();
	}

	@Override
	public BillListVO quickPayInfo(String stmtId, String currPage, String totalCount) {
		return WuyeUtil.quickPayInfo(stmtId, currPage, totalCount).getData();
	}

	@Override
	public String queryCouponIsUsed(String userId) {

		BaseResult<String> r = WuyeUtil.couponUseQuery(userId);
		return r.getResult();
	}
	
	

}
