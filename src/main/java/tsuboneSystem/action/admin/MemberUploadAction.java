package tsuboneSystem.action.admin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.struts.upload.FormFile;
import org.seasar.s2csv.csv.S2CSVParseCtrl;
import org.seasar.s2csv.csv.factory.S2CSVCtrlFactory;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.csv.MemberUploadCsv;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MemberUploadForm;
import tsuboneSystem.service.TMemberService;

public class MemberUploadAction {
	
	public String actionName = "MemberUpload";
	
	@ActionForm
	@Resource
	protected MemberUploadForm memberUploadForm;
	
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
	
	//画面に表示するエラーを格納する
	ArrayList<String> errorMsgList = new ArrayList<String>();
	
	@Execute(validator = false)
	public String upload() {
		try {
			formFile = memberUploadForm.file;
			//チェックが通ればアップロードを実行する
			if (check()) {
				uploadMember();
			}
		} finally {
			
		}
		
		return "memberUpload.jsp";
	}

	/**
	 * アップロードされたCSVを全件アップロードする
	 */
	protected void uploadMember() {
		try (Reader reader = new InputStreamReader(formFile.getInputStream())) {
			S2CSVParseCtrl<MemberUploadCsv> controller = s2csvCtrlFactory.getParseController(MemberUploadCsv.class, reader);
			while (controller.readNext()) {
				MemberUploadCsv csv = controller.parse();
				uploadOneRecordMember(csv);
			}
		} catch (IOException e) {
			e.printStackTrace();
			setError("ファイル入出力中にエラーが発生しました。");
		}
	}

	/**
	 * csvを1レコードDBにセットする
	 * @param csv
	 */
	private void uploadOneRecordMember(MemberUploadCsv csv) {
		TMember member = new TMember();
		member.hname = csv.hname;
		member.password = csv.password;
		member.mail = csv.mail;
		member.userName = csv.userName;
		try {
			tMemberService.insert(member);
		} catch(Exception e) {
			e.printStackTrace();
			//画面に表示するエラーを表示
			setError("EXCEPTIONが発生しました。\n " + csv.toString() + "\n" + e.getStackTrace());
		}
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
}
