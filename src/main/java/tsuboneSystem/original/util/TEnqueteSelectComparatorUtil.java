package tsuboneSystem.original.util;

import java.util.Comparator;

import tsuboneSystem.entity.TEnqueteSelect;

/**
 * アンケートの選択肢を選択した人数で並び替えるコンパレータサブクラス
 * @author Hiroaki
 *
 */
public class TEnqueteSelectComparatorUtil  implements Comparator<TEnqueteSelect>{

	@Override
	public int compare(TEnqueteSelect select1, TEnqueteSelect select2) {
		// 選択肢を選択した人数で並び替える
		// > : 降順
		// < : 昇順
		return Integer.parseInt(select1.resultNum) > Integer.parseInt(select2.resultNum) ? -1 : 1;
	}
}
