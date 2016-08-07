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

package tsuboneSystem.action.admin;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.entity.TBbsSubject;
import tsuboneSystem.form.BbsForm;
import tsuboneSystem.service.TBbsSubjectService;

public class BbsDeleteAction {

	/** TMemberのサービスクラス */
	@Resource
	protected TBbsSubjectService tBbsSubjectService;

	@ActionForm
	@Resource
	protected BbsForm bbsForm;

	@Execute(validator = false, urlPattern = "{id}", reset = "resetInput")
	public String index() {
		TBbsSubject subject = tBbsSubjectService.findById(bbsForm.id);
		subject.deleteFlag = true;
		tBbsSubjectService.update(subject);
		//return "bbsDeleteComplete.jsp";
		return "/admin/bbsList/?redirect=true";
	}
}
