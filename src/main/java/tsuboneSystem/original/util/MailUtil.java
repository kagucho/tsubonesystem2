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

package tsuboneSystem.original.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

import tsuboneSystem.original.util.ConfigUtil;

public class MailUtil {
	/**
	 * 送信に失敗したアドレスの一覧を取得。<br />
	 * なんらかのエラーが発生した場合にはnullが返されます。nullが返された時は呼び出し元でエラー処理を行ってください。
	 * @throws MessagingException 
	 * @throws IOException
	 */
	public static HashSet<String> getFaledSendAddressList() throws MessagingException, IOException {
		HashSet<String> failedSendAddressList = new HashSet<String>();
		final Properties props = new Properties();

		props.setProperty("mail.pop3.host", "pop.gmail.com");
		props.setProperty("mail.pop3.port", "995");

		// タイムアウト設定
		props.setProperty("mail.pop3.connectiontimeout", "60000");
		props.setProperty("mail.pop3.timeout", "60000");

		// SSL関連設定
		props.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.pop3.socketFactory.fallback", "false");
		props.setProperty("mail.pop3.socketFactory.port", "995");

		final Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ConfigUtil.getConfig("mail.address"),
								  ConfigUtil.getConfig("mail.pw"));
			}
		});

		// デバッグを行います。標準出力にトレースが出ます。
		session.setDebug(true);

		Store store = null;
		try {
			store = session.getStore("pop3");
			store.connect();

			Folder folder = null;
			try {
				folder = store.getFolder("INBOX");
				folder.open(Folder.READ_WRITE);
				
				// メッセージ一覧を取得
				final Message[] messages = folder.getMessages();
				for (Message message : messages) {
					String[] addressList = message.getHeader("X-Failed-Recipients");

					// 不到達通知メールじゃなければスキップする
					if (addressList == null) {
						continue;
					}
					
					//削除フラグが立っていたらスキップ
					if (Flags.Flag.DELETED.equals(message.getFlags())) {
						continue;
					}

					//不到達のアドレスを格納
					for (String address : addressList) {
						failedSendAddressList.add(address);
					}
					
					//注意：
					//message.getContent()を呼び出すとメールが削除される可能性があります。(gmailは削除された扱いになります。2014/5/16 なみけん)
					//何故か削除フラグだとメールが削除されないのでここでコンテンツを取得し削除する
					 message.getContent();

					// 不到達通知メールを削除する
					message.setFlag(Flags.Flag.DELETED, true);
					
				}
			} finally {
				if (folder != null) {folder.close(false);}
			}
		} finally {
			if (store != null) {
					store.close();
			}
		}
		
		return  failedSendAddressList;
	}
}
