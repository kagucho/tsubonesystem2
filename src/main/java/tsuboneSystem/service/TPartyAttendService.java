/*
 * Copyright (C) 2014-2016  Kagucho <kagucho.net@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package tsuboneSystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.code.MailBrowsingRightsCode;
import tsuboneSystem.code.PartyAttendCode;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;
import tsuboneSystem.original.util.MailManagerUtil;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TMemberClubNames.MemberId;
import static tsuboneSystem.names.TPartyAttendNames.*;

/**
 * {@link TPartyAttend}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/04/13 15:02:32")
public class TPartyAttendService extends AbstractService<TPartyAttend> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TPartyAttend findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TPartyAttend> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
    
    /**
     * PartyIdとMemberIdですべてのエンティティを検索します。
     * 
     * @return エンティティ
     */
	public TPartyAttend findByPartyIdMemberId(Integer partyId,Integer memberId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(partyId(),partyId);
    	where.eq(memberId(),memberId);
        return select().where(where).getSingleResult();
    }
    
    /**
     * PartyIdですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
	public List<TPartyAttend> findByPartyId(Integer partyId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(partyId(),partyId);
        return select().innerJoin(tMember()).innerJoin(tParty()).where(where).getResultList();
    }
	
	/**
     * PartyIdですべてのエンティティを検索します。(未提出)
     * 
     * @return エンティティのリスト
     */
	public List<TPartyAttend> findByPartyId_UNSUBMITTED(Integer partyId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(partyId(),partyId);
    	where.eq(attend(), Integer.valueOf(PartyAttendCode.UNSUBMITTED.getCode()));
        return select().innerJoin("TMember").where(where).getResultList();
    }
    
    /**
     * PartyIdですべてのエンティティを検索します。(出席)
     * 
     * @return エンティティのリスト
     */
	public List<TPartyAttend> findByPartyIdAndAttendOn(Integer partyId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(partyId(),partyId);
    	where.eq(attend(), Integer.valueOf(PartyAttendCode.YES_ATTEND.getCode()));
        return select().innerJoin("TMember").where(where).getResultList();
    }
    
    /**
     * PartyIdですべてのエンティティを検索します。(欠席)
     * 
     * @return エンティティのリスト
     */
	public List<TPartyAttend> findByPartyIdAndAttendOff(Integer partyId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(partyId(),partyId);
    	where.eq(attend(), Integer.valueOf(PartyAttendCode.NO_ATTEND.getCode()));
        return select().innerJoin("TMember").where(where).getResultList();
    }
    
    /**
     * MemberIdとPartyIdでエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TPartyAttend findByMemberIdWithPartyId(String OffMemberId, Integer partyId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(MemberId(), OffMemberId);
    	where.eq(partyId(), partyId);
        return select().where(where).getSingleResult();
    }
    
    public void noticeMemberNum(TParty tParty){
    	if (tParty.noticeMemberNum != null && !tParty.noticeMemberNum.equals(new Integer(0))) {
    		//現在の出席者の件数を検索する
    		SimpleWhere where = new SimpleWhere();
    		where.eq(partyId(), tParty.id);
    		where.eq(attend(), PartyAttendCode.YES_ATTEND.getCodeNumber());
    		Long count = jdbcManager.from(TPartyAttend.class).where(where).getCount();//現在の出席者数
    		Long settingNum = Long.valueOf(tParty.noticeMemberNum);//設定人数
    		
    		//設定人数に等しかったらメールを送信する
    		if (count.equals(settingNum)) {
    			
    			//会議の出席対象
        		List<TMember>tMemberSendList = new ArrayList<TMember>();
        		for (TPartyAttend one : findByPartyId(tParty.id)) {
        			tMemberSendList.add(one.tMember);
        		}
        		
        		//現在の会議の出席者
        		List<TMember> attendMemberList = new ArrayList<TMember>();
        		for (TPartyAttend one : findByPartyIdAndAttendOn(tParty.id)) {
        			attendMemberList.add(one.tMember);
        		}
    			
    			String title = "イベントに設定された人数に出席者数が到達したのでお知らせします！！";
    			
    			StringBuffer bf = new StringBuffer();
    			bf.append("設定された人数に出席者数が到達したのでお知らせします。");
    			bf.append("\n");
    			bf.append("\n");
    			bf.append("会議名：");bf.append(tParty.meetingName);
    			bf.append("\n");
    			bf.append("設定人数：");bf.append(tParty.noticeMemberNum.toString());
    			bf.append("\n");
    			bf.append("\n");
    			bf.append("現在の出席者一覧：");
    			bf.append("\n");
    			for (TMember member : attendMemberList) {
    				bf.append(member.hname);
    				bf.append("\n");
    			}
    			String content = new String(bf);
    			
    			
    			//メールを送信する
    	    	MailManagerUtil mailUtil = new MailManagerUtil();
    	    	mailUtil.setBrowsingRights(MailBrowsingRightsCode.MEMBER.getCodeNumber());
    	    	mailUtil.setTitle(title);
    	    	mailUtil.setContent(content);
    	    	mailUtil.setLinkUrlFlag(false);
    	    	mailUtil.setToAddressActorSplit(tMemberSendList);
    	    	mailUtil.sendMail();
    		}
    	}
    }
}