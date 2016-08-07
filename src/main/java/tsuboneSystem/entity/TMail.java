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

package tsuboneSystem.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TMemberエンティティクラス
 *
 * @author oowada
 */
@Entity
@Table(name = "T_MAIL")
public class TMail implements Serializable {

	private static final long serialVersionUID = 1L;

    /* idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, unique = true)
    public Integer id;

    /* メールの作成者 */
    @Column(nullable = true, unique = false)
    public Integer  registMemberId;

    /* メールのタイトル */
    @Column(nullable = true, unique = false)
    public String  title;

    /* メールの内容 */
    @Column(nullable = true, unique = false, columnDefinition ="mediumtext")
    public String  content;

    /* 送信成功可否 */
    @Column(columnDefinition ="boolean default '0'")
    public boolean  errorFlag;

    /* 閲覧権限 */
    @Column(columnDefinition ="default '3'")
    public Integer  browsingRights;

    /* 登録時間 */
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    public Date registTime;


    /** TMail(ID) = TMailSendMember(mailId) */
    @OneToMany(mappedBy = "TMail")
    public List<TMailSendMember> tMailSendMember;

    /** TMail(registMemberId) = TMember(id) */
    @ManyToOne
    @JoinColumn(name = "REGIST_MEMBER_ID", referencedColumnName = "ID")
    public TMember tMember;

    /** IDをTPartySendMail(MailId)に関連付ける */
    @OneToOne(mappedBy = "TMail")
    public TPartySendMail tPartySendMail;

    /** IDをtBbsSubject(MailId)に関連付ける */
    @OneToOne(mappedBy = "TMail")
    public TBbsSubject tBbsSubject;

    /** IDをtBbsDetail(MailId)に関連付ける */
    @OneToOne(mappedBy = "TMail")
    public TBbsDetail tBbsDetail;

}