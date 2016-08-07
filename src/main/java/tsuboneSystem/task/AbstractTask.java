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

package tsuboneSystem.task;

import tsuboneSystem.original.manager.MailManager;

public abstract class AbstractTask {
	public void doExecute() {
		try {
			//タスクを実行する
			process();
		} catch(Exception e) {
			//Exceptionが発生したらメールを送る
			failureMail(e);
		}
	}
	
	private void failureMail(Exception e) {
		StringBuilder content = new StringBuilder();
		content.append("バッチ実行中にExceptionが発生しました。");
		content.append("=====================================");
		content.append(e.getMessage());
		
		MailManager mailManager = new MailManager();
		mailManager .setTitle("バッチファイル実行失敗【" + getTascName() + "】");
		mailManager.setContent(content.toString());
		//TODO メールの送り先を設定する
		if (!mailManager.sendMail()) {
			System.out.println("メール送信失敗");
		};
	}
	
	/**
	 * @return タスク名
	 */
	abstract String getTascName();
	
	/**
	 * タスクを実装する
	 * @return 成功したらTRUE
	 */
	abstract void process() throws Exception;
}
