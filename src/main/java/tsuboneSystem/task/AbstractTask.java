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
