package com.chitmaster.transformer;

import com.chitmaster.dto.DtoType;

public class ChitGroupTransformer implements Transformer {

	@Override
	public Transformer transform(DtoType dtoType) {
		
		//if (dtoType.equals(DtoType.CHIT_GROUP)) {
			return new ChitGroupTransformer();
		//} 
	}

	
}
