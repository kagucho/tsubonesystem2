package tsuboneSystem.action.admin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.commons.lang3.text.StrBuilder;
import org.apache.struts.upload.FormFile;
import org.seasar.framework.exception.IORuntimeException;
import org.seasar.s2csv.csv.S2CSVParseCtrl;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationResultRuntimeException;
import org.seasar.s2csv.csv.factory.S2CSVCtrlFactory;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

import tsuboneSystem.csv.MemberUploadCsv;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MemberUploadForm;
import tsuboneSystem.original.manager.MailManager;
import tsuboneSystem.original.util.DigestUtil;
import tsuboneSystem.service.TMemberService;

public class MemberUploadAction {
	
	public String actionName = "MemberUpload";
	
	@ActionForm
	@Resource
	protected MemberUploadForm memberUploadForm;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	//アップロードされたファイルを格納する
	protected FormFile formFile;
	
	@Resource
	protected S2CSVCtrlFactory s2csvCtrlFactory;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	@Execute(validator = false)
	public String index() {
		return "memberUpload.jsp";
	}
	
	//画面に表示するエラー文を格納するString
	public String errorMsg;
	
	//画面に表示する情報を格納するString
	public String infoMsg;
	
	//エラーを格納するリスト
	protected ArrayList<String> errorMsgList = new ArrayList<String>();
	
	//メールを送るuserNameとPWを格納するリスト
	ArrayList<String[]> userList = new ArrayList<>();
	
	@Execute(validator = false)
	public String upload() {
		try {
			formFile = memberUploadForm.file;
			//チェックが通ればアップロードを実行する
			if (check()) {
				uploadMember();
			}
			
			if (errorMsgList.size() == 0) {
				sendMail();
			}
		} finally {
			errorMsgToString();
		}
		
		return "memberUpload.jsp";
	}
	
	/**
	 * メールを送信する
	 */
	protected void sendMail() {
		MailManager manager = new MailManager();
		for (String[] user : userList) {
			manager.setToAddress(tMemberService.findByLoginIdPassword(user[0], DigestUtil.md5(user[1])));
		}
		manager.setTitle("<カグチョ>登録完了のお知らせ");
		manager.setContent("登録が完了しました。こちらからログインして更に詳細な情報を登録してください。");
		if (!manager.sendMail()) {
			setError("メールの送信に失敗しました。");
		}
	}

	/**
	 * エラーメッセージを見やすい形に変えて格納する
	 */
	private void errorMsgToString() {
		if (errorMsgList.size() == 0) {
			errorMsg = "";
			infoMsg = "全てのデータが正常に登録されました。今登録したメンバーにメールが直ちに送られます。";
			return;
		} 
		
		StringBuilder msg = new StringBuilder();
		for (String errorMsg : errorMsgList) {
			msg.append(errorMsg);
			msg.append("\n");
		}
		msg.append("\n");
		errorMsg = msg.toString();
		infoMsg = "エラーが発生したためデータの登録は一件も行われていません。もう一度はじめからやり直してください。\n";
	}

	/**
	 * アップロードされたCSVを全件アップロードする
	 */
	protected void uploadMember() {
		
		//行番号
		int i = 0;
		//csvを一行ずつ読み込みDBに挿入していく
		try (Reader reader = new InputStreamReader(formFile.getInputStream(), "Shift_JIS")) {
			S2CSVParseCtrl<MemberUploadCsv> controller = s2csvCtrlFactory.getParseController(MemberUploadCsv.class, reader);
			while (controller.readNext()) {
				i++;
				MemberUploadCsv csv = controller.parse();
				uploadOneRecordMember(csv, i);
			}
		} catch (CSVValidationResultRuntimeException e) {
			setValidationError(e, i);
		}catch (IOException e) {
			e.printStackTrace();
			setError(i + "行目のファイル入出力中にエラーが発生しました。もう一度実行してください。");
		}
	}

	private void setValidationError(CSVValidationResultRuntimeException e, int i) {
		StrBuilder errorMsg = new StrBuilder();
		errorMsg.append(i + "行目のデータ構造が間違っています。");
		//何故かエラーになるのでコメントアウト
//		CSVValidateResult result = e.getValidateResult();
//		for (CSVMsg msg : result.getMsgs()) {
//			errorMsg.append("\n" + msg.toString());
//		}
		setError(errorMsg.toString());
	}

	/**
	 * csvを1レコードDBにセットする
	 * @param csv
	 * @param i 
	 */
	private void uploadOneRecordMember(MemberUploadCsv csv, int i) {
		TMember member = new TMember();
		member.hname = csv.hname;
		member.password = csv.password;
		member.mail = csv.mail;
		member.userName = csv.userName;
		try {
			tMemberService.insert(member);
		} catch(Exception e) {
			//画面に表示するエラーを表示
			setError(i + "行目のデータ挿入中にエラーが発生しました。\n(すでに存在しているハンドルネーム、メールアドレス、ユーザー名の可能性があります。)\n " + csv.toString() + "\n");
			return;
		}
		userList.add(new String[]{csv.userName, csv.password});
	}

	/**
	 * エラーメッセージをセットする
	 * @param errorMsg
	 */
	protected void setError(String errorMsg) {
		errorMsgList.add(errorMsg);
	}

	/**
	 * エラーが無いならTRUE
	 * @return
	 */
	protected boolean check() {
		//ファイルがアップロードされていないならエラー
		if (formFile == null) {
			return false;
		}
		
		//CSVファイルでないならエラー
		if (!formFile.getFileName().toLowerCase().endsWith(".csv")) {
			return false;
		}
		
		return true;
	}
	
	@Execute(validator = false)
	public String download() {
		try {
			ResponseUtil.download(new String("memberUpload.csv".getBytes("Shift_JIS"), "Shift_JIS"),
					"hname,mail,id,pw".getBytes());
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
		return null;
	}
}
