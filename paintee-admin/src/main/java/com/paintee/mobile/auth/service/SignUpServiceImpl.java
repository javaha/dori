/**
@file SignUpServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | SignUpServiceImpl.java |    
| Package | com.paintee.mobile.auth.service |    
| Project name | paintee-admin |    
| Type name | SignUpServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 12. 오후 11:02:42 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.auth.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paintee.common.mail.ConfirmMailVO;
import com.paintee.common.mail.HtmlContentBuilder;
import com.paintee.common.mail.MailService;
import com.paintee.common.repository.entity.ConfirmHash;
import com.paintee.common.repository.entity.ConfirmHashExample;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.UserExample;
import com.paintee.common.repository.helper.ConfirmHashHelper;
import com.paintee.common.repository.helper.UserHelper;
import com.paintee.common.util.Sha512Encrypt;

/**
@class SignUpServiceImpl
com.paintee.mobile.auth.service \n
   ㄴ SignUpServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 12. 오후 11:02:42 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 회원가입 servcie 구현채
*/
@Service(value="com.paintee.mobile.auth.service.SignUpServiceImpl")
public class SignUpServiceImpl implements SignUpService {
	private final static Logger logger = LoggerFactory.getLogger(SignUpServiceImpl.class);

	@Autowired
	private UserHelper userHelper;

	@Autowired
	private HtmlContentBuilder mtmlContentBuilder;

	@Autowired
	private MailService mailService;
	
	@Autowired
	private ConfirmHashHelper confirmHashHelper;

	@Value("#{config['common.signup.confirm.url'] }")
	private String confirmUrl;

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 회원가입
	 @remark
	 - 오버라이드 함수의 상세 설명 : 회원가입
	 @see com.paintee.mobile.auth.service.SignUpService#registUser(com.paintee.common.repository.entity.User)
	*/
	@Transactional
	public boolean registUser(User user) throws Exception {
		boolean result = false;

		DateTime today = new DateTime();

		//회원정보 등록
		String plainText = user.getPassword();
		String newUserId = UUID.randomUUID().toString();

		user.setUserId(newUserId);
		user.setPassword(Sha512Encrypt.hash(plainText));
		user.setUserStatus("T");
		user.setCreatedDate(today.toDate());

		userHelper.insertSelective(user);

		//계정활성화를 위한 정보 저장
		DateTime expireDate = today.plusDays(7);

		String confirmHashString = Sha512Encrypt.hash(newUserId+expireDate.getMillis());
		ConfirmHash confirmHash = new ConfirmHash();
		confirmHash.setHash(confirmHashString);
		confirmHash.setUserId(user.getUserId());
		confirmHash.setExpireDate(expireDate.toDate());

		confirmHashHelper.insertSelective(confirmHash);

		//회원가입 메일 발송
		ConfirmMailVO confirmMailVO = new ConfirmMailVO();
		confirmMailVO.setTitle("SignUp Title");
		confirmMailVO.setSenderName("paintee");
		confirmMailVO.setConfirmUrl(confirmUrl+confirmHashString);

		mailService.sendMail(user.getEmail(), "SignUp confirm", mtmlContentBuilder.getSignupConfirmMail(confirmMailVO));

		result = true;

		return result;
	}
	
	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : hash 정보를 통해 사용자계정 활성화
	 @remark
	 - 오버라이드 함수의 상세 설명 : hash 정보를 통해 사용자계정을 활성화시킨다.(0:정상, 1:해당 정보를 찾지 못한경우, 2:expire date 가 지난경우)
	 @see com.paintee.mobile.auth.service.SignUpService#confirmHsh(java.lang.String)
	*/
	@Transactional
	public int confirmHsh(String hash) throws Exception {
		int result = 0;

		ConfirmHashExample confirmHashExample = new ConfirmHashExample();
		ConfirmHashExample.Criteria where = confirmHashExample.createCriteria();
		where.andHashEqualTo(hash);

		List<ConfirmHash> confirmHashList = confirmHashHelper.selectByExample(confirmHashExample);

		if(confirmHashList != null && confirmHashList.size() == 1) {
			ConfirmHash confirmHash = confirmHashList.get(0);

			Date today = new Date();

			int compare = today.compareTo(confirmHash.getExpireDate());
			
			logger.debug("compare:{}", compare);
			if(compare == 0 || compare < 0) {
				User user = userHelper.selectByPrimaryKey(confirmHash.getUserId());

				user.setUserStatus("N");

				userHelper.updateByPrimaryKeySelective(user);

				confirmHashHelper.deleteByPrimaryKey(confirmHash.getSeq());
			} else {//expire date 가 지난경우
				User user = userHelper.selectByPrimaryKey(confirmHash.getUserId());

				result = 2;

				DateTime tmpToday = new DateTime();

				//계정활성화를 위한 정보 저장
				DateTime expireDate = tmpToday.plusDays(7);

				String confirmHashString = Sha512Encrypt.hash(user.getUserId()+expireDate.getMillis());
				ConfirmHash newConfirmHash = new ConfirmHash();
				newConfirmHash.setHash(confirmHashString);
				newConfirmHash.setUserId(user.getUserId());
				newConfirmHash.setExpireDate(expireDate.toDate());

				confirmHashHelper.insertSelective(confirmHash);

				//회원가입 메일 발송
				ConfirmMailVO confirmMailVO = new ConfirmMailVO();
				confirmMailVO.setTitle("SignUp Title");
				confirmMailVO.setSenderName("paintee");
				confirmMailVO.setConfirmUrl(confirmUrl+confirmHashString);

				mailService.sendMail(user.getEmail(), "SignUp confirm", mtmlContentBuilder.getSignupConfirmMail(confirmMailVO));

				confirmHashHelper.deleteByPrimaryKey(confirmHash.getSeq());
			}
		} else {//해당 정보를 찾지 못한경우
			result = 1;
		}

		return result;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 :  email, user name 중복 체크
	 @remark
	 - 오버라이드 함수의 상세 설명 :  email, user name 중복 체크(0-사용가능한 email, user name/1-email 중복/2-user name 중복
	 @see com.paintee.mobile.auth.service.SignUpService#checkDuplicate(com.paintee.common.repository.entity.User)
	*/
	public int checkDuplicate(User user) {
		int result = 0;

		UserExample emailExample = new UserExample();
		UserExample.Criteria where = emailExample.createCriteria();
		where.andEmailEqualTo(user.getEmail());

		int count = userHelper.countByExample(emailExample);

		if(count > 0) {
			result = 1;
		} else {
			UserExample userNameExample = new UserExample();
			UserExample.Criteria userNameWhere = userNameExample.createCriteria();
			userNameWhere.andNameEqualTo(user.getName());
			count = userHelper.countByExample(userNameExample);
			
			if(count > 0) {
				result = 2;
			}
		}

		return result;
	}
}