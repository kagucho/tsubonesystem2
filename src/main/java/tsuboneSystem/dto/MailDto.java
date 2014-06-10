package tsuboneSystem.dto;

import java.util.List;

import tsuboneSystem.entity.TMember;

public class MailDto {
	
	/* メールを送信したメンバーのID　*/
	public Integer registMemberId;
	
	/* メールのタイトル　*/
	public String title;
	
	/* メールの内容　*/
	public String content;
	
	/** メールを送りたいメンバー　*/
	public List<TMember> tMemberSendList;
	
	/** メンバー一覧　*/
	public List<TMember> tMemberItem;
	
}
