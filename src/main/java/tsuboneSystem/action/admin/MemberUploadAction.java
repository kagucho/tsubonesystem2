package tsuboneSystem.action.admin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

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
	protected FormFile file;
	
	@Resource
	protected S2CSVCtrlFactory ctrlFactory;
	
	@Execute(validator = false)
	public String index() {
		return "memberUpload.jsp";
	}
	
	@Execute(validator = false)
	public String upload() {
		file = memberUploadForm.form;
		if (check()) {
			try (Reader reader = new InputStreamReader(file.getInputStream())) {
				S2CSVParseCtrl<MemberUploadCsv> controller = ctrlFactory.getParseController(MemberUploadCsv.class, reader);
				while (controller.readNext()) {
					MemberUploadCsv csv = controller.parse();
					uploadMember(csv);
				}
			} catch (IOException e) {
				e.printStackTrace();
				setError("ファイル入出力中にエラーが発生しました。");
			}
		}
		
		return "memberUpload.jsp";
	}

	private void uploadMember(MemberUploadCsv csv) {
		TMember member = new TMember();
		member.hname = csv.hname;
		member.password = csv.password;
		member.mail = csv.mail;
		member.userName = csv.userName;
		new TMemberService().insert(member);
	}

	protected void setError(String errorMsg) {
		//TODO 未実装
	}

	/**
	 * エラーが無いならTRUE
	 * @return
	 */
	protected boolean check() {
		//ファイルがアップロードされていないならエラー
		if (file == null) {
			return false;
		}
		
		//CSVファイルでないならエラー
		if (!file.getFileName().toLowerCase().endsWith(".csv")) {
			return false;
		}
		
		return true;
	}
}
