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

package tsuboneSystem.original.manager;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.text.StrBuilder;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.util.StringUtil;

import tsuboneSystem.code.MailBrowsingRightsCode;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.original.util.ConfigUtil;
import tsuboneSystem.service.TMemberService;

import com.sun.mail.smtp.SMTPTransport;

public class MailManager {
	
	public MailManager() {
		//this.fromMemberId = fromMemberId;
	}
	//送信先アドレス
	List<TMember> toAddress = new ArrayList<TMember>();

	//内容
	String content = null;

	//タイトル
	String title = null;
	
	//遷移させたいUrl
	String requestUrl =null;
	
	//送信元(表示名)
	String displayName = "神楽坂一丁目通信局";

	//エラーメッセージ
	StringBuilder errorMsg = new StringBuilder();

	//エンコード
	String encoding = "ISO-2022-JP";
	
	//ログをDBに残すならTRUE
	boolean isSetLog = false;
	
	//送信者のmemberID
	Integer sendMemberID;
	
	//メールの回覧権限(デフォルトはMEMBER)
	public Integer browsingRights = MailBrowsingRightsCode.MEMBER.getCodeNumber();
	
	TMail tMail = new TMail();

	/**
	 * タイトルを追加する。
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 現在の内容に追加する。
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 送信者のID
	 * @param sendMemberID
	 */
	public void setSendMemberId(Integer sendMemberID){
		this.sendMemberID = sendMemberID;
	}
	
	/**
	 * 遷移させたいURL
	 * @param requestUrl
	 */
	public void setRequestUrl(String requestUrl){
		this.requestUrl = requestUrl;
	}

	/**
	 * アドレスを設定する
	 * @param toAddress
	 */
	public void setToAddress(TMember...member) {
		toAddress = new ArrayList<TMember>();
		for(TMember tMember : member){
			toAddress.add(tMember);
		}
			

	}
	/**
	 * メール送信
	 * @return
	 */
	public boolean sendMail() {
		
		//エラーがあればTRUE
		if (!check()) {
			return false;
		}
		Properties objPrp = new Properties();
		objPrp.setProperty("mail.smtp.host", "smtp.gmail.com");
		objPrp.setProperty("mail.smtp.port", "465");
		objPrp.setProperty("mail.smtp.auth", "true");
		
		//タイムアウトも一応
        objPrp.setProperty("mail.smtp.connectiontimeout", "5000");
        objPrp.setProperty("mail.smtp.timeout", "5000");
 
        //必要性はよくわからないけど、JavaMailはMessage-IDにこの値を付加するから一応
        objPrp.setProperty("mail.user", "kagucho.net@gmail.com");
        objPrp.setProperty("mail.host", "smtp.gmail.com");
 
        //サーバとの会話内容を出力してくれる！
        objPrp.setProperty("mail.debug", "true");
		
        // SSL関連設定
        objPrp.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        objPrp.setProperty("mail.smtp.socketFactory.fallback", "false");
        objPrp.setProperty("mail.smtp.socketFactory.port", "465");

		String address = ConfigUtil.getConfig("mail.address");
		String pw = ConfigUtil.getConfig("mail.pw");

        // メールセッションを確立
		Session session = Session.getInstance(objPrp, new PlainAuthenticator(address,pw));
		// 送信メッセージを生成
		MimeMessage objMsg = new MimeMessage(session);
		
		try {
			// 送信元
			objMsg.setFrom(new InternetAddress(address,displayName));
			
			// 件名
			objMsg.setSubject(title, encoding);
			
			// 送信先（TOのほか、CCやBCCも設定可能）
			objMsg.setRecipients(Message.RecipientType.BCC, getToAddress());
			
			// 本文
			objMsg.setText(getContent(), encoding);
			
			// メール送信
			SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			try {
		        t.connect("smtp.gmail.com", address, pw);
		        t.sendMessage(objMsg, objMsg.getAllRecipients());
		      } finally {
		        t.close();
		      }
			return false;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return true;
		} 
	}
	//メール本文の最初に表示する文字
	private String getHeader() {
		TMemberService tMemberService = SingletonS2Container.getComponent(TMemberService.class);
		if (sendMemberID != null) {
			TMember tMember = tMemberService.findById(sendMemberID);
			StrBuilder builder = new StrBuilder();
			builder.append("\"");
			builder.append(tMember.hname);
			builder.append("\"");
			builder.append("さんより");
			builder.append("\n");
			builder.append("\n");
			return builder.toString();
		}
		return null;
	}
	
	//メール本文の最後に表示する文字
	private String getFooder() {
		StrBuilder builder = new StrBuilder();
		builder.append("\n");
		builder.append("////////////////////////////////\n");
		builder.append("神楽坂一丁目通信局");
		builder.append("\n");
		builder.append(ConfigUtil.getConfig("web.uri") + "login/");
		builder.append("\n");
		builder.append("\n");
		return builder.toString();
	}
	
	//ヘッダーと本文、フッターをくっつける
	private String getContent(){
		StringBuffer conBf = new StringBuffer();
		if (StringUtil.isNotEmpty(getHeader())) {
			conBf.append(getHeader());
		}
		conBf.append(content);
		conBf.append(getFooder());
		return conBf.toString();
	}
	
	/**
	 * 送信先アドレスをセットする
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws AddressException
	 */
	private Address[] getToAddress() throws UnsupportedEncodingException, AddressException {
		
		InternetAddress[] addressArray = new InternetAddress[toAddress.size()];
		
		int count = 0;
		for (TMember member : toAddress) {
			addressArray[count] = new InternetAddress(member.mail, member.hname, encoding);
			count++;
		}
		return addressArray;
	}

	/**
	 * メールを送信する前にチェックを行う		<br />
	 * チェックをしてOKならTRUE
	 * @return
	 */
	private boolean check() {
		boolean notErrorFlg = true;
		if (toAddress.size() == 0) {
			notErrorFlg = false;
			errorMsg.append("not toAddress");
		}
		
		if (title == null) {
			title = "";
		}
		
		if (content == null) {
			content = "";
		}
		return notErrorFlg;
	}
	
	class PlainAuthenticator extends Authenticator {
	    private final String user;
	    private final String password;
	
	    public PlainAuthenticator(final String user, final String password) {
	        this.user = user;
	        this.password = password;
	    }
	
	    @Override
	    protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication(this.user, this.password);
	    }
	}

}
