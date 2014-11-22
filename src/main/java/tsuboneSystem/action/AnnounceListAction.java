package tsuboneSystem.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.seasar.struts.util.ResponseUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.entity.TSubmit;
import tsuboneSystem.form.AnnounceListForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TImageUploadService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TSubmitService;
import tsuboneSystem.service.TSubmitTagKindService;
import tsuboneSystem.service.TTopAnnounceService;

public class AnnounceListAction {
	
	public String actionName = "作品一覧";
	
	/** AnnounceListActionのアクションフォーム */
	@ActionForm
	@Resource
	protected AnnounceListForm announceListForm;
	
	/** TClubServiceのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TTopAnnounceServiceのサービスクラス */
	@Resource
	protected TTopAnnounceService tTopAnnounceService;
	
	/** TImageUploadServiceのサービスクラス */
	@Resource
	protected TImageUploadService tImageUploadService;
	
	/** TSubmitService */
	@Resource
	public TSubmitService tSubmitService;
	
	/** TMemberService */
	@Resource
	public TMemberService tMemberService;
	
	/** TSubmitTagKindService */
	@Resource
	public TSubmitTagKindService tSubmitTagKindService;
	
	
	/**
	 * おしらせ(作品展示)画面表示
	 * 
	 * */
    @Execute(validator = false, urlPattern = "{id}")
	public String index() {
    	
    	// 作品分類タグの選択肢
    	announceListForm.submitTagNameMap = tSubmitTagKindService.getSubmitTagKindMap();
    	// トップアナウンス情報
    	announceListForm.tTopAnnounce = tTopAnnounceService.findById(announceListForm.id);
    	// 作品情報
    	announceListForm.tSubmitList = announceListForm.tTopAnnounce.tSubmitList;
    	List<TSubmit> subList = new ArrayList<TSubmit>();
    	for (TSubmit tSubmit : announceListForm.tSubmitList) {
    		TSubmit sub = tSubmit;
    		sub.tImageUpload = tImageUploadService.findById(tSubmit.submitCaptionImageId);
    		subList.add(sub);
    	}
    	
        return "announceList.jsp";
    }
    
    /**
	 * ダウンロード処理
	 * 
	 * */
    @Execute(validator = false, urlPattern = "download/{tSubmitId}")
	public String download() {
    	
    	// ダウンロード対象の作品を取得する
    	TSubmit tSubmit = tSubmitService.findById(announceListForm.tSubmitId);
    	
    	if (tSubmit == null) {
    		// TODO download対象の作品の情報が取得できなかったときの処理
    	}
    	
    	// ダウンロード対象ファイルの読み込み用オブジェクト
    	FileInputStream fis = null; 
    	InputStreamReader isr = null; 
    	HttpServletResponse res = ResponseUtil.getResponse();

    	// ダウンロードファイルの書き出し用オブジェクト
    	OutputStream os = null; 
    	OutputStreamWriter osw = null; 

    	try { 
    	    // ダウンロード対象ファイルのFileオブジェクトを生成
    	    File file = new File(tSubmit.submitProductFilePath); 

    	    if (!file.exists() || !file.isFile()) { 
    	        // ファイルが存在しない場合のエラー処理
    	    } 

    	    // レスポンスオブジェクトのヘッダー情報を設定
    	    res.setContentType("application/octet-stream"); 
    	    res.setHeader("Content-Disposition", "attachment;filename=" + new String(tSubmit.submitProductFileName.getBytes("Windows-31J"), "ISO-8859-1")); 

    	    // ダウンロード対象ファイルの読み込み用オブジェクトを生成
    	    fis = new FileInputStream(file); 
    	    isr = new InputStreamReader(fis, "ISO-8859-1"); 

    	    // ダウンロードファイルの書き出し用オブジェクトを生成
    	    os = res.getOutputStream(); 
    	    osw = new OutputStreamWriter(os, "ISO-8859-1"); 

    	    // IOストリームを用いてファイルダウンロードを行う
    	    int i; 
    	    while ((i = isr.read()) != -1) { 
    	        osw.write(i); 
    	    } 
    	} catch (FileNotFoundException e) { 
    	    // 例外発生時処理
    	} catch (UnsupportedEncodingException e) { 
    	    // 例外発生時処理
    	} catch (IOException e) { 
    	    // 例外発生時処理
    	} finally { 

    	    try { 
    	        // 各オブジェクトを忘れずにクローズ
    	        if (osw != null) { 
    	            osw.close(); 
    	        } 
    	        if (os != null) { 
    	            os.close(); 
    	        } 
    	        if (isr != null) { 
    	            isr.close(); 
    	        } 
    	        if (fis != null) { 
    	            fis.close(); 
    	        } 
    	    } catch (IOException e) { 
    	        // 例外発生時処理
    	    } 

    	}
    	
    	
        return null;
    }
    
}
