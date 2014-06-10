package tsuboneSystem.dto;

import java.io.Serializable;
import java.util.Map;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;


@Component(instance = InstanceType.SESSION)
public class MemberDto implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	/* 出欠席を返さないゴミのmap(表示するために使用)　*/
	public Map<String, String> memberMap;
	
	
}
