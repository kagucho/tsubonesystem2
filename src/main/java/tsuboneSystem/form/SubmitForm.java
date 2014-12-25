package tsuboneSystem.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.annotation.Required;

import tsuboneSystem.code.FileKindCode;
import tsuboneSystem.code.SubmitProductFileTypeCode;
import tsuboneSystem.entity.TImageUpload;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TSubmitTagKind;
import tsuboneSystem.entity.TTopAnnounce;
import tsuboneSystem.original.util.TsuboneSystemUtil;

@Component(instance = InstanceType.SESSION) 
public class SubmitForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    /** id　*/
    public Integer id;
    
    /** 登録者 */
    @Required
    public String registId;
    
    /** 作品の分類タグ */
    public String submitTagKindId;
    
    /** お知らせID */
    public String topAnnounceId;
    
    /** 作品名 */
    @Required
    public String submitName;
    
    /** 作品の説明 */
    public String submitDetail;
    
    /** 作品のキャプション画像 */
    public FormFile submitCaptionImageFile;
    
    /** 作品のキャプション画像ID */
    public Integer submitCaptionImageId;
    
    /** 提出物 */
    public FormFile submitFile;
    
    /** 提出物の名前 */
    public String submitProductFileName;
    
    /** 提出物の保存先 */
    public String submitProductFilePath;
    
    /** 提出物の種別(大まかな拡張子の種類:"画像系""ソフト系") */
    public String submitProductFileType;
    
    /** SoundCloudの作品URL(DTM部用) */
    public String soundCloudUrl;
    
    /** TOPお知らせ */
    public TTopAnnounce tTopAnnounce;
    
    /** キャプション画像 */
    public TImageUpload tImageUpload;
    
    /** 作品分類タグ */
    public TSubmitTagKind tSubmitTagKind;
    
    /** 登録者情報 */
    public TMember tMember;
    
    /** 作品分類タグ選択肢 */
    public Map<String, String> submitTagNameMap = new HashMap<String, String>();
    
    /** 作品の提出者(管理者などは他人の作品を提出できる) */
    public Map<String, String> submitMemberMap = new HashMap<String, String>();
    
    /** おしらせ */
    public Map<String, String> topAnnounceMap = new HashMap<String, String>();
    
    /** ファイル種大別 */
    public Map<String, String> submitProductFileCodeMap = new HashMap<String, String>();
    
    /** 新規作成処理か判定 */
    public boolean registFlag = false;
    
	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
		id = null;
		registId = null;
		topAnnounceId = null;
		submitTagKindId = null;
		submitName = null;
		submitDetail = null;
		submitCaptionImageId = null;
		submitProductFileName = null;
		submitProductFilePath = null;
		submitProductFileType = null;
		tImageUpload = null;
		if (submitFile != null) {
			submitFile.destroy();
		}
		if (submitCaptionImageFile != null) {
			submitCaptionImageFile.destroy();
		}
	}
	
	//オリジナルチェック
    public ActionMessages validateBase(){
    	
    	ActionMessages errors = new ActionMessages();
    	
    	int programsSize = 52428800;// プログラムのアップロード容量は50MBとする
    	int imageSize = 10485760;// 画像が提出物の時のアップロード容量は10MBとする
    	int captionImageSize = 5242880;// caption画像のアップロード容量は2MBとする
    	String [] programType = {FileKindCode.EXE.getName(), FileKindCode.ZIP.getName()};// プログラム系の拡張子
    	String [] imageType = {FileKindCode.JPEG.getName(), FileKindCode.JPG.getName(), FileKindCode.PNG.getName(), FileKindCode.ZIP_BIG.getName()};// 画像系の拡張子
    	
    	// 提出物 ファイル種別確認
    	// プログラム系
    	if (StringUtil.isNotEmpty(submitProductFileType)) {
    		if (submitProductFileType.equals(SubmitProductFileTypeCode.PROGRAMS.getCode())) {
        		// プログラム系
    			if (StringUtil.isEmpty(submitDetail)) {
    				errors.add("submitDetail",new ActionMessage("作品の説明は必須です",false));
    			}
        		if (submitFile.getFileSize() > 0 || submitCaptionImageFile.getFileSize() > 0){
        			// ファイルサイズチェック
        			if (!TsuboneSystemUtil.isFileSizeCheck(submitFile, programsSize)) {
        				// ファイル拡張子チェック
        				if (TsuboneSystemUtil.isFileKindCheck(submitFile, programType)) {
        					errors.add("submitFile",new ActionMessage("zip、exeファイル以外はアップロードできません",false));
        				}
        			} else {
        				errors.add("submitFile",new ActionMessage("ファイルサイズが大きすぎます。最大10MBです",false));
        			}
        			// ファイルサイズチェック
        			if (!TsuboneSystemUtil.isFileSizeCheck(submitCaptionImageFile, captionImageSize)) {
        				// ファイル拡張子チェック
        				if (TsuboneSystemUtil.isFileKindCheck(submitCaptionImageFile, imageType)) {
        					errors.add("submitCaptionImageFile",new ActionMessage("jpg、pngファイル以外はアップロードできません",false));
        				}
        			} else {
        				errors.add("submitCaptionImageFile",new ActionMessage("ファイルサイズが大きすぎます。最大10MBです",false));
        			}
        		} else {
        			errors.add("submitFile",new ActionMessage("プログラム系で選択した場合はどちらも必須です",false));
        			errors.add("submitCaptionImageFile",new ActionMessage("プログラム系で選択した場合はどちらも必須です",false));
        		}
        	} else if (submitProductFileType.equals(SubmitProductFileTypeCode.IMAGE.getCode())) {
        		// 画像系
        		if (StringUtil.isEmpty(submitDetail)) {
    				errors.add("submitDetail",new ActionMessage("作品の説明は必須です",false));
    			}
        		if (submitCaptionImageFile.getFileSize() > 0){
        			if (!TsuboneSystemUtil.isFileSizeCheck(submitCaptionImageFile, imageSize)) {
        				if (TsuboneSystemUtil.isFileKindCheck(submitCaptionImageFile, imageType)) {
        					errors.add("submitCaptionImageFile",new ActionMessage("jpg、pngファイル以外はアップロードできません",false));
        				}
            		} else {
            			errors.add("submitCaptionImageFile",new ActionMessage("ファイルサイズが大きすぎます。最大10MBです",false));
            		}
    			} else {
    				errors.add("submitCaptionImageFile",new ActionMessage("画像系で選択した場合は必須です",false));
    			}
        	} else if (submitProductFileType.equals(SubmitProductFileTypeCode.DTM.getCode())) {
        		// DTM部はSoundCloudのurlが必須
        		if (StringUtil.isEmpty(soundCloudUrl)) {
        			errors.add("soundCloudUrl",new ActionMessage("soundCloudのURLは必須です",false));
        		}
        		if (StringUtil.isEmpty(submitDetail)) {
    				errors.add("submitDetail",new ActionMessage("作品の説明は必須です",false));
    			}
        	} 
    	} else {
    		errors.add("submitProductFileType",new ActionMessage("種別の選択は必須です",false));
    	}
		return errors;
    }
}
