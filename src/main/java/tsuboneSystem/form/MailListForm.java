package tsuboneSystem.form;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMailSendMember;
import tsuboneSystem.entity.TMember;


@Component(instance = InstanceType.SESSION)
public class MailListForm implements Serializable{

	private static final long serialVersionUID = 1L;

	/* id　*/
	public Integer id;

	/* メールを送信したメンバーのID　*/
	public Integer registMemberId;

	/* メールのタイトル　*/
	public String title;

	/* メールの内容　*/
	public String content;

	/* 自分に届いたメールチェック */
	public String myMailCheck;

	/** Member一覧　*/
	public List<TMember> tMemberItem;

	/** Mail一覧　*/
	public List<TMail> tMailItem;

	/** MailSendMember一覧　*/
	public List<TMailSendMember> tMailSendMemberItem;

	/** 全メンバーのリスト　*/
	public List<TMember> tMemberAllList;

	/** memberのマップ */
	public Map<Integer,String> memberMapIS;


}
