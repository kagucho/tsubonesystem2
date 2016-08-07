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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TEnqueteSelectエンティティクラス
 *
 * @author ryuya
 */
@Entity
@Table(name = "T_ENQUETE_ANSWER")
public class TEnqueteAnswer implements Serializable {

	private static final long serialVersionUID = 1L;

    /* idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    public Integer id;

    /* メンバーID */
    @Column()
    public Integer  memberId;

    /* 選ばれた選択肢 */
    @Column()
    public Integer  enqueteSelectedId;

    /* TEnqueteSelectをTEnqueteに関連づける */
    @ManyToOne
    @JoinColumn(name = "ENQUETE_SELECTED_ID", referencedColumnName = "ID")
    public TEnqueteSelect tEnqueteSelect;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID")
    public TMember tMember;

}