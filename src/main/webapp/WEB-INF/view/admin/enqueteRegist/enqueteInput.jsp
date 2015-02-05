<html>
<html lang="jp">
  <head>
    <%@ include file="/WEB-INF/view/common/headInnerCommon.jsp"%>
    <script src="${f:url('/js/jquery.add-input-area.4.7.1.js')}"></script>
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            $("#selectedContents").addInputArea();
            $("#list1").addInputArea();
        });
    </script>
  </head>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
    <h3>アンケートの情報を入力してください。</h3>
    <h5 class="hissu">＊がついている項目は必須です</h5>
    <s:form method="POST" >
        <form name="party" class="form-horizontal">
		       <div class="form-group">
		           <label class="control-label col-sm-4" for=title>アンケートの名前</label>
		           <div class="col-sm-8 memberF" >
		               <input type="text" id="title" name="title" property="title" class="form-control" placeholder="title" value="${title}">
		               <html:errors property="title"/>
		           </div>
		       </div>
		       <div class="form-group">
		           <label class="control-label col-sm-4" for="memo">会議の内容</label>
		           <div class="col-sm-8 memberF">
		               <textarea class="form-control" name="memo" rows="5" property="memo" placeholder="memo">${f:h(memo)}</textarea>
		               <html:errors property="memo"/>
		           </div>
		       </div>
		      <div class="form-group">
                <ul id="selectedContents">
                    <li class="selectedContents_var">
		              <label class="control-label col-sm-4" for="selectedContents[0]" id_format="selectedContents[%d]">選択肢</label>
		              <div class="col-sm-6 memberF" >
		                  <input type="text" id="selectedContents[0]" name="selectedContents[0]" id_format="selectedContents[%d]" name_format="selectedContents[%d]"  class="form-control" placeholder="選択肢" value="${selectedContents[0]}"/>
		                  <html:errors property="selectedContents"/>
                          <input type="button" class="selectedContents_del btn btn-danger marginUP" value="削除"/>

		              </div>
                    </li>
                </ul>
                <input type="button" class="selectedContents_add btn btn-info" value="追加"/>
		      </div>
            <div class="form-group">
                <div class="col-sm-8">
                    <input type="submit" value="確認" id="confirm" name="confirm" property="confirm" class="col-md-6 col-md-offset-6 col-sm-10 col-sm-offset-4 col-xs-12  btn btn-primary">
                </div>
            </div>
        </form>
    </s:form>
</div>

</body>
</html>