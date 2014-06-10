package tsuboneSystem.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TMember;


@Component(instance = InstanceType.SESSION) 
public class MailForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/* id　*/
	public Integer id;
	
	/* メールを送信したメンバーのID　*/
	public Integer registMemberId;
	
	/* メールのタイトル　*/
	public String title;
	
	/* メールの内容　*/
	public String content;
	
	/** Member一覧　*/
	public List<TMember> tMemberItem;
	
	/** 選ばれたmember　*/
	public String[] memberSelect;
	
	/** Member一覧　*/
	public List<TMember> tMemberSendList = new ArrayList<TMember>();
	
	public String getContent() {
		return content; 
	}
	
}
