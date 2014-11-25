package tsuboneSystem.original.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.upload.FormFile;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.struts.util.ResponseUtil;
import org.seasar.struts.util.ServletContextUtil;
import org.seasar.struts.util.UploadUtil;

import tsuboneSystem.code.ActorKindCode;
import tsuboneSystem.code.ImageFilePurposeCode;
import tsuboneSystem.entity.TAdmin;
import tsuboneSystem.entity.TImageUpload;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TSubmit;
import tsuboneSystem.form.SubmitForm;
import tsuboneSystem.service.TAdminService;
import tsuboneSystem.service.TImageUploadService;
import tsuboneSystem.service.TLeadersService;
import tsuboneSystem.service.TSubmitService;

/**
 *
 *このシステムで使う様々なUtil
 *
 *
 */
public class TsuboneSystemUtil {
	
	/**
	 * メンバーの権限を判定する
	 * 
	 * @param TMember
	 * @return ActorKindCode
	 * @author Hiroaki
	 * 
	 * */
	public static String actorKind(TMember member){
		
		String actorKind = null;
		
		//メンバーに該当がいたら、そのメンバーが役職に付いているかを判定する
		List<TLeaders> leaderList = new ArrayList<TLeaders>();
		TLeadersService tLeadersService = SingletonS2Container.getComponent(TLeadersService.class);
		leaderList = tLeadersService.findByMemberIdOrderKindList(member.id);
		
		//メンバーに該当がいたら、そのメンバーが管理者に付いているかを判定する
		TAdminService tAdminService = SingletonS2Container.getComponent(TAdminService.class);
		TAdmin tAdmin = tAdminService.findByMemberId(member.id);
		
		//管理者の場合はadmin
		if(tAdmin != null){
			actorKind = ActorKindCode.ADMIN.getCode();
    		return actorKind;
		}
    	//部長以上の場合はleaders
    	if (leaderList.size() > 0) {
    		actorKind = ActorKindCode.LEADERS.getCode();
        	return actorKind;
    	}else{
    		actorKind = ActorKindCode.MEMBER.getCode();
        	return actorKind;
    	}
	}
	
	/**
	 * Uploadされたファイルの拡張子を判定する
	 * 
	 * @param FormFile formFile, String kind
	 * @return 設定された拡張子と等しかったらfalse
	 * @author Hiroaki
	 * 
	 * */
	public static boolean isFileKindCheck(FormFile formFile, String[] kinds){
		 // アップロードされたファイルのCheck
        if (formFile.getFileSize() > 0) {
        	String fileContentType = new String();
        	fileContentType = formFile.getContentType();
        	if (fileContentType != null) {
        		//設定された拡張子と等しいか判定する
        		String[] splitStr = fileContentType.split("/");
        		if (!Arrays.asList(kinds).contains(splitStr[1])) {
        			return true;
        		} else {
        			return false;
        		}
        	} else {
        		//ファイルの種類が判別できないとき
        		return true;
        	}
        } else {
        	//ファイルがUploadされていないとき
        	return true;
        }
	}
	
	/**
	 * Uploadされたファイルのファイルサイズを判定する
	 * 
	 * @param FormFile formFile,int size
	 * @return 設定されたファイルサイズ(byte)よりも大きかったらtrue
	 * @author Hiroaki
	 * 
	 * */
	public static boolean isFileSizeCheck(FormFile formFile, int size){
		if (formFile.getFileSize() > size) {
			return true;
		}
		return false;
	}
	
	/**
	 * String型(yyyy/mm/dd)をDate型に変換する
	 * 
	 * @param String(yyyy/mm/dd)
	 * @return Date
	 * @author Hiroaki
	 * 
	 * */
	public static Date  parseDate(String strDate) {
		if (StringUtils.isNotEmpty(strDate)) {
			Date date = new Date();
			try {
				// 変換
				date = new SimpleDateFormat("yyyy/MM/dd").parse(strDate);
			} catch (ParseException e) {
				//起こりえない
				e.printStackTrace();
			}
			return date;
		} else {
			return null;
		}
	}
	
	/**
	 * TImageUploadに登録されている画像ファイルを削除し、該当のTImageUploadも削除する
	 * 削除に失敗した場合true
	 * 
	 * @param TImageUpload
	 * @return boolean
	 * @author Hiroaki
	 * 
	 * */
	public static boolean deleteFile(TImageUpload tImageUpload) {
		if (tImageUpload != null) {
			File file = new File(tImageUpload.filePath);
			if (file.exists()) {
				if (file.delete()) {
					TImageUploadService tImageUploadService = SingletonS2Container.getComponent(TImageUploadService.class);
					tImageUploadService.delete(tImageUpload);
				} else {
					return true;
				}
			} else {
				return true;
			}
		} else {
			return true;
		}
		return false;
	}
	
	/**
	 * 画像ファイルをアップロード
	 * アップロードに成功した場合はTImageUploadのid
	 * アップロードに失敗した場合はnull
	 * 
	 * @param FormFile imageFilePurposeCode
	 * @return Integer
	 * @author Hiroaki
	 * 
	 * */
	public static Integer createImageFile(FormFile file, String imageFilePurposeCode) {
		Integer rtnInt = null;
		//ServletContext オブジェクトの作成
    	ServletContext app = ServletContextUtil.getServletContext();
    	
    	//ランダム文字を生成
    	String rm = RandomStringUtils.randomAlphabetic(10);
    	
    	//ファイルの格納先フォルダの絶対パスを取得(DBにこのパスを保存しておく)
    	String path = app.getRealPath("/images/top/announce/" + rm + file.getFileName());
    	
    	//ファイル書き込み（ファイルパスが空の場合は何もしません）
        UploadUtil.write(path, file);
        
        //ファイル名とファイルパスをDBに追加
    	TImageUpload imageUpload = new TImageUpload();
    	imageUpload.fileName = rm + file.getFileName();
    	imageUpload.filePath = path;
    	imageUpload.ImageFilePurpose = Integer.valueOf(imageFilePurposeCode);
    	TImageUploadService tImageUploadService = SingletonS2Container.getComponent(TImageUploadService.class);
    	tImageUploadService.insert(imageUpload);
    	if (imageUpload.id != null) {
    		rtnInt = imageUpload.id;
    	}
    	return rtnInt;
	}
	
	/**
	 * 画像ファイルをアップロード
	 * アップロードに成功した場合はTImageUploadのid
	 * アップロードに失敗した場合はnull
	 * 
	 * @param FormFile imageFilePurposeCode
	 * @return Integer
	 * @author Hiroaki
	 * 
	 * */
	public static void createSubmitFile(SubmitForm submitForm) {
		// キャプション画像の登録
		submitForm.submitCaptionImageId = createImageFile(submitForm.submitCaptionImageFile, ImageFilePurposeCode.SUBMIT_CAPTION.getCode());
		
		//ServletContext オブジェクトの作成
    	ServletContext app = ServletContextUtil.getServletContext();
    	
    	//ランダム文字を生成
    	String rm = RandomStringUtils.randomAlphabetic(10);
    	
    	//ファイルの格納先フォルダの絶対パスを取得(DBにこのパスを保存しておく)
    	String path = app.getRealPath("/submit/" + rm + submitForm.submitFile.getFileName());
    	
    	//ファイル書き込み（ファイルパスが空の場合は何もしません）
        UploadUtil.write(path, submitForm.submitFile);
        
        //ファイル名とファイルパスをDBに追加
        submitForm.submitProductFileName = submitForm.submitFile.getFileName();
    	submitForm.submitProductFilePath= path;
	}
	
	public static void deleteSubmitFile(SubmitForm submitForm, boolean deleteFlag) {
		// キャプション画像の削除
		TImageUploadService tImageUploadService = SingletonS2Container.getComponent(TImageUploadService.class);
		if (!deleteFile(tImageUploadService.findById(submitForm.submitCaptionImageId))) {
			submitForm.submitCaptionImageId = null;
		}
		
		
		// 提出物の削除
		TSubmitService tSubmitService = SingletonS2Container.getComponent(TSubmitService.class);
		TSubmit tSubmit = tSubmitService.findById(submitForm.id);
		if (tSubmit != null) {
			File file = new File(tSubmit.submitProductFilePath);
			if (file.exists()) {
				if (file.delete()) {
					submitForm.submitProductFilePath = null;
					if (deleteFlag) {
						tSubmit.submitProductFilePath = null;
						tSubmit.deleteFlag = true;
						tSubmitService.update(tSubmit);
					}
				}
			}
		}
	}
	
	/**
	 * 作品のDownload処理
	 * 
	 * @param Integer submitId
	 * @return null
	 * @author Hiroaki
	 * 
	 * */
	public static String submitDownload(Integer submitId){
		
		// ダウンロード対象の作品を取得する
		TSubmitService tSubmitService = SingletonS2Container.getComponent(TSubmitService.class);
    	TSubmit tSubmit = tSubmitService.findById(submitId);
    	
    	if (tSubmit == null) {
    		// TODO download対象の作品の情報が取得できなかったときの処理T
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
